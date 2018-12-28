package visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sistema.Cuadricula;
import sistema.Ser;
import sistema.TipoTerreno;
import util.Archivo;
import util.Constante;
import util.Imagen;

public class Ventana {

    static String nombre;
    static final long serialVersionUID = 1L;
    public static JFrame principal;
    public static JFrame infoTerrenos;
    public static JFrame ventanaSelecSer;
    public static JFrame ser;
    public static JFrame arbol;
    public static JLabel filasColumnas;
    public static JLabel estadoInicioFin;
    public static JTextField nombreSer;

    public static final String TITULO_VENTANA_SER = "Seres";
    public static final String TITULO_VENTANA_INFO_TERRENOS = "Informacion terrenos";
    public static final String TITULO_VENTANA_PRINCIPAL = "Laberinto";
    public static final String TITULO_VENTANA_ELEGIR_SER = "Elegir ser";
    public static final String TITULO_VENTANA_ARBOL = "Mostrar arbol";

    public static int indiceSer;
    public static int serElegido;
    public static boolean jugar;
    public static boolean ventanaInfoTerreno;

    static JComboBox<Integer> comboSeres;
    public static ArrayList<Ser> listaSeres;
    public static ArrayList<TipoTerreno> listaTipoTerrenos;

    public static void inicializar() {
        jugar = true;
        indiceSer = 0;
        serElegido = -1;
        listaSeres = new ArrayList<Ser>();
        listaTipoTerrenos = new ArrayList<TipoTerreno>();
        crearVentanaPrincipal();
    }

    public static void crearVentanaPrincipal() {
        Constante.inicialiar();

        principal = new JFrame();
        principal.setLayout(null);
        principal.setName(TITULO_VENTANA_PRINCIPAL);
        principal.setTitle(TITULO_VENTANA_PRINCIPAL);
        principal.setResizable(Constante.esRedimensionable());
        principal.setSize(Constante.dameAnchoVentana(), Constante.dameAltoVentana());
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel.inicializar();
        principal.add(Panel.cuadricula);
        principal.add(Panel.cordenada);

        estadoInicioFin = new JLabel();
        estadoInicioFin.setFont(new Font("Arial", Font.BOLD, 20));
        estadoInicioFin.setBounds(Constante.dameAnchoCuadricula(), 0,
                Constante.dameMargenAnchoCuadricula(), 50);
        principal.add(estadoInicioFin);

        filasColumnas = new JLabel();
        filasColumnas.setBounds(50, Constante.dameAltoVentana() - Constante.dameMargenError() * 2, 400, 50);
        filasColumnas.setFont(new Font("Arial", Font.BOLD, 20));
        principal.add(filasColumnas);

        ManejadorBoton manejador = new ManejadorBoton();
        Botones.inicializar(manejador);

        principal.add(Botones.botonesPrincipal);
        principal.add(Botones.botonesPersonaje);
        principal.setVisible(true);
    }

    static JLabel imagenTerreno;
    static JLabel lblIdTerreno;
    static JLabel lblTipoTerreno;
    static JLabel lblTerrenosRestantes;
    static ImageIcon imgIcon;
    static JTextField txtTipoTerreno;

    static int indice;
    static int numTipoTerrenos;

    public static void crearVentanaInfoTerrenos() {
        indice = 0;
        ventanaInfoTerreno = true;
        numTipoTerrenos = Archivo.listaTipoTerrenos.size();
        Font formatoLetra = new Font("Arial", Font.BOLD, 15);

        infoTerrenos = new JFrame();
        infoTerrenos.setLayout(null);
        infoTerrenos.setName(TITULO_VENTANA_INFO_TERRENOS);
        infoTerrenos.setTitle(TITULO_VENTANA_INFO_TERRENOS);
        infoTerrenos.setResizable(Constante.esRedimensionable());
        infoTerrenos.setBounds(Constante.dameAnchoCuadricula(), 0, Constante.dameMargenAnchoCuadricula(),
                Constante.dameAltoVentana() / 2);
        infoTerrenos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listaTipoTerrenos = new ArrayList<TipoTerreno>();
        lblIdTerreno = new JLabel("ID: " + Archivo.listaTipoTerrenos.get(indice));
        lblIdTerreno.setBounds(20, 40, 200, 30);
        lblIdTerreno.setFont(formatoLetra);
        infoTerrenos.add(lblIdTerreno);

        lblTerrenosRestantes = new JLabel("Restantes: " + numTipoTerrenos);
        lblTerrenosRestantes.setBounds(150, 20, 200, 30);
        lblTerrenosRestantes.setFont(formatoLetra);
        infoTerrenos.add(lblTerrenosRestantes);

        lblTipoTerreno = new JLabel("Terreno: ");
        lblTipoTerreno.setBounds(20, 80, 200, 30);
        lblTipoTerreno.setFont(formatoLetra);
        infoTerrenos.add(lblTipoTerreno);

        txtTipoTerreno = new JTextField();
        txtTipoTerreno.setFont(formatoLetra);
        txtTipoTerreno.setBounds(100 + 10, 80, 200, 30);

        txtTipoTerreno.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                    e.consume();
                }
            }
        });

        infoTerrenos.add(txtTipoTerreno);

        imagenTerreno = new JLabel();
        imgIcon = Archivo.listaColores.get(indice);
        imagenTerreno.setBounds(140, 150, Constante.dameLadoImagen(), Constante.dameLadoImagen());
        imagenTerreno.setIcon(Archivo.listaColores.get(indice));
        eventosImagen(imagenTerreno, TITULO_VENTANA_INFO_TERRENOS);
        infoTerrenos.add(imagenTerreno);

        JButton btnCordenada = new JButton("Aceptar");
        btnCordenada.setBounds(0, infoTerrenos.getHeight() - 60, infoTerrenos.getWidth(), 30);
        eventosBoton(btnCordenada, TITULO_VENTANA_INFO_TERRENOS);
        infoTerrenos.add(btnCordenada);

        infoTerrenos.addWindowListener(new EventosVentana());
        infoTerrenos.setVisible(true);
    }

    public static void limpiarDatosTerrenos() {
        numTipoTerrenos--;
        if (numTipoTerrenos != 0) {
            listaTipoTerrenos.add(new TipoTerreno(imgIcon, Archivo.listaTipoTerrenos.get(indice),
                    txtTipoTerreno.getText()));
            indice++;
            lblIdTerreno.setText("ID: " + Archivo.listaTipoTerrenos.get(indice));
            imagenTerreno.setIcon(Archivo.listaColores.get(indice));
            imgIcon = Archivo.listaColores.get(indice);

            txtTipoTerreno.setText("");
            lblTerrenosRestantes.setText("Restantes: " + numTipoTerrenos);
        } else {
            infoTerrenos.dispose();
            ventanaInfoTerreno = false;
            listaTipoTerrenos.add(new TipoTerreno(imgIcon, Archivo.listaTipoTerrenos.get(indice),
                    txtTipoTerreno.getText()));
            Cuadricula.actualizarMatrizTerrenos(listaTipoTerrenos);
        }
    }

    static JTable tablaSer;
    static DefaultTableModel modeloTabla;
    static boolean imagenElegida;

    public static void crearVentanaSer() {
        imagenElegida = false;
        ser = new JFrame();
        ser.setLayout(null);
        ser.setTitle("Seres");
        ser.setResizable(Constante.esRedimensionable());
        ser.setBounds(Constante.dameAnchoCuadricula(), 0, infoTerrenos.getWidth(), infoTerrenos.getHeight());
        ser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inicializarTabla();
        JScrollPane scrollTablaSer = new JScrollPane();
        scrollTablaSer.setBounds(0, 0, ser.getWidth(), ser.getHeight() / 2);
        scrollTablaSer.setViewportView(tablaSer);
        ser.add(scrollTablaSer);

        JLabel imgPersonaje = new JLabel();
        imgIcon = new ImageIcon(Ventana.class.getResource("/imagenes/foto.png"));
        imagenTerreno.setBounds(30, ser.getHeight() / 2, Constante.dameLadoImagen(),
                Constante.dameLadoImagen());

        imagenTerreno.setIcon(imgIcon);
        eventosImagen(imgPersonaje, TITULO_VENTANA_SER);
        ser.add(imagenTerreno);

        JButton btnSer = new JButton("Aceptar");
        btnSer.setBounds(60 + imgIcon.getIconWidth(),
                (ser.getHeight() / 2) + (imgIcon.getIconWidth() / 3), 150, 40);
        eventosBoton(btnSer, TITULO_VENTANA_SER);
        ser.add(btnSer);

        JLabel etiquetaSer = new JLabel();
        etiquetaSer.setBounds(30, (ser.getHeight() / 2) + 180, 300, 15);
        etiquetaSer.setText("Nombre del ser: ");
        ser.add(etiquetaSer);

        nombreSer = new JTextField(20);
        nombreSer.setBounds(165, (ser.getHeight() / 2) + 175, 150, 25);

        nombreSer.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                    e.consume();
                }
            }
        });

        ser.add(nombreSer);

        ser.setVisible(true);
    }

    public static void inicializarTabla() {
        tablaSer = new JTable();
        String[] titulosTablaSer = new String[]{"ID", "Terreno", "Costos"};
        modeloTabla = new DefaultTableModel(null, titulosTablaSer) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (columnIndex == 0 || columnIndex == 1) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        Object[] objeto = null;
        for (int i = 0; i < listaTipoTerrenos.size(); i++) {
            modeloTabla.addRow(objeto);
            tablaSer.setModel(modeloTabla);
            tablaSer.setValueAt(listaTipoTerrenos.get(i).dameId(), i, 0);
            tablaSer.setValueAt(listaTipoTerrenos.get(i).dameTipo(), i, 1);
        }
        tablaSer.setModel(modeloTabla);
    }

    public static void eventosImagen(JLabel imgPresionada, String contexto) {
        imgPresionada.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent click) {
                if (contexto.equals(TITULO_VENTANA_INFO_TERRENOS)) {
                    elegirImagen();
                } else {
                    elegirImagen();
                }
            }
        });
    }

    public static void eventosBoton(JButton btnPresionado, String contexto) {
        btnPresionado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent boton) {
                if (contexto.equals(TITULO_VENTANA_INFO_TERRENOS)) {
                    if ((numTipoTerrenos != 0) && (!txtTipoTerreno.getText().isEmpty())) {
                        limpiarDatosTerrenos();
                    } else {
                        JOptionPane.showMessageDialog(null, "No dejar campos vacíos", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (contexto.equals(TITULO_VENTANA_SER)) {
                    if (imagenElegida && !nombreSer.getText().isEmpty()) {
                        crearSer();
                    } else if (nombreSer.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El ser debe tener un nombre", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No elegiste imagen");
                    }
                } else {
                    serElegido = (int) comboSeres.getSelectedItem();
                    ventanaSelecSer.dispose();
                }
            }
        });
    }

    public static void crearSer() {
        int error = 0;
        ArrayList<String> terrenosVal = new ArrayList<String>();
        ArrayList<String> terrenosInval = new ArrayList<String>();
        ArrayList<Float> costos = new ArrayList<Float>();
        String costo;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            costo = modeloTabla.getValueAt(i, 2).toString();
            if (!costo.isEmpty() && costo != null) {
                if (costo.equals("N/A")) {
                    terrenosInval.add(modeloTabla.getValueAt(i, 0).toString());
                } else if (esCostoValido(costo)) {
                    terrenosVal.add(modeloTabla.getValueAt(i, 0).toString());
                    costos.add(Float.valueOf(costo));
                } else {
                    error = 2;
                    break;
                }
            } else {
                error = 1;
                break;
            }
        }

        switch (error) {
            case 0:
                indiceSer++;
                imagenElegida = false;
                listaSeres.add(new Ser(nombre, imgIcon, terrenosVal, terrenosInval, costos));
                ser.dispose();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Error, tienes filas vacías");
                break;

            case 2:
                JOptionPane.showMessageDialog(null, "Costos inválidos");
                break;
        }
    }

    public static boolean esCostoValido(String costo) {
        boolean esCostoValido = false;
        Pattern expresion = Pattern.compile("\\d*(\\.|\\d*)\\d+$");
        Matcher validarCosto = expresion.matcher(costo);
        if (validarCosto.matches()) {
            esCostoValido = true;
        }
        return esCostoValido;
    }

    public static void elegirImagen() {
        if (Archivo.elegirArchivo("Imagen")) {
            if (Archivo.esArchivoValido()) {
                imgIcon = Imagen.redimencionar(null, Cuadricula.ancho, Cuadricula.alto,
                        Imagen.dameImagenEnBytes(Archivo.dameArchivo()));
                imagenElegida = true;
            } else {
                JOptionPane.showMessageDialog(null, "Imagen inválida");
            }
        }
        imagenTerreno.setIcon(imgIcon);
    }

    public static void selecSer() {
        ventanaSelecSer = new JFrame();
        ventanaSelecSer.setLayout(null);
        ventanaSelecSer.setTitle(TITULO_VENTANA_ELEGIR_SER);
        ventanaSelecSer.setSize(400, 200);
        ventanaSelecSer.setResizable(Constante.esRedimensionable());
        ventanaSelecSer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        comboSeres = new JComboBox<Integer>();
        comboSeres.setBounds(10, 10, ventanaSelecSer.getWidth() / 2, 30);

        JLabel imagenSer = new JLabel();
        imagenSer.setBounds(10, 50, 135, 135);
        ventanaSelecSer.add(imagenSer);

        for (int i = 1; i <= indiceSer; i++) {
            comboSeres.addItem(i);
        }

        JLabel etiquetaSer = new JLabel();
        etiquetaSer.setBounds(30, 50, 300, 15);
        etiquetaSer.setFont(new java.awt.Font("Ubuntu", 3, 15));

        comboSeres.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent comboBox) {
                if (comboBox.getSource() == comboSeres) {
                    serElegido = (int) comboSeres.getSelectedItem();
                    imagenSer.setIcon(listaSeres.get(serElegido - 1).dameImagen());
                    etiquetaSer.setText(listaSeres.get(serElegido - 1).dameNombre());
                    ventanaSelecSer.add(etiquetaSer);
                }
            }
        });
        ventanaSelecSer.add(comboSeres);

        JButton btnSelecSer = new JButton("Aceptar");
        btnSelecSer.setBounds(50 + comboSeres.getWidth(), 10, 100, 30);
        eventosBoton(btnSelecSer, TITULO_VENTANA_ELEGIR_SER);
        ventanaSelecSer.add(btnSelecSer);

        ventanaSelecSer.setVisible(true);
    }
}
