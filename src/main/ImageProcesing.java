package main;

import objects.procesor.Procesor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageProcesing {

  public ImageProcesing() {
  }

  public void xor() {
    ArrayList<Integer> data = new ArrayList<Integer>();

    File input = null;
    BufferedImage image = null;
    int width = 0;
    int height = 0;

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna.jpg");
      image = ImageIO.read(input);
    } catch (IOException e) {
      System.out.println(e);
    }

    width = image.getWidth();
    height = image.getHeight();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color c = new Color(image.getRGB(j, i));
        data.add(c.getRed());
      }
    }


    ArrayList<Integer> instructions = new ArrayList<Integer>();

    instructions.add(0b00001_0010_0000000000000000_1111000);//XOR KEY

    for (int i = 0; i < (height * width / 8); i++) {
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
    }

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b11111_0000_00000000000000000000000); //END
    instructions.add(0b00000_0000_00000000000000000000000);


    Procesor procesor1 = new Procesor(instructions, data);
    procesor1.runThreaded();

    int count = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(255, 255, 255);
        if (data.get(count) <= 255) {
          color = new Color(data.get(count), data.get(count), data.get(count));
        } else {
          System.out.println(count + " " + data.get(count));
        }
        image.setRGB(j, i, color.getRGB());
        count++;
      }
    }

    System.out.println("Almost almost there");

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna_xor_encripted.jpg");
      ImageIO.write(image, "jpg", input);
    } catch (IOException e) {
      System.out.println(e);
    }

    Procesor procesor2 = new Procesor(instructions, data);
    procesor2.runThreaded();

    count = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(255, 255, 255);
        if (data.get(count) <= 255) {
          color = new Color(data.get(count), data.get(count), data.get(count));
        } else {
          System.out.println(count + " " + data.get(count));
        }
        image.setRGB(j, i, color.getRGB());
        count++;
      }
    }

    System.out.println("Almost there");

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna_xor_unencripted.jpg");
      ImageIO.write(image, "jpg", input);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public void rotate() {
    ArrayList<Integer> data = new ArrayList<Integer>();

    File input = null;
    BufferedImage image = null;
    int width = 0;
    int height = 0;

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna.jpg");
      image = ImageIO.read(input);
    } catch (IOException e) {
      System.out.println(e);
    }

    width = image.getWidth();
    height = image.getHeight();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color c = new Color(image.getRGB(j, i));
        data.add(c.getRed());
      }
    }


    ArrayList<Integer> instructions = new ArrayList<Integer>();

    instructions.add(0b00001_0010_0000000000000000_0000011);//Shift amount KEY

    for (int i = 0; i < (height * width / 8); i++) {
      instructions.add(0b00001_0001_00000000000000000000000 + (i * 8));//def

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10000_1001_0001_0000000000000000000);//lv

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10100_1001_1001_0010_000000000000000);//srvc

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10001_0000_0001_1001_000000000000000);//sv
    }

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b11111_0000_00000000000000000000000); //END
    instructions.add(0b00000_0000_00000000000000000000000);


    Procesor procesor1 = new Procesor(instructions, data);
    procesor1.runThreaded();

    int count = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(255, 255, 255);
        if (data.get(count) <= 255) {
          color = new Color(data.get(count), data.get(count), data.get(count));
        } else {
          System.out.println(count + " " + data.get(count));
        }
        image.setRGB(j, i, color.getRGB());
        count++;
      }
    }

    System.out.println("Almost almost there");

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna_srcv_encripted.jpg");
      ImageIO.write(image, "jpg", input);
    } catch (IOException e) {
      System.out.println(e);
    }

    instructions.clear();

    instructions.add(0b00001_0010_0000000000000000_0000011);//Shift amount KEY

    for (int i = 0; i < (height * width / 8); i++) {
      instructions.add(0b00001_0001_00000000000000000000000 + (i * 8));//def

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10000_1001_0001_0000000000000000000);//lv

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10101_1001_1001_0010_000000000000000);//slvc

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10001_0000_0001_1001_000000000000000);//sv
    }

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b11111_0000_00000000000000000000000); //END
    instructions.add(0b00000_0000_00000000000000000000000);

    Procesor procesor2 = new Procesor(instructions, data);
    procesor2.runThreaded();

    count = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(255, 255, 255);
        if (data.get(count) <= 255) {
          color = new Color(data.get(count), data.get(count), data.get(count));
        } else {
          System.out.println(count + " " + data.get(count));
        }
        image.setRGB(j, i, color.getRGB());
        count++;
      }
    }

    System.out.println("Almost there");

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna_srcv_unnencripted.jpg");
      ImageIO.write(image, "jpg", input);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public void add() {
    ArrayList<Integer> data = new ArrayList<Integer>();

    File input = null;
    BufferedImage image = null;
    int width = 0;
    int height = 0;

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna.jpg");
      image = ImageIO.read(input);
    } catch (IOException e) {
      System.out.println(e);
    }

    width = image.getWidth();
    height = image.getHeight();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color c = new Color(image.getRGB(j, i));
        data.add(c.getRed());
      }
    }


    ArrayList<Integer> instructions = new ArrayList<Integer>();

    for (int i = 0; i < (height * width / 8); i++) {
      instructions.add(0b00001_0001_00000000000000000000000 + (i * 8));//def

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10000_1001_0001_0000000000000000000);//lv

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10011_1001_1001_1010_000000000000000);//add

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10001_0000_0001_1001_000000000000000);//sv
    }

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b11111_0000_00000000000000000000000); //END
    instructions.add(0b00000_0000_00000000000000000000000);

    Procesor procesor1 = new Procesor(instructions, data);
    ArrayList<Integer> key = new ArrayList<>();
    key.add(123);
    key.add(250);
    key.add(56);
    key.add(98);
    key.add(20);
    key.add(83);
    key.add(150);
    key.add(200);
    procesor1.setPrivateKey(key);
    procesor1.runThreaded();

    int count = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(255, 255, 255);
        if (data.get(count) <= 255) {
          color = new Color(data.get(count), data.get(count), data.get(count));
        } else {
          System.out.println(count + " " + data.get(count));
        }
        image.setRGB(j, i, color.getRGB());
        count++;
      }
    }

    System.out.println("Almost almost there");

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna_add_encripted.jpg");
      ImageIO.write(image, "jpg", input);
    } catch (IOException e) {
      System.out.println(e);
    }

    instructions.clear();

    instructions.add(0b00001_0010_0000000000000000_0000011);//Shift amount KEY

    for (int i = 0; i < (height * width / 8); i++) {
      instructions.add(0b00001_0001_00000000000000000000000 + (i * 8));//def

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10000_1001_0001_0000000000000000000);//lv

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10111_1001_1001_1010_000000000000000);//subv

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10001_0000_0001_1001_000000000000000);//sv
    }

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b11111_0000_00000000000000000000000); //END
    instructions.add(0b00000_0000_00000000000000000000000);

    Procesor procesor2 = new Procesor(instructions, data);
    procesor2.setPrivateKey(key);
    procesor2.runThreaded();

    count = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(255, 255, 255);
        if (data.get(count) <= 255) {
          color = new Color(data.get(count), data.get(count), data.get(count));
        } else {
          System.out.println(count + " " + data.get(count));
        }
        image.setRGB(j, i, color.getRGB());
        count++;
      }
    }

    System.out.println("Almost there");

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna_add_unnencripted.jpg");
      ImageIO.write(image, "jpg", input);
    } catch (IOException e) {
      System.out.println(e);
    }
  }


  public void custom() {
    ArrayList<Integer> data = new ArrayList<Integer>();

    File input = null;
    BufferedImage image = null;
    int width = 0;
    int height = 0;

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna.jpg");
      image = ImageIO.read(input);
    } catch (IOException e) {
      System.out.println(e);
    }

    width = image.getWidth();
    height = image.getHeight();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color c = new Color(image.getRGB(j, i));
        data.add(c.getRed());
      }
    }

    ArrayList<Integer> instructions = new ArrayList<Integer>();
    int counter = 0;
    for (int i = 0; i < ((height * width) / 8); i++) {
      instructions.add(0b00001_0001_00000000000000000000000 + (i * 8));//def
      counter += 8;

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10000_1001_0001_0000000000000000000);//lv

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b11100_1001_1001_1010_000000000000000);//xor

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b11101_1001_1001_1010_000000000000000);//srcv

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);

      instructions.add(0b10001_0000_0001_1001_000000000000000);//sv

      instructions.add(0b00000_0000_00000000000000000000000);
      instructions.add(0b00000_0000_00000000000000000000000);
    }

    instructions.add(0b11111_0000_00000000000000000000000); //END
    instructions.add(0b00000_0000_00000000000000000000000);


    Procesor procesor1 = new Procesor(instructions, data);

    ArrayList<Integer> key = new ArrayList<>();
    key.add(123);
    key.add(250);
    key.add(56);
    key.add(98);
    key.add(20);
    key.add(83);
    key.add(150);
    key.add(200);
    procesor1.setPrivateKey(key);
    procesor1.runThreaded();

    int count = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(255, 255, 255);
        if (data.get(count) <= 255) {
          color = new Color(data.get(count), data.get(count), data.get(count));
        } else {
          System.out.println(count + " " + data.get(count));
        }
        image.setRGB(j, i, color.getRGB());
        count++;
      }
    }

    System.out.println("Almost almost there");

    try {
      input = new File("/home/jeanpaul/eclipse-workspace/procesadorVectorial/src/images/lenna_custom_encripted.jpg");
      ImageIO.write(image, "jpg", input);
    } catch (IOException e) {
      System.out.println(e);
    }

  }

  public void testLogic() {

    ArrayList<Integer> instructions = new ArrayList<Integer>();

    instructions.add(0b00001_0001_00000000000000000000011);//def assign 3 to register R1

    instructions.add(0b10000_1001_0000_0000000000000000000);//lv cargar en RV1 los datos a partir de 0

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b10100_1010_1001_0001_000000000000000);//srcv
    instructions.add(0b10101_1011_1001_0001_000000000000000);//slcv
    instructions.add(0b11000_1100_1001_0001_000000000000000);//srlv
    instructions.add(0b11001_1101_1001_0001_000000000000000);//sllv
    instructions.add(0b10110_1110_1001_0001_000000000000000);//or
    instructions.add(0b10010_1111_1001_0001_000000000000000);//xor

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b11111_0000_00000000000000000000000); //END
    instructions.add(0b00000_0000_00000000000000000000000);

    ArrayList<Integer> data = new ArrayList<Integer>();
    data.add(0);
    data.add(1);
    data.add(2);
    data.add(10);
    data.add(50);
    data.add(255);
    data.add(300);
    data.add(1000);
    Procesor procesor1 = new Procesor(instructions, data);
    procesor1.setDebug(true);
    procesor1.runThreaded();
  }

  public void testAritmetic() {

    ArrayList<Integer> instructions = new ArrayList<Integer>();

    instructions.add(0b00001_0001_00000000000000000010000);//def assign 3 to register R1

    instructions.add(0b10000_1001_0000_0000000000000000000);//lv   cargar en RV1 los datos a partir de 0

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);

    instructions.add(0b10011_1011_1001_1010_000000000000000);//addvv
    instructions.add(0b11010_1100_1001_0001_000000000000000);//addve
    instructions.add(0b10111_1101_1001_1010_000000000000000);//subvv
    instructions.add(0b11011_1110_1001_0001_000000000000000);//subve

    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);
    instructions.add(0b00000_0000_00000000000000000000000);


    instructions.add(0b11111_0000_00000000000000000000000); //END
    instructions.add(0b00000_0000_00000000000000000000000);

    ArrayList<Integer> data = new ArrayList<Integer>();
    data.add(0);
    data.add(1);
    data.add(2);
    data.add(5);
    data.add(10);
    data.add(100);
    data.add(200);
    data.add(255);
    Procesor procesor1 = new Procesor(instructions, data);
    ArrayList<Integer> key = new ArrayList<Integer>();
    procesor1.setPrivateKey(data);
    procesor1.setDebug(true);
    procesor1.runThreaded();
  }

}
