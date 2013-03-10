package effects;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public class Sound {
	
	private Audio wavEffect;
	
	public Sound(SoundType type) {
		init(type);
	}
	
	//Nao eh usado..
	public static void create() {
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public void init(SoundType type) {
		try {
			wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(type.location));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void play(SoundType type) {
		wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
		
		// polling is required to allow streaming to get a chance to
		// queue buffers.
		SoundStore.get().poll(0);
	}
	
	public static void destroy() {
		AL.destroy();
	}
}
