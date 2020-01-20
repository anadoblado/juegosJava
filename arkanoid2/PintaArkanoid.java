package Juegos.arkanoid2;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PintaArkanoid extends Canvas {
	//Con JFrame se har� la ventana que albergar� el juego
	JFrame ventana = new JFrame("Arkanoid");

	// Con variables estat�ticas, ya que las queremos fijas, indicamos el
	//ancho y alto de la ventana
	private static final int JFRAME_WIDTH = 500;
	private static final int JFRAME_HEIGHT = 800;
	public static final int SPEED = 30;
	//Vamos a crear una variable para establecer la instancia del patr�n Singleton
	private static PintaArkanoid instance = null;
	public BufferedImage nave = null;
	private BufferStrategy strategy;
	
	
	
	
	//ObjetoAPintar ladrillo1 = new Ladrillo();
	//ObjetoAPintar ladrillo2 = new Ladrillo();
	//ObjetoAPintar ladrillo3 = new Ladrillo();
	//ObjetoAPintar ladrillo4 = new Ladrillo();
	
	
	
	public List<ObjetoAPintar> world = new ArrayList<ObjetoAPintar>();
	Pelota ball = null;
	
	
	
	

	/**
	 * Constructor para inicializar la ventana y verla
	 */
	public PintaArkanoid() {
		// Obtengo la referencia al panel principal, despu�s de haber implementado
		//el Canvas
		JPanel panel = (JPanel) ventana.getContentPane();
		// Creo una plantilla en el panel, para poder ponerle el Canvas bordes
		panel.setLayout(new BorderLayout());
		// Se agrega el Canvas al Panel
		panel.add(this, BorderLayout.CENTER);

		//As� le damos las dimensiones a la venta, no uso el constructor
		//que genera con el Source porque as� le doy las dimensiones a la
		//ventana
		ventana.setBounds(0, 0, JFRAME_WIDTH, JFRAME_HEIGHT);

		//Agrego un windowListener, pero el adaptador para solo 
		//coger un m�todo de todos los que implementa el windowListener
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarAplicacion();
			}
		});
		// Para que la ventana est� en visible
		ventana.setVisible(true);
		ventana.setResizable(true);
		ventana.setIgnoreRepaint(true);
		this.createBufferStrategy(2);
		strategy = getBufferStrategy();
		// El foco de Windows ir� al Canvas, para que directamente podamos interactuar
		//con el juego mediante teclado
		this.requestFocus();
		
		
		
	}
	
	public void initWorld() {
		Pelota ball = new Pelota("ball");
		Nave nave = new Nave();
		int coordX = 10;
		int coordY = 20;
		for (int i = 0; i < 6; i++) {
		coordY += 20;
			for (int j = 0; j < 9; j++) {
				if(i == 0) {
					Ladrillo brick = new Ladrillo();
					coordX += brick.ANCHO_LADRILLO + 2;
					brick.setColor(Color.cyan);
					brick.setxCoord(coordX);
					brick.setyCoord(coordY);
					world.add(brick);
				}
				if(i == 1) {
					Ladrillo brick = new Ladrillo();
					coordX += brick.ANCHO_LADRILLO + 2;
					brick.setColor(Color.yellow);
					brick.setxCoord(coordX);
					brick.setyCoord(coordY);
					world.add(brick);
				}
				if (i == 2) {
					Ladrillo brick = new Ladrillo();
					coordX += brick.ANCHO_LADRILLO + 2;
					brick.setColor(Color.magenta);
					brick.setxCoord(coordX);
					brick.setyCoord(coordY);
					world.add(brick);
				}
				if (i == 3) {
					Ladrillo brick = new Ladrillo();
					coordX += brick.ANCHO_LADRILLO + 2;
					brick.setColor(Color.green);
					brick.setxCoord(coordX);
					brick.setyCoord(coordY);
					world.add(brick);
				}
				if (i == 4) {
					Ladrillo brick = new Ladrillo();
					coordX += brick.ANCHO_LADRILLO + 2;
					brick.setColor(Color.ORANGE);
					brick.setxCoord(coordX);
					brick.setyCoord(coordY);
					world.add(brick);
				}
				if(i == 5) {
					Ladrillo brick = new Ladrillo();
					coordX += brick.ANCHO_LADRILLO + 2;
					brick.setColor(Color.blue);
					brick.setxCoord(coordX);
					brick.setyCoord(coordY);
					world.add(brick);
				}
				

			}
			coordX = 10;
		}
		world.add(ball);
		world.add(nave);
		
	}
	

	/**
	 * M�todos para devolver la instancia del patr�n Singleton
	 * @return
	 */
	public static PintaArkanoid getInstance () {
		if (instance == null) {
			instance = new PintaArkanoid ();
		}
		return instance;
	}
	/**
	 * M�todo para antes de cerrar la ventana con la equis de la esquina, le pregunta al usuario
	 */
	private void cerrarAplicacion() {
		// Un array de opciones, que nos dar� los nombres de los botones
		String [] opciones = {"Aceptar", "Cancelar"};
		// Genera la ventana de di�logo, con los mensajes y las opciones
		int eleccion = JOptionPane.showOptionDialog(ventana, "�Seguro que quieres cerrar la ventana?", "Salir de la aplicaci�n",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		// Si la elecci�n es Aceptar, le decimos que se cierre
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	/**
	 * M�todo para pintar el Canvas
	 */
	public void paint () {
		//System.out.println("Estoy en paint()");
		
		// Pinto el rect�ngulo tan grande como el Canvas
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		for (ObjetoAPintar obj : world) {
			obj.paint(g);
			
		}
		
		//if (nave==null) {
			//nave = loadImage("../res/nave-50x15.png");
		//}
		//g.drawImage(nave, 250, 720,this);
		strategy.show();
		
		
		
	}
	
	public void upDateWorld() {
		for(ObjetoAPintar obj : this.world) {
			obj.seMueve();
		};
	}
	
	
	/**
	 * M�todo donde se ejecuta el movimiento del juego
	 */
	public void game() {
		initWorld();;
		while (this.isVisible()) {
			upDateWorld();
			//ball.seMueve();
			//ball.paint(this.getGraphics());
			paint();
			try { 
				 Thread.sleep(SPEED);
			} catch (InterruptedException e) {}
			
			
		}
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public BufferedImage loadImage(String nombre) {
		URL url=null;
		try {
			url = getClass().getResource(nombre);
			return ImageIO.read(url);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se pudo cargar la imagen " + nombre +" de "+url);
			System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
			System.exit(0);
			return null;
		}
	}



	public static void main(String[] args) {
		PintaArkanoid.getInstance().game();

	}

	/**
	 * @return the ventana
	 */
	public JFrame getVentana() {
		return ventana;
	}

	/**
	 * @param ventana the ventana to set
	 */
	public void setVentana(JFrame ventana) {
		this.ventana = ventana;
	}

	/**
	 * @return the jframeWidth
	 */
	public static int getJframeWidth() {
		return JFRAME_WIDTH;
	}

	/**
	 * @return the jframeHeight
	 */
	public static int getJframeHeight() {
		return JFRAME_HEIGHT;
	}

	/**
	 * @param instance the instance to set
	 */
	public static void setInstance(PintaArkanoid instance) {
		PintaArkanoid.instance = instance;
	}
	

}
