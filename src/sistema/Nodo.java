package sistema;

import java.util.ArrayList;

public class Nodo {

    Nodo padre;
    Nodo hijoArr;
    Nodo hijoAba;
    Nodo hijoIzq;
    Nodo hijoDer;

    int numHijos;
    int numHijosExp;
    int fila;
    int columna;
    int prioridad;
    String info;
    String posicion;
    String direccion;
    float costoAcumulado;
    float costoTotal;
    float distanciaManhatan;

    ArrayList<Integer> numVisitas;

    public Nodo damePadre() {
        return padre;
    }

    public void fijaPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo dameArriba() {
        return hijoArr;
    }

    public void fijaArriba(Nodo hijoArr) {
        this.hijoArr = hijoArr;
    }

    public Nodo dameAbajo() {
        return hijoAba;
    }

    public void fijaAbajo(Nodo hijoAba) {
        this.hijoAba = hijoAba;
    }

    public Nodo dameIzquierda() {
        return hijoIzq;
    }

    public void fijaIzquierda(Nodo hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public Nodo dameDerecha() {
        return hijoDer;
    }

    public void fijaDerecha(Nodo hijoDer) {
        this.hijoDer = hijoDer;
    }

    public int dameNumVisitas() {
        return numVisitas.size();
    }

    public void fijaNuevoNumVisita(int numVisita) {
        numVisitas.add(numVisita);
    }

    public int dameNumHijos() {
        return numHijos;
    }

    public void aumentarNumHijos() {
        numHijos += 1;
    }

    public int dameNumHijosExp() {
        return numHijosExp;
    }

    public void aumentarNumHijosExp() {
        numHijosExp += 1;
    }

    public int dameFila() {
        return fila;
    }

    public void fijaFila(int columna) {
        this.columna = columna;
    }

    public int dameColumna() {
        return columna;
    }

    public void fijaColumna(int fila) {
        this.fila = fila;
    }

    public int damePrioridad() {
        return prioridad;
    }

    public void fijaPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String damePosicion() {
        return posicion;
    }

    public void fijaPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String dameDireccion() {
        return direccion;
    }

    public void fijaDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void fijaCostoA(float costoAcumulado) {
        this.costoAcumulado = costoAcumulado;
    }

    public float dameCostoA() {
        return costoAcumulado;
    }

    public float dameCostoT() {
        return costoAcumulado + distanciaManhatan;
    }

    public void fijaDistanciaM(int fila, int columna) {
        int fil, col;
        fil = this.fila - fila;
        col = this.columna - columna;
        if (fil < 0) {
            fil *= -1;
        }
        if (col < 0) {
            col *= -1;
        }
        distanciaManhatan = fil + col;
    }

    public float dameDistanciaM() {
        return distanciaManhatan;
    }

    public String dameInfo() {
        String visitas = Laberinto.terrenos[fila][columna].dameMascara().getText();
        info = "\nPadre >> ";
        if (padre != null) {
            info += padre.damePosicion();
        } else {
            info += "N/A";
        }
        info += "\nPosicion >> " + posicion
                + "\nNumero de hijos >> " + numHijos + "\nOrden de visita >> " + visitas + "\nEstado >> ";

        if (numHijos <= numHijosExp) {
            info += "cerrado";
        } else {
            info += "abierto";
        }

        return info;
    }

    public String dameInfoAEstrella() {
        String inf = "";
        inf = "\nNodo (" + posicion + ") --> Prioridad(" + prioridad + ") --> " + "Costo(" + costoAcumulado + ")" + " --> "
                + "Manhatan(" + distanciaManhatan + ") --> Direccion(" + direccion + ") Posicion("
                + fila + ":" + columna + ")";
        return inf;
    }

    public Nodo(String pos, int fil, int col) {
        padre = null;
        hijoArr = null;
        hijoAba = null;
        hijoIzq = null;
        hijoDer = null;
        numHijos = 0;
        numVisitas = new ArrayList<Integer>();
        numHijosExp = 0;
        posicion = pos;
        fila = fil;
        columna = col;
    }
}
