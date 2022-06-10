/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.porfolio.enums;

/**
 *
 * @author Nickler
 */
public enum Status {
    IDEA("Idea"), IMPORTANTE("Importante"), TERMINADA("Terminada");
    
    private String nombreVista;

    private Status(String nombreVista) {
        this.nombreVista = nombreVista;
    }
    
    public String getNombreVista() {
        return nombreVista;
    }
}