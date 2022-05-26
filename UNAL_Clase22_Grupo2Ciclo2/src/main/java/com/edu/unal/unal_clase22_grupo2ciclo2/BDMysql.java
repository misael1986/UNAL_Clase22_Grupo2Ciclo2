/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unal.unal_clase22_grupo2ciclo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class BDMysql {

    public Connection conectar() {
        String dbURL = "jdbc:mysql://localhost:3306/libreria";
        String username = "admin";
        String password = "Admin123#";

        Connection conn = null;
        // conectar
        try {
            conn = DriverManager.getConnection(
                    dbURL, username, password);
            if (conn != null) {
                System.out.println(" Conectado ");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
//------------------------------------------------------------------

    public void insertar(Connection conn) {
        String sql = "INSERT INTO libro "
                + "(libId,libNombre,libPub ,ediId,"
                + "autId , libPrecio ) "
                + "VALUES (?,?,?,?,?,?)";

        int rowsInserted = 0;
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, 1010);
            statement.setString(2, "La Hojarasca");
            statement.setInt(3, 1955);
            statement.setInt(4, 1);
            statement.setInt(5, 1);
            statement.setDouble(6, 95000.0);
            rowsInserted = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDMysql.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        if (rowsInserted > 0) {
            System.out.println(" Insercion exitosa !");
        }

    }
//-------------------------------------------------------

    public void seleccionar(Connection conn, String tx) {
        String sql = "SELECT * FROM libro WHERE libId=" + tx;
        System.out.println(sql);
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
            while (result.next()) {
                String titulo = result.getString(2);
                Integer pub = result.getInt(3);
                Double costo = result.getDouble(6);
                System.out.println("Titulo : "
                        + titulo + "Año publicacion: " + pub
                        + " Costo : " + costo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDMysql.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    //-----------------------------------------------------------
    public void actualizar(Connection conn) {
        String sql = " UPDATE libro SET libNombre =?,"
                + "libPub =?, libPrecio =? WHERE libId =?";

        try {

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, "Cronica de una muerte anunciada");
            statement.setInt(2, 1981);
            statement.setDouble(3, 100500.0);
            statement.setInt(4, 1010);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El registro fue "
                        + " actualizado exitosamente !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //---------------------------------------
    public void borrar(Connection conn) {
        String sql = " DELETE FROM libro WHERE libId =?";
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, 1010);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(" Borrado exitoso !");
            }
            else { System.out.println("Dato no está o ID no es correcto");}
        } catch (SQLException ex) {
            Logger.getLogger(BDMysql.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //------------------------
}