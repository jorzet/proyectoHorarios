package com.horarios.repository;

import java.sql.DriverManager;

public class Conection {

    public static final String URL = "jdbc:mysql://localhost:3306/prueba";
    public static final String username = "root";
    public static final String password = "admin3124";

    public static Conection getConnection() {
        Conection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Conection) DriverManager.getConnection(URL, username, password);
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}
