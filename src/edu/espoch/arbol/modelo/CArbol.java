/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espoch.arbol.modelo;

import edu.espoch.estado.modelo.CEstado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author foqc
 */
public class CArbol {

    private CNodoArbol raiz;
    private Integer mrg;//Maximo RECIPIENTE GRANDE
    private Integer mrp;//Maximo RECIPIENTE pequenio
    private Integer cd;//cantidad deseada

    public CArbol() {
        this.mrg = 0;
        this.mrp = 0;
        this.cd = 0;
    }

    public CArbol(Integer mrg, Integer mrp, Integer cd) {
        this.mrg = mrg;
        this.mrp = mrp;
        this.cd = cd;
    }

    public Integer getMrg() {
        return mrg;
    }

    public void setMrg(Integer mrg) {
        this.mrg = mrg;
    }

    public Integer getMrp() {
        return mrp;
    }

    public void setMrp(Integer mrp) {
        this.mrp = mrp;
    }

    public Integer getCd() {
        return cd;
    }

    public void setCd(Integer cd) {
        this.cd = cd;
    }
    
    public CNodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(CNodoArbol raiz) {
        this.raiz = raiz;
    }

    public void crearArbol() {
        CEstado objCEstado = new CEstado();//estado inicial de los recipientes (0,0)
        objCEstado.setRecipienteGrande(0);
        objCEstado.setRecipientePequenio(0);
        CNodoArbol objNodoRaizArbol = new CNodoArbol(objCEstado);
        objNodoRaizArbol.setPadreNodoArbol(null);
        raiz = objNodoRaizArbol;//raiz del rabol

        raiz.crearHijos(raiz, this.mrg, this.mrp, this.cd);
    }



    public List<CEstado> solucion() {
        return raiz.solucion(this.raiz, cd);
    }
    
    public List<CEstado> mejorSolucion() {
        return raiz.mejorSolucion(this.raiz, cd);
    }
    
    public List<List<CEstado>> listarTodasSoluciones(){
        List<List<CEstado>> lstSoluciones=new ArrayList<>();        
        return raiz.listaSoluciones(this.raiz, cd,lstSoluciones);
    }
}
