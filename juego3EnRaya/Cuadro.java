package Juegos.juego3EnRaya;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * @author anita
 *En esta clase se crea la clase del objeto "cuadro"
 *que ir� pintado en el Canvas
 */
public class Cuadro {
	// Coordenadas de cada cuadro sobre el tablero
	private int xCoord, yCoord;
	// Coordenadas del pixel superior izquierdo de cada cuadro que se representar�
	//en el Canvas
	private int esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY;
	// Ancho y alto del cuadro
	private int ancho, alto;
	// Variable booleana que cambiar� cuando se haga click en un cuadro
	// del Paso 9
	// Esta variable ya no se necesita desde el paso 10
	//      private boolean clickHecho = false;
	
	// Jugador que ha hecho click sobre este cuadro, pasando el cuadro a ser
	// de su propiedad. El valor 0 indica que el cuadro no pertenece 
	// a ning�n jugador
	private int jugadorPropietario = 0;

	/**
	 * @param xCoord
	 * @param yCoord
	 */
	public Cuadro(int xCoord, int yCoord) {
		super();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	/**
	 * Utilizo este m�todo para que cada cuadro pueda pintarse a s� mismo.
	 * Necesitamos un objeto de tipo Graphics, que nos permita acceder a la memoria de video
	 * @param g
	 */
	public void paint (Graphics g) {
		// Obtengo el ancho y alto de cada cuadro, obteni�ndolos del Canvas
		// principal del juego, dividiendo el ancho y alto del Canvas entre 3
		ancho = TresEnRaya.getInstance().getWidth() / 3;
		alto = TresEnRaya.getInstance().getHeight() / 3;
		esquinaSuperiorIzquierdaX = this.xCoord * ancho;
		esquinaSuperiorIzquierdaY = this.yCoord * alto;
		
		// Lo siguiente pinta el borde de cada cuadro
		g.setColor(Color.white);
		g.drawRect(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY, ancho, alto);
		
		// Pinta im�genes vectoriales sobre el cuadro sobre el que se hace
		//click dentro del Paso 9
		//if (this.clickHecho) { 
		// En el Paso 10, ya no necesito s�lo saber que hacen click, sino saber qui�n,
		// por lo que implementamos esa comprobaci�n en el m�todo paint
			//pintaImagenesVectoriales(g);
		pintaImagenesMapasDeBits(g);
			
		//}
	}
	
	/**
	 * Paso 9, que pinta c�rculos
	 * Paso 10, incorpora a este m�todo que seg�n el jugador se pinte una X o un c�rculo
	 * @param g
	 */
	private void pintaImagenesVectoriales (Graphics g) {
		// En el Paso 10, dependiendo del propietario del cuadro, se pinta una cosa u otra
		if (this.jugadorPropietario == TresEnRaya.JUGADOR_1) { // Comprueba el jugador, para 1 pinta una cruz
			// Para pintar una cruz, pintamos dos l�neas que se cruzan
			g.drawLine(this.esquinaSuperiorIzquierdaX, this.esquinaSuperiorIzquierdaY, this.esquinaSuperiorIzquierdaX + this.ancho, this.esquinaSuperiorIzquierdaY + alto);
			g.drawLine(this.esquinaSuperiorIzquierdaX, this.esquinaSuperiorIzquierdaY + this.alto, this.esquinaSuperiorIzquierdaX + this.ancho, this.esquinaSuperiorIzquierdaY);
		}
		// Si es el jugador 2, reutilizamos el c�rculo
		if (this.jugadorPropietario == TresEnRaya.JUGADOR_2) {
			// Dentro del Paso 9 va a pintar un c�rculo cuando se haga 
			// click en un cuadro
			g.drawOval(this.esquinaSuperiorIzquierdaX, this.esquinaSuperiorIzquierdaY, this.ancho, this.alto);
		
		}
		}
	/**
	 * PASO 11 DONDE SE CARGAN IM�GENES
	 * @param g
	 */
	private void pintaImagenesMapasDeBits (Graphics g) {
		// Ahora, dependiendo del jugador propietario se pintar� una imagen u otra
		BufferedImage imagenAPintar = null;
		if(this.jugadorPropietario == TresEnRaya.JUGADOR_1) {
			imagenAPintar = (BufferedImage) SpritesRepository.getInstance().getSprite(SpritesRepository.IMAGEN_JUGADOR_1);
		}
		if(this.jugadorPropietario == TresEnRaya.JUGADOR_2) {
			imagenAPintar = (BufferedImage) SpritesRepository.getInstance().getSprite(SpritesRepository.IMAGEN_JUGADOR_2);
		}
		//Una vez que sabemos qu� imgen se pinta, la sacamos por pantalla
		if (imagenAPintar != null) {
			int x = this.esquinaSuperiorIzquierdaX + this.ancho / 2 - imagenAPintar.getWidth() / 2;
			int y = this.esquinaSuperiorIzquierdaY + this.alto / 2 - imagenAPintar.getHeight() / 2 ;
			g.drawImage(imagenAPintar, x, y, null);
		}
	}
	
	/**
	 * Paso 8
	 * Este m�todo detecta si el click se ha hecho dentro de las coordenadas del cuadro
	 * en el que se hace click, es decir, el cuadro que representa ese objeto sobre
	 * el que estamos
	 * @param xClick
	 * @param yClick
	 * @return
	 */
	public boolean seHaHechoClickSobreCuadro (int xClick, int yClick) {
		// Hay que comprobar si las coordenadas est�n dentro del espacio ocupado
		//por el cuadro
		if (xClick > this.esquinaSuperiorIzquierdaX && xClick < (esquinaSuperiorIzquierdaX + ancho) // comprueba si la coord X est� dentro del ancho
				&&
			yClick > this.esquinaSuperiorIzquierdaY && yClick < (esquinaSuperiorIzquierdaY + alto)) // comprueba si la coord Y est� dentro del alto 
			{
			return true;
			
		}
		return false;
	}
	
	/**
	 * En el paso 10, hacemos propietario del cuadro al jugador que hace click
	 * pas�ndole su dibujo
	 * Este m�todo se ejecuta al hacer click sobre el cuadro
	 * Paso 8
	 */
	public void click ( int jugador) {// le pasamos la variable jugador, para comprobar turno, Paso 10
		if (this.jugadorPropietario == 0) {
			this.jugadorPropietario = jugador;
			
		}
		// Actualizo la matriz de jugadas para reflejar la nueva jugada
		TresEnRaya.getInstance().getMatrizJugadas()[this.xCoord][this.yCoord] = jugador;
		
		
		//this.clickHecho = true; // Actualizamos la bandera con el Paso 9
		// Va a obligar a repintar el Canvas en cada click con el Paso 9
		TresEnRaya.getInstance().repaint();
		TresEnRaya.getInstance().revalidate();
		
		// Imprimo matriz de jugadas
		System.out.println("Estado del juego");
		for (int i = 0; i < TresEnRaya.getInstance().getMatrizJugadas().length; i++) {
			for (int j = 0; j < TresEnRaya.getInstance().getMatrizJugadas()[i].length; j++) {
				System.out.print(TresEnRaya.getInstance().getMatrizJugadas()[i][j] + "\t");
			}
			System.out.println();
			
		}
		
		
		// Imprime en consola d�nde se hizo click
		System.out.println("Click sobre el cuadro " + this.toString());
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Cuadro [xCoord=" + xCoord + ", yCoord=" + yCoord + "," + 
				", jugadorPropietario=" + jugadorPropietario + "]";
	}

	/**
	 * @return the xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * @param xCoord the xCoord to set
	 */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
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

	/**
	 * @return the esquinaSuperiorIzquierdaX
	 */
	public int getEsquinaSuperiorIzquierdaX() {
		return esquinaSuperiorIzquierdaX;
	}

	/**
	 * @param esquinaSuperiorIzquierdaX the esquinaSuperiorIzquierdaX to set
	 */
	public void setEsquinaSuperiorIzquierdaX(int esquinaSuperiorIzquierdaX) {
		this.esquinaSuperiorIzquierdaX = esquinaSuperiorIzquierdaX;
	}

	/**
	 * @return the esquinaSuperiorIzquierdaY
	 */
	public int getEsquinaSuperiorIzquierdaY() {
		return esquinaSuperiorIzquierdaY;
	}

	/**
	 * @param esquinaSuperiorIzquierdaY the esquinaSuperiorIzquierdaY to set
	 */
	public void setEsquinaSuperiorIzquierdaY(int esquinaSuperiorIzquierdaY) {
		this.esquinaSuperiorIzquierdaY = esquinaSuperiorIzquierdaY;
	}

	/**
	 * @return the ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}
	
	
	
	
	
	

}
