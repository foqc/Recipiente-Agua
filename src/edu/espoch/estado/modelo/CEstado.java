/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espoch.estado.modelo;

/**
 *
 * @author foqc
 */
public class CEstado {
    private Integer recipienteGrande;
    private Integer recipientePequenio;

    public CEstado() {
        this.recipienteGrande=0;
        this.recipientePequenio=0;
    }

    public Integer getRecipienteGrande() {
        return recipienteGrande;
    }

    public void setRecipienteGrande(Integer recipienteGrande) {
        this.recipienteGrande = recipienteGrande;
    }

    public Integer getRecipientePequenio() {
        return recipientePequenio;
    }

    public void setRecipientePequenio(Integer recipientePequenio) {
        this.recipientePequenio = recipientePequenio;
    }
    
    
}
