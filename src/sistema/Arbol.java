package sistema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import util.Constante;
import visual.Botones;
import visual.EventosRaton;

public class Arbol {

    Nodo nodoRaiz;
    Nodo nodoActual;
    Nodo temp;
    int filaFinal;
    int columnaFinal;
    float costo;
    public boolean estaTrabajando;
    public String direccion;
    String posicionBuscada;
    ArrayList<Nodo> nodos;
    Queue<Nodo> cola;
    public LinkedList<Nodo> listaNodos;
    String algoritmo;
    int numNodos;
    int indice;
    ArrayList<DefaultMutableTreeNode> padres;

    public Arbol(Nodo nodo, String algorit) {
        indice = 1;
        estaTrabajando = false;
        nodoRaiz = nodo;
        filaFinal = EventosRaton.estadoFinal.dameFila();
        columnaFinal = EventosRaton.estadoFinal.dameColumna();
        nodo.fijaPadre(null);
        nodo.fijaCostoA(0);
        nodo.fijaDistanciaM(filaFinal, columnaFinal);
        nodo.fijaPrioridad(0);
        nodoActual = nodo;
        listaNodos = new LinkedList<Nodo>();
        algoritmo = algorit;
        JOptionPane.showMessageDialog(null, "Algoritmo " + algoritmo);
    }

    public void mover(String direccion, int numVisita) {
        switch (direccion) {
            case Constante.ARRIBA:
                if (nodoActual.dameArriba() != null) {
                    nodoActual.aumentarNumHijosExp();
                    nodoActual = nodoActual.dameArriba();
                } else {
                    nodoActual.fijaNuevoNumVisita(numVisita + 1);
                    if (nodoActual.damePadre() != null) {
                        nodoActual = nodoActual.damePadre();
                    }
                }
                break;
            case Constante.ABAJO:
                if (nodoActual.dameAbajo() != null) {
                    nodoActual.aumentarNumHijosExp();
                    nodoActual = nodoActual.dameAbajo();
                } else if (nodoActual.damePadre() != null) {
                    nodoActual = nodoActual.damePadre();
                }
                break;
            case Constante.IZQUIERDA:
                if (nodoActual.dameIzquierda() != null) {
                    nodoActual.aumentarNumHijosExp();
                    nodoActual = nodoActual.dameIzquierda();
                } else if (nodoActual.damePadre() != null) {
                    nodoActual = nodoActual.damePadre();
                }
                break;
            case Constante.DERECHA:
                if (nodoActual.dameDerecha() != null) {
                    nodoActual.aumentarNumHijosExp();
                    nodoActual = nodoActual.dameDerecha();
                } else if (nodoActual.damePadre() != null) {
                    nodoActual = nodoActual.damePadre();
                }
                break;
        }
    }

    public void expandir(int fil, int col, String direccion) {
        boolean esInsertable = true;
        costo = Laberinto.terrenos[fil][col].dameCosto();
        temp = new Nodo(Laberinto.terrenos[fil][col].damePosicion(), fil, col);
        temp.fijaPadre(nodoActual);
        temp.fijaCostoA(nodoActual.dameCostoA() + costo);
        temp.fijaDistanciaM(filaFinal, columnaFinal);
        switch (direccion) {
            case Constante.ARRIBA:
                nodoActual.fijaArriba(temp);
                temp.fijaPrioridad(Integer.valueOf(Botones.prioridadArriba.getSelectedItem()));
                break;

            case Constante.ABAJO:
                nodoActual.fijaAbajo(temp);
                temp.fijaPrioridad(Integer.valueOf(Botones.prioridadAbajo.getSelectedItem()));
                break;

            case Constante.IZQUIERDA:
                nodoActual.fijaIzquierda(temp);
                temp.fijaPrioridad(Integer.valueOf(Botones.prioridadIzquierda.getSelectedItem()));
                break;

            case Constante.DERECHA:
                nodoActual.fijaDerecha(temp);
                temp.fijaPrioridad(Integer.valueOf(Botones.prioridadDerecha.getSelectedItem()));
                break;
        }
        temp.fijaDireccion(direccion);

        for (int i = 0; i < listaNodos.size(); i++) {
            Nodo actual = listaNodos.get(i);
            if (algoritmo.equals(Botones.BACKTRAQUING)) {
                if (temp.damePrioridad() <= actual.damePrioridad()) {
                    listaNodos.add(i, temp);
                    esInsertable = false;
                    break;
                }
            } else if (temp.dameCostoT() < actual.dameCostoT()
                    || (temp.damePrioridad() <= actual.damePrioridad()
                    && temp.dameCostoT() <= actual.dameCostoT())) {
                listaNodos.add(i, temp);
                esInsertable = false;
                break;
            }
        }
        if (esInsertable) {
            listaNodos.add(temp);
        }
    }

    public void iniciarAlgoritmo() {
        Nodo actual;
        while (!Laberinto.finalizado) {
            if (!Laberinto.regresar) {
                actual = listaNodos.getFirst();
                listaNodos.removeFirst();
                if (algoritmo.equals(Botones.BACKTRAQUING)) {
                    for (int i = 0; i < listaNodos.size(); i++) {
                        listaNodos.removeFirst();
                    }
                }
                JOptionPane.showMessageDialog(null, "");
                Laberinto.moverSer(actual.dameDireccion(), actual.dameFila(), actual.dameColumna(), actual);
            } else {
                nodoActual = nodoActual.damePadre();
                Laberinto.moverSer("regresar", nodoActual.dameFila(), nodoActual.dameColumna(), nodoActual);
            }
        }
    }

    public String listarNodos() {
        String infoNodos = "";
        for (int i = 0; i < listaNodos.size(); i++) {
            infoNodos += listaNodos.get(i).dameInfoAEstrella();
        }
        return infoNodos;
    }

    public void mostrarArbol(Nodo nodo) {
        numNodos = 1;
        totalNodos(nodoRaiz);

        Nodo actual;
        padres = new ArrayList<>();
        nodos = new ArrayList<Nodo>();

        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(nodo.damePosicion() + " --> (" + nodo.dameCostoA() + ")" + "(" + nodo.dameDistanciaM() + ")");
        DefaultTreeModel modelo = new DefaultTreeModel(raiz);
        JTree tree = new JTree(modelo);

        int limite = 1;
        int numHijos = 0;
        int indiceNodos = 0;
        int indicePadres = 0;
        DefaultMutableTreeNode padre;
        DefaultMutableTreeNode hijo;

        padres.add(raiz);
        padre = padres.get(indicePadres);
        encolar(nodoRaiz);

        while (limite < (numNodos - 1)) {
            actual = nodos.get(indiceNodos);
            encolar(actual);
            hijo = new DefaultMutableTreeNode(actual.damePosicion() + " --> (" + actual.dameCostoA() + ")" + "(" + actual.dameDistanciaM() + ")");
            modelo.insertNodeInto(hijo, padre, numHijos);
            encolarPadre(actual, hijo);
            numHijos++;
            if (numHijos >= actual.damePadre().dameNumHijos()) {
                numHijos = 0;
                indicePadres++;
                padre = padres.get(indicePadres);
            }
            limite++;
            indiceNodos++;
        }
        Nodo n = nodos.get(indiceNodos);
        modelo.insertNodeInto(new DefaultMutableTreeNode(n.damePosicion() + " --> (" + n.dameCostoA() + ")" + "(" + n.dameDistanciaM() + ")"), padres.get(indicePadres), numHijos);
        JFrame ventanaArbol = new JFrame();
        JScrollPane scroll = new JScrollPane(tree);
        ventanaArbol.getContentPane().add(scroll);
        ventanaArbol.pack();
        ventanaArbol.setVisible(true);
        ventanaArbol.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void encolar(Nodo nodo) {
        if (nodo.dameArriba() != null) {
            nodos.add(nodo.dameArriba());
        }
        if (nodo.dameDerecha() != null) {
            nodos.add(nodo.dameDerecha());
        }
        if (nodo.dameAbajo() != null) {
            nodos.add(nodo.dameAbajo());
        }
        if (nodo.dameIzquierda() != null) {
            nodos.add(nodo.dameIzquierda());
        }
    }

    public void encolarPadre(Nodo nodo, DefaultMutableTreeNode padre) {
        int auxNumHijos = 0;
        if (nodo.dameArriba() != null) {
            auxNumHijos++;
        }
        if (nodo.dameDerecha() != null) {
            auxNumHijos++;
        }
        if (nodo.dameAbajo() != null) {
            auxNumHijos++;
        }
        if (nodo.dameIzquierda() != null) {
            auxNumHijos++;
        }
        if (auxNumHijos != 0) {
            padres.add(padre);
        }
    }

    public void totalNodos(Nodo nodo) {
        if (nodo.dameArriba() != null) {
            numNodos++;
            totalNodos(nodo.dameArriba());
        }
        if (nodo.dameDerecha() != null) {
            numNodos++;
            totalNodos(nodo.dameDerecha());
        }
        if (nodo.dameAbajo() != null) {
            numNodos++;
            totalNodos(nodo.dameAbajo());
        }
        if (nodo.dameIzquierda() != null) {
            numNodos++;
            totalNodos(nodo.dameIzquierda());
        }
    }

    public Nodo dameNodoRaiz() {
        return nodoRaiz;
    }

    public void aumentarNumeroVisitas(int fil, int col) {
        posicionBuscada = Laberinto.terrenos[fil][col].damePosicion();
        buscarNodo(nodoRaiz);
        nodoActual.fijaNuevoNumVisita(Laberinto.numVisita + 2);
    }

    private void buscarNodo(Nodo nodo) {
        if (nodo.damePosicion() != posicionBuscada) {
            if (nodo.dameArriba() != null) {
                buscarNodo(nodo.dameArriba());
            }
            if (nodo.dameAbajo() != null) {
                buscarNodo(nodo.dameAbajo());
            }
            if (nodo.dameIzquierda() != null) {
                buscarNodo(nodo.dameIzquierda());
            }
            if (nodo.dameDerecha() != null) {
                buscarNodo(nodo.dameDerecha());
            }
        } else {
            nodoActual = nodo;
        }
    }
}
