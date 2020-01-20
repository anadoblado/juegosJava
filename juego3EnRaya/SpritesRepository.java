package Juegos.juego3EnRaya;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

// Paso 11 donde se van a cargar im�genes desde el HashMap

/**
 * Esta clase es para el almac�n de im�genes
 * @author anita
 *
 * @param <BufferedImage>
 */
public class SpritesRepository {
	// En el almac�n de im�genes se mapear� con un objeto de tipo HashMap<String, BufferedImage>
	// En este tipo de HashMap todas las claves ser�n de tipo String y todos los objetos
	// almacenados de tipo BufferedImage
	private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	
	// Variable principal del patr�n Singleton
	private static SpritesRepository instance = null;
	
	// Carpeta en la que se encuentran los recursos: im�genes, sonidos
	private static String RESOURCES_FOLDER = "../resources/images/";
	
	// Im�genes que van a usarse en el juego en el paso 13, que hace la carga de 
	// recursos antes de empezar
	public static String IMAGEN_JUGADOR_1 = "r2d2_a_-_copia.png";
	public static String IMAGEN_JUGADOR_2 = "bb8_a_-_copia.png";

	/**
	 * 
	 */
	public SpritesRepository() {
		this.getSprite(IMAGEN_JUGADOR_1);
		this.getSprite(IMAGEN_JUGADOR_2);
	}
	
	/**
	 * M�todo principal del patr�n Singleton
	 * @return
	 */
	public static SpritesRepository getInstance() {
		if (instance == null) {
			instance = new SpritesRepository();
		}
		return instance;
	}
	
	/**
	 * Carga un recurso del disco duro, dentro de un objeto de tipo BufferedImage
	 * @param resourceName
	 * @return
	 */
	private BufferedImage loadImage(String resourceName) {
		// Localiza el recurso objeto de tipo URL
		URL url = null;
		
		try {
			url = getClass().getResource(resourceName);
			return ImageIO.read(url);
		}catch (Exception e) {
			// Aqu� capturamos el error y vemos qu� error es
			System.out.println("No se pudo cargar la imagen " + resourceName + " de " + url);
			System.out.println("El error fue: " + e.getClass().getName()+" "+e.getMessage());
			System.exit(0); //Fin del programa
		}
		return null; // A esta l�nea se llega si no se puede cargar el recurso
	}
	
	/**
	 * M�todo para obtener una imagen
	 * @param resourceName
	 * @return
	 */
	public BufferedImage getSprite(String resourceName) {
		// En la primera instancia intentamos obtener el objeto BufferedImage a partir del HashMap
		BufferedImage img = sprites.get(resourceName);
		
		// En caso de que el objeto BufferedImage no exista dentro del HashMap
		// se carga desde el discoduro
		if (img == null) {
			img = loadImage(RESOURCES_FOLDER + resourceName);
			// Una vez eue se carga en la memoria, lo agrego al HashMap
			// as� no hay que volver a buscarlo fuera del HashMap. Como
			// clave del objeto en el HashMap utilizo el nombre del archivo
			sprites.put(resourceName, img);
		}
		return img;
	}
	
	
}
