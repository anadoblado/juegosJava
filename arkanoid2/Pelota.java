package Juegos.arkanoid2;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends ObjetoAPintar {
	
	protected int vx = 5;
	protected int vy = 5;
	protected static final int ANCHO_PELOTA = 15;
	protected static final int ALTO_PELOTA = 15;

	private static Pelota instance = null;
	
	public Pelota(String nombre) {
		super();
		this.xCoord = 200;
		this.yCoord = 200;
	}

	@Override
	public void paint(Graphics g) {
		
		g.setColor(Color.gray);
		g.fillOval(xCoord, yCoord, ANCHO_PELOTA, ALTO_PELOTA);
		
		
	}
	
	public void seMueve() {
		
		xCoord += vx;
				//(int)((Math.random()-(1/2))*PintaArkanoid.getJframeWidth()/2);
		yCoord += vy;
				//(int)((Math.random()-(1/2))*PintaArkanoid.getJframeHeight()/2);
		
		if (xCoord>PintaArkanoid.getInstance().getWidth() - ANCHO_PELOTA|| xCoord < 0) {
			vx = -vx  ;
			
		}
		if (yCoord>PintaArkanoid.getInstance().getHeight() - ALTO_PELOTA || yCoord < 0) {
			vy = -vy;
			
		}
		
	}
	

	@Override
	public String toString() {
		return "Pelota [nombre=" + nombre + ", xCoord=" + xCoord + ", yCoord=" + yCoord + ", ancho=" + ancho + ", alto="
				+ alto + ", toString()=" + super.toString() + ", getNombre()=" + getNombre() + ", getxCoord()="
				+ getxCoord() + ", getyCoord()=" + getyCoord() + ", getAncho()=" + getAncho() + ", getAlto()="
				+ getAlto() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	

	/**
	 * 
	 * @return
	 */
	public static Pelota getInstance() {
		if (instance == null) {
			instance = new Pelota (nombre);
		}
		return instance;
	}

	/**
	 * 
	 * @param instance
	 */
	public static void setInstance(Pelota instance) {
		Pelota.instance = instance;
	}
	

}
