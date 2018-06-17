import java.io.File;
import javax.sound.sampled.*;

public class Sound {


	/*
	playClip
	plays a specified .wav file
	*/
    public static void playClip(File file) {
		
        try {
            final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
            clip.addLineListener(new LineListener() {
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });

            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }

    }

}