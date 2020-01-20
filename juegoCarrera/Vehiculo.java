package Juegos.juegoCarrera;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class Vehiculo {
	// nombre del piloto
	private String nombre;
	private int posicion;
	// usando la clase Color, uso color como cualidad del veh�culo
	private Color color;
	// Pista por donde va a ir el veh�culo
	private Pista p;
	private int numeroPista;
	// recogo en una Lista la posici�n en la que est� el veh�culo en las tiradas, para ver el n�mero de tiradas que necesita para llegar a la meta
	public List <Integer> tacometro  = new ArrayList <Integer>();
	private int yCoord;

	
	
	
	/**
	 * @param nombre
	 * @param posicion
	 * @param color
	 */
	public Vehiculo(String nombre, int numeroPista, Color color) {
		super();
		this.nombre = nombre;
		this.posicion = 0;
		this.color = color;
		this.numeroPista = numeroPista; // para al generar el veh�culo darle el n�mero de pista
		construirPista();
		// as� le damos la coordenada de la pista
		this.p = new Pista(yCoord);
	}

	
   
	public int construirPista () {
		yCoord = (numeroPista*PintaCarrera.getJframeHeight()/4);
		return yCoord;
		
	}
	
	@Override
	public String toString() {
		return "Vehiculo [nombre=" + nombre + ", posicion=" + posicion + ", color=" + color + "]";
	}

    /**
     * Cualidad que hace que avance
     */
	public boolean seMueve() {
		boolean seMueve = true;
		if(this.posicion < p.getLongitud()) {
			this.posicion += Utils.obtenerNumeroAleatorio(1, 6);
			for (int i = 0; i < p.getObstaculos().size(); i++) {
				if (p.getObstaculos().get(i).getPosicion() == posicion) {
					posicion += p.getObstaculos().get(i).getImpulso();
					System.out.println(this.nombre + " ha pisado un obstaculo de tipo " + p.getObstaculos().get(i).getNombre() + " en la posici�n " + p.getObstaculos().get(i).getPosicion());
					
					// uso el el condicional para meter los sonidos, uso el impulso para comparar, si es negativo, es una mancha
				if(p.getObstaculos().get(i).getImpulso() < 0) {
					SoundsRepository.getInstance().playSound(SoundsRepository.EFECTO_OIL);
				}
				else {
					SoundsRepository.getInstance().playSound(SoundsRepository.EFECTO_RAMPA);
				}
				}
				
			}
			
			//JOptionPane.showMessageDialog(null, "El veh�culo de: " + this.nombre + " est� en la posici�n: " + this.posicion);
			// Se me hace muy pesado con el JOptionPane que salga para cada tirada d�nde est� cada uno, por eso est� comentado
			
			this.tacometro.add(this.posicion); // Cuenta las posiciones en la que est� el veh�culo y la cantidad de tiradas que necesit� para llegar a la meta
		}
		else {
			seMueve = false;
			System.out.println("Es fin de carrera para " + this.nombre);
			// saca por pantalla quien va acabando la carrera
		}
		return seMueve;
		
		
	}
	/**
	 * Imprime el tac�metro
	 */
	public void imprimirTacometro () {
		System.out.println(tacometro.toString());
	}
	
	/**
	 * M�todo abstracto de pintar el veh�culo entero "paint" 
	 * @param g
	 */
	public abstract void paint(Graphics g);
	
	/**
	 * y s�lo el veh�culo con "paintMe"
	 * @param g
	 */
	public abstract void paintMe(Graphics g);

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}



	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	/**
	 * @return the posicion
	 */
	public int getPosicion() {
		return posicion;
	}



	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}



	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}



	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}


	/**
	 * @return the p
	 */
	public Pista getP() {
		return p;
	}


	/**
	 * @param p the p to set
	 */
	public void setP(Pista p) {
		this.p = p;
	}


	/**
	 * @return the tacometro
	 */
	public List<Integer> getTacometro() {
		return tacometro;
	}


	/**
	 * @param tacometro the tacometro to set
	 */
	public void setTacometro(List<Integer> tacometro) {
		this.tacometro = tacometro;
	}

	




	/**
	 * @return the numeroPista
	 */
	public int getNumeroPista() {
		return numeroPista;
	}




	/**
	 * @param numeroPista the numeroPista to set
	 */
	public void setNumeroPista(int numeroPista) {
		this.numeroPista = numeroPista;
	}



	/**
	 * @return the yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}



	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	
	
		
	

	

	

}
