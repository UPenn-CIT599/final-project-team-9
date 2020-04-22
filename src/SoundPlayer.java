import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

public class SoundPlayer {

	private String filename;
	private Clip clip;
	
	public SoundPlayer() {
		
		if(Panel.level == 1) {
			this.filename = "Giant.wav";
		}
		else if(Panel.level == 2) {
			this.filename = "Kul_Riddim.wav";
		}
		else if(Panel.level == 3) { 
			this.filename = "Night Owl.wav";
		}
		else if(Panel.level == 0) {
			this.filename = "Neck_Pillow.wav";
		}
		else if(Panel.level == 4) {
			this.filename = "Big_Explosion.wav";
		}
		else if(Panel.level == 5) {
			this.filename = "game_over.wav";
		}
		else if(Panel.level == 6) {
			this.filename = "life lost.wav";
		}
		else if(Panel.level == 7) {
			this.filename = "Crash.wav";
		}
		else if(Panel.level == 8) {
			this.filename = "Level Up.wav";
		}
		
		
	}

	public void playSound() {

		File musicLocation = new File(filename);

		if (musicLocation.exists()) {
			try {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicLocation);
				AudioFormat format = audioInput.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				this.clip = (Clip) AudioSystem.getLine(info);
				this.clip.open(audioInput);
				this.clip.start();
				this.clip.loop(Clip.LOOP_CONTINUOUSLY);
				
			} catch(Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	public void playSoundEffect() {

		File musicLocation = new File(filename);

		if (musicLocation.exists()) {
			try {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicLocation);
				AudioFormat format = audioInput.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				this.clip = (Clip) AudioSystem.getLine(info);
				this.clip.open(audioInput);
				this.clip.start();
				
			} catch(Exception e) {
				e.printStackTrace();
			}

		}
	}
	public void stop() {
		this.clip.stop();
	}
}
