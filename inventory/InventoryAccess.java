package inventory;

import engine.AdventureGameConstructor;
import static engine.AdventureGameConstructor.aC;
import static engine.AdventureGameConstructor.interactionPanel;
import static engine.AdventureGameConstructor.inventoryTextArea;
import static engine.AdventureGameConstructor.storyTextArea;


public class InventoryAccess {

    //LIST OF ITEMS AND VARIABLES
   String[] collectables = {"Scissors","Strange Weapon","Sound Weapon","Paper","Bolt Cutters","Shadow Key"};
   boolean[] haveCollected = {false,false,false,false,false,false};
   //Sound Weapon Stuff
   public boolean soundWeaponAvailable = true;
   int soundWeaponUses = 4;
   //Boss Fight stuff
   

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
            AdventureGameConstructor.storyTextArea.setText("You took the "+collectables[i]+" and put it in your pocket");
            setInventory();
         }
      }
   }
   
   //USES THE ITEM SPECIFIED
   public void usedItem(String item, String usageMessage) //Ask if I can remove 'Need: ' part of code
   {
      if(item.equals("Sound Weapon"))
      {
         aC.playSFX("SoundWeaponSFX.wav", 2f);
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
         aC.playSFX("Item_break1.wav", 2f);
         storyTextArea.setText(usageMessage);
         for(int i = 0 ; i<collectables.length ; i++)
         {
            haveCollected[i] = false;
            setInventory();
         }
      }
      if(item.equals("Shadow Key"))
      {
         aC.playSFX("doorcreak1.wav", 2f);
         storyTextArea.setText(usageMessage);
         for(int i = 0 ; i<collectables.length ; i++)
         {
            haveCollected[i] = false;
            setInventory();
         }
      }
   }
}
