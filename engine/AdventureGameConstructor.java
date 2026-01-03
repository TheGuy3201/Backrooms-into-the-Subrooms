package engine;

import audiomanager.AudioController;
import enemies.CreatureController;
import engine.actionperformed.ChoiceController;
import inventory.InventoryAccess;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import levels.GameLevels;

public class AdventureGameConstructor extends JFrame implements ActionListener
{
    ////////////////////////// FIELDS ////////////////////////////
   static final long serialVersionUID = 1;
   
   // Picture area details
   public BufferedImage buffer;   // instance variable for double buffering
   final int WIDTH = 1250;   //to fit with resolution of monitor

   public final int HEIGHT = 850;
   public int picWidth;

   // Set up fonts for the picture area
   Font chillerFont = new Font("Chiller", Font.PLAIN, 48); 
   public Random rG = new Random();
   // GUI components that will need to be accessed from many methods
   public static JPanel interactionPanel;
   public String[] choices;
   public JComboBox<String> choiceComboBox;
   public static JTextArea storyTextArea;

   public static JTextArea inventoryTextArea;
   public JPanel picturePanel;

   public static InventoryAccess iA = new InventoryAccess();
   public static CreatureController cC;
   public static ObjectWriter oW = new ObjectWriter();
   public static AudioController aC = new AudioController();
   public static ChoiceController chC;
   public static GameLevels gL;
   
    ///////////////////////// CONSTRUCTOR /////////////////////////
    public AdventureGameConstructor()
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
      // Action listener will be added after chC is initialized
      
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
      
      // Initialize components that need 'this' reference after construction
      cC = new CreatureController(this);
      chC = new ChoiceController(this);
      gL = new GameLevels(this);
      
      // Now add the action listener after chC is initialized
      choiceComboBox.addActionListener(chC);
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
      
      if(g != null)
      {
         g.drawImage(buffer,10,10,this);
         Toolkit.getDefaultToolkit().sync();
         g.dispose();
      }
   }
   
   //CLEAR THE BUFFER
   public void clearBuffer()
   {
      Graphics2D b = buffer.createGraphics();
      b.setColor(Color.BLACK);
      b.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
      b.dispose();
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
      catch(InterruptedException e)
      {
         // Interrupted while painting
      }
   }

   //STARTS GAME
   public void beginGame()
   {
      Graphics2D b = buffer.createGraphics();
      GameLevels.drawIntroPicture(b, this);
      b.dispose();
   }

   //THIS WILL CLOSE GAME
   public void exit(Graphics2D b, String deathMessage)
   {
      oW.text(b,Color.red,"Chiller",Font.BOLD, deathMessage,52,40,150);
      drawScreen();
      int answer = JOptionPane.showConfirmDialog(null,"Are you sure you want to give up your life for us?"); //The Icon Thing
      if (answer == 0) 
         System.exit(0);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      chC.actionPerformed(e);
   }
}