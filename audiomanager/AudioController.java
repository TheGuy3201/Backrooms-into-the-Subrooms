package audiomanager;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioController {
    //ALLOWS AUDIO TO BE PLAYED
   public void playSFX(String pathway, float volume)
   {
      try
      {
         URL audioUrl = getClass().getResource("/resources/sounds/"+pathway);
         if(audioUrl == null)
         {
            System.err.println("Audio file not found: " + pathway);
            return;
         }
         AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
         Clip clip = AudioSystem.getClip();
         clip.open(audioStream);
         FloatControl setVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
         setVolume.setValue(volume);
         clip.start();
      }
      catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
      {
         System.err.println("Error playing sound: " + e.getMessage());
      }
   }
}
