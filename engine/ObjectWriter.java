package engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ObjectWriter {

    //DRAWS ACTUAL IMAGES LIKE PNG AND JPEG
   public void addPicture(Graphics2D b, String picFileName, int w, int h, int x, int y)
   {
      BufferedImage img;
      Image newImage;
      try
      {
         img = ImageIO.read(getClass().getResource("/resources/"+picFileName));
         if(w != 0 && h != 0)
         {
            newImage = img.getScaledInstance(w,h,Image.SCALE_DEFAULT);
            b.drawImage(newImage, x, y, null);
         }
         else
            b.drawImage(img, x, y, null);
      }
      catch(IOException e)
      {
         String msg1 = "Runtime error caught in addPicture: \""+picFileName+"\" not found or read properly";
         String msg2 = "Make sure that your picture file is in the same folder as your .java file";
         System.out.println(msg1+"\n"+msg2);
         text(b,Color.orange,"Arial",Font.BOLD, msg1,14,10,50);
         text(b,Color.orange,"Arial",Font.BOLD, msg2,14,10,65);
      }
   }

   //CREATES EITHER A RECT, CIRCLE OR A LINE
   public void object(char shape, Graphics2D b, Color c, int x, int y, int w, int h, int strokeSize)
   {
      b.setColor(c);
       switch (shape) {
           case 'r' -> {
               if(strokeSize > 0)
               {
                   b.setStroke(new BasicStroke(strokeSize));
                   b.drawRect(x,y,w,h);
               }
               else
                   b.fillRect(x,y,w,h);
           }
           case 'o' -> {
               if(strokeSize > 0)
               {
                   b.setStroke(new BasicStroke(strokeSize));
                   b.drawOval(x,y,w,h);
               }
               else
                   b.fillOval(x,y,w,h);
           }
           case 'L' -> {
               b.setStroke(new BasicStroke(strokeSize));
               b.drawLine(x,y,w,h);
           }
           default -> {
           }
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

}
