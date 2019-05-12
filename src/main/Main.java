package main;

import objects.procesor.Procesor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {

    ArrayList<Integer> instructions = new ArrayList<Integer>();

    instructions.add(0b00001_0001_00000000000000001011000);//def

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b10000_1001_0001_0000000000000000000);//lv

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b10010_1010_1001_0001_000000000000000);//xor

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b10001_0000_0001_1010_000000000000000);//sv

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b11111_0000_00000000000000000000000); //END

    ArrayList<Integer> data = new ArrayList<Integer>();
    for(int i = 0; i < 100; i++) {
      data.add(i);
    }

    Procesor procesor = new Procesor(instructions, data);
    procesor.runThreaded();



/*
    try {
      File input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna.jpg");
      BufferedImage image = ImageIO.read(input);
      int width = image.getWidth();
      int height = image.getHeight();

      int count = 0;

      for(int i=0; i<height; i++) {

        for(int j=0; j<width; j++) {

          count++;
          Color c = new Color(image.getRGB(j, i));
          System.out.println("S.No: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());
        }
      }

    } catch (Exception e) {
      System.out.println("S.No: failed to read");
    }

  }*/
  }
}
