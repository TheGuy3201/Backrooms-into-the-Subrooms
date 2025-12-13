package engine;

import javax.swing.JFrame;

/****************************************************************
 * class with the main method and "instantiates" the 
 *       JFrame with all components.
 ****************************************************************/   
public class JoshuaAdventureGame12
{ 
   //static final long serialVersionUID = 12;
   
   //IN ALL HONESTY I DONT KNOW
   public static void main(String args[]) throws Exception 
   { 
      AdventureGameConstructor frame = new AdventureGameConstructor();
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.beginGame();
   }// end main method
}// end class 
