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
         URL audioUrl = getClass().getResource("/resources/"+pathway);
         if(audioUrl == null)
         {
            System.out.println("Audio file not found: " + pathway);
            System.out.println("Make sure audio file is in .wav format and path is specified properly.");
            return;
         }
         AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
         Clip clip = AudioSystem.getClip();
         clip.open(audioStream);
         FloatControl setVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
         setVolume.setValue(volume);
         clip.start();
         
            //clip.stop();
      }
      catch(IOException | LineUnavailableException | UnsupportedAudioFileException e)
      {
         System.out.println("Run time error when adding audio file "+pathway);
         System.out.println("Make sure audio file is in .wav format and path is specified properly. ");
         e.printStackTrace();
      }
   }
}
