/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espoch.principal;

import edu.espoch.arbol.modelo.CArbol;
import edu.espoch.arbol.modelo.CNodoArbol;
import edu.espoch.estado.modelo.CEstado;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author foqc
 */
public class ClassPrincipal {

    public static void main(String[] args) {

        CArbol arbol = new CArbol(5, 3, 7);

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            muestraMenu();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    arbol.crearArbol();
                    System.out.println("****RECORRIDO***");
                    System.out.println("Raiz: ( " + arbol.getRaiz().getInformacionNodoArbol().getRecipienteGrande() + "," + arbol.getRaiz().getInformacionNodoArbol().getRecipientePequenio() + " )");

                    imprimirNodoProfundidad(arbol.getRaiz());
                    System.out.println("****SOLUCION***");
                    recorrerSolucion(arbol.solucion());

                    System.out.println("****MEJOR SOLUCION***");
                    recorrerMejorSolucion(arbol.mejorSolucion());

                    System.out.println("****TODAS LAS SOLUCIONES***");
                    recorrerTodosSolucion(arbol.listarTodasSoluciones());
                }
                break;

                case 2:
                    System.out.println("****RECORRIDO***");
                    System.out.println("****RECORRIDO***");
                    System.out.println("Raiz: ( " + arbol.getRaiz().getInformacionNodoArbol().getRecipienteGrande() + "," + arbol.getRaiz().getInformacionNodoArbol().getRecipientePequenio() + " )");
                    imprimirNodoAncho(arbol.getRaiz());
                    break;

                case 0:
                    System.exit(0);
                default:
                    System.out.println("Opcion no existe!");
            }
        } while (opcion != 0);

    }

    private static void muestraMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("**************************************************************************************\n");
        menu.append("*  SELECCIONE EL UNA ACCION A REALIZAR ¿Qué desea ingresar?    *\n");
        menu.append("*  1.- GENERAR ARBOL 2.-MOSTRAR ARBOL 0.- SALIR   *\n");
        menu.append("**************************************************************************************\n");
        System.out.println(menu.toString());
    }

    private static void imprimirNodoProfundidad(CNodoArbol nodoActual) {
        CNodoArbol aux = null;
        for (int i = 0; i < nodoActual.getLstHijosNodoArbol().size(); i++) {
            aux = (CNodoArbol) nodoActual.getLstHijosNodoArbol().get(i);
            System.out.println("Nodo Papa: (" + nodoActual.getInformacionNodoArbol().getRecipienteGrande()
                    + " , " + nodoActual.getInformacionNodoArbol().getRecipientePequenio()
                    + ") -- Nodo hijo: (" + aux.getInformacionNodoArbol().getRecipienteGrande()
                    + " , " + aux.getInformacionNodoArbol().getRecipientePequenio() + ") ===========>"
                    + "Tiene " + (nodoActual.getLstHijosNodoArbol().size()) + " hijos");
            imprimirNodoProfundidad(aux);
        }

    }

    private static void imprimirNodoAncho(CNodoArbol nodoActual) {//OJO: MEtodo inconcluso
        if (!nodoActual.getLstHijosNodoArbol().isEmpty()) {
            CNodoArbol aux = null;
            System.out.println("Nodo Papa: (" + nodoActual.getInformacionNodoArbol().getRecipienteGrande()
                    + " , " + nodoActual.getInformacionNodoArbol().getRecipientePequenio() + ")");
            System.out.println(" ===========>" + "Tiene " + (nodoActual.getLstHijosNodoArbol().size()) + " hijos");

            for (int i = 0; i < nodoActual.getLstHijosNodoArbol().size(); i++) {
                aux = (CNodoArbol) nodoActual.getLstHijosNodoArbol().get(i);
                System.out.println(" (" + aux.getInformacionNodoArbol().getRecipienteGrande()
                        + " , " + aux.getInformacionNodoArbol().getRecipientePequenio() + ")");
            }
            if (nodoActual.getPadreNodoArbol() != null) {
                for (int i = 0; i <= nodoActual.getPadreNodoArbol().getLstHijosNodoArbol().size(); i++) {
                    if (i == nodoActual.getPadreNodoArbol().getLstHijosNodoArbol().size()) {
                        imprimirNodoAncho(nodoActual.getPadreNodoArbol().getLstHijosNodoArbol().get(0).getLstHijosNodoArbol().get(0));
                    } else {
                        imprimirNodoAncho(nodoActual.getPadreNodoArbol().getLstHijosNodoArbol().get(i));
                    }
                }
                //imprimirNodoAncho(nodoActual.getPadreNodoArbol().getLstHijosNodoArbol().get(1));
            } else {
                if (!nodoActual.getLstHijosNodoArbol().isEmpty()) {
                    aux = nodoActual.getLstHijosNodoArbol().get(0);
                    imprimirNodoAncho(aux);
                } else {
                    System.out.println("No hay hijos de este nodo...");
                }
            }
        }

    }

    private static void recorrerSolucion(List<CEstado> lstSol) {
        CEstado aux = null;
        if (lstSol != null) {
            System.out.println("tama;o solucion " + lstSol.size());
            for (CEstado lstSol1 : lstSol) {
                aux = lstSol1;
                System.out.print("(" + aux.getRecipienteGrande() + "," + aux.getRecipientePequenio() + ")");
            }
            System.out.println("");
        }
    }

    private static void recorrerMejorSolucion(List<CEstado> lstSol) {
        CEstado aux = null;
        if (lstSol != null) {
            System.out.println("tama;o solucion " + lstSol.size());
            for (CEstado lstSol1 : lstSol) {
                aux = lstSol1;
                System.out.print("(" + aux.getRecipienteGrande() + "," + aux.getRecipientePequenio() + ")");
            }
            System.out.println("");
        }
    }

    private static void recorrerTodosSolucion(List<List<CEstado>> lstSoluciones) {
        CEstado aux = null;
        if (lstSoluciones != null) {
            System.out.println(lstSoluciones.size() + " soluciones encontradas");
            for (int i = 0; i < lstSoluciones.size(); i++) {
                System.out.println("tama;o solucion " + lstSoluciones.get(i).size());
                for (int j = 0; j < lstSoluciones.get(i).size(); j++) {
                    aux = lstSoluciones.get(i).get(j);
                    System.out.print("(" + aux.getRecipienteGrande() + "," + aux.getRecipientePequenio() + ")");
                }
                System.out.println("");
            }
            System.out.println("");
        }
    }
}
