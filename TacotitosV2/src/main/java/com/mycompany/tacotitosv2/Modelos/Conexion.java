package com.mycompany.tacotitosv2.Modelos;
import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
        
    Connection con;
    public Connection getConnection(){
        try {
            String url="jdbc:mariadb://localhost:3306/tacotitos";
            String user="root",pass="";
            con=DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}

