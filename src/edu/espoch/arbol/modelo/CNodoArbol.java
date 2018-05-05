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
public class CNodoArbol {

    private CEstado informacionNodoArbol;//informacion
    private List<CNodoArbol> lstHijosNodoArbol;//lista de hijos del tipo nodoArbol
    private CNodoArbol padreNodoArbol;// para determinar el padre de este nodo del arbol

    public CNodoArbol(CEstado informacion) {
        this.lstHijosNodoArbol = new ArrayList<>();
        this.informacionNodoArbol = informacion;
    }

    public CEstado getInformacionNodoArbol() {
        return informacionNodoArbol;
    }

    public void setInformacionNodoArbol(CEstado informacionNodoArbol) {
        this.informacionNodoArbol = informacionNodoArbol;
    }

    public CNodoArbol getPadreNodoArbol() {
        return padreNodoArbol;
    }

    public void setPadreNodoArbol(CNodoArbol padreNodoArbol) {
        this.padreNodoArbol = padreNodoArbol;
    }

    public List<CNodoArbol> getLstHijosNodoArbol() {
        return lstHijosNodoArbol;
    }

    public void setLstHijosNodoArbol(List<CNodoArbol> lstHijosNodoArbol) {
        this.lstHijosNodoArbol = lstHijosNodoArbol;
    }

    private Boolean esRepetido(CNodoArbol nodoActual, CEstado estadoPosible) {
        if (nodoActual != null) {
            if (nodoActual.getInformacionNodoArbol().getRecipienteGrande().compareTo(estadoPosible.getRecipienteGrande()) == 0
                    && nodoActual.getInformacionNodoArbol().getRecipientePequenio().compareTo(estadoPosible.getRecipientePequenio()) == 0) {
                return true;
            } else {
                return esRepetido(nodoActual.getPadreNodoArbol(), estadoPosible);
            }
        } else {
            return false;
        }

    }

    private Boolean esMetaPadre(CNodoArbol nodoActual, Integer cd) {
        return esMeta(nodoActual.getInformacionNodoArbol(), cd);
    }

    /*              INSERTAR NODOS          */
    public void crearHijos(CNodoArbol nodoActual, Integer mrg, Integer mrp, Integer cd) {
        if (nodoActual != null) {
            CNodoArbol nodoNuevo;
            CEstado estadoPosible;
            estadoPosible = this.llenarRecipienteGrande(nodoActual.getInformacionNodoArbol(), mrg);

            if (estadoPosible != null && !this.esRepetido(nodoActual, estadoPosible) && !esMetaPadre(nodoActual, cd)) {
                nodoNuevo = new CNodoArbol(estadoPosible);
                nodoNuevo.setPadreNodoArbol(nodoActual);
                nodoActual.getLstHijosNodoArbol().add(nodoNuevo);
            }

            estadoPosible = this.llenarRecipientePequenio(nodoActual.getInformacionNodoArbol(), mrp);

            if (estadoPosible != null && !this.esRepetido(nodoActual, estadoPosible) && !esMetaPadre(nodoActual, cd)) {
                nodoNuevo = new CNodoArbol(estadoPosible);
                nodoNuevo.setPadreNodoArbol(nodoActual);
                nodoActual.getLstHijosNodoArbol().add(nodoNuevo);
            }
            estadoPosible = this.vaciarRecipienteGrande(nodoActual.getInformacionNodoArbol());

            if (estadoPosible != null && !this.esRepetido(nodoActual, estadoPosible) && !esMetaPadre(nodoActual, cd)) {
                nodoNuevo = new CNodoArbol(estadoPosible);
                nodoNuevo.setPadreNodoArbol(nodoActual);
                nodoActual.getLstHijosNodoArbol().add(nodoNuevo);
            }
            estadoPosible = this.vaciarRecipientePequenio(nodoActual.getInformacionNodoArbol());

            if (estadoPosible != null && !this.esRepetido(nodoActual, estadoPosible) && !esMetaPadre(nodoActual, cd)) {
                nodoNuevo = new CNodoArbol(estadoPosible);
                nodoNuevo.setPadreNodoArbol(nodoActual);
                nodoActual.getLstHijosNodoArbol().add(nodoNuevo);
            }
            estadoPosible = this.pasarRecipienteGrandeaPequenio(nodoActual.getInformacionNodoArbol(), mrp);

            if (estadoPosible != null && !this.esRepetido(nodoActual, estadoPosible) && !esMetaPadre(nodoActual, cd)) {
                nodoNuevo = new CNodoArbol(estadoPosible);
                nodoNuevo.setPadreNodoArbol(nodoActual);
                nodoActual.getLstHijosNodoArbol().add(nodoNuevo);
            }
            estadoPosible = this.pasarRecipienteGrandeaPequenio1(nodoActual.getInformacionNodoArbol(), mrp);

            if (estadoPosible != null && !this.esRepetido(nodoActual, estadoPosible) && !esMetaPadre(nodoActual, cd)) {
                nodoNuevo = new CNodoArbol(estadoPosible);
                nodoNuevo.setPadreNodoArbol(nodoActual);
                nodoActual.getLstHijosNodoArbol().add(nodoNuevo);
            }

            estadoPosible = this.pasarRecipientePequenioaGrande(nodoActual.getInformacionNodoArbol(), mrg);

            if (estadoPosible != null && !this.esRepetido(nodoActual, estadoPosible) && !esMetaPadre(nodoActual, cd)) {
                nodoNuevo = new CNodoArbol(estadoPosible);
                nodoNuevo.setPadreNodoArbol(nodoActual);
                nodoActual.getLstHijosNodoArbol().add(nodoNuevo);
            }

            estadoPosible = this.pasarRecipientePequenioaGrande1(nodoActual.getInformacionNodoArbol(), mrg);

            if ((estadoPosible != null && !this.esRepetido(nodoActual, estadoPosible) && !esMetaPadre(nodoActual, cd))) {
                nodoNuevo = new CNodoArbol(estadoPosible);
                nodoNuevo.setPadreNodoArbol(nodoActual);
                nodoActual.getLstHijosNodoArbol().add(nodoNuevo);
            } else {
            }

            for (CNodoArbol tmp : nodoActual.getLstHijosNodoArbol()) {
                crearHijos(tmp, mrg, mrp, cd);
            }
        }
    }

    private CEstado llenarRecipienteGrande(CEstado estadoActual, Integer mrg) {
        Integer crg = estadoActual.getRecipienteGrande();//crg cantidad recipiente grande
        Integer crp = estadoActual.getRecipientePequenio();//crp cantidad recipiente grande

        CEstado nuevoEstado = new CEstado();//mi estado posible
        //llenando recipiente grande
        if (crg >= 0 && crg < mrg) {
            nuevoEstado.setRecipienteGrande(mrg);
            nuevoEstado.setRecipientePequenio(crp);
        } else {
            nuevoEstado = null;
        }
        return nuevoEstado;
    }

    private CEstado llenarRecipientePequenio(CEstado estadoActual, Integer mrp) {
        Integer crg = estadoActual.getRecipienteGrande();//crg cantidad recipiente grande
        Integer crp = estadoActual.getRecipientePequenio();//crp cantidad recipiente grande

        CEstado nuevoEstado = new CEstado();//mi estado posible
        //llenando recipiente pequenio
        if (crp >= 0 && crp < mrp) {
            nuevoEstado.setRecipienteGrande(crg);
            nuevoEstado.setRecipientePequenio(mrp);
        } else {
            nuevoEstado = null;
        }
        return nuevoEstado;
    }

    private CEstado vaciarRecipienteGrande(CEstado estadoActual) {
        Integer crg = estadoActual.getRecipienteGrande();//crg cantidad recipiente grande
        Integer crp = estadoActual.getRecipientePequenio();//crp cantidad recipiente grande

        CEstado nuevoEstado = new CEstado();//mi estado posible
        //vaciar recipiente grande
        if (crg > 0) {
            nuevoEstado.setRecipienteGrande(0);
            nuevoEstado.setRecipientePequenio(crp);
        } else {
            nuevoEstado = null;
        }
        return nuevoEstado;
    }

    private CEstado vaciarRecipientePequenio(CEstado estadoActual) {
        Integer crg = estadoActual.getRecipienteGrande();//crg cantidad recipiente grande
        Integer crp = estadoActual.getRecipientePequenio();//crp cantidad recipiente grande

        CEstado nuevoEstado = new CEstado();//mi estado posible
        //vaciar recipiente pequenio
        if (crp > 0) {
            nuevoEstado.setRecipienteGrande(crg);
            nuevoEstado.setRecipientePequenio(0);
        } else {
            nuevoEstado = null;
        }
        return nuevoEstado;
    }

    private CEstado pasarRecipienteGrandeaPequenio(CEstado estadoActual, Integer mrp) {
        Integer crg = estadoActual.getRecipienteGrande();//crg cantidad recipiente grande
        Integer crp = estadoActual.getRecipientePequenio();//crp cantidad recipiente grande

        CEstado nuevoEstado = new CEstado();//mi estado posible
        //pasar del recipiente grande al pequenio (toodo el agua)
        //si existe espacio suficiente en el recipiente pequenio
        if ((crg > 0) && (crp < mrp) && (crg <= (mrp - crp))) {
            nuevoEstado.setRecipienteGrande(0);
            nuevoEstado.setRecipientePequenio(crp + crg);
        } else {
            nuevoEstado = null;
        }
        return nuevoEstado;
    }

    private CEstado pasarRecipienteGrandeaPequenio1(CEstado estadoActual, Integer mrp) {
        Integer crg = estadoActual.getRecipienteGrande();//crg cantidad recipiente grande
        Integer crp = estadoActual.getRecipientePequenio();//crp cantidad recipiente grande

        CEstado nuevoEstado = new CEstado();//mi estado posible
        //pasar del recipiente grande al pequenio (lo que se pueda)
        //si no existe espacio suficiente el el recipiente pequenio
        if ((crg > 0) && (crp < mrp) && (crg > (mrp - crp))) {
            nuevoEstado.setRecipienteGrande(crg - (mrp - crp));
            nuevoEstado.setRecipientePequenio(mrp);
        } else {
            nuevoEstado = null;
        }
        return nuevoEstado;
    }

    private CEstado pasarRecipientePequenioaGrande(CEstado estadoActual, Integer mrg) {
        Integer crg = estadoActual.getRecipienteGrande();//crg cantidad recipiente grande
        Integer crp = estadoActual.getRecipientePequenio();//crp cantidad recipiente grande

        CEstado nuevoEstado = new CEstado();//mi estado posible
        //pasar del recipiente pequenio al grande (todo el agua)
        //si existe espacio suficiente el el recipiente grande
        if ((crp > 0) && (crg < mrg) && (crp <= (mrg - crg))) {
            nuevoEstado.setRecipienteGrande(crg + crp);
            nuevoEstado.setRecipientePequenio(0);
        } else {
            nuevoEstado = null;
        }
        return nuevoEstado;
    }

    private CEstado pasarRecipientePequenioaGrande1(CEstado estadoActual, Integer mrg) {
        Integer crg = estadoActual.getRecipienteGrande();//crg cantidad recipiente grande
        Integer crp = estadoActual.getRecipientePequenio();//crp cantidad recipiente grande

        CEstado nuevoEstado = new CEstado();//mi estado posible
        //pasar del recipiente pequenio al grande (lo que se pueda)
        //si no existe espacio suficiente el el recipiente grande
        if ((crp > 0) && (crg < mrg) && (crp > (mrg - crg))) {
            nuevoEstado.setRecipienteGrande(mrg);
            nuevoEstado.setRecipientePequenio(crp - (mrg - crg));
        } else {
            nuevoEstado = null;
        }
        return nuevoEstado;
    }
    CNodoArbol resultado1 = null;

    private List<CEstado> recorrerRamaSolucion(CNodoArbol nodoActual) {
        List<CEstado> lstsolucion = new ArrayList<>();
        while (nodoActual != null) {
            lstsolucion.add(0, nodoActual.getInformacionNodoArbol());
            nodoActual = nodoActual.getPadreNodoArbol();
        }
        return lstsolucion;
    }

    public List<CEstado> solucion(CNodoArbol nodoActual, Integer cd) {
        CNodoArbol nodoMeta = buscarSolucion(nodoActual, cd);
        List<CEstado> lstsolucion = new ArrayList<>();
        if (nodoMeta != null) {
            lstsolucion = (recorrerRamaSolucion(nodoMeta));

        }
        return lstsolucion;
    }

    private Boolean esMeta(CEstado estado, Integer cd) {
        Integer crg = estado.getRecipienteGrande();
        Integer crp = estado.getRecipientePequenio();
        Integer lt = crg + crp;
        return (crg.compareTo(cd) == 0 || crp.compareTo(cd) == 0 || lt.compareTo(cd) == 0);
    }

    /**
     * metodo que devuelve el primer nodo solucion que encuentre
     *
     * @param nodoActual
     * @param cd
     * @return
     */
    private CNodoArbol buscarSolucion(CNodoArbol nodoActual, Integer cd) {

        if (nodoActual == null) {
            return null;
        } else {
            if (esMeta(nodoActual.getInformacionNodoArbol(), cd)) {

                return nodoActual;
            } else {
                for (CNodoArbol tmp : nodoActual.getLstHijosNodoArbol()) {
                    return buscarSolucion(tmp, cd);
                }
                return null;
            }
        }
    }

    public List<CEstado> mejorSolucion(CNodoArbol nodoActual, Integer cd) {
        List<List<CEstado>> lstSoluciones = new ArrayList<>();
        List<List<CEstado>> lstMejorSolucion = listaSoluciones(nodoActual, cd, lstSoluciones);
        List<CEstado> lstsolucion = new ArrayList<>();
        if (lstMejorSolucion != null) {
            int menor = 0;
            for (int i = 0; i < lstMejorSolucion.size(); i++) {
                if (i == 0) {
                    lstsolucion = lstMejorSolucion.get(i);
                    menor = lstMejorSolucion.get(i).size();
                } else {
                    if (lstMejorSolucion.get(i).size() < menor) {
                        lstsolucion = lstMejorSolucion.get(i);
                        menor = lstMejorSolucion.get(i).size();
                    }
                }
            }

        }
        return lstsolucion;
    }

    /**
     *
     * @param nodoActual recibe un nodo
     * @param cd cantidad deseada
     * @param lstSoluciones recibe una lista de lista de estados
     * @return devuelve una lista de todas las soluciones que encuentre en el
     * arbol
     */
    public List<List<CEstado>> listaSoluciones(CNodoArbol nodoActual, Integer cd, List<List<CEstado>> lstSoluciones) {
        List<CEstado> lstHijos;
        if (esMeta(nodoActual.getInformacionNodoArbol(), cd)) {
            lstHijos = recorrerRamaSolucion(nodoActual);
            lstSoluciones.add(lstHijos);
        }

        for (CNodoArbol tmp : nodoActual.getLstHijosNodoArbol()) {
            listaSoluciones(tmp, cd, lstSoluciones);
        }
        return lstSoluciones;
    }

}
