package com.globokas.dao;

import com.globokas.entity.BeanReporteSeguimiento;
import com.globokas.entity.EntidadConciliacion;
import com.globokas.model.Racal;
import com.globokas.service.DecifrarTarjetaService;
import com.globokas.service.RacalService;
import com.globokas.utils.SqlConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pvasquez
 */
public class EntidadConciliacionDAO {

    public List<EntidadConciliacion> getEntidadConciliacion(int idReporte) {

        List<EntidadConciliacion> entidadConciliacionList = new ArrayList<>();
        ResultSet rs = null;
        Connection conn = null;

        try {

            SqlConection c = new SqlConection();
            conn = c.SQLServerConnection("G");
            PreparedStatement ps;
            ps = conn.prepareStatement("{call GES_SP_LISTA_GENERACION_REPORTES_RACAL(?)}");
            ps.setInt(1, idReporte);
            rs = ps.executeQuery();
            while (rs.next()) {
                EntidadConciliacion entidad = new EntidadConciliacion();
                entidad.setDes_ent(rs.getString("des_ent"));
                entidad.setId_ent(rs.getInt("id_ent"));
                entidad.setVc_host_ftp(rs.getString("vc_host_ftp"));
                entidad.setVc_username_ftp(rs.getString("vc_username_ftp"));
                entidad.setVc_password_ftp(rs.getString("vc_password_ftp"));
                entidad.setVc_descripcion_reporte("VC_DESCRIPCION_REPORTE");
                entidad.setVc_nombre_stored_sp(rs.getString("vc_nombre_stored_sp"));
                entidad.setIn_cantidad_parametros(rs.getInt("in_cantidad_parametros"));
                entidad.setVc_nombre_archivo_conciliacion(rs.getString("vc_nombre_archivo_conciliacion"));
                entidad.setVc_nombre_archivo_conciliacion_ext(rs.getString("vc_nombre_archivo_conciliacion_ext"));
                entidad.setIn_envia_conciliacion(rs.getInt("in_envia_conciliacion"));
                entidad.setVc_carpeta_generacion(rs.getString("vc_carpeta_generacion"));
                entidad.setVc_carpeta_destino(rs.getString("vc_carpeta_destino"));
                entidad.setVc_extension_archivo(rs.getString("vc_extension_archivo"));
                entidad.setVc_modo_conexion(rs.getString("vc_modo_conexion").trim());
                entidad.setIn_envio_correo(rs.getInt("IN_ENVIA_CORREO"));
                entidad.setIn_nombre_fecha_archivo(rs.getInt("in_nombre_fecha_archivo"));
                entidad.setIn_encripta_gpg(rs.getInt("intEncriptaGPG"));
                entidad.setIn_genera_archivo_ofuscado(rs.getInt("intGeneraArchivoOfuscado"));
                entidadConciliacionList.add(entidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return entidadConciliacionList;
    }

    public String generarArchivoConciliacionOpe(String fecha, int entidad,
            String nombreStored, int numeroDeParametros, RacalService racalService) throws SQLException {

        StringBuilder archivoStr = new StringBuilder();

        ResultSet rs;
        Connection conn;

        SqlConection c = new SqlConection();
        conn = c.SQLServerConnection("R");
        PreparedStatement ps;
        ps = conn.prepareStatement("{call " + nombreStored + "}");
        ps.setString(1, fecha);
        if (numeroDeParametros == 2) {
            ps.setInt(2, entidad);
        }

        rs = ps.executeQuery();

        while (rs.next()) {
            String tarjeta = "";
            if (!rs.getString(2).equals("") && !rs.getString(4).equals("")) {
                tarjeta = decifrarTarjeta(rs.getString(2), rs.getString(3), rs.getString(4), racalService);
            }
            if (tarjeta.equals("")) {
                archivoStr.append(rs.getString(1));
            } else {
                archivoStr.append(rs.getString(1).replace(rs.getString(5), tarjeta));
            }
            archivoStr.append("\r\n");
        }

        rs.close();
        ps.close();
        conn.close();

        return archivoStr.toString();

    }

    public String generarArchivoConciliacionInterno(String fecha, int entidad,
            String nombreStored, int numeroDeParametros) throws SQLException {

        StringBuilder archivoStr = new StringBuilder();

        ResultSet rs;
        Connection conn;

        SqlConection c = new SqlConection();
        conn = c.SQLServerConnection("R");
        PreparedStatement ps;
        ps = conn.prepareStatement("{call " + nombreStored + "}");
        ps.setString(1, fecha);
        if (numeroDeParametros == 2) {
            ps.setInt(2, entidad);
        }

        rs = ps.executeQuery();

        while (rs.next()) {
            archivoStr.append(rs.getString(1));
            archivoStr.append("\r\n");
        }

        rs.close();
        ps.close();
        conn.close();

        return archivoStr.toString();

    }

    public String decifrarTarjeta(String fecha, String trace, String terminal, RacalService racalService) {
        DecifrarTarjetaService decifrar = new DecifrarTarjetaService();
        Racal racal = new Racal();
        racal.setTransDate(fecha);
        racal.setTrace(trace);
        racal.setTerminal(terminal);
//        System.out.println("[Solicitud desencriptar] terminal: "+terminal+" trace: "+trace);
        return decifrar.decifrarTarjeta(racal, racalService);
    }

    public List ListarDestinatariosBD(int CodigoReporte) throws SQLException {

        Connection con;

        SqlConection c = new SqlConection();
        con = c.SQLServerConnection("R");
        PreparedStatement stmt;
        stmt = con.prepareStatement("{call dbo.uspDestinatarios(?)}");
        stmt.setInt(1, CodigoReporte);

        ArrayList<BeanReporteSeguimiento> listado_mail;
        try (ResultSet rs_mail = stmt.executeQuery()) {
            listado_mail = new ArrayList<>();
            while (rs_mail.next()) {
                
                BeanReporteSeguimiento bean_mail = new BeanReporteSeguimiento();
                bean_mail.setNom_mail(rs_mail.getString("vchNombre"));
                bean_mail.setMail(rs_mail.getString("vchCorreo"));
                bean_mail.setVer_mail(rs_mail.getString("chVersion"));
                bean_mail.setMial_ToCcBcc(rs_mail.getString("vchCopia"));
                listado_mail.add(bean_mail);
            }
        }
        stmt.close();
        con.close();

        return listado_mail;
    }

    public void eliminarLogTransaccional(String tablaLogTransac, String fecha) {

    }

}
