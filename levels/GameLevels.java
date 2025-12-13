package levels;
import engine.AdventureGameConstructor;
import static engine.AdventureGameConstructor.aC;
import static engine.AdventureGameConstructor.cC;
import static engine.AdventureGameConstructor.iA;
import static engine.AdventureGameConstructor.oW;
import static engine.AdventureGameConstructor.storyTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameLevels {
   final private AdventureGameConstructor game;
   
   public int numberOfDestructs = 0; //goes upto 4
   int numberOfShots = 0; //goes upto 2

   public GameLevels(AdventureGameConstructor game) {
      this.game = game;
   }
   
    //all LEVEL STUFF BELOW
   public void badEnding()
   {
      game.clearBuffer();
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "friend house.jpeg", game.picWidth, game.HEIGHT, 0,22);
      game.drawScreen();
      b.dispose();
      storyTextArea.setText("You are free, and able to live your life. Still haunted by the memories, you get flashbacks. Only to one day learn, they weren't flashbacks, but instead the future that would soon unfold. \n \nThe world would be consumed by the ever growing Observer, and the end of days would near");
      String[] newItems = {" ","Leave, and go touch some grass"};
      game.setChoices(newItems);
   }
   
   public void outside()
   {
      aC.playSFX("MainMusic.wav", 1.0f);
      game.clearBuffer();
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "friend house.jpeg", game.picWidth, game.HEIGHT, 0,22);
      game.drawScreen();
      b.dispose();
      storyTextArea.setText("You are about to go to your friends house, you are really excited. You begin walking up to the house, only to -");
      String[] newItems = {" ","You have NO CHOICES","Leave, and go touch some grass"};
      game.setChoices(newItems);
   }
   //LEVEL 0 UMM LEVELS
   public void level0_ENT()
   {
      game.clearBuffer();
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0.png", game.picWidth, game.HEIGHT, 0,22);
      game.drawScreen();
      b.dispose();
      if(iA.haveItem("Sound Weapon"))
      {
         storyTextArea.setText("I feel like there is something behind this wall, I wonder why, let me try with the sound weapon");
         String[] newItems = {" ","Use the sound weapon on the wall","Walk left","Walk forward"};
         game.setChoices(newItems);
      }
      else
      {
         storyTextArea.setText("You see old yellow wallpaper, and the bright buzzing lights overhead. The smell of the moist, mouldy carpet floods your sinuses. All you see is a seemingly endless maze of halls. \n \n \nThe Observer: I am the Observer, do not worry we won't kill you... yet, so stay a while, oh and please let my pets play with your innards, they are very hungry");
         String[] newItems = {" ","Walk left","Walk forward","Leave, and go touch some grass"};
         game.setChoices(newItems);
      }
   }
   public void peeOnFloor()
   {
      iA.usedItem("Sound Weapon", "You used the sound weapon to open up a secret room");
      game.clearBuffer();
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "commodoreFish.jpg", game.picWidth/2, game.HEIGHT/2, 120,100);
      aC.playSFX("PeeOnFloor.wav", 2f);
      storyTextArea.setText(storyTextArea.getText()+"\n \n \n \nYou feel yourself beginning to laugh, 5 days later you continue to laugh, even after your body decaying you continue to laugh, THE FISH HAS CAUGHT YOU, AND YOU ARE NOW DEAD.");
      game.exit(b, "Welcome to the endless prison of laughter");
      game.drawScreen();
      b.dispose();
   }
   public void level0_hallway1()
   {
      game.clearBuffer();
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0hall1.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("All you see is more yellow wallpaper and the buzzing lights still irritating the hell out of you");
      if(!(iA.haveItem("Scissors")))
      {
         storyTextArea.setText(storyTextArea.getText()+"\n\nYou see an item, it appears to be scissors... how odd");
         String[] newItems = {" ","Go through the opening","Take the scissors","Leave the scissors","go back"};
         game.setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ","Go through the opening","go back"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      b.dispose();
   }
   public void level0_hallway2_2()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0hall2.2.png", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("You can see the area is blurry, but two seconds ago it was almost crystal clear. You can feel your sanity draining 'Is there anyone here... anyone left?' \n \n Unknown Voice: Which way shall WE go");
      String[] newItems = {" ", "Go down Left hall", "Go down Right hall","Walk back"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   public void level0_hallway2_4()//went left
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0hall2.4.png", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("You can't find another path you try to go back but can't THE ENTRANCE IS GONE! \n \n \n \nCOME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US");
      if(iA.haveItem("Sound Weapon"))
      {
         storyTextArea.setText(storyTextArea.getText()+"I might have one way out, I hope it works");
         String[] newItems = {" ","Use the Sound Weapon against the wall","Use the sound weapon against yourself"};
         game.setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ","FEEL OUR PAIN","FEEL IMPRISONNED","Bash your head against the wall"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      b.dispose();
   }
   public void level0_hallway2_6()//went right
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0hall2.6.png", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("Unknown Creature: This place can be your tomb.... and most likely will,\n \n \nThe Observer: SILENCE I AM THE ONE WHO SPEAKS TO THE INTRU- GUESTS, NOT YOU \n \n \nWho or what was that, you feel cold, and like someone is watching from all directions. ");
      String[] newItems = {" ","Go through the narrow hallway","Go through the far hallway","Go through the way you came"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   public void level0_hallway2_8()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0hall2.8.png", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: I am sorry for yelling - \n \n Random Raspy voice: WE ARE THE LOST, HUNTED, AND A COMBINE OF ABOMINATIONS ALL MUST JOIN US, INCLUDI- \n \n \nThe Observer: at you, these creatures want all the power we have, but we do not give what is not to be theirs, and it shall stay with us. \n \n \nWhat is that on the floor, is that a satellite dish");
      if(!(iA.haveItem("Sound Weapon")))
      {
         oW.addPicture(b, "sound weapon.png", game.picWidth/4, game.HEIGHT/4, 20,500);
         String[] newItems = {" ","Grab the mini satelite dish","Go back down the hallway"};
         game.setChoices(newItems);
      }
      else if(iA.haveItem("Sound Weapon"))
      {
         storyTextArea.setText(storyTextArea.getText()+" \n \nYou have already gotten everything you needed from here");
         String[] newItems = {" ","Go back down the hallway"};
         game.setChoices(newItems);
      }
      
      game.drawScreen();
      b.dispose();
   }
   public void level0_hallway2_9()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0hall2.9.png", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("You have found one of our pets, so please let it consume you, allow all of your cell, all your life, be used to aid your journey in exiting this prison");
      String[] newItems = {" ","Give up LET US CONJOIN IN ONE WE ARE THE UNITED DEMONS OF THE SUBROOMS"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   public void level0_hallway2()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0hall2.jpeg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("My heart is racing, maybe Matteo is here. He could help. You call his name twice. All you get back is the echo's of your footsteps. 'CoME ViSIT US to BE FREE FROM PAIN'");
      String[] newItems = {" ","Go Forward","go back"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   public void level0_hallway3()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0hall3.jpg", game.picWidth, game.HEIGHT, 0,22);
      
      if(iA.haveItem("Paper"))
      {
         storyTextArea.setText("I hate how empty it is, and quiet, I feel like someone's constantly watching me");
         String[] newItems = {" ","Keep following the %$# PRISON &$#","Go Back"};
         game.setChoices(newItems);
      }
      else if(!(iA.haveItem("Paper")))
      {
         storyTextArea.setText("I rounded the corner and found another room but I found somthing on the floor, a paper.");
         String[] newItems = {" ","Take the Paper","Keep following the %$# PRISON &$#","Go Back"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      b.dispose();
   }
   public void Note()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Old Paper_Backrooms.png", (game.picWidth/2)+70, game.HEIGHT, 200, 22);
      storyTextArea.setText("\n You wonder who killed this knight, was it a hero or villain and where is it's body?");
      oW.text(b, new Color(95,57,57),"Comic Sans MS", Font.BOLD,"Here lies the DeMO- the Blue Knight,",24,210,300);
      oW.text(b, new Color(95,57,57),"Comic Sans MS", Font.BOLD,"he who has slain, has been slain and",24,210,330);
      oW.text(b, new Color(95,57,57),"Comic Sans MS", Font.BOLD,"now thou spirit shall be lost to the",24,210,360);
      oW.text(b, new Color(95,57,57),"Comic Sans MS", Font.BOLD,"monsters of the backrooms",24,210,390);
      game.drawScreen();
      b.dispose();
   }
   public void level0_hallway4()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0hall4.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("You notice the way the area looks, is changing from a clear area to what almost looks like a low quality image... you cant find an exit but an idea randomly pops in your head to cut a hole in one of the pillars");
      if(iA.haveItem("Scissors"))
      {
         String[] newItems = {" ","Cut a large hole in wallpaper on far left pillar","Cut a large hole in wallpaper on far right pillar","Cut a large hole in wallpaper on mid left pillar","Cut a large hole in wallpaper on mid right pillar","Cut a large hole in wallpaper on front left pillar","Cut a large hole in wallpaper on front right pillar","Go to previous area"};
         game.setChoices(newItems);
      }
      else if(!(iA.haveItem("Scissors")))
      {
         String[] newItems = {" ","Look for another way out","Go to previous area"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      b.dispose();
   }
   public void level0_hallwayEXT()//mid right pillar leads to exit
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl0hall4.jpg", game.picWidth, game.HEIGHT, 0,22);
      oW.object('r',b, Color.white,320,200,60,200,0);
      storyTextArea.setText("You cut a large hole in the mid right pillar, big enough for you to fit through, whats odd though is the hole is a different size then what you cut, in fact it's not even attached to the pillar at all!");
      String[] newItems = {" ","Go through it(you wont be able to return back here)","Go to previous area"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   
   //LEVEL 2 LEVELS I GUESS
   public void level2_hallwayENT()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl2_hall_ENT.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: Welcome to level 2, if you want to know where level 1 is, well... we consumed it when we cloned our old home...");
      String[] newItems = {" ","Continue forward", "Continue backward"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   
   public void level2_hallway1()//went backward
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl2_hall1.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("It's almost a dead end but there is a ladder, but there is a trap door in the way, damnit");
      if(iA.haveItem("Bobby Pin"))
      {
         String[] newItems = {" ","Unlock the trapdoor","Go back to where you teleported"};
         game.setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ","Try opening the trapdoor", "Go back to where you teleported"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      b.dispose();
   }
   
   public void level2_hallway2()//went forward
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl2_hall2.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: We are pleased to have you here, you are going to help us consume this level and grow in power. \n \n \nYou: Ok listen here Mr.Observer, I dont know what you want this thing for, but I will find a way to stop you and escape");
      String[] newItems = {" ","Keep on going forward", "Go back to where you teleported"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   
   public void level2_hallway3()//keep on going forward
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl2_hall3.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: I showed you mercy, treated you as a guest and THIS IS WHAT YOU GIVE ME \n \n \nYou: You litterally said your going to eat me or something.");
      String[] newItems = {" ","YOU CAN ONLY GO ONE WAY?!", "Return to where you were last"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   
   public void level2_hallway4()//You can only go one way
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      int randNum = game.rG.nextInt(100);
      oW.addPicture(b, "Backroomslvl2_hall4.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: I am sorry I had frightened you, MY INTENTION WAS TO TORMENT YOU WITH FEAR AND ALLOW THE FEAR MISGUIDE YOU \n \n \nYou: If that is your plan, than your failing, cause I am not afraid of you!! \n \n \nThe Observer: Well then my plan is going perfectly...");
      if(randNum >= 42 && randNum <=63)
      {
         String[] newItems = {" ", "Challenging me is how I kill you", "Go down the narrow hallway to the left", "No Clip to level 4", "GO BACK IF YOU WISH TO LIVE"};
         game.setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ", "Challenging me is how I kill you", "Go down the narrow hallway to the left", "GO BACK IF YOU WISH TO LIVE"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      b.dispose();
   }
   
   public void level2_hallway5()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl2_hall5.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("You found a key attached to the pipe, you tried ripping it off the wall but couldn't, maybe if you had some sort of tool to break the metal wire. \n \n \nThe Observer: Go through that door so we can finally meet... and become one.");
      if(iA.haveItem("Scissors"))
      {
         if(iA.haveItem("Bolt Cutters"))
         {
            String[] newItems = {" ","Break the wire with the bolt cutters","Try to go through the exit door","Go back down the narrow hallway"};
            game.setChoices(newItems);
         }
         else
         {
            String[] newItems = {" ","Use the scissors to break the wire","Try to go through the exit door","Go back down the narrow hallway"};
            game.setChoices(newItems);
         }
      }
      else if(iA.haveItem("Bolt Cutters"))
      {
         String[] newItems = {" ","Break the wire with the bolt cutters","Try to go through the exit door","Go back down the narrow hallway"};
         game.setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ","Try to go through the exit door","Go back down the narrow hallway"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      b.dispose();
   }
   
   public void level2_hallway6()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl2_hall6.jpg", game.picWidth, game.HEIGHT, 0,22);
      game.drawScreen();
      b.dispose();
      if(iA.haveItem("Bolt Cutters"))
      {
         storyTextArea.setText("The Observer: My goals are beyond your understanding, I will gain control, AND I WILL OVERPOWER MY FORMER MASTER AND HIS MINIONS");
         String[] newItems = {" ","Keep going towards the white light", "Try using the bolt cutters on one of the pebbles", "Go back down this ENDLESS HALL"};
         game.setChoices(newItems);
      }
      else
      {
         storyTextArea.setText("You see a weird light, but also some bolt cutters, maybe it could be useful...\n \n \nUnknown Voice: Stop him... you must not let him grow any mo- \n \n \nThe Observer: SILENCE! I need you to stop, and allow me to gain control of this prison");
         String[] newItems = {" ","Keep going towards the white light", "Grab the bolt cutters", "Go back down this ENDLESS HALL"};
         game.setChoices(newItems);
      }
   }
   
   public void level2_hallway7()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl2_hall7.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("Your in a narrow hall, and it is really hot. You think it's the pipes overheating \n \n \nYou: Mr. Observer, if you think the heat will kill me, then you got another thing comin, and it isn't in your favour.");
      String[] newItems = {" ","Tap the pipes to see how hot they are", "Keep on going down this heated hallway","Go back to where you think is safe"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   
   public void level2_hallwayEXT()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl2_hall7.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: Thanks to you, I can now consume this level, and you have fallen where I began... where we will begin. I will admit though... you survived far longer than I expected.");
      String[] newItems = {" ","Become his rival", "DIE DIE DIE DIE DIE DIE DIE DIE DIE DIE","Challenge Him."};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   
   //LEVEL 4 LEVELS... AGAIN
   public void level4_ENT()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl4_ENT.jpg", game.picWidth, game.HEIGHT, 0,22);
      cC.level4 = true;
      storyTextArea.setText("The Observer: Welc0me to your l@s& St@n^d, th!s l&vel wi)l be y@up gr@>e nO(h!ng c&n s%ve YOU KNOW MY PETS OWN THIS LEVEL AND SO DOES MY COnSumti&n, ! w!(ll) a(d)d y(ou) to MY COLLECTION OF PET F@@D \n \n \nYou: 1 28 643943 83 92160 %$%& I WANT TO SPEAK, oh im speaking, what are you doing to me!? \n \n \nThe Observer: I am consuming this place, and because of this, I am going to grow in power all thanks to my former master.\n \n \n$#^%# : I have opened two doors to your left and right, they can take you back to the other levels before you continue forward, just know once you exit this inbetween levels area, you wont be able to come back.");
      String[] newItems = {" ","Go down left door(level 0)","Go down right door(level 2)","Go down the door behind you","Proceed into FR33D0M"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   
   public void level4_hallway1()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl4_hallway1.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: Incr3di9le yo@ m@de it h3re, as you can see th&s leveeeeel is br&ken, CoRRRRRRRupted, Fr@ctu7ed. You: And im supposed to join it, well im not, so don't get your hopes up!");
      String[] newItems = {" ","Proceed into l3333333vel 1","Go through the front door","kill yourself"};
      game.setChoices(newItems);
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }
   
   public void level4_hallway2()//Proceed into level 1
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "Backroomslvl1_Area1.png", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: Oh wait I missed this part of level 1, well maybe its time I consume it, SO GET OUT");
      String[] newItems = {" ","Go forward","try to go back","kill yourself"};
      game.setChoices(newItems);
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }
   public void level4_hallway3()//Go forward
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "lvl4_3.png", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: I've had enough of you guests, maybe it's time I-\n \n \n The Overseer: He has too much power, YOU MUST STOP HIM");
      String[] newItems = {" ","Go farther down the hall","go back into l3v3l ONE","kill yourself"};
      game.setChoices(newItems);
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }
   
   public void level4_hallway4()//go farther down hall
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "lvl4_5.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Overseer: I built this place, to encase him, to prevent, to KILL him. But he outgrew this thick shell, and now has the ability to consume his prison and convert it into his home to destroy yours, and mine.");
      if(iA.haveItem("Scissors"))
      {
         String[] newItems = {" ","Go through the wall","try to go back","kill yourself"};
         game.setChoices(newItems);
      }
      else
      {
         storyTextArea.setText(storyTextArea.getText()+"It's a dead end, I might have to go back");
         String[] newItems = {" ","try to go back","kill yourself"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }
   public void level4_hallway5()//go through wall or 
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "lvl4_4.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("You see a shadow, but no one is actually there, you are beginning to get scared, and the constant urge to kill yourself grows. \n \n \nThe Observer: You m#st not pr0ce33d f@rw4rd, 4 my p3ts w1ll k1ll yu");
      String[] newItems = {" ","YOU MUST DESTROY HIM, KEEP GOING","go back down this Dea- Hallway","kill yourself"};
      game.setChoices(newItems);
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }
   
   public void level4_hallway6()//You must destroy him
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "lvl4_6.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("There is something on the flo-. \n \n \nThe Overseer: grab that item, you will need it to kill my former apprentice");
      if(iA.haveItem("Strange Weapon"))
      {
         String[] newItems = {" ","Progress down the hall","kill yourself"};
         game.setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ","Grab the strange weapon","Progress down the hall","Return to that strange shadow room","kill yourself"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }
   
   public void level4_hallway7()//Progress down the hall
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "lvl4_7.png", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("Unknown: Free us \n \n \nMore Unknown people: FREE US ALL PLEASE \n \n \nMary Joe: My baby wh3r3 is h% I w@nt &&&& m7 b1b7 BAAAAAAAAAAAAAAA\n \n \nYou: What is going on... why is everyone so damn loud");
      String[] newItems = {" ","Don't give up and push forward","Go back to the weapon","kill yourself"};
      game.setChoices(newItems);
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }
   
   public void level4_hallway8()//Dont give up push forward
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "lvl4_8.png", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Overseer: YES THERE IT IS, you will need a key to open it, and don't forget to have the weapon. This is where we will face our foe, and defeat him");
      if(iA.haveItem("Shadow Key"))
      {
         String[] newItems = {" ","Unlock the door with key, and go through","Return to that hall behind you","kill yourself"};
         game.setChoices(newItems);
      }
      else if(iA.haveItem("Scissors"))
      {
         String[] newItems = {" ","Try to pick it with the scissors","Return to that hall behind you","kill yourself"};
         game.setChoices(newItems);
      }
      else if(iA.haveItem("Sound Weapon"))
      {
         String[] newItems = {" ","Try to blow it open with the sound weapon","Return to that hall behind you","kill yourself"};
         game.setChoices(newItems);
      }
      else if(iA.haveItem("Strange Weapon"))
      {
         String[] newItems = {" ","Try to make a hole with the strange weapon","Return to that hall behind you","kill yourself"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }
   
   //THE FINAL LEVEL, THE EPICENTER OF THE OBSERVER
   public void lvl444_rm1()//Use key to open door
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "lvl444_0.png", game.picWidth, game.HEIGHT, 0,22);
      
      if(iA.haveItem("Paper"))
      {
         storyTextArea.setText("The Overseer: That paper you had found, was written by the Observer when he killed my knight. Whom was supposed to slay him, but was slain.\n \n \n");
      }
      else
      {
         storyTextArea.setText("The Overseer: You are close, HURRY there is little time left before he finds you");
      }
      
      String[] newItems = {" ","Go on to level 2","Go back through the door"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   
   public void lvl444_rm2()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "lvl444_2.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: There you are, your lucky the pets don't come here... they are quite freightened of me. \n \n \nYou: I will stop you, and I will defeat you... just as the Overseer said. \n \n \nThe Observer: Fool, he has no control over this place, and neither do you, so give up now and I will allow you safe passage");
      String[] newItems = {" ","Go on to level 4","Go back through the door"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   
   public void lvl444_rm3()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "lvl444_4.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Overseer: This is it my friend, I will not be able to influence the fight, I can only guide you. He will not attack as long as you do not threaten him");
      String[] newItems = {" ","Go where no other has gone","Go back through the door"};
      game.setChoices(newItems);
      game.drawScreen();
      b.dispose();
   }
   
   //BOSS ARENA KINDA
   public void lvl444_rm4()
   {
      cC.lvl444 = true;
      //playSFX("FinalFight.wav",5f);
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "The Observer_idle.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Observer: It is such an honour to meet you, somehow you have made it farther than anyone else. You should be proud, although I do not understand why your not. \n \nYou notice how fleshy it is, how large, powerful it is. You also notice jump pads to other side areas, maybe I could use them\n \n \nThe Observer: I can grant you passage back to your world, right where you last were just let me know, and you will be brought back\n \n \nThe Overseer: DO NOT ACCEPT I BROUGHT YOU HERE FOR A REASON, AND YOU MUST COMPLETE YOUR MISSION");
      if(iA.haveItem("Strange Weapon"))
      {
         storyTextArea.setText(storyTextArea.getText()+"\n \nThe Overseer: Shoot your large weapon at it from 2 angles, destroy the core to destroy him.");
         String[] newItems = {" ","Jump to the left platform","Jump to the right platform","Go back to your friends house"};
         game.setChoices(newItems);
      }
      else
      {
         storyTextArea.setText(storyTextArea.getText()+"\n \nThe Overseer: You need to activate battery A self destruction sequence");
         String[] newItems = {" ","Activate Self Destruction","Jump to the left platform","Jump to the right platform","Go back to your friends house"};
         game.setChoices(newItems);
      }

      game.drawScreen();
      b.dispose();
   }
   
   public void platformRight()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "control panel.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Overseer");
      if(iA.haveItem("Strange Weapon"))
      {
         storyTextArea.setText("The Overseer: Shoot your large weapon at it from 2 angles, destroy the core to destroy him.");
         String[] newItems = {" ","Fire the Strange Weapon from the right","Jump to the front platform","Jump to the rear platform"};
         game.setChoices(newItems);
      }
      else
      {
         storyTextArea.setText("The Overseer: You need to activate battery B self destruction sequence");
         String[] newItems = {" ","Activate Self Destruction","Jump to the front platform","Jump to the rear platform"};
         game.setChoices(newItems);
      }
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }
   
   public void platformLeft()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "control panelC.jpg", game.picWidth, game.HEIGHT, 0,22);
      storyTextArea.setText("The Overseer");
      if(iA.haveItem("Strange Weapon"))
      {
         storyTextArea.setText("The Overseer: Shoot your large weapon at it from 2 angles, destroy the core to destroy him.");
         String[] newItems = {" ","Fire the Strange Weapon from the left","Jump to the front platform","Jump to the rear platform"};
         game.setChoices(newItems);
      }
      else
      {
         storyTextArea.setText("The Overseer: You need to activate battery C self destruction sequence");
         String[] newItems = {" ","Activate Self Destruction","Jump to the front platform","Jump to the rear platform"};
         game.setChoices(newItems);
      }
      if(numberOfShots == 2)
         storyTextArea.setText(storyTextArea.getText()+"\n \nThe Overseer: YES THATS IT, quick shoot at it from the front, and he will die");
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }
   
   public void platformRear()
   {
      game.clearBuffer();
      Graphics2D b = game.buffer.createGraphics();
      oW.addPicture(b, "control panelD.jpg", game.picWidth, game.HEIGHT, 0,22);
      if(iA.haveItem("Strange Weapon"))
      {
         storyTextArea.setText("The Overseer: Shoot your large weapon at it from 2 angles, destroy the core to destroy him.");
         String[] newItems = {" ","Fire the Strange Weapon at it","Jump to the left platform","Jump to the right platform"};
         game.setChoices(newItems);
      }
      else
      {
         storyTextArea.setText("The Overseer: You need to activate battery D self destruction sequence");
         String[] newItems = {" ","Activate Self Destruction","Jump to the left platform","Jump to the right platform"};
         game.setChoices(newItems);
      }
      if(numberOfShots == 2)
         storyTextArea.setText(storyTextArea.getText()+"\n \nThe Overseer: YES THATS IT, quick shoot at it from the front, and he will die");
      game.drawScreen();
      cC.randAttack();
      b.dispose();
   }

   //DRAW A PICTURE USING ELLIPSES,RECTANGLES,ETC.
   public static void drawIntroPicture(Graphics2D b, AdventureGameConstructor game)
   {
      oW.object('r',b,new Color(0,0,0),0,24,game.picWidth,game.HEIGHT,0);
      oW.object('r',b,new Color(102,13,6),2,22,game.picWidth-10,game.HEIGHT-10,5);
      
      oW.object('o',b,new Color(140,25,4),150,-250,400,400,40);
      oW.object('o',b,Color.cyan,150,100,500,500,40);
      oW.object('o',b,Color.black,150,200,600,600,40);
      oW.triangle(b,new Color(255,53,18),200,500,400,500,300,300,0);
      oW.triangle(b,new Color(255,53,18),400,500,600,500,500,300,0);
      oW.triangle(b,new Color(255,53,18),300,300,500,300,400,100,0);
      oW.text(b,Color.orange,"Courier New",Font.BOLD, "The Backrooms: In the Subrooms",47,6,700); //litterally words
      oW.text(b,Color.black,"Chiller",Font.BOLD, "0",72,480,450);
      oW.text(b,Color.black,"Chiller",Font.BOLD, "2",72,270,450);
      oW.text(b,Color.black,"Chiller",Font.BOLD, "4",72,370,250);
      oW.text(b,new Color(13,13,13),"Chiller",Font.BOLD, "444",72,350,380);
      
   }
}
