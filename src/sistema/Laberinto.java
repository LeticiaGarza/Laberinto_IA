package sistema;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import util.Constante;
import visual.Botones;
import visual.EventosRaton;

public class Laberinto {

    static int numVisita;
    public static int fila, columna;

    static Ser ser;
    static Nodo actual;
    static Nodo anterior;
    static int numFallas;
    static ImageIcon imagen;
    static JPanel cuadricula;
    static Terreno[][] terrenos;
    static boolean movimientoValido;
    static boolean puedesRepetirNodos;
    static final int FALLAS_PERMITIDAS = 0;
    static String algoritmo;

    public static Arbol arbol;
    public static boolean finalizado;
    public static boolean regresar;
    static boolean esBackTracking;

    public static void jugar(Ser ser, JPanel cuadricula, String repetirNodos) {
        Laberinto.ser = ser;
        finalizado = false;
        regresar = false;
        Laberinto.imagen = ser.dameImagen();
        Laberinto.cuadricula = cuadricula;
        terrenos = Cuadricula.mtzTerrenos;
        fila = EventosRaton.estadoInicial.dameFila();
        columna = EventosRaton.estadoInicial.dameColumna();
        movimientoValido = true;
        if (esTerrenoValido(fila, columna)) {
            numVisita = 1;
            algoritmo = Botones.tipoAlgoritmo.getSelectedItem();
            Nodo raiz = new Nodo(terrenos[fila][columna].damePosicion(), fila, columna);
            arbol = new Arbol(raiz, algoritmo);
            terrenos[fila][columna].dameImagen().setIcon(imagen);
            terrenos[fila][columna].dameImagen().repaint();
            actualizarTerreno();
            desocultarTerrenos();
            if (repetirNodos == Botones.ETIQUETA_REPETIR) {
                puedesRepetirNodos = true;
            } else {
                puedesRepetirNodos = false;
            }
            if (algoritmo.equals(Botones.BACKTRAQUING)) {
                esBackTracking = true;
            } else {
                esBackTracking = false;
            }
            arbol.iniciarAlgoritmo();
        } else {
            movimientoValido = false;
            JOptionPane.showMessageDialog(null, "No puedes poner el ser en el inicio");
        }
    }

    public static boolean esTerrenoValido(int fil, int col) {
        boolean esTerrenoValido = true;
        String idTerreno = terrenos[fil][col].dameId();
        for (int i = 0; i < ser.dameTerrenosInval().size(); i++) {
            if (ser.dameTerrenosInval().get(i).equals(idTerreno)) {
                esTerrenoValido = false;
                break;
            }
        }
        return esTerrenoValido;
    }

    public static void moverSer(String direccion, int fil, int col, Nodo act) {
        if (movimientoValido) {
            fila = fil;
            columna = col;
            numFallas = 0;
            anterior = act.damePadre();
            actual = act;
            switch (direccion) {
                case Constante.ARRIBA:
                    moverArriba();
                    break;
                case Constante.ABAJO:
                    moverAbajo();
                    break;
                case Constante.IZQUIERDA:
                    moverIzquierda();
                    break;
                case Constante.DERECHA:
                    moverDerecha();
                    break;
                default:
                    controlMovimiento();
                    break;
            }
        }
    }

    public static void moverArriba() {
        arbol.mover(Constante.ARRIBA, numVisita + 1);
        controlMovimiento();
    }

    public static void moverAbajo() {
        arbol.mover(Constante.ABAJO, numVisita + 1);
        controlMovimiento();
    }

    public static void moverIzquierda() {
        arbol.mover(Constante.IZQUIERDA, numVisita + 1);
        controlMovimiento();
    }

    public static void moverDerecha() {
        arbol.mover(Constante.DERECHA, numVisita + 1);
        controlMovimiento();
    }

    public static void controlMovimiento() {
        numVisita++;
        if (!finalizado) {
            renderizarPersonaje();
            actualizarTerreno();
            desocultarTerrenos();
            if (fila == EventosRaton.estadoFinal.dameFila()
                    && columna == EventosRaton.estadoFinal.dameColumna()) {
                finalizado = true;
            }
        }
    }

    public static void renderizarPersonaje() {
        JLabel terrenoAnt = terrenos[anterior.dameFila()][anterior.dameColumna()].dameImagen();
        JLabel terrenoAct = terrenos[fila][columna].dameImagen();
        terrenoAnt.setIcon(terrenos[anterior.dameFila()][anterior.dameColumna()].dameImagenAux());
        terrenoAct.setIcon(imagen);
        terrenoAct.repaint();
        terrenoAnt.repaint();
    }

    public static void actualizarTerreno() {
        Terreno terreno = terrenos[fila][columna];
        terreno.fijaOcultar(false);
        terreno.dameMascara().setIcon(null);
        terreno.dameMascara().setText(terreno.dameMascara().getText() + " " + numVisita);
        cuadricula.add(terreno.dameMascara());
        cuadricula.add(terreno.dameImagen());
        terreno.dameMascara().repaint();
    }

    public static void desocultarTerrenos() {
        fila--;
        if (fila >= 0) {
            desocultar();
            if (terrenos[fila][columna].dameNumVisita() == 0 || puedesRepetirNodos || esBackTracking) {
                if (esTerrenoValido(fila, columna)) {
                    if (esBackTracking && terrenos[fila][columna].dameNumVisita() == 0) {
                        numFallas++;
                        arbol.nodoActual.aumentarNumHijos();
                        arbol.expandir(fila, columna, Constante.ARRIBA);
                        JOptionPane.showMessageDialog(null, "Actual (" + arbol.nodoActual.damePosicion() + ")"
                                + "expandio arriba");
                    } else if (!esBackTracking) {
                        numFallas++;
                        arbol.nodoActual.aumentarNumHijos();
                        arbol.expandir(fila, columna, Constante.ARRIBA);
                        regresar = false;
                    }
                }
            }
        }

        fila += 2;
        if (fila < Constante.dameFilas()) {
            desocultar();
            if (terrenos[fila][columna].dameNumVisita() == 0 || puedesRepetirNodos || esBackTracking) {
                if (esTerrenoValido(fila, columna)) {
                    if (esBackTracking && terrenos[fila][columna].dameNumVisita() == 0) {
                        numFallas++;
                        arbol.nodoActual.aumentarNumHijos();
                        arbol.expandir(fila, columna, Constante.ABAJO);
                    } else if (!esBackTracking) {
                        numFallas++;
                        arbol.nodoActual.aumentarNumHijos();
                        arbol.expandir(fila, columna, Constante.ABAJO);
                        regresar = false;
                    }
                }
            }
        }

        fila--;
        columna--;
        if (columna >= 0) {
            desocultar();
            if (terrenos[fila][columna].dameNumVisita() == 0 || puedesRepetirNodos || esBackTracking) {
                if (esTerrenoValido(fila, columna)) {
                    if (esBackTracking && terrenos[fila][columna].dameNumVisita() == 0) {
                        numFallas++;
                        arbol.nodoActual.aumentarNumHijos();
                        arbol.expandir(fila, columna, Constante.IZQUIERDA);
                        JOptionPane.showMessageDialog(null, "Actual (" + arbol.nodoActual.damePosicion() + ")"
                                + "expandio izquierda");
                    } else if (!esBackTracking) {
                        numFallas++;
                        arbol.nodoActual.aumentarNumHijos();
                        arbol.expandir(fila, columna, Constante.IZQUIERDA);
                        regresar = false;
                    }
                }
            }
        }

        columna += 2;
        if (columna < Constante.dameColumnas()) {
            desocultar();
            if (terrenos[fila][columna].dameNumVisita() == 0 || puedesRepetirNodos || esBackTracking) {
                if (esTerrenoValido(fila, columna)) {
                    if (esBackTracking && terrenos[fila][columna].dameNumVisita() == 0) {
                        numFallas++;
                        arbol.nodoActual.aumentarNumHijos();
                        arbol.expandir(fila, columna, Constante.DERECHA);
                        JOptionPane.showMessageDialog(null, "Actual (" + arbol.nodoActual.damePosicion() + ")"
                                + "expandio derecha");
                    } else if (!esBackTracking) {
                        numFallas++;
                        arbol.nodoActual.aumentarNumHijos();
                        arbol.expandir(fila, columna, Constante.DERECHA);
                        regresar = false;
                    }
                }
            }
        }
        columna--;
        terrenos[fila][columna].aumentarVisita();

        if (numFallas == FALLAS_PERMITIDAS || esBackTracking) {
            Nodo padre;
            if (esBackTracking) {
                JOptionPane.showMessageDialog(null, "Numero de fallas >> " + numFallas);
                if (numFallas == FALLAS_PERMITIDAS) {
                    JLabel terreno = terrenos[fila][columna].dameMascara();
                    JLabel terrenoAct = terrenos[fila][columna].dameImagen();
                    terrenoAct.setIcon(terrenos[fila][columna].dameImagenAux());
                    terrenoAct.repaint();
                    terreno.repaint();
                    regresar = true;
                } else {
                    regresar = false;
                }
            } else if (numFallas == FALLAS_PERMITIDAS) {
                padre = arbol.listaNodos.getFirst().damePadre();
                int f = padre.dameFila(), c = padre.dameColumna();
                arbol.aumentarNumeroVisitas(f, c);
                JLabel terreno = terrenos[fila][columna].dameMascara();
                JLabel terrenoAct = terrenos[fila][columna].dameImagen();
                terrenoAct.setIcon(terrenos[fila][columna].dameImagenAux());
                terrenoAct.repaint();
                terreno.repaint();
            }
        }

    }

    public static void desocultar() {
        if (terrenos[fila][columna].estaOculto()) {
            terrenos[fila][columna].fijaImagenTerreno(
                    terrenos[fila][columna].dameImagenAux());
            terrenos[fila][columna].fijaOcultar(false);
        }
    }
}
