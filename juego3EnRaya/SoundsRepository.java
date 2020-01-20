package Juegos.juego3EnRaya;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;

/**
 * PASO 11 PARA CARGAR SONIDOS
 * @author anita
 *
 */
public class SoundsRepository {
	// Almac�n de sonidos, se trata de un HashMap que tiene String como "claves" de los objetos que son sus nombres
	// y "AudioClip" como objetos
	private HashMap<String, AudioClip> sounds = new HashMap<String, AudioClip>();
	
	// Variable principal del patr�n Singleton
	private static SoundsRepository instance = null;
	
	// Carpeta con los recursos de sonido
	private static String RESOURCES_FOLDER = "../resources/sounds/";
	
	//Fijamos los sonidos que se van a utilizar en el juego
	public static String MUSICA_DE_FONDO = "imperial_march.wav";
	public static String EFECTO_JUGADOR_1 = "R2D2 1.wav";
	public static String EFECTO_JUGADOR_2 = "bb8_beep_beep.wav";

	/**
	 * Constructor por defecto
	 */
	public SoundsRepository() {
		// lo creamos vacio, pero en el paso 13 lo completamos para la carga
		this.getAudioClip(MUSICA_DE_FONDO);
		this.getAudioClip(EFECTO_JUGADOR_1);
		this.getAudioClip(EFECTO_JUGADOR_2);
		
	}
	
	/**
	 * M�todo principal del patr�n Singleton
	 * @return
	 */
	public static SoundsRepository getInstance() {
		if (instance == null) {
			instance = new SoundsRepository();
		}
		return instance;
	}
	
	/**
	 * Carga un sonido en forma de AudioClip desde el disco duro
	 * @param name
	 * @return
	 */
	private AudioClip loadResource(String name) {
		URL url = null;
		url = getClass().getResource(name);
		return Applet.newAudioClip(url);
	}
	
	/**
	 * Obtiene el archivo de audio, AudioClip, del HashMap, si no lo encuentra all�
	 * va a buscarlo al disco duro y lo guarda en el HashMap para otras cargas
	 * @param resourceName
	 * @return
	 */
	private AudioClip getAudioClip(String resourceName) {
		// En primera instancia intentamos obtener el objeto AudioClip a partir del HashMap
		AudioClip clip = sounds.get(resourceName);
		
		// En caso de que no exista en el HashMap, se carga desde el disco duro
		if(clip == null) {
			clip = loadResource(RESOURCES_FOLDER + resourceName);
			// Una vez que cargo el recurso en la memoria, lo agrego al HashMap, as� no habr� que volver a 
			// buscarlo en el disco duro. Como "clave" del objeto para buscarlo en el HashMap utilizo el nombre del fichero
			sounds.put(resourceName, clip);
			
		}
		return clip;
	}
	
	/**
	 * Lanza un sonido, el sonido solo se oye una vez
	 * @param name
	 */
	public void playSound (final String name) {
		getAudioClip(name).play();
	}
	
	/**
	 * Lanza el sonido en bucle
	 * @param name
	 */
	public void loopSound (final String name) {
		getAudioClip(name).loop();
	}
	
	
	
	
}
