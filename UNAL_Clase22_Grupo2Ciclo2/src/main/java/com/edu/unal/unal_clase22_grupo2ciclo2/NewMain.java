/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unal.unal_clase22_grupo2ciclo2;

import java.sql.Connection;

/**
 *
 * @author Usuario
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BDMysql nuevaCon= new BDMysql();
        Connection co=nuevaCon.conectar();
        //nuevaCon.insertar(co);
        //nuevaCon.seleccionar(co, "1001");
        //nuevaCon.actualizar(co);
        nuevaCon.borrar(co);
    }
    
}
