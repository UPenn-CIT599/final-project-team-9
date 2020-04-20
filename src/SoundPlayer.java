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
	
	public SoundPlayer(int level) {
		if(level == 0) {
			this.filename = "Neck_Pillow.wav";
		}
		else if(level == 1) {
			this.filename = "Giant.wav";
		}
		else if(level == 2) {
			this.filename = "Kul_Riddim.wav";
		}
		else {
			this.filename = "Night Owl.wav";
		}
		
	}

	public void Sound() {

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

				/*JOptionPane.showMessageDialog(null, "Press OK to pause");
				long clipTimePosition = clip.getMicrosecondPosition();
				clip.stop();

				JOptionPane.showMessageDialog(null, "Press OK to resume");
				clip.setMicrosecondPosition(clipTimePosition);
				clip.start();

				JOptionPane.showMessageDialog(null, "Press OK to stop playing");*/
				
			} catch(Exception e) {
				e.printStackTrace();
			}

		}
	}
	public void stop() {
		this.clip.stop();
	}
}
