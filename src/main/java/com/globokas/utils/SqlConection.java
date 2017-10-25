package com.globokas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pvasquez
 */
public class SqlConection {
    
    // TODO Auto-generated method stub
    public Connection SQLServerConnection(String database) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("No encuentra driver");
            cnfe.printStackTrace();
        }
        Connection c = null;

        try {
            
            String databaseNombre = "";
            String userDatabase = "";
            String passDatabase = "";
            String ipDataBase=ConfigApp.getValue("ip_bd_produccion");
            
            if(database.equalsIgnoreCase("G")){
                databaseNombre = ConfigApp.getValue("databasename_gestion");
                userDatabase = ConfigApp.getValue("user_gestion");
//                passDatabase = ConfigApp.getValue("pass_gestion");
                passDatabase = "PassControl$";
            }else if(database.equalsIgnoreCase("R")){
                databaseNombre = ConfigApp.getValue("databasename_repositorio");
                userDatabase = ConfigApp.getValue("user_repositorio");
//                passDatabase = ConfigApp.getValue("pass_repositorio");
                passDatabase = "PassControl$";
            }
            
            c = DriverManager.getConnection("jdbc:sqlserver://" + ipDataBase  +";databaseName=" + databaseNombre + ";user=" +userDatabase+ ";password="+passDatabase+";");

        } catch (SQLException se) {
            se.printStackTrace();
        }

        if (c == null) {
            System.out.println("No Conecto!");
        }
        return c;

    }
    
}
