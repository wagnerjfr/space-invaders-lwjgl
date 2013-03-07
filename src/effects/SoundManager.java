package effects;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.WaveData;


import static org.lwjgl.openal.AL10.*;


public class SoundManager {

	private int buffer, source;
	private SoundType type;
	
	public SoundManager(SoundType type) {
		this.type = type;
		setUp();
	}
	
	public static void create() {
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	protected void setUp() {
		WaveData data;
		try {
			data = WaveData.create(new BufferedInputStream(new FileInputStream(type.location)));
			buffer = alGenBuffers();
			alBufferData(buffer, data.format, data.data, data.samplerate);
			data.dispose();
			source = alGenSources();
			alSourcei(source, AL_BUFFER, buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void play() {
		alSourcePlay(source);
	}
	
	/**
	 * Delete sound buffer (not sure if it's necessary to call)
	 */
	protected void destroyBuffer() {
		alDeleteBuffers(buffer);
	}

	public static void destroy() {
		AL.destroy();
	}
}
