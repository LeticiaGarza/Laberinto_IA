package sistema;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import util.Archivo;
import util.Constante;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import visual.Panel;

public class Cuadricula {

    static JPanel cuadricula;
    static Border margen;
    static int filas, columnas;
    public static int ancho, alto;
    static ImageIcon mascara;
    public static Terreno[][] mtzTerrenos;
    public static ArrayList<TipoTerreno> listTerrenos;

    public static void inicializar() {
        cuadricula = Panel.cuadricula;
        cuadricula.removeAll();
        filas = Constante.dameFilas();
        columnas = Constante.dameColumnas();
        mascara = Archivo.listaColores.get(9);
        alto = Constante.dameAltoCuadricula() / (Constante.dameFilas() + 1);
        ancho = Constante.dameAnchoCuadricula() / (Constante.dameColumnas() + 1);
    }

    public static void pintarCuadricula() {
        inicializar();
        crearMatrizTerrenos();
        String idListaTipoTerrenos;
        for (int indice = 0; indice < Archivo.listaTipoTerrenos.size(); indice++) {
            Terreno terreno;
            idListaTipoTerrenos = Archivo.listaTipoTerrenos.get(indice);
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    terreno = mtzTerrenos[i][j];
                    if (terreno.dameId().equals(idListaTipoTerrenos)) {
                        terreno.fijaImagenTerreno(Archivo.listaColores.get(indice));
                    }
                }
            }
        }
        pintarFilasColumnas();
        pintarTerrenos();
        cuadricula.repaint();
    }

    public static void crearMatrizTerrenos() {
        int letraColumna;
        int numeroFila;
        String posicion;
        JLabel terreno, mascara;
        String[] idTerrenos;
        mtzTerrenos = new Terreno[filas][columnas];
        numeroFila = 1;
        for (int i = 0; i < filas; i++) {
            letraColumna = 'A';
            idTerrenos = Archivo.listaTerrenos.get(i);
            for (int j = 0; j < columnas; j++) {
                terreno = new JLabel();
                terreno.setBounds((j + 1) * ancho, (i + 1) * alto, ancho, alto);
                mascara = new JLabel();
                mascara.setBounds((j + 1) * ancho, (i + 1) * alto, ancho, alto);
                posicion = ((char) letraColumna) + String.valueOf(numeroFila);
                mtzTerrenos[i][j] = new Terreno(terreno, mascara, idTerrenos[j],
                        null, i, j, posicion, (j + 1) * ancho, (i + 1) * alto);
                letraColumna++;
            }
            numeroFila++;
        }
    }

    public static void pintarFilasColumnas() {
        char letra = 'A';
        int numero = 1;
        Font formatoLetra = new Font("Arial", Font.BOLD, 20);
        JLabel cuadro;
        for (int i = 1; i <= columnas; i++) {
            cuadro = new JLabel();
            cuadro.setFont(formatoLetra);
            cuadro.setBounds(i * ancho, 0, alto, ancho);
            cuadro.setText("  " + letra);
            cuadricula.add(cuadro);
            letra++;
        }
        for (int j = 1; j <= filas; j++) {
            cuadro = new JLabel();
            cuadro.setFont(formatoLetra);
            cuadro.setBounds(0, j * alto, alto, ancho);
            cuadro.setText(" " + numero);
            cuadricula.add(cuadro);
            numero++;
        }
    }

    public static void pintarTerrenos() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                cuadricula.add(mtzTerrenos[i][j].dameImagen());
            }
        }
    }

    public static Terreno dameTerreno(int x, int y) {
        Terreno terreno = null;
        boolean fueEncontrado = false;
        int i = 0;
        while (i < filas && !fueEncontrado) {
            for (int j = 0; j < columnas; j++) {
                terreno = mtzTerrenos[i][j];
                if ((x >= terreno.damePosX() && (terreno.damePosX() + ancho) >= x)
                        && (y >= terreno.damePosY() && (terreno.damePosY() + alto) >= y)) {
                    fueEncontrado = true;
                    break;
                }
            }
            i++;
        }
        return terreno;
    }

    public static void actualizarMatrizTerrenos(ArrayList<TipoTerreno> list) {
        listTerrenos = list;
        TipoTerreno tipoTerreno;
        Terreno terreno;
        for (int indice = 0; indice < listTerrenos.size(); indice++) {
            tipoTerreno = listTerrenos.get(indice);
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    terreno = mtzTerrenos[i][j];
                    if (terreno.dameId().equals(tipoTerreno.dameId())) {
                        terreno.fijaImagenTerreno(tipoTerreno.dameImagen());
                        terreno.fijaTipo(tipoTerreno.dameTipo());
                        terreno.fijaImagenAux(tipoTerreno.dameImagen());
                        terreno.fijaVisita(0);
                    }
                }
            }
        }
        cuadricula.repaint();
    }

    public static void reiniciar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                mtzTerrenos[i][j].dameMascara().setText("");
                mtzTerrenos[i][j].dameMascara().setIcon(mtzTerrenos[i][j].dameImagenAux());
                mtzTerrenos[i][j].dameImagen().setIcon(mascara);
                mtzTerrenos[i][j].fijaOcultar(true);
                cuadricula.add(mtzTerrenos[i][j].dameImagen());
                cuadricula.add(mtzTerrenos[i][j].dameMascara());
                mtzTerrenos[i][j].dameMascara().repaint();
                mtzTerrenos[i][j].dameImagen().repaint();
            }
        }
    }

    public static void fijarCostos(Ser ser) {
        ArrayList<String> listaTerrenos = ser.dameTerrenosVal();
        ArrayList<Float> listaCostos = ser.dameCostos();
        Terreno t;
        int numTerrenos = listaTerrenos.size();
        for (int i = 0; i < numTerrenos; i++) {
            for (int j = 0; j < filas; j++) {
                for (int k = 0; k < columnas; k++) {
                    t = mtzTerrenos[j][k];
                    if (t.dameId().equals(listaTerrenos.get(i))) {
                        t.fijaCosto(listaCostos.get(i));
                    }
                }
            }
        }
    }

    public static void ocultarLaberinto() {
        Terreno terreno;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                terreno = mtzTerrenos[i][j];
                terreno.fijaImagenTerreno(mascara);
            }
        }
        cuadricula.repaint();
    }
}
