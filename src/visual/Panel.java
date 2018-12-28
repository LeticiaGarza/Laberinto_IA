package visual;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Constante;
import sistema.Terreno;

public class Panel {
	static JPanel cordenada;
	public static JPanel cuadricula;
	
	static JLabel lblIdTerreno;
	static JLabel lblTipoTerreno;
	static JLabel lblPosicion;
	
	public static void inicializar(){		
		crearPanelCuadricula();
		crearPanelCordenada();
	}
	
	public static void crearPanelCuadricula(){
		cuadricula = new JPanel();		
		cuadricula.setLayout(null);
		cuadricula.setBounds(0, 50, Constante.dameAnchoCuadricula(), Constante.dameAltoCuadricula());
		cuadricula.addMouseListener(new EventosRaton());
		cuadricula.setFocusTraversalKeysEnabled(true);
	}
	
	public static void crearPanelCordenada(){
		Font formatoLetra = new Font("Arial",Font.BOLD,20);
		cordenada = new JPanel();	
		cordenada.setLayout(null);
		cordenada.setLocation(Constante.dameAnchoCuadricula(),50);
		cordenada.setSize(Constante.dameMargenAnchoCuadricula(),250);		
				
		lblIdTerreno = new JLabel();
		lblIdTerreno.setBounds(30,30,150,30);
		lblIdTerreno.setFont(formatoLetra);
		cordenada.add(lblIdTerreno);			
		
		lblTipoTerreno = new JLabel();
		lblTipoTerreno.setBounds(30,100,250,30);
		lblTipoTerreno.setFont(formatoLetra);
		cordenada.add(lblTipoTerreno);
				
		lblPosicion = new JLabel();
		lblPosicion.setBounds(30,180,150,30);
		lblPosicion.setFont(formatoLetra);
		cordenada.add(lblPosicion);		
	}
	
	public static void mostrarDatosCordenada(Terreno terreno){
		lblIdTerreno.setText("ID: " + terreno.dameId());
		lblTipoTerreno.setText("Tipo terreno: " + terreno.dameTipo());
		lblPosicion.setText("Posicion: "+terreno.damePosicion());
	}
	
	public static void pintar(Graphics grafico){
		grafico.drawString("Hola", 100, 100);
	}
	
	public static void paint(Graphics grafico){
		Graphics2D circulo = (Graphics2D)grafico;
		circulo.setStroke(new BasicStroke(5.f));
		circulo.setPaint(Color.RED);
		circulo.drawOval(100, 100, 50, 50);
	}
}
