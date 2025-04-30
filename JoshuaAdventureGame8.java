import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.util.Random;
 
/****************************************************************
 * class with the main method and "instantiates" the 
 *       JFrame with all components.
 ****************************************************************/   
public class JoshuaAdventureGame8
{ 
   static final long serialVersionUID = 1;
   
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
   Font smallArialFont = new Font("Arial", Font.BOLD, 20); 
   Font largeArialFont = new Font("Arial", Font.BOLD, 40); 
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
      setInventory();
      
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
   String[] collectables = {"Scissors","Bobby Pin","Sound Weapon","Paper","Almond Water"};
   boolean[] haveCollected = {false,false,false,false,false};
   //String[] exploredArea = {"level0_EXT"};
   //boolean[] haveExplored = {false};
   
   
   //INVENTORY THINGY
   public void setInventory()
   {
      String collected = "Collected: ";
      String missing = "Need: ";
      
      for(int i = 0 ; i<collectables.length ; i++)
         if(haveCollected[i])
            if(collected.equals("Collected: "))
               collected = collected + collectables[i];
            else
               collected = collected +", "+ collectables[i];
         else
            if(missing.equals("Need: "))
               missing = missing + collectables[i];
            else
               missing = missing + ", " + collectables[i];
      
      inventoryTextArea.setText(collected+"\n"+missing);
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
   
   public void takeItem(String item)
   {
      for(int i = 0 ; i<collectables.length ; i++)
      {
         if(item.equals(collectables[i]))
         {
            haveCollected[i] = true;
            storyTextArea.setText("You took the "+collectables[i]+" and put it in your pocket");
            setInventory();
         }
      }
   }
   
   
   //CREATES THE SCREEN STUFF
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
         File audioFile = new File (pathway);
         AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
         Clip clip = AudioSystem.getClip();
         clip.open(audioStream);
         FloatControl setVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
         setVolume.setValue(volume);
         clip.start();
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
         img = ImageIO.read(new File(picFileName));
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
   
   
   // public boolean haveLocated(String location)
//    {
//       for(int i = 0 ; i < exploredArea.length ; i++)
//          if(location.equals(exploredArea[i]))
//             return haveExplored[i];
//          return false;
//    }
//    public void explored(String location)
//    {
//       for(int i = 0 ; i<exploredArea.length ; i++)
//       {
//          if(location.equals(exploredArea[i]))
//          {
//             haveExplored[i] = true;
//          }
//       }
//    }
   
   
   //CREATURE ATTACK 'ANIMATION'
   public void creatureAttack(Graphics2D b, String picName, int w, int h, String audioName, int lengthOfTime)
   {
      for(int i = 0 ; i < lengthOfTime ; i++)
      {
         if(i < lengthOfTime && !(haveItem("Sound Weapon")))
         {
            int randX = rG.nextInt(picWidth-200);
            int randY = rG.nextInt(HEIGHT-200);
            addPicture(b, picName, w, h, randX, randY);
            playSFX(audioName, 6f);
            drawScreen();
         }
         
      }
      if(haveItem("Sound Weapon"))
         storyTextArea.setText(storyTextArea.getText()+"You fought the creatures off, but should be careful in case they return");
      else
      {
         String[] newItems = {" ","Accept your death?"};
         setChoices(newItems);
      }
   }
   
   
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
            exit(b, "Wow that was a loud death, thank you for dying");
            
         if(selected.equals("Accept your death?"))
            System.exit(0);
            
         if(selected.equals("You have NO CHOICES")||selected.equals("go back"))
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
         
         if(selected.equals("Grab the mini satelite dish"))
         {
            takeItem("Sound Weapon");
            storyTextArea.setText(storyTextArea.getText()+"What does this thing do?? Maybe I can get a signal out to get help!");
            String[] newItems = {" ","Test the item","Go back down the hallway"};
            setChoices(newItems);
         }
         
         if(selected.equals("Test the item"))
            soundWeapon("You used the sound weapon");
            
         if(selected.equals("Use the Sound Weapon against the wall"))
         {
            level0_hallway2_2();
            soundWeapon("You used the sound weapon\n \n \nYou ran out of the room as quick as you could when the wall broke down, you could go back in and use the sound weapon again if needed");
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
            
         if(selected.equals("Go through it(you wont be able to return back here)")||selected.equals("Go back to where you teleported"))
            level2_hallwayENT();
            
         if(selected.equals("Continue forward")) 
            level2_hallway2();
            
         if(selected.equals("Continue backward"))
            level2_hallway1();
            
         if(selected.equals("Try opening the trapdoor"))
            storyTextArea.setText(storyTextArea.getText()+"You couldn't unlock it, maybe there is a key or bobby pin somewhere");
            
         if(selected.equals("Unlock the trapdoor"))
            storyTextArea.setText(storyTextArea.getText()+"You managed to unlock it, the bobby pin might break after 2 more tries of opening other doors");
         
      }
   }
   public void soundWeapon(String usageMessage)
   {
      playSFX("SoundWeaponSFX.wav", 2f); 
      storyTextArea.setText(usageMessage);
   }
   
   
   //all LEVEL STUFF BELOW
   public void outside()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "friend house.jpeg", picWidth, HEIGHT, 0,22);
      drawScreen();
      b.dispose();
      storyTextArea.setText("You are about to go to your friends house, you are really excited. You begin walking up to the house, only to -");
      String[] newItems = {" ","You have NO CHOICES","Leave, and go touch some grass"};
      setChoices(newItems);
   }
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
      soundWeapon("You used the sound weapon to open up a secret room");
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "commodoreFish.jpg", picWidth/2, HEIGHT/2, 120,100);
      playSFX("PeeOnFloor.wav", 2f);
      storyTextArea.setText(storyTextArea.getText()+"\n \n \n \nYou feel yourself beginning to laugh, 5 days later you continue to laugh, even after your body decaying you continue to laugh, THE FISH HAS CAUGHT YOU, AND YOU ARE NOW DEAD.");
      exit(b, "Welcome to the endless prison of laughter, ha ha ha... ha");
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
         storyTextArea.setText(storyTextArea.getText()+"You have already gotten everything you needed from here");
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
   
   //LEVEL 2 UMM LEVELS LOL
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
   public void level2_hallway3()
   {
      Graphics2D b = buffer.createGraphics();
      addPicture(b, "Backroomslvl2_hall3.jpg", picWidth, HEIGHT, 0,22);
      storyTextArea.setText("The Observer: I showed you mercy, treated you as a guest and THIS IS WHAT YOU GIVE ME \n \n \nYou: You litterally said your going to eat me or something.");
      String[] newItems = {" ","Keep on going forward", "Go back to where you teleported"};//FINISH THIS THING HERE
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
      if(shape == 'r')
      {
         if(strokeSize > 0)
         {
            b.setStroke(new BasicStroke(strokeSize));
            b.drawRect(x,y,w,h);
         }
         else
            b.fillRect(x,y,w,h);
      }
      
      else if(shape == 'o')
      {
         if(strokeSize > 0)
         {
            b.setStroke(new BasicStroke(strokeSize));
            b.drawOval(x,y,w,h);
         }
         else
            b.fillOval(x,y,w,h);
      }
      else if(shape == 'L')
      {
         b.setStroke(new BasicStroke(strokeSize));
         b.drawLine(x,y,w,h);
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
   public void beginGame()
   {
      Graphics2D b = buffer.createGraphics();
      drawIntroPicture(b);
      b.dispose();
   }
} //end of class AdventureGame