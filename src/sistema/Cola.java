package sistema;

import javax.swing.tree.DefaultMutableTreeNode;

public class Cola {

    Nodo fin;
    Nodo inicio;
    int longitud;

    public Cola() {
        fin = null;
        inicio = null;
        longitud = 0;
    }

    public void encolar(Nodo nodo) {
        if (inicio != null) {
            fin.fijaSig(nodo);
            nodo.fijaAnt(fin);
            fin = nodo;
        } else {
            inicio = nodo;
            fin = nodo;
        }
        longitud += 1;
    }

    public DefaultMutableTreeNode desencolar() {
        DefaultMutableTreeNode primero;
        if (longitud == 0) {
            primero = null;
        } else {
            primero = inicio.dameValor();
            if (longitud != 1) {
                inicio.fijaSig(inicio.dameSig());
                inicio.fijaAnt(null);
            }
            longitud -= 1;
        }
        return primero;
    }

    public int longitud() {
        return longitud;
    }

    public class Nodo {

        DefaultMutableTreeNode valor;
        Nodo sig;
        Nodo ant;

        public Nodo(DefaultMutableTreeNode valor) {
            this.valor = valor;
            ant = null;
            sig = null;
        }

        public Nodo dameSig() {
            return sig;
        }

        public void fijaSig(Nodo sig) {
            this.sig = sig;
        }

        public Nodo dameAnt() {
            return ant;
        }

        public void fijaAnt(Nodo ant) {
            this.ant = ant;
        }

        public DefaultMutableTreeNode dameValor() {
            return valor;
        }
    }
}
