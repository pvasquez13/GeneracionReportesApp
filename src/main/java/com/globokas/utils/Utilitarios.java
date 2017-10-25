package com.globokas.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author pvasquez
 */
public class Utilitarios {

    public String zipear(String rutaArchivoInicial, String nombreArchivoInicial, String rutaArchivoResultante, String archivoResultante) {

        String inputFile = rutaArchivoInicial + nombreArchivoInicial;
        FileInputStream in;
        try {
            in = new FileInputStream(inputFile);

            FileOutputStream out1 = new FileOutputStream(rutaArchivoResultante + archivoResultante + ".zip");

            byte b[] = new byte[2048];
            ZipOutputStream zipOut = new ZipOutputStream(out1);
            ZipEntry entry = new ZipEntry(inputFile);
            zipOut.putNextEntry(entry);
            int len = 0;
            while ((len = in.read(b)) != -1) {
                zipOut.write(b, 0, len);
            }
            zipOut.closeEntry();
            zipOut.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return archivoResultante + ".zip";
    }

}
