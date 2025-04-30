import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
 
/****************************************************************
 * class with the main method and "instantiates" the 
 *       JFrame with all components.
 ****************************************************************/   
public class JoshuaAdventureGame11
{ 
   //static final long serialVersionUID = 11;
   
   //IN ALL HONESTY I DONT KNOW
   public static void main(String args[]) throws Exception 
   { 
      AdventureGame frame = new AdventureGame();
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.beginGame();
   }// end main method
}// end class 
  
  
/****************************************************************
 * Graphics Adventure Game Class
 ****************************************************************/    
class AdventureGame extends JFrame implements ActionListener
{
   ////////////////////////// FIELDS ////////////////////////////
   static final long serialVersionUID = 1;
   
   // Picture area details
   BufferedImage buffer;   // instance variable for double buffering
   final int WIDTH = 1250, HEIGHT = 850;   //to fit with resolution of monitor
   int picWidth;

   // Set up fonts for the picture area
   Font chillerFont = new Font("Chiller", Font.PLAIN, 48); 
   //Font smallArialFont = new Font("Arial", Font.BOLD, 20); 
   //Font largeArialFont = new Font("Arial", Font.BOLD, 40); 
   Random rG = new Random();
   // GUI components that will need to be accessed from many methods
   JPanel interactionPanel;
   String[] choices;
   JComboBox<String> choiceComboBox;
   JTextArea storyTextArea, inventoryTextArea;
   JPanel picturePanel;
   
     
   /*************************************************************************
   *     Constructor                                                        *
   *     Builds the main window and all of the components that we see.      *
   *************************************************************************/ 
   public AdventureGame()
   {
      super ("Joshua's Game(The Backrooms: In the Subrooms)");
      int interactionPanelWidth = 400;
      picWidth = WIDTH-interactionPanelWidth;
      buffer = new BufferedImage(picWidth-5, HEIGHT+15, BufferedImage.TYPE_INT_RGB);
      
      //THE PICTURE AREA
      picturePanel = new JPanel();
      picturePanel.setDoubleBuffered(true);
      
      //CREATES RIGHT SIDE OF SCREEN TO DISPLAY CHOICES
      interactionPanel = new JPanel();
      interactionPanel.setLayout(new BorderLayout(5,10));
      interactionPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
      
      //CHOICE SELECTION AREA
      JPanel choicePanel = new JPanel();
      
      //AREA WITH CHOICES
      Border blackline = BorderFactory.createLineBorder(Color.black);
      TitledBorder choiceTitle = BorderFactory.createTitledBorder(blackline, " What do you want to do ");
      choicePanel.setBorder(choiceTitle);
      String[] tempStrArray = {" ","Visit your friends in ETERNAL PAIN?","Leave, and go touch some grass"};
      choices = tempStrArray;
      choiceComboBox = new JComboBox<>(choices);
      choiceComboBox.setFont(new Font("Serif", Font.PLAIN, 20));
      choiceComboBox.setEditable(false);
      choiceComboBox.setPreferredSize(new Dimension(interactionPanelWidth-40, 30));
      choicePanel.add(choiceComboBox);
      choiceComboBox.addActionListener(this);
      
      //orange Area with story
      storyTextArea = new JTextArea();
      storyTextArea.setBackground(new Color(230, 150, 11));
      storyTextArea.setMargin(new Insets(10,10,10,10));
      storyTextArea.setFont(new Font("Serif", Font.PLAIN, 20));
      storyTextArea.setLineWrap(true);
      storyTextArea.setWrapStyleWord(true);
      storyTextArea.setEditable(false);
      storyTextArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createRaisedBevelBorder()));
      
      //THE INVENTORY SECTION OF GAME
      inventoryTextArea = new JTextArea();
      inventoryTextArea.setMargin(new Insets(10,10,10,10));
      inventoryTextArea.setFont(new Font("Serif", Font.PLAIN, 20));
      inventoryTextArea.setLineWrap(true);
      inventoryTextArea.setWrapStyleWord(true);
      inventoryTextArea.setEditable(false);
      TitledBorder inventoryTitle = BorderFactory.createTitledBorder(blackline, "   Inventory   ");
      inventoryTextArea.setBorder(inventoryTitle);
      inventoryTextArea.setMinimumSize(new Dimension(interactionPanelWidth-40,80));
      
      //FINALIZES THE SECTIONS OF THE SCREEN PICTURE, CHOICE AND INVENTORY
      interactionPanel.add(choicePanel, BorderLayout.PAGE_START);
      interactionPanel.add(storyTextArea, BorderLayout.CENTER);
      interactionPanel.add(inventoryTextArea, BorderLayout.PAGE_END);
      
      //set sizes and borders of both panels
      picturePanel.setPreferredSize(new Dimension(picWidth,HEIGHT));
      picturePanel.setMinimumSize(new Dimension(picWidth,HEIGHT));
      picturePanel.setBorder(BorderFactory.createLineBorder(Color.black));
      
      interactionPanel.setPreferredSize(new Dimension(interactionPanelWidth, HEIGHT));
      interactionPanel.setMinimumSize(new Dimension(interactionPanelWidth, HEIGHT));
      interactionPanel.setBorder(BorderFactory.createCompoundBorder(
                                    BorderFactory.createEmptyBorder(10,10,10,10),
                                    BorderFactory.createLoweredBevelBorder()));
      
      JPanel pane = (JPanel) getContentPane();
      pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
      pane.add(picturePanel);
      pane.add(interactionPanel);
      

      //MAKE JFRAME VISIBLE
      setSize(WIDTH, HEIGHT);
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
   }
   
   
   //CREATES THE CHOICES FOR SPECIFIC AREA
   public void setChoices(String[] choices)
   {
      choiceComboBox.removeAllItems();
      for(String s : choices)
         choiceComboBox.addItem(s);
   }
   
   
   //PLACE PICTURE FROM BUFFER ON SCREEN
   public void drawScreen()
   {
      Graphics2D g =(Graphics2D)this.getGraphics();
      
      g.drawImage(buffer,10,10,this);
      Toolkit.getDefaultToolkit().sync();
      g.dispose();
   }
   
   
   //LIST OF ITEMS AND VARIABLES
   String[] collectables = {"Scissors","Strange Weapon","Sound Weapon","Paper","Bolt Cutters","Shadow Key"};
   boolean[] haveCollected = {false,false,false,false,false,false};
   //Sound Weapon Stuff
   boolean soundWeaponAvailable = true;
   int soundWeaponUses = 4;
   //Boss Fight stuff
   boolean lvl444 = false;
   int numberOfDestructs = 0; //goes upto 4
   int numberOfShots = 0; //goes upto 2
   
   
   //INVENTORY THINGY
   public void setInventory()
   {
      String collected = "Collected: ";

      for(int i = 0 ; i<collectables.length ; i++)
         if(haveCollected[i])
            if(collected.equals("Collected: "))
               collected = collected + collectables[i];
            else
               collected = collected +", "+ collectables[i];

      inventoryTextArea.setText(collected);
      interactionPanel.repaint();
   }
   
   
   //CHANGE ITEM TAKEN TO TRUE
   public boolean haveItem(String item)
   {
      for(int i = 0 ; i < collectables.length ; i++)
         if(item.equals(collectables[i]))
            return haveCollected[i];
      return false;
   }
   
   //DOES EXACTLY WHAT IT SAYS... TAKES THE ITEM
   public void takeItem(String item)
   {
      for(int i = 0 ; i<collectables.length ; i++)
      {
         if(item.equals(collectables[i]))
         {
            if(item.equals("Sound Weapon") && soundWeaponAvailable)
            {
               soundWeaponAvailable = false;
               soundWeaponUses = 4;
            }
               
            haveCollected[i] = true;
            storyTextArea.setText("You took the "+collectables[i]+" and put it in your pocket");
            setInventory();
         }
      }
   }
   
   //USES THE ITEM SPECIFIED
   public void usedItem(String item, String usageMessage) //Ask if I can remove 'Need: ' part of code
   {
      if(item.equals("Sound Weapon"))
      {
         playSFX("SoundWeaponSFX.wav", 2f);
         storyTextArea.setText(usageMessage);
         soundWeaponUses -= 1;
         
         if(soundWeaponUses < 1)
         {
            for(int i = 0 ; i<collectables.length ; i++)
            {
               haveCollected[i] = false;
               setInventory();
            }
         }
      }
      if(item.equals("Scissors"))
      {
         playSFX("Item_break1.wav", 2f);
         storyTextArea.setText(usageMessage);
         for(int i = 0 ; i<collectables.length ; i++)
         {
            haveCollected[i] = false;
            setInventory();
         }
      }
      if(item.equals("Shadow Key"))
      {
         playSFX("doorcreak1.wav", 2f);
         storyTextArea.setText(usageMessage);
         for(int i = 0 ; i<collectables.length ; i++)
         {
            haveCollected[i] = false;
            setInventory();
         }
      }
   }
   
   //CREATES THE SCREEN STUFF
   @Override
   public void paint(Graphics g)
   {
      try
      {
         Thread.sleep(1000);
         drawScreen();
         interactionPanel.repaint();
      }
      catch(Exception e)
      {
         System.out.println("Problem painting the screen: \n");
         e.printStackTrace();
      }
   }
   
   //ALLOWS AUDIO TO BE PLAYED
   public void playSFX(String pathway, float volume)
   {
      try
      {
         File audioFile = new File ("resources/"+pathway);
         AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
         Clip clip = AudioSystem.getClip();
         clip.open(audioStream);
         FloatControl setVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
         setVolume.setValue(volume);
         clip.start();
         
            //clip.stop();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         System.out.println("Run time error when adding audio file "+pathway);
         System.out.println("Make sure audio file is in .wav format and path is specified properly. ");
      }
   }
   
   
   //DRAWS ACTUAL IMAGES LIKE PNG AND JPEG
   public void addPicture(Graphics2D b, String picFileName, int w, int h, int x, int y)
   {
      BufferedImage img = null;
      Image newImage = null;
      try
      {
         img = ImageIO.read(getClass().getResource("resources/"+picFileName));
         if(w != 0 && h != 0)
         {
            newImage = img.getScaledInstance(w,h,Image.SCALE_DEFAULT);
            b.drawImage(newImage, x, y, this);
         }
         else
            b.drawImage(img, x, y, this);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         String msg1 = "Runtime error caught in addPicture: \""+picFileName+"\" not found or read properly";
         String msg2 = "Make sure that your picture file is in the same folder as your .java file";
         System.out.println(msg1+"\n"+msg2);
         text(b,Color.orange,"Arial",Font.BOLD, msg1,14,10,50);
         text(b,Color.orange,"Arial",Font.BOLD, msg2,14,10,65);
      }
   }
   
   
   //CREATURE ATTACK 'ANIMATION' KINDA
   boolean level4 = false;
   public void creatureAttack(Graphics2D b, String picName, int w, int h, String audioName, int lengthOfTime)
   {
      for(int i = 0 ; i < lengthOfTime ; i++)
      {
         if(i < lengthOfTime && !(haveItem("Sound Weapon")))
         {
            int randX = rG.nextInt(picWidth-200);
            int randY = rG.nextInt(HEIGHT-200);
            addPicture(b, picName, w, h, randX, randY);
            playSFX(audioName, 4f);
            if(level4 == false)
               drawScreen();
         }
         
      }
      if(haveItem("Sound Weapon"))
         usedItem("Sound Weapon", "\n \n \nYou fought the creatures off with the sound weapon, but should be careful in case they return");
      else
      {
         String[] newItems = {" ","Accept your death?"};
         setChoices(newItems);
      }
   }
   //RANDOM CHANCE OF BEING ATTACKED
   boolean testMode = false;
   public void randAttack()
   {
      if(testMode == false)
      {
         Graphics2D b =(Graphics2D)this.getGraphics();
         
         int randNum = rG.nextInt(100);
         if(lvl444 == true)
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
   //THIS WILL CLOSE GAME
   public void exit(Graphics2D b, String deathMessage)
   {
      text(b,Color.red,"Chiller",Font.BOLD, deathMessage,52,40,150);
      drawScreen();
      int answer = JOptionPane.showConfirmDialog(null,"Are you sure you want to give up your life for us?"); //The Icon Thing
      if (answer == 0) 
         System.exit(0);
         
      else if(answer >= 1)
         System.out.println("Well thats cool to know you dont want to die(You selected No, or Cancel)");
   }
   
   
   //CHANGES THE CHOICES DISPLAYED/AVAILABLE IN AREA
   @Override
   public void actionPerformed(ActionEvent e)
   {
      Graphics2D b = buffer.createGraphics();
      if(e.getSource() == choiceComboBox)
      {
         String selected = choiceComboBox.getSelectedItem().toString();
         if(selected.equals("Visit your friends in ETERNAL PAIN?"))
            outside();
            
         if(selected.equals("Leave, and go touch some grass")||selected.equals("FEEL OUR PAIN")||selected.equals("FEEL IMPRISONNED")||selected.equals("Bash your head against the wall")||selected.equals("Give up LET US CONJOIN IN ONE WE ARE THE UNITED DEMONS OF THE SUBROOMS"))
            exit(b, "Thank you for freeing yourself from torment");
            
         if(selected.equals("Use the sound weapon against yourself"))
         {
            usedItem("Sound Weapon", "You freed yourself, all it took was the courage to do it...");
            exit(b, "Wow that was a loud death, thank you for dying");
         }
            
         if(selected.equals("Accept your death?"))
            System.exit(0);
            
         if(selected.equals("You have NO CHOICES")||selected.equals("go back")||selected.equals("Go down left door(level 0)"))
            level0_ENT();
            
         if(selected.equals("Walk left")||selected.equals("Walk back"))
            level0_hallway1();
            
         if(selected.equals("Walk forward")||selected.equals("Go Back"))
            level0_hallway2();
            
         if(selected.equals("Take the scissors"))
            takeItem("Scissors");
            
         if(selected.equals("Go through the opening")||selected.equals("Go through the way you came"))
            level0_hallway2_2();
            
         if(selected.equals("Go down Left hall"))
            level0_hallway2_4();
            
         if(selected.equals("Go down Right hall")||selected.equals("Go back down the hallway"))
            level0_hallway2_6();
            
         if(selected.equals("Go through the far hallway"))
            level0_hallway2_8();
            
         if(selected.equals("Go through the narrow hallway"))
            level0_hallway2_9();
            
         if(selected.equals("Go Forward"))
            level0_hallway3();
            
         if(selected.equals("Take the Paper"))
         {
            Note();
            takeItem("Paper");
         }
         
         if(selected.equals("Keep following the %$# PRISON &$#"))
            level0_hallway4();
            
         if(selected.equals("Go to previous area"))
            level0_hallway3();
            
         if(selected.equals("Look for another way out"))
         {
            storyTextArea.setText("Good Luck with that...");
            creatureAttack(b, "stalker.png",300,300, "Jumpscare_1.wav", 8);
         }
         
         if(selected.equals("Grab the mini satelite dish") && soundWeaponAvailable == true)
         {
            takeItem("Sound Weapon");
            storyTextArea.setText(storyTextArea.getText()+"What does this thing do?? Maybe I can get a signal out to get help!");
            String[] newItems = {" ","Test the item","Go back down the hallway"};
            setChoices(newItems);
         }
         
         if(selected.equals("Test the item"))
            usedItem("Sound Weapon", "You used the sound weapon");
            
         if(selected.equals("Use the Sound Weapon against the wall"))
         {
            level0_hallway2_2();
            usedItem("Sound Weapon", "You used the sound weapon\n \n \nYou ran out of the room as quick as you could when the wall broke down, you could go back in and use the sound weapon again if needed");
         }
         
         if(selected.equals("Use the sound weapon on the wall"))
            peeOnFloor();
            
         if(selected.equals("Cut a large hole in wallpaper on far left pillar")||selected.equals("Cut a large hole in wallpaper on far right pillar"))
            creatureAttack(b, "smiler.jfif", (720/3), (610/3),"Jumpscare_1.wav",11);
            
         if(selected.equals("Cut a large hole in wallpaper on mid right pillar"))
            creatureAttack(b, "zombie thing.jfif", (720/3), (610/3),"Jumpscare_1.wav", 6);
            
         if(selected.equals("Continue to Exit")||selected.equals("Cut a large hole in wallpaper on mid left pillar")) //lvl0 exit
            level0_hallwayEXT();
            
         if(selected.equals("Cut a large hole in wallpaper on front left pillar")||selected.equals("Cut a large hole in wallpaper on front right pillar"))
            creatureAttack(b, "The Converted.jfif", (720/3), (610/3),"Jumpscare_1.wav", 28);
            
         if(selected.equals("Go through it(you wont be able to return back here)")||selected.equals("Go back to where you teleported")||selected.equals("Go down right door(level 2)"))
            level2_hallwayENT();
            
         if(selected.equals("Continue forward")||selected.equals("Return to where you were last")) 
            level2_hallway2();
            
         if(selected.equals("Continue backward"))
            level2_hallway1();
            
         if(selected.equals("Try opening the trapdoor"))
         {
            playSFX("doorRattle.wav", 3f);
            storyTextArea.setText(storyTextArea.getText()+" \n \n \nYou couldn't unlock it, maybe there is a key or bobby pin somewhere");
         }
            
         if(selected.equals("Unlock the trapdoor"))
         {
            storyTextArea.setText(storyTextArea.getText()+"You managed to unlock it, the bobby pin might break after 2 more tries of opening other doors");
            playSFX("doorcreak1.wav", 3f);
            
         }
         
         if(selected.equals("Keep on going forward")||selected.equals("GO BACK IF YOU WISH TO LIVE"))
            level2_hallway3();
            
         if(selected.equals("YOU CAN ONLY GO ONE WAY?!")||selected.equals("Go back down the narrow hallway")||selected.equals("Go back down this ENDLESS HALL"))
            level2_hallway4();
         
         if(selected.equals("Go down the narrow hallway to the left"))
            level2_hallway5();
            
         if(selected.equals("Use the scissors to break the wire"))
            usedItem("Scissors","You broke your scissors, and still couldn't manage to break the wire");
            
         if(selected.equals("Try to go through the exit door"))
            creatureAttack(b, "bone thief.jfif",300,300, "BoneThief_audio.wav", 20);
            
         if(selected.equals("Break the wire with the bolt cutters"))
            takeItem("Shadow Key");
            
         if(selected.equals("Challenging me is how I kill you")||selected.equals("Go back to where you think is safe"))
            level2_hallway6();
            
         if(selected.equals("Grab the bolt cutters"))
         {
            takeItem("Bolt Cutters");
            String[] newItems = {" ","Keep going towards the white light", "Try using the bolt cutters on one of the pebbles", "Go back down this ENDLESS HALL"};
            setChoices(newItems);
         }
            
         if(selected.equals("Try using the bolt cutters on one of the pebbles"))
            storyTextArea.setText(storyTextArea.getText()+"\n \n \nThe bolt cutters broke the pebble instantly after just touching it!");
            
         if(selected.equals("Keep going towards the white light"))
            level2_hallway7();
         
         if(selected.equals("Tap the pipes to see how hot they are"))
         {
            playSFX("PipeBoom_audio.wav", 6f);
            exit(b, "You felt your body burn, you might have a chance at dying again later...");
         }
            
         if(selected.equals("Keep on going down this heated hallway"))
            level2_hallwayEXT();
         
         if(selected.equals("No Clip to level 4")||selected.equals("Become his rival")||selected.equals("Challenge Him."))
            level4_ENT();
            
         if(selected.equals("Go down the door behind you"))
            creatureAttack(b, "Abomination.png", 340, 480,"Abomination_audio.wav",40);
         
         if(selected.equals("Proceed into FR33D0M"))
            level4_hallway1();
            
         if(selected.equals("kill yourself"))
            exit(b, "Do not worry about your body, it will be consumed later.");
            
         if(selected.equals("Proceed into l3333333vel 1")||selected.equals("go back into l3v3l ONE"))
            level4_hallway2();
            
         if(selected.equals("Go forward"))
            level4_hallway3();
         
         if(selected.equals("Go farther down the hall")||selected.equals("go back down this Dea- Hallway"))
            level4_hallway4();
            
         if(selected.equals("Go through the wall")||selected.equals("Return to that strange shadow room"))
            level4_hallway5();
            
         if(selected.equals("try to go back"))
            exit(b, "You died... by losing your head, ha ha ha ha ha.");
            
         if(selected.equals("Go through the front door")||selected.equals("Go back to the weapon")||selected.equals("YOU MUST DESTROY HIM, KEEP GOING"))
            level4_hallway6();
         
         if(selected.equals("Progress down the hall")||selected.equals("Return to that hall behind you"))
            level4_hallway7();
         
         if(selected.equals("Grab the strange weapon"))
            takeItem("Strange Weapon");
         
         if(selected.equals("Don't give up and push forward"))
            level4_hallway8();
            
         if(selected.equals("Unlock the door with key, and go through")&& haveItem("Strange Weapon"))
            lvl444_rm1();
            
         if(selected.equals("Try to pick it with the scissors")||selected.equals("Try to blow it open with the sound weapon")||selected.equals("Try to make a hole with the strange weapon")||selected.equals(""))
            storyTextArea.setText(storyTextArea.getText()+"That item didn't work, cmon, how do I open it");
         
         if(selected.equals("Go on to level 2"))
            lvl444_rm2();
            
         if(selected.equals("Go on to level 4"))
            lvl444_rm3();
         
         if(selected.equals("Go where no other has gone")||selected.equals("Jump to the front platform"))
            lvl444_rm4();
            
         if(selected.equals("Jump to the right platform"))
            platformRight();
            
         if(selected.equals("Jump to the left platform"))
            platformLeft();
            
         if(selected.equals("Jump to the rear platform"))
            platformRear();
            
         if(selected.equals("Fire the Strange Weapon at it"))
         {
            playSFX("End Audio.wav",6f);
         }
         
         if(selected.equals("Activate Self Destruction")&& numberOfDestructs < 4)
         {
            numberOfDestructs += 1;
            storyTextArea.setText(storyTextArea.getText()+" \n \nThe Overseer: Great now keep going, get the rest");
         }
         
         if(selected.equals("Fire the Strange Weapon from the right")||selected.equals("Fire the Strange Weapon from the left"))
            storyTextArea.setText(storyTextArea.getText()+"\n \nThat shot didn't seem to do anything, maybe from a different angle");
            
         if(selected.equals("Go back to your friends house"))
            badEnding();
            
         if(selected.equals("Rest, and celebrate victory"))
            System.exit(0);
            
      }
   }
   
   
   //all LEVEL STUFF BELOW
   public void badEnding()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "friend house.jpeg", picWidth, HEIGHT, 0,22);
      drawScreen();
      b.dispose();
      storyTextArea.setText("You are free, and able to live your life. Still haunted by the memories, you get flashbacks. Only to one day learn, they weren't flashbacks, but instead the future that would soon unfold. \n \nThe world would be consumed by the ever growing Observer, and the end of days would near");
      String[] newItems = {" ","Leave, and go touch some grass"};
      setChoices(newItems);
   }
   
   public void outside()
   {
      playSFX("MainMusic.wav", 1.0f);
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "friend house.jpeg", picWidth, HEIGHT, 0,22);
      drawScreen();
      b.dispose();
      storyTextArea.setText("You are about to go to your friends house, you are really excited. You begin walking up to the house, only to -");
      String[] newItems = {" ","You have NO CHOICES","Leave, and go touch some grass"};
      setChoices(newItems);
   }
   //LEVEL 0 UMM LEVELS
   public void level0_ENT()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0.png", picWidth, HEIGHT, 0,22);
      drawScreen();
      b.dispose();
      if(haveItem("Sound Weapon"))
      {
         storyTextArea.setText("I feel like there is something behind this wall, I wonder why, let me try with the sound weapon");
         String[] newItems = {" ","Use the sound weapon on the wall","Walk left","Walk forward"};
         setChoices(newItems);
      }
      else
      {
         storyTextArea.setText("You see old yellow wallpaper, and the bright buzzing lights overhead. The smell of the moist, mouldy carpet floods your sinuses. All you see is a seemingly endless maze of halls. \n \n \nThe Observer: I am the Observer, do not worry we won't kill you... yet, so stay a while, oh and please let my pets play with your innards, they are very hungry");
         String[] newItems = {" ","Walk left","Walk forward","Leave, and go touch some grass"};
         setChoices(newItems);
      }
   }
   public void peeOnFloor()
   {
      usedItem("Sound Weapon", "You used the sound weapon to open up a secret room");
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "commodoreFish.jpg", picWidth/2, HEIGHT/2, 120,100);
      playSFX("PeeOnFloor.wav", 2f);
      storyTextArea.setText(storyTextArea.getText()+"\n \n \n \nYou feel yourself beginning to laugh, 5 days later you continue to laugh, even after your body decaying you continue to laugh, THE FISH HAS CAUGHT YOU, AND YOU ARE NOW DEAD.");
      exit(b, "Welcome to the endless prison of laughter");
      drawScreen();
      b.dispose();
   }
   public void level0_hallway1()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0hall1.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("All you see is more yellow wallpaper and the buzzing lights still irritating the hell out of you");
      if(!(haveItem("Scissors")))
      {
         storyTextArea.setText(storyTextArea.getText()+"\n\nYou see an item, it appears to be scissors... how odd");
         String[] newItems = {" ","Go through the opening","Take the scissors","Leave the scissors","go back"};
         setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ","Go through the opening","go back"};
         setChoices(newItems);
      }
      drawScreen();
      b.dispose();
   }
   public void level0_hallway2_2()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0hall2.2.png", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("You can see the area is blurry, but two seconds ago it was almost crystal clear. You can feel your sanity draining 'Is there anyone here... anyone left?' \n \n Unknown Voice: Which way shall WE go");
      String[] newItems = {" ", "Go down Left hall", "Go down Right hall","Walk back"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   public void level0_hallway2_4()//went left
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0hall2.4.png", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("You can't find another path you try to go back but can't THE ENTRANCE IS GONE! \n \n \n \nCOME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US COME TO US");
      if(haveItem("Sound Weapon"))
      {
         storyTextArea.setText(storyTextArea.getText()+"I might have one way out, I hope it works");
         String[] newItems = {" ","Use the Sound Weapon against the wall","Use the sound weapon against yourself"};
         setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ","FEEL OUR PAIN","FEEL IMPRISONNED","Bash your head against the wall"};
         setChoices(newItems);
      }
      drawScreen();
      b.dispose();
   }
   public void level0_hallway2_6()//went right
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0hall2.6.png", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("Unknown Creature: This place can be your tomb.... and most likely will,\n \n \nThe Observer: SILENCE I AM THE ONE WHO SPEAKS TO THE INTRU- GUESTS, NOT YOU \n \n \nWho or what was that, you feel cold, and like someone is watching from all directions. ");
      String[] newItems = {" ","Go through the narrow hallway","Go through the far hallway","Go through the way you came"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   public void level0_hallway2_8()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0hall2.8.png", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: I am sorry for yelling - \n \n Random Raspy voice: WE ARE THE LOST, HUNTED, AND A COMBINE OF ABOMINATIONS ALL MUST JOIN US, INCLUDI- \n \n \nThe Observer: at you, these creatures want all the power we have, but we do not give what is not to be theirs, and it shall stay with us. \n \n \nWhat is that on the floor, is that a satellite dish");
      if(!(haveItem("Sound Weapon")))
      {
         addPicture(b, "sound weapon.png", picWidth/4, HEIGHT/4, 20,500);
         String[] newItems = {" ","Grab the mini satelite dish","Go back down the hallway"};
         setChoices(newItems);
      }
      else if(haveItem("Sound Weapon"))
      {
         storyTextArea.setText(storyTextArea.getText()+" \n \nYou have already gotten everything you needed from here");
         String[] newItems = {" ","Go back down the hallway"};
         setChoices(newItems);
      }
      
      drawScreen();
      b.dispose();
   }
   public void level0_hallway2_9()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0hall2.9.png", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("You have found one of our pets, so please let it consume you, allow all of your cell, all your life, be used to aid your journey in exiting this prison");
      String[] newItems = {" ","Give up LET US CONJOIN IN ONE WE ARE THE UNITED DEMONS OF THE SUBROOMS"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   public void level0_hallway2()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0hall2.jpeg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("My heart is racing, maybe Matteo is here. He could help. You call his name twice. All you get back is the echo's of your footsteps. 'CoME ViSIT US to BE FREE FROM PAIN'");
      String[] newItems = {" ","Go Forward","go back"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   public void level0_hallway3()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0hall3.jpg", picWidth, HEIGHT, 0,22);
      
      if(haveItem("Paper"))
      {
         storyTextArea.setText("I hate how empty it is, and quiet, I feel like someone's constantly watching me");
         String[] newItems = {" ","Keep following the %$# PRISON &$#","Go Back"};
         setChoices(newItems);
      }
      else if(!(haveItem("Paper")))
      {
         storyTextArea.setText("I rounded the corner and found another room but I found somthing on the floor, a paper.");
         String[] newItems = {" ","Take the Paper","Keep following the %$# PRISON &$#","Go Back"};
         setChoices(newItems);
      }
      drawScreen();
      b.dispose();
   }
   public void Note()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Old Paper_Backrooms.png", (picWidth/2)+70, HEIGHT, 200, 22);
      storyTextArea.setText("\n You wonder who killed this knight, was it a hero or villain and where is it's body?");
      text(b, new Color(95,57,57),"Comic Sans MS", Font.BOLD,"Here lies the DeMO- the Blue Knight,",24,210,300);
      text(b, new Color(95,57,57),"Comic Sans MS", Font.BOLD,"he who has slain, has been slain and",24,210,330);
      text(b, new Color(95,57,57),"Comic Sans MS", Font.BOLD,"now thou spirit shall be lost to the",24,210,360);
      text(b, new Color(95,57,57),"Comic Sans MS", Font.BOLD,"monsters of the backrooms",24,210,390);
      drawScreen();
      b.dispose();
   }
   public void level0_hallway4()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0hall4.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("You notice the way the area looks, is changing from a clear area to what almost looks like a low quality image... you cant find an exit but an idea randomly pops in your head to cut a hole in one of the pillars");
      if(haveItem("Scissors"))
      {
         String[] newItems = {" ","Cut a large hole in wallpaper on far left pillar","Cut a large hole in wallpaper on far right pillar","Cut a large hole in wallpaper on mid left pillar","Cut a large hole in wallpaper on mid right pillar","Cut a large hole in wallpaper on front left pillar","Cut a large hole in wallpaper on front right pillar","Go to previous area"};
         setChoices(newItems);
      }
      else if(!(haveItem("Scissors")))
      {
         String[] newItems = {" ","Look for another way out","Go to previous area"};
         setChoices(newItems);
      }
      drawScreen();
      b.dispose();
   }
   public void level0_hallwayEXT()//mid right pillar leads to exit
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl0hall4.jpg", picWidth, HEIGHT, 0,22);
      object('r',b, Color.white,320,200,60,200,0);
      storyTextArea.setText("You cut a large hole in the mid right pillar, big enough for you to fit through, whats odd though is the hole is a different size then what you cut, in fact it's not even attached to the pillar at all!");
      String[] newItems = {" ","Go through it(you wont be able to return back here)","Go to previous area"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   
   //LEVEL 2 LEVELS I GUESS
   public void level2_hallwayENT()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl2_hall_ENT.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: Welcome to level 2, if you want to know where level 1 is, well... we consumed it when we cloned our old home...");
      String[] newItems = {" ","Continue forward", "Continue backward"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   
   public void level2_hallway1()//went backward
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl2_hall1.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("It's almost a dead end but there is a ladder, but there is a trap door in the way, damnit");
      if(haveItem("Bobby Pin"))
      {
         String[] newItems = {" ","Unlock the trapdoor","Go back to where you teleported"};
         setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ","Try opening the trapdoor", "Go back to where you teleported"};
         setChoices(newItems);
      }
      drawScreen();
      b.dispose();
   }
   
   public void level2_hallway2()//went forward
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl2_hall2.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: We are pleased to have you here, you are going to help us consume this level and grow in power. \n \n \nYou: Ok listen here Mr.Observer, I dont know what you want this thing for, but I will find a way to stop you and escape");
      String[] newItems = {" ","Keep on going forward", "Go back to where you teleported"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   
   public void level2_hallway3()//keep on going forward
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl2_hall3.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: I showed you mercy, treated you as a guest and THIS IS WHAT YOU GIVE ME \n \n \nYou: You litterally said your going to eat me or something.");
      String[] newItems = {" ","YOU CAN ONLY GO ONE WAY?!", "Return to where you were last"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   
   public void level2_hallway4()//You can only go one way
   {
      Graphics2D b = buffer.createGraphics();
      int randNum = rG.nextInt(100);
      addPicture(b, "Backroomslvl2_hall4.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: I am sorry I had frightened you, MY INTENTION WAS TO TORMENT YOU WITH FEAR AND ALLOW THE FEAR MISGUIDE YOU \n \n \nYou: If that is your plan, than your failing, cause I am not afraid of you!! \n \n \nThe Observer: Well then my plan is going perfectly...");
      if(randNum >= 42 && randNum <=63)
      {
         String[] newItems = {" ", "Challenging me is how I kill you", "Go down the narrow hallway to the left", "No Clip to level 4", "GO BACK IF YOU WISH TO LIVE"};
         setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ", "Challenging me is how I kill you", "Go down the narrow hallway to the left", "GO BACK IF YOU WISH TO LIVE"};
         setChoices(newItems);
      }
      drawScreen();
      b.dispose();
   }
   
   public void level2_hallway5()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl2_hall5.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("You found a key attached to the pipe, you tried ripping it off the wall but couldn't, maybe if you had some sort of tool to break the metal wire. \n \n \nThe Observer: Go through that door so we can finally meet... and become one.");
      if(haveItem("Scissors"))
      {
         if(haveItem("Bolt Cutters"))
         {
            String[] newItems = {" ","Break the wire with the bolt cutters","Try to go through the exit door","Go back down the narrow hallway"};
            setChoices(newItems);
         }
         else
         {
            String[] newItems = {" ","Use the scissors to break the wire","Try to go through the exit door","Go back down the narrow hallway"};
            setChoices(newItems);
         }
      }
      else if(haveItem("Bolt Cutters"))
      {
         String[] newItems = {" ","Break the wire with the bolt cutters","Try to go through the exit door","Go back down the narrow hallway"};
         setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ","Try to go through the exit door","Go back down the narrow hallway"};
         setChoices(newItems);
      }
      drawScreen();
      b.dispose();
   }
   
   public void level2_hallway6()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl2_hall6.jpg", picWidth, HEIGHT, 0,22);
      drawScreen();
      b.dispose();
      if(haveItem("Bolt Cutters"))
      {
         storyTextArea.setText("The Observer: My goals are beyond your understanding, I will gain control, AND I WILL OVERPOWER MY FORMER MASTER AND HIS MINIONS");
         String[] newItems = {" ","Keep going towards the white light", "Try using the bolt cutters on one of the pebbles", "Go back down this ENDLESS HALL"};
         setChoices(newItems);
      }
      else
      {
         storyTextArea.setText("You see a weird light, but also some bolt cutters, maybe it could be useful...\n \n \nUnknown Voice: Stop him... you must not let him grow any mo- \n \n \nThe Observer: SILENCE! I need you to stop, and allow me to gain control of this prison");
         String[] newItems = {" ","Keep going towards the white light", "Grab the bolt cutters", "Go back down this ENDLESS HALL"};
         setChoices(newItems);
      }
   }
   
   public void level2_hallway7()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl2_hall7.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("Your in a narrow hall, and it is really hot. You think it's the pipes overheating \n \n \nYou: Mr. Observer, if you think the heat will kill me, then you got another thing comin, and it isn't in your favour.");
      String[] newItems = {" ","Tap the pipes to see how hot they are", "Keep on going down this heated hallway","Go back to where you think is safe"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   
   public void level2_hallwayEXT()
   {
      Graphics2D b = buffer.createGraphics();
      object('r',b,Color.black,0,22,picWidth,HEIGHT,0);
      storyTextArea.setText("The Observer: Thanks to you, I can now consume this level, and you have fallen where I began... where we will begin. I will admit though... you survived far longer than I expected.");
      String[] newItems = {" ","Become his rival", "DIE DIE DIE DIE DIE DIE DIE DIE DIE DIE","Challenge Him."};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   
   //LEVEL 4 LEVELS... AGAIN
   public void level4_ENT()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl4_ENT.jpg", picWidth, HEIGHT, 0,22);
      level4 = true;
      storyTextArea.setText("The Observer: Welc0me to your l@s& St@n^d, th!s l&vel wi)l be y@up gr@>e nO(h!ng c&n s%ve YOU KNOW MY PETS OWN THIS LEVEL AND SO DOES MY COnSumti&n, ! w!(ll) a(d)d y(ou) to MY COLLECTION OF PET F@@D \n \n \nYou: 1 28 643943 83 92160 %$%& I WANT TO SPEAK, oh im speaking, what are you doing to me!? \n \n \nThe Observer: I am consuming this place, and because of this, I am going to grow in power all thanks to my former master.\n \n \n$#^%# : I have opened two doors to your left and right, they can take you back to the other levels before you continue forward, just know once you exit this inbetween levels area, you wont be able to come back.");
      String[] newItems = {" ","Go down left door(level 0)","Go down right door(level 2)","Go down the door behind you","Proceed into FR33D0M"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   
   public void level4_hallway1()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl4_hallway1.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: Incr3di9le yo@ m@de it h3re, as you can see th&s leveeeeel is br&ken, CoRRRRRRRupted, Fr@ctu7ed. You: And im supposed to join it, well im not, so don't get your hopes up!");
      String[] newItems = {" ","Proceed into l3333333vel 1","Go through the front door","kill yourself"};
      setChoices(newItems);
      drawScreen();
      randAttack();
      b.dispose();
   }
   
   public void level4_hallway2()//Proceed into level 1
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl1_Area1.png", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: Oh wait I missed this part of level 1, well maybe its time I consume it, SO GET OUT");
      String[] newItems = {" ","Go forward","try to go back","kill yourself"};
      setChoices(newItems);
      drawScreen();
      randAttack();
      b.dispose();
   }
   public void level4_hallway3()//Go forward
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "lvl4_3.png", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: I've had enough of you guests, maybe it's time I-\n \n \n The Overseer: He has too much power, YOU MUST STOP HIM");
      String[] newItems = {" ","Go farther down the hall","go back into l3v3l ONE","kill yourself"};
      setChoices(newItems);
      drawScreen();
      randAttack();
      b.dispose();
   }
   
   public void level4_hallway4()//go farther down hall
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "lvl4_5.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Overseer: I built this place, to encase him, to prevent, to KILL him. But he outgrew this thick shell, and now has the ability to consume his prison and convert it into his home to destroy yours, and mine.");
      if(haveItem("Scissors"))
      {
         String[] newItems = {" ","Go through the wall","try to go back","kill yourself"};
         setChoices(newItems);
      }
      else
      {
         storyTextArea.setText(storyTextArea.getText()+"It's a dead end, I might have to go back");
         String[] newItems = {" ","try to go back","kill yourself"};
         setChoices(newItems);
      }
      drawScreen();
      randAttack();
      b.dispose();
   }
   public void level4_hallway5()//go through wall or 
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "lvl4_4.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("You see a shadow, but no one is actually there, you are beginning to get scared, and the constant urge to kill yourself grows. \n \n \nThe Observer: You m#st not pr0ce33d f@rw4rd, 4 my p3ts w1ll k1ll yu");
      String[] newItems = {" ","YOU MUST DESTROY HIM, KEEP GOING","go back down this Dea- Hallway","kill yourself"};
      setChoices(newItems);
      drawScreen();
      randAttack();
      b.dispose();
   }
   
   public void level4_hallway6()//You must destroy him
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "lvl4_6.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("There is something on the flo-. \n \n \nThe Overseer: grab that item, you will need it to kill my former apprentice");
      if(haveItem("Strange Weapon"))
      {
         String[] newItems = {" ","Progress down the hall","kill yourself"};
         setChoices(newItems);
      }
      else
      {
         String[] newItems = {" ","Grab the strange weapon","Progress down the hall","Return to that strange shadow room","kill yourself"};
         setChoices(newItems);
      }
      drawScreen();
      randAttack();
      b.dispose();
   }
   
   public void level4_hallway7()//Progress down the hall
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "lvl4_7.png", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("Unknown: Free us \n \n \nMore Unknown people: FREE US ALL PLEASE \n \n \nMary Joe: My baby wh3r3 is h% I w@nt &&&& m7 b1b7 BAAAAAAAAAAAAAAA\n \n \nYou: What is going on... why is everyone so damn loud");
      String[] newItems = {" ","Don't give up and push forward","Go back to the weapon","kill yourself"};
      setChoices(newItems);
      drawScreen();
      randAttack();
      b.dispose();
   }
   
   public void level4_hallway8()//Dont give up push forward
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "lvl4_8.png", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Overseer: YES THERE IT IS, you will need a key to open it, and don't forget to have the weapon. This is where we will face our foe, and defeat him");
      if(haveItem("Shadow Key"))
      {
         String[] newItems = {" ","Unlock the door with key, and go through","Return to that hall behind you","kill yourself"};
         setChoices(newItems);
      }
      else if(haveItem("Scissors"))
      {
         String[] newItems = {" ","Try to pick it with the scissors","Return to that hall behind you","kill yourself"};
         setChoices(newItems);
      }
      else if(haveItem("Sound Weapon"))
      {
         String[] newItems = {" ","Try to blow it open with the sound weapon","Return to that hall behind you","kill yourself"};
         setChoices(newItems);
      }
      else if(haveItem("Strange Weapon"))
      {
         String[] newItems = {" ","Try to make a hole with the strange weapon","Return to that hall behind you","kill yourself"};
         setChoices(newItems);
      }
      drawScreen();
      randAttack();
      b.dispose();
   }
   
   //THE FINAL LEVEL, THE EPICENTER OF THE OBSERVER
   public void lvl444_rm1()//Use key to open door
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "lvl444_0.png", picWidth, HEIGHT, 0,22);
      
      if(haveItem("Paper"))
      {
         storyTextArea.setText("The Overseer: That paper you had found, was written by the Observer when he killed my knight. Whom was supposed to slay him, but was slain.\n \n \n");
      }
      else
      {
         storyTextArea.setText("The Overseer: You are close, HURRY there is little time left before he finds you");
      }
      
      String[] newItems = {" ","Go on to level 2","Go back through the door"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   
   public void lvl444_rm2()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "lvl444_2.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: There you are, your lucky the pets don't come here... they are quite freightened of me. \n \n \nYou: I will stop you, and I will defeat you... just as the Overseer said. \n \n \nThe Observer: Fool, he has no control over this place, and neither do you, so give up now and I will allow you safe passage");
      String[] newItems = {" ","Go on to level 4","Go back through the door"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   
   public void lvl444_rm3()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "lvl444_4.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Overseer: This is it my friend, I will not be able to influence the fight, I can only guide you. He will not attack as long as you do not threaten him");
      String[] newItems = {" ","Go where no other has gone","Go back through the door"};
      setChoices(newItems);
      drawScreen();
      b.dispose();
   }
   
   //BOSS ARENA KINDA
   public void lvl444_rm4()
   {
      lvl444 = true;
      //playSFX("FinalFight.wav",5f);
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "The Observer_idle.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: It is such an honour to meet you, somehow you have made it farther than anyone else. You should be proud, although I do not understand why your not. \n \nYou notice how fleshy it is, how large, powerful it is. You also notice jump pads to other side areas, maybe I could use them\n \n \nThe Observer: I can grant you passage back to your world, right where you last were just let me know, and you will be brought back\n \n \nThe Overseer: DO NOT ACCEPT I BROUGHT YOU HERE FOR A REASON, AND YOU MUST COMPLETE YOUR MISSION");
      if(haveItem("Strange Weapon"))
      {
         storyTextArea.setText(storyTextArea.getText()+"\n \nThe Overseer: Shoot your large weapon at it from 2 angles, destroy the core to destroy him.");
         String[] newItems = {" ","Jump to the left platform","Jump to the right platform","Go back to your friends house"};
         setChoices(newItems);
      }
      else
      {
         storyTextArea.setText(storyTextArea.getText()+"\n \nThe Overseer: You need to activate battery A self destruction sequence");
         String[] newItems = {" ","Activate Self Destruction","Jump to the left platform","Jump to the right platform","Go back to your friends house"};
         setChoices(newItems);
      }

      drawScreen();
      b.dispose();
   }
   
   public void platformRight()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "control panel.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Overseer");
      if(haveItem("Strange Weapon"))
      {
         storyTextArea.setText("The Overseer: Shoot your large weapon at it from 2 angles, destroy the core to destroy him.");
         String[] newItems = {" ","Fire the Strange Weapon from the right","Jump to the front platform","Jump to the rear platform"};
         setChoices(newItems);
      }
      else
      {
         storyTextArea.setText("The Overseer: You need to activate battery B self destruction sequence");
         String[] newItems = {" ","Activate Self Destruction","Jump to the front platform","Jump to the rear platform"};
         setChoices(newItems);
      }
      drawScreen();
      randAttack();
      b.dispose();
   }
   
   public void platformLeft()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "control panelC.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Overseer");
      if(haveItem("Strange Weapon"))
      {
         storyTextArea.setText("The Overseer: Shoot your large weapon at it from 2 angles, destroy the core to destroy him.");
         String[] newItems = {" ","Fire the Strange Weapon from the left","Jump to the front platform","Jump to the rear platform"};
         setChoices(newItems);
      }
      else
      {
         storyTextArea.setText("The Overseer: You need to activate battery C self destruction sequence");
         String[] newItems = {" ","Activate Self Destruction","Jump to the front platform","Jump to the rear platform"};
         setChoices(newItems);
      }
      if(numberOfShots == 2)
         storyTextArea.setText(storyTextArea.getText()+"\n \nThe Overseer: YES THATS IT, quick shoot at it from the front, and he will die");
      drawScreen();
      randAttack();
      b.dispose();
   }
   
   public void platformRear()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "control panelD.jpg", picWidth, HEIGHT, 0,22);
      if(haveItem("Strange Weapon"))
      {
         storyTextArea.setText("The Overseer: Shoot your large weapon at it from 2 angles, destroy the core to destroy him.");
         String[] newItems = {" ","Fire the Strange Weapon at it","Jump to the left platform","Jump to the right platform"};
         setChoices(newItems);
      }
      else
      {
         storyTextArea.setText("The Overseer: You need to activate battery D self destruction sequence");
         String[] newItems = {" ","Activate Self Destruction","Jump to the left platform","Jump to the right platform"};
         setChoices(newItems);
      }
      if(numberOfShots == 2)
         storyTextArea.setText(storyTextArea.getText()+"\n \nThe Overseer: YES THATS IT, quick shoot at it from the front, and he will die");
      drawScreen();
      randAttack();
      b.dispose();
   }

   //DRAW A PICTURE USING ELLIPSES,RECTANGLES,ETC.
   public void drawIntroPicture(Graphics2D b)
   {
      object('r',b,new Color(0,0,0),0,24,picWidth,HEIGHT,0);
      object('r',b,new Color(102,13,6),2,22,picWidth-10,HEIGHT-10,5);
      
      object('o',b,new Color(140,25,4),150,-250,400,400,40);
      object('o',b,Color.cyan,150,100,500,500,40);
      object('o',b,Color.black,150,200,600,600,40);
      triangle(b,new Color(255,53,18),200,500,400,500,300,300,0);
      triangle(b,new Color(255,53,18),400,500,600,500,500,300,0);
      triangle(b,new Color(255,53,18),300,300,500,300,400,100,0);
      text(b,Color.orange,"Courier New",Font.BOLD, "The Backrooms: In the Subrooms",47,6,700); //litterally words
      text(b,Color.black,"Chiller",Font.BOLD, "0",72,480,450);
      text(b,Color.black,"Chiller",Font.BOLD, "2",72,270,450);
      text(b,Color.black,"Chiller",Font.BOLD, "4",72,370,250);
      text(b,new Color(13,13,13),"Chiller",Font.BOLD, "444",72,350,380);
      
   }
   //CREATES EITHER A RECT, CIRCLE OR A LINE
   public void object(char shape, Graphics2D b, Color c, int x, int y, int w, int h, int strokeSize)
   {
      b.setColor(c);
       switch (shape) {
           case 'r':
               if(strokeSize > 0)
               {
                   b.setStroke(new BasicStroke(strokeSize));
                   b.drawRect(x,y,w,h);
               }
               else
                   b.fillRect(x,y,w,h);
               break;
           case 'o':
               if(strokeSize > 0)
               {
                   b.setStroke(new BasicStroke(strokeSize));
                   b.drawOval(x,y,w,h);
               }
               else
                   b.fillOval(x,y,w,h);
               break;
           case 'L':
               b.setStroke(new BasicStroke(strokeSize));
               b.drawLine(x,y,w,h);
               break;
           default:
               break;
       }
   }
   
   //draws a semi circle
   public void arc(Graphics2D b, Color c, int x, int y, int w, int h, int startAngle, int angleSize, int strokeSize)
   {
      b.setColor(c);
      
      if(strokeSize > 0)
      {
         b.setStroke(new BasicStroke(strokeSize));
         b.drawArc(x,y,w,h,startAngle,angleSize);
      }
      else
         b.fillArc(x,y,w,h,startAngle,angleSize);
   }
   
   //writes a line of text
   public void text(Graphics2D b, Color c, String font, int style, String sentence, int fontSize, int x, int y)
   {
      b.setColor(c);
      
      Font wrdStyle = new Font(font, style, fontSize);
      b.setFont(wrdStyle);
      b.drawString(sentence, x, y);
   }
   
   //draw a triangle
   public void triangle(Graphics2D b, Color c, int x1, int y1, int x2, int y2, int x3, int y3, int strokeSize)
   {
      b.setColor(c);

      int[] xValues = {x1, x2, x3};
      int[] yValues = {y1, y2, y3};
      
      if (strokeSize > 0)
      {
         b.setStroke(new BasicStroke(strokeSize));
         b.drawPolygon(xValues, yValues, 3);
      }
      else
         b.fillPolygon(xValues, yValues, 3);
   }
   
   //draw 5 point polygon
   public void polygon(Graphics2D b, Color c, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5, int strokeSize)
   {
      b.setColor(c);

      int[] xValues = {x1, x2, x3, x4, x5};
      int[] yValues = {y1, y2, y3, y4, y5};
      
      if (strokeSize > 0)
      {
         b.setStroke(new BasicStroke(strokeSize));
         b.drawPolygon(xValues, yValues, 5);
      }
      else
         b.fillPolygon(xValues, yValues, 5);
   }
   //STARTS GAME
   public void beginGame()
   {
      Graphics2D b = buffer.createGraphics();
      drawIntroPicture(b);
      b.dispose();
   }
} //end of class AdventureGame