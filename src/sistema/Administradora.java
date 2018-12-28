package sistema;

import javax.swing.JOptionPane;
import util.Archivo;
import util.Constante;
import visual.Botones;
import visual.EventosRaton;
import visual.Panel;
import visual.Ventana;

public class Administradora {

    public static void cargarMapa() {
        if (Archivo.elegirArchivo("Texto")) {
            if (Archivo.esArchivoValido()) {
                if (Archivo.validarDatosArchivo()) {
                    Ventana.filasColumnas.setText("Filas: " + Constante.dameFilas()
                            + " Columnas: " + Constante.dameColumnas());
                    Ventana.principal.add(Ventana.filasColumnas);
                    Cuadricula.pintarCuadricula();
                    Ventana.crearVentanaInfoTerrenos();
                } else {
                    JOptionPane.showMessageDialog(null, "Datos de mapa no válidos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Archivo inválido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No elegiste archivo");
        }
    }

    public static void jugar() {
        if (Ventana.jugar) {
            if (Archivo.listaTipoTerrenos != null) {
                if (EventosRaton.estadoInicial != null) {
                    if (EventosRaton.estadoFinal != null) {
                        if (Ventana.listaSeres.size() > 0) {
                            if (Ventana.serElegido != -1) {
                                Ser ser = Ventana.listaSeres.get(Ventana.serElegido - 1);
                                Ventana.jugar = false;
                                Cuadricula.ocultarLaberinto();
                                Cuadricula.fijarCostos(ser);
                                Laberinto.jugar(ser, Panel.cuadricula,
                                        Botones.tipoRepetir.getSelectedItem().toString()/*"Repetir nodos"*/);
                            } else {
                                Ventana.selecSer();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No has creado seres");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Tienes que elegir un terreno final");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Tienes que elegir un terreno inicial");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No has cargado el mapa");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Juego en proceso");
        }
    }

    public static void reiniciar() {
        if (!Ventana.jugar) {
            Cuadricula.reiniciar();
            Ventana.jugar = true;
            Ventana.serElegido = -1;
            EventosRaton.estadoFinal = null;
            EventosRaton.estadoInicial = null;
            Laberinto.finalizado = false;
            Cuadricula.actualizarMatrizTerrenos(Cuadricula.listTerrenos);
        } else {
            JOptionPane.showMessageDialog(null, "No has iniciado el juego");
        }
    }

    public static void crearSer() {
        if (Ventana.listaTipoTerrenos.size() > 0) {
            if (Ventana.indiceSer < 3) {
                Ventana.crearVentanaSer();
            } else {
                JOptionPane.showMessageDialog(null, "No puedes crear más de 3 seres");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No has cargado la informacion de terrenos");
        }
    }

    public static void infoTerrenos() {
        if (!Ventana.ventanaInfoTerreno) {
            Ventana.crearVentanaInfoTerrenos();
        }
    }

    public static void mostrarArbol() {
        Laberinto.arbol.mostrarArbol(Laberinto.arbol.dameNodoRaiz());
    }
}
