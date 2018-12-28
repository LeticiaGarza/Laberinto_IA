package visual;

import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Constante;

public class Botones {

    public static JPanel botonesPrincipal;
    public static JPanel botonesPersonaje;

    public static final String ETIQUETA_BTN_CARGAR_MAPA = "Cargar mapa";
    public static final String ETIQUETA_BTN_JUGAR = "Jugar";
    public static final String ETIQUETA_BTN_REINICIAR = "Reiniciar";
    public static final String ETIQUETA_BTN_CREAR_SER = "Crear ser";
    public static final String ETIQUETA_BTN_INFO_TERRENO = "Terreno";
    public static final String ETIQUETA_BTN_MOSTRAR_ARBOL = "Mostrar arbol";

    public static final String ETIQUETA_PROPRI_1 = "1";
    public static final String ETIQUETA_PROPRI_2 = "2";
    public static final String ETIQUETA_PROPRI_3 = "3";
    public static final String ETIQUETA_PROPRI_4 = "4";

    public static final String ETIQUETA_REPETIR = "Repetir nodos";
    public static final String ETIQUETA_NO_REPETIR = "No repetir nodos";

    public static final String BACKTRAQUING = "Backtracking";
    private static final String A_ESTRELLA = "A-Estrella";

    public static Choice tipoAlgoritmo;
    public static Choice tipoRepetir;
    public static Choice prioridadArriba;
    public static Choice prioridadIzquierda;
    public static Choice prioridadDerecha;
    public static Choice prioridadAbajo;

    public static void inicializar(ActionListener accion) {
        crearBotonesPrincipal(accion);
        crearBotonesPersonaje(accion);
    }

    public static void crearBotonesPrincipal(ActionListener accion) {
        botonesPrincipal = new JPanel();
        botonesPrincipal.setLayout(new GridLayout(1, 7));
        botonesPrincipal.setLocation(0, 0);
        botonesPrincipal.setSize(Constante.dameAnchoCuadricula(), 50);

        JButton btnCargarMapa = new JButton(ETIQUETA_BTN_CARGAR_MAPA);
        btnCargarMapa.setBackground(Color.YELLOW);
        btnCargarMapa.addActionListener(accion);
        botonesPrincipal.add(btnCargarMapa);

        JButton btnJugar = new JButton(ETIQUETA_BTN_JUGAR);
        btnJugar.setBackground(Color.YELLOW);
        btnJugar.addActionListener(accion);
        botonesPrincipal.add(btnJugar);

        JButton btnReiniciar = new JButton(ETIQUETA_BTN_REINICIAR);
        btnReiniciar.setBackground(Color.YELLOW);
        btnReiniciar.addActionListener(accion);
        botonesPrincipal.add(btnReiniciar);

        JButton btnInfoTerreno = new JButton(ETIQUETA_BTN_INFO_TERRENO);
        btnInfoTerreno.setBackground(Color.YELLOW);
        btnInfoTerreno.addActionListener(accion);
        botonesPrincipal.add(btnInfoTerreno);

        JButton btnCrearSer = new JButton(ETIQUETA_BTN_CREAR_SER);
        btnCrearSer.setBackground(Color.YELLOW);
        btnCrearSer.addActionListener(accion);
        botonesPrincipal.add(btnCrearSer);

        JButton mostrarArbol = new JButton(ETIQUETA_BTN_MOSTRAR_ARBOL);
        mostrarArbol.setBackground(Color.YELLOW);
        mostrarArbol.addActionListener(accion);
        botonesPrincipal.add(mostrarArbol);
    }

    public static void crearBotonesPersonaje(ActionListener accion) {
        int anchoChoice = Constante.dameAnchoVentana() - Constante.dameAnchoCuadricula();
        int altoChoice = 60;
        String[] prioridades = {ETIQUETA_PROPRI_1, ETIQUETA_PROPRI_2, ETIQUETA_PROPRI_3, ETIQUETA_PROPRI_4};

        botonesPersonaje = new JPanel();
        botonesPersonaje.setLayout(new GridLayout(5, 2)/*null*/);
        botonesPersonaje.setLocation(Constante.dameAnchoCuadricula(), Constante.dameAltoVentana()
                - Constante.dameMargenAnchoCuadricula());
        botonesPersonaje.setSize(Constante.dameMargenAnchoCuadricula(),
                Constante.dameMargenAnchoCuadricula());

        botonesPersonaje.add(new JLabel("Algoritmo a utilizar"));
        tipoAlgoritmo = new Choice();
        tipoAlgoritmo.addItem(A_ESTRELLA);
        tipoAlgoritmo.addItem(BACKTRAQUING);
        tipoAlgoritmo.setBounds(0, 0, anchoChoice, altoChoice);
        botonesPersonaje.add(tipoAlgoritmo);

        tipoRepetir = new Choice();
        tipoRepetir.addItem(ETIQUETA_NO_REPETIR);
        tipoRepetir.addItem(ETIQUETA_REPETIR);
        botonesPersonaje.add(tipoRepetir);

        prioridadArriba = new Choice();
        prioridadIzquierda = new Choice();
        prioridadDerecha = new Choice();
        prioridadAbajo = new Choice();
        for (int i = 0; i < 4; i++) {
            prioridadArriba.add(prioridades[i]);
            prioridadIzquierda.add(prioridades[i]);
            prioridadDerecha.add(prioridades[i]);
            prioridadAbajo.add(prioridades[i]);
        }
        botonesPersonaje.add(new JLabel());
        prioridadArriba.setBounds(0, 1 * altoChoice + 10, anchoChoice, altoChoice);
        botonesPersonaje.add(prioridadArriba);

        botonesPersonaje.add(new JLabel());
        prioridadIzquierda.setBounds(0, 2 * altoChoice + 10, anchoChoice, altoChoice);
        botonesPersonaje.add(prioridadIzquierda);

        botonesPersonaje.add(new JLabel());
        prioridadDerecha.setBounds(0, 3 * altoChoice + 10, anchoChoice, altoChoice);
        botonesPersonaje.add(prioridadDerecha);

        botonesPersonaje.add(new JLabel());
        prioridadAbajo.setBounds(0, 4 * altoChoice + 10, anchoChoice, altoChoice);
        botonesPersonaje.add(prioridadAbajo);
    }
}
