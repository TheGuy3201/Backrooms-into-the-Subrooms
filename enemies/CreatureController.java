package enemies;

import engine.AdventureGameConstructor;
import static engine.AdventureGameConstructor.aC;
import static engine.AdventureGameConstructor.cC;
import static engine.AdventureGameConstructor.iA;
import static engine.AdventureGameConstructor.oW;
import java.awt.Graphics2D;

public class CreatureController
{
   final private AdventureGameConstructor game;
   
   public CreatureController(AdventureGameConstructor game) {
      this.game = game;
   }
   
   //CREATURE ATTACK 'ANIMATION' KINDA
   public boolean level4 = false;
   public boolean lvl444 = false;

   public void creatureAttack(Graphics2D b, String picName, int w, int h, String audioName, int lengthOfTime)
   {
      for(int i = 0 ; i < lengthOfTime ; i++)
      {
         if(i < lengthOfTime && !(iA.haveItem("Sound Weapon")))
         {
            int randX = game.rG.nextInt(game.picWidth-200);
            int randY = game.rG.nextInt(game.HEIGHT-200);
            oW.addPicture(b, picName, w, h, randX, randY);
            aC.playSFX(audioName, 4f);
            if(level4 == false)
               game.drawScreen();
         }
         
      }
      if(iA.haveItem("Sound Weapon"))
         iA.usedItem("Sound Weapon", "\n \n \nYou fought the creatures off with the sound weapon, but should be careful in case they return");
      else
      {
         String[] newItems = {" ","Accept your death?"};
         game.setChoices(newItems);
      }
   }
   //RANDOM CHANCE OF BEING ATTACKED
   boolean testMode = false;
   public void randAttack()
   {
      if(testMode == false)
      {
         Graphics2D b =(Graphics2D)game.getGraphics();
         
         int randNum = game.rG.nextInt(100);
         if(cC.lvl444 == true)
         {
            if(randNum <= 18)
               creatureAttack(b, "Abomination.png", 340, 480,"Abomination_audio.wav",11);
            else if(randNum > 19 && randNum < 32)
               creatureAttack(b, "Observer Attack.jpg", 340, 480,"Abomination_audio.wav",9);
            else
               System.out.println("You are safe... for now");
         }
         else
         {
            if(randNum <= 10)
               creatureAttack(b, "Abomination.png", 340, 480,"Abomination_audio.wav",13);
            else if(randNum > 69 && randNum < 82)
               creatureAttack(b, "smiler.jfif", 340, 480,"ScreechSFX.wav",8);
            else if(randNum > 83 && randNum < 91)
               creatureAttack(b, "stalker.png", 340, 480,"Jumpscare_1.wav",8);
            else
               System.out.println("You are safe... for now");
         }
      }
      
   }
}