package engine.actionperformed;

import engine.AdventureGameConstructor;
import static engine.AdventureGameConstructor.aC;
import static engine.AdventureGameConstructor.cC;
import static engine.AdventureGameConstructor.gL;
import static engine.AdventureGameConstructor.iA;
import static engine.AdventureGameConstructor.storyTextArea;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceController implements ActionListener {

   private final AdventureGameConstructor game;
   
   public ChoiceController(AdventureGameConstructor game) {
      this.game = game;
   }
    //CHANGES THE CHOICES DISPLAYED/AVAILABLE IN AREA
   @Override
   public void actionPerformed(ActionEvent e)
   {
      Graphics2D b = game.buffer.createGraphics();
      if(e.getSource() == game.choiceComboBox)
      {
         String selected = game.choiceComboBox.getSelectedItem().toString();
         if(selected.equals("Visit your friends in ETERNAL PAIN?"))
            gL.outside();
            
         if(selected.equals("Leave, and go touch some grass")||selected.equals("FEEL OUR PAIN")||selected.equals("FEEL IMPRISONNED")||selected.equals("Bash your head against the wall")||selected.equals("Give up LET US CONJOIN IN ONE WE ARE THE UNITED DEMONS OF THE SUBROOMS"))
            game.exit(b, "Thank you for freeing yourself from torment");
            
         if(selected.equals("Use the sound weapon against yourself"))
         {
            iA.usedItem("Sound Weapon", "You freed yourself, all it took was the courage to do it...");
            game.exit(b, "Wow that was a loud death, thank you for dying");
         }
            
         if(selected.equals("Accept your death?"))
            System.exit(0);
            
         if(selected.equals("You have NO CHOICES")||selected.equals("go back")||selected.equals("Go down left door(level 0)"))
            gL.level0_ENT();
            
         if(selected.equals("Walk left")||selected.equals("Walk back"))
            gL.level0_hallway1();
            
         if(selected.equals("Walk forward")||selected.equals("Go Back"))
            gL.level0_hallway2();
            
         if(selected.equals("Take the scissors"))
            iA.takeItem("Scissors");
            
         if(selected.equals("Go through the opening")||selected.equals("Go through the way you came"))
            gL.level0_hallway2_2();
            
         if(selected.equals("Go down Left hall"))
            gL.level0_hallway2_4();
            
         if(selected.equals("Go down Right hall")||selected.equals("Go back down the hallway"))
            gL.level0_hallway2_6();
            
         if(selected.equals("Go through the far hallway"))
            gL.level0_hallway2_8();
            
         if(selected.equals("Go through the narrow hallway"))
            gL.level0_hallway2_9();
            
         if(selected.equals("Go Forward"))
            gL.level0_hallway3();
            
         if(selected.equals("Take the Paper"))
         {
            gL.Note();
            iA.takeItem("Paper");
         }
         
         if(selected.equals("Keep following the %$# PRISON &$#"))
            gL.level0_hallway4();
            
         if(selected.equals("Go to previous area"))
            gL.level0_hallway3();
            
         if(selected.equals("Look for another way out"))
         {
            storyTextArea.setText("Good Luck with that...");
            cC.creatureAttack(b, "stalker.png",300,300, "Jumpscare_1.wav", 8);
         }
         
         if(selected.equals("Grab the mini satelite dish") && iA.soundWeaponAvailable == true)
         {
            iA.takeItem("Sound Weapon");
            storyTextArea.setText(storyTextArea.getText()+"What does this thing do?? Maybe I can get a signal out to get help!");
            String[] newItems = {" ","Test the item","Go back down the hallway"};
            game.setChoices(newItems);
         }
         
         if(selected.equals("Test the item"))
            iA.usedItem("Sound Weapon", "You used the sound weapon");
            
         if(selected.equals("Use the Sound Weapon against the wall"))
         {
            gL.level0_hallway2_2();
            iA.usedItem("Sound Weapon", "You used the sound weapon\n \n \nYou ran out of the room as quick as you could when the wall broke down, you could go back in and use the sound weapon again if needed");
         }
         
         if(selected.equals("Use the sound weapon on the wall"))
            gL.peeOnFloor();
            
         if(selected.equals("Cut a large hole in wallpaper on far left pillar")||selected.equals("Cut a large hole in wallpaper on far right pillar"))
            cC.creatureAttack(b, "smiler.jfif", (720/3), (610/3),"Jumpscare_1.wav",11);
            
         if(selected.equals("Cut a large hole in wallpaper on mid right pillar"))
            cC.creatureAttack(b, "zombie thing.jfif", (720/3), (610/3),"Jumpscare_1.wav", 6);
            
         if(selected.equals("Continue to Exit")||selected.equals("Cut a large hole in wallpaper on mid left pillar")) //lvl0 exit
            gL.level0_hallwayEXT();
            
         if(selected.equals("Cut a large hole in wallpaper on front left pillar")||selected.equals("Cut a large hole in wallpaper on front right pillar"))
            cC.creatureAttack(b, "The Converted.jfif", (720/3), (610/3),"Jumpscare_1.wav", 28);
            
         if(selected.equals("Go through it(you wont be able to return back here)")||selected.equals("Go back to where you teleported")||selected.equals("Go down right door(level 2)"))
            gL.level2_hallwayENT();
            
         if(selected.equals("Continue forward")||selected.equals("Return to where you were last")) 
            gL.level2_hallway2();
            
         if(selected.equals("Continue backward"))
            gL.level2_hallway1();
            
         if(selected.equals("Try opening the trapdoor"))
         {
            aC.playSFX("doorRattle.wav", 3f);
            storyTextArea.setText(storyTextArea.getText()+" \n \n \nYou couldn't unlock it, maybe there is a key or bobby pin somewhere");
         }
            
         if(selected.equals("Unlock the trapdoor"))
         {
            storyTextArea.setText(storyTextArea.getText()+"You managed to unlock it, the bobby pin might break after 2 more tries of opening other doors");
            aC.playSFX("doorcreak1.wav", 3f);
         }
         
         if(selected.equals("Keep on going forward")||selected.equals("GO BACK IF YOU WISH TO LIVE"))
            gL.level2_hallway3();
            
         if(selected.equals("YOU CAN ONLY GO ONE WAY?!")||selected.equals("Go back down the narrow hallway")||selected.equals("Go back down this ENDLESS HALL"))
            gL.level2_hallway4();
         
         if(selected.equals("Go down the narrow hallway to the left"))
            gL.level2_hallway5();
            
         if(selected.equals("Use the scissors to break the wire"))
            iA.usedItem("Scissors","You broke your scissors, and still couldn't manage to break the wire");
            
         if(selected.equals("Try to go through the exit door"))
            cC.creatureAttack(b, "bone thief.jfif",300,300, "BoneThief_audio.wav", 20);
            
         if(selected.equals("Break the wire with the bolt cutters"))
            iA.takeItem("Shadow Key");
            
         if(selected.equals("Challenging me is how I kill you")||selected.equals("Go back to where you think is safe"))
            gL.level2_hallway6();
            
         if(selected.equals("Grab the bolt cutters"))
         {
            iA.takeItem("Bolt Cutters");
            String[] newItems = {" ","Keep going towards the white light", "Try using the bolt cutters on one of the pebbles", "Go back down this ENDLESS HALL"};
            game.setChoices(newItems);
         }
            
         if(selected.equals("Try using the bolt cutters on one of the pebbles"))
            storyTextArea.setText(storyTextArea.getText()+"\n \n \nThe bolt cutters broke the pebble instantly after just touching it!");
            
         if(selected.equals("Keep going towards the white light"))
            gL.level2_hallway7();
         
         if(selected.equals("Tap the pipes to see how hot they are"))
         {
            aC.playSFX("PipeBoom_audio.wav", 6f);
            game.exit(b, "You felt your body burn, you might have a chance at dying again later...");
         }
            
         if(selected.equals("Keep on going down this heated hallway"))
            gL.level2_hallwayEXT();
         
         if(selected.equals("No Clip to level 4")||selected.equals("Become his rival")||selected.equals("Challenge Him."))
            gL.level4_ENT();
            
         if(selected.equals("Go down the door behind you"))
            cC.creatureAttack(b, "Abomination.png", 340, 480,"Abomination_audio.wav",40);
         
         if(selected.equals("Proceed into FR33D0M"))
            gL.level4_hallway1();
            
         if(selected.equals("kill yourself"))
            game.exit(b, "Do not worry about your body, it will be consumed later.");
            
         if(selected.equals("Proceed into l3333333vel 1")||selected.equals("go back into l3v3l ONE"))
            gL.level4_hallway2();
            
         if(selected.equals("Go forward"))
            gL.level4_hallway3();
         
         if(selected.equals("Go farther down the hall")||selected.equals("go back down this Dea- Hallway"))
            gL.level4_hallway4();
            
         if(selected.equals("Go through the wall")||selected.equals("Return to that strange shadow room"))
            gL.level4_hallway5();
            
         if(selected.equals("try to go back"))
            game.exit(b, "You died... by losing your head, ha ha ha ha ha.");
            
         if(selected.equals("Go through the front door")||selected.equals("Go back to the weapon")||selected.equals("YOU MUST DESTROY HIM, KEEP GOING"))
            gL.level4_hallway6();
         
         if(selected.equals("Progress down the hall")||selected.equals("Return to that hall behind you"))
            gL.level4_hallway7();
         
         if(selected.equals("Grab the strange weapon"))
            iA.takeItem("Strange Weapon");
         
         if(selected.equals("Don't give up and push forward"))
            gL.level4_hallway8();
            
         if(selected.equals("Unlock the door with key, and go through")&& iA.haveItem("Strange Weapon"))
            gL.lvl444_rm1();
            
         if(selected.equals("Try to pick it with the scissors")||selected.equals("Try to blow it open with the sound weapon")||selected.equals("Try to make a hole with the strange weapon")||selected.equals(""))
            storyTextArea.setText(storyTextArea.getText()+"That item didn't work, cmon, how do I open it");
         
         if(selected.equals("Go on to level 2"))
            gL.lvl444_rm2();
            
         if(selected.equals("Go on to level 4"))
            gL.lvl444_rm3();
         
         if(selected.equals("Go where no other has gone")||selected.equals("Jump to the front platform"))
            gL.lvl444_rm4();
            
         if(selected.equals("Jump to the right platform"))
            gL.platformRight();
            
         if(selected.equals("Jump to the left platform"))
            gL.platformLeft();
            
         if(selected.equals("Jump to the rear platform"))
            gL.platformRear();
            
         if(selected.equals("Fire the Strange Weapon at it"))
         {
            aC.playSFX("End Audio.wav",6f);
         }
         
         if(selected.equals("Activate Self Destruction")&& gL.numberOfDestructs < 4)
         {
            gL.numberOfDestructs += 1;
            storyTextArea.setText(storyTextArea.getText()+" \n \nThe Overseer: Great now keep going, get the rest");
         }
         
         if(selected.equals("Fire the Strange Weapon from the right")||selected.equals("Fire the Strange Weapon from the left"))
            storyTextArea.setText(storyTextArea.getText()+"\n \nThat shot didn't seem to do anything, maybe from a different angle");
            
         if(selected.equals("Go back to your friends house"))
            gL.badEnding();
            
         if(selected.equals("Rest, and celebrate victory"))
            System.exit(0);
            
      }
   }
}
