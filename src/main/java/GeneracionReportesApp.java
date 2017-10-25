import com.globokas.dao.EntidadConciliacionDAO;
import com.globokas.entity.EntidadConciliacion;
import com.globokas.service.RacalService;
import com.globokas.utils.ConfigApp;
import com.globokas.utils.Mail;
import com.zehon.FileTransferStatus;
import com.zehon.exception.FileTransferException;
import com.zehon.sftp.SFTP;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pvasquez
 */
public class GeneracionReportesApp {

    private static String asuntoCorreo;
    private static String bodyCorreo;

    public static void main(String[] args) {
        try {
            Process(obtenerFecha(), Integer.parseInt(args[0]));
        } catch (Exception ex) {
            Logger.getLogger(GeneracionReportesApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Process(String fecha, int idReporte) throws Exception {

        EntidadConciliacionDAO entidadDAO = new EntidadConciliacionDAO();
        List<EntidadConciliacion> entidadConciliacionList = entidadDAO.getEntidadConciliacion(idReporte);
        RacalService racalService = RacalService.getInstance();

        for (EntidadConciliacion entidad : entidadConciliacionList) {

            String filename = obtenerNombreArchivo(entidad, fecha);
            String ruta = entidad.getVc_carpeta_generacion();
            String fileRutaFull = ruta + filename;

            System.out.println("*****Inicio Generacion Archivo PAN claro" + entidad.getDes_ent() + " - [" + filename + "]*****");
            try (BufferedWriter bwGpg = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileRutaFull), "latin1"))) {
                String archivo = entidadDAO.generarArchivoConciliacionOpe(fecha, entidad.getId_ent(), entidad.getVc_nombre_stored_sp(),
                        entidad.getIn_cantidad_parametros(), racalService);

                bwGpg.write(archivo);
            }

            System.out.println("********************* Fin Generacion Conciliacion  " + entidad.getDes_ent() + "********************** ");

            asuntoCorreo = "FileGenerateStatus - " + entidad.getVc_descripcion_reporte() + " - SUCCESS ";
            bodyCorreo = "La generación del archivo " + filename + " ha culminado satisfactoriamente";

            if (entidad.getIn_encripta_gpg() == 1) {
                encryptPGP(fileRutaFull);
            }

            if (entidad.getIn_envia_conciliacion() == 1) {
                envioSFTP(entidad, fileRutaFull, filename);
            }

            deleteFile(fileRutaFull);

            if (entidad.getIn_genera_archivo_ofuscado() == 1) {
                generarArchivoInterno(entidad, fileRutaFull, filename, fecha);
            }

            if (entidad.getIn_envio_correo() == 1) {
                Mail mail = new Mail();
                mail.enviaCorreoPorGrupo(asuntoCorreo, bodyCorreo, 23);
            }
        }

        racalService.closeSixFactory();
    }

    public static String obtenerNombreArchivo(EntidadConciliacion entidad, String fecha) {
        String filename;
        if (entidad.getIn_nombre_fecha_archivo() == 1) {
            filename = entidad.getVc_nombre_archivo_conciliacion() + fecha + "." + entidad.getVc_extension_archivo();
        } else {
            filename = entidad.getVc_nombre_archivo_conciliacion() + "." + entidad.getVc_extension_archivo();
        }
        return filename;
    }

    public static void generarArchivoInterno(EntidadConciliacion entidad, String filenamePath, String filename, String fecha)
            throws IOException, SQLException {
        System.out.println("*****Inicio Generacion Archivo Interno" + entidad.getDes_ent() + " - [" + filename + "]*****");
        EntidadConciliacionDAO entidadDAO = new EntidadConciliacionDAO();
        try (BufferedWriter bwInterno = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filenamePath), "latin1"))) {
            String archivoInt = entidadDAO.generarArchivoConciliacionInterno(fecha, entidad.getId_ent(), entidad.getVc_nombre_stored_sp(),
                    entidad.getIn_cantidad_parametros());
            bwInterno.write(archivoInt);
            System.out.println("*****Fin Generacion Archivo Interno" + entidad.getDes_ent() + " - [" + filename + "]*****");
        }
    }

    public static String obtenerFecha() {
        String modo_automatico = ConfigApp.getValue("MODO_AUTOMATICO");
        System.out.println("MODO_AUTOMATICO :" + modo_automatico);
        String fecha;

        if (modo_automatico.equals("FALSE")) {
            fecha = ConfigApp.getValue("FECHA_OPERACION");
        } else {//FECHA DE HOY
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            fecha = df.format(new Date());
        }

        System.out.println("Inicio Proceso Conciliacion :" + fecha);
        return fecha;
    }

    public static void encryptPGP(String file) {
        try {
            String passwordPGP = ConfigApp.getValue("llaveEncriptarGPG");
            String cmd = "gpg --encrypt --recipient " + passwordPGP + " " + file;
            Process p = Runtime.getRuntime().exec(cmd);
            System.out.println("Waiting for batch file ...");
            p.waitFor();
            System.out.println("El archivo " + file + " fue encriptado satisfactoriamente");
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (InterruptedException ex) {
            Logger.getLogger(GeneracionReportesApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void envioSFTP(EntidadConciliacion entidad, String filenamePath, String filename) throws FileTransferException {
        System.out.println("********************* Inicio transferencia Conciliacion  *********************** ");
        String host = entidad.getVc_host_ftp();
        String username = entidad.getVc_username_ftp();
        String password = entidad.getVc_password_ftp();
        String destFolder = entidad.getVc_carpeta_destino();
        String filePath = (filenamePath).trim();
//        String filePath = (filenamePath).trim() + ".gpg";

        System.out.println(filePath + "Iniciando sftp");
        int status = -1;

        if (entidad.getVc_modo_conexion().equals("SFTP")) {
            status = SFTP.sendFile(filePath, destFolder, host, username, password);
        }

        if (status != -1) {
            if (FileTransferStatus.SUCCESS == status) {
                System.out.println(filePath + " got " + entidad.getVc_modo_conexion() + " successfully to  folder " + destFolder);
                asuntoCorreo = "FileTransferStatus - " + entidad.getVc_descripcion_reporte() + "- SUCCESS";
                bodyCorreo = "El archivo " + filename + " ha sido transferido correctamente.";

            } else if (FileTransferStatus.FAILURE == status) {
                System.out.println("Fail to " + entidad.getVc_modo_conexion() + "  to  folder " + destFolder);
                asuntoCorreo = "FileTransferStatus - " + entidad.getVc_descripcion_reporte() + "- FAILURE";
                bodyCorreo = "El archivo " + filename + " no ha sido transferido correctamente.";
            }
        } else {
            System.out.println("No se ha especificado el modo de conexion" + destFolder);
            asuntoCorreo = "FileTransferStatus - " + entidad.getVc_descripcion_reporte() + "- FAILURE";
            bodyCorreo = "El archivo " + filename + " no ha sido transferido correctamente. No se ha especificado el modo de conexion";

        }

        System.out.println("********************* Fin transferencia Conciliacion *********************** ");
    }

    public static void deleteFile(String fileName) {
        try {

            File file = new File(fileName);

            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
