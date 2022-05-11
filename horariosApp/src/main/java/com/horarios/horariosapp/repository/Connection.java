package com.horarios.horariosapp.repository;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    public java.sql.Connection conexion=null;

    public java.sql.Connection initConnection(){

        try{
            System.out.println("pasa1");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("pasa2");
            //String url = "jdbc:mysql://148.204.86.36:3306/BasePT?"+"user=root&password=ipnUPIITA145&?autoReconnect=true&useSSL=false";
            String url = "jdbc:mysql://127.0.0.1:3306/mydb?user=jorge&password=1234&?autoReconnect=true&useSSL=false";
            System.out.println("pasa3");
            conexion= DriverManager.getConnection(url);
            System.out.println("pasa4");
        }
        catch(ClassNotFoundException ex){
            System.err.println(ex+ "Error1 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(SQLException ex){
            System.err.println(ex+ "Error2 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(Exception ex){
            System.err.println(ex+ "Error3 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        finally{
            return conexion;
        }
    }

    public void closeConnection() throws SQLException {
        if(conexion != null){
            conexion.close();
        }
    }
}
