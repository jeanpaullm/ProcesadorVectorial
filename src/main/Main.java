package main;

import objects.procesor.Procesor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {


    ArrayList<Integer> data = new ArrayList<Integer>();

    File input = null;
    BufferedImage  image = null;
    int width = 0;
    int height = 0;

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna.jpg");
      image = ImageIO.read(input);
    }catch(IOException e){
      System.out.println(e);
    }

    width = image.getWidth();
    height = image.getHeight();

    for(int i=0; i<height; i++) {
      for(int j=0; j<width; j++) {
        Color c = new Color(image.getRGB(j, i));
        data.add(c.getRed());
      }
    }


    ArrayList<Integer> instructions = new ArrayList<Integer>();

    instructions.add(0b00001_0010_0000000000000000_1111000);//XOR KEY

    for (int i = 0;  i < (height*width / 8); i++) {
      instructions.add(0b00001_0001_00000000000000000000000 + (i * 8));//def

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10000_1001_0001_0000000000000000000);//lv

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10010_1001_1001_0010_000000000000000);//xor

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10001_0000_0001_1001_000000000000000);//sv

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);
    }

    instructions.add(0b11111_0000_00000000000000000000000); //END


    Procesor procesor1 = new Procesor(instructions, data);
    procesor1.runThreaded();

    int count = 0;
    for(int i=0; i<height; i++) {
      for(int j=0; j<width; j++) {
        Color color = new Color(255,255,255);
        if(data.get(count) <= 255) {
          color = new Color(data.get(count),data.get(count),data.get(count));
        }
        else {
          System.out.println(count + " " + data.get(count));
        }
        image.setRGB(j, i, color.getRGB());
        count++;
      }
    }

    System.out.println("Almost almost there");

    try{
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/encripted_lenna.jpg");
      ImageIO.write(image, "jpg", input);
    }catch(IOException e){
      System.out.println(e);
    }

    Procesor procesor2 = new Procesor(instructions, data);
    procesor2.runThreaded();

    count = 0;
    for(int i=0; i<height; i++) {
      for(int j=0; j<width; j++) {
        Color color = new Color(255,255,255);
        if(data.get(count) <= 255) {
          color = new Color(data.get(count),data.get(count),data.get(count));
        }
        else {
          System.out.println(count + " " + data.get(count));
        }
        image.setRGB(j, i, color.getRGB());
        count++;
      }
    }

    System.out.println("Almost there");

    try{
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/unencripted_lenna.jpg");
      ImageIO.write(image, "jpg", input);
    }catch(IOException e){
      System.out.println(e);
    }

  }
}
