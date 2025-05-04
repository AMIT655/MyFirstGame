package ImageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Temp {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\amits\\OneDrive\\שולחן העבודה\\1\\TREE2.jpeg");
        if (file.exists()){
            System.out.println("file exists :) ");

            try {
                BufferedImage image = ImageIO.read(file);

                int width = image.getWidth();
                int height = image.getHeight();

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        int rgb = image.getRGB(i, j);
                        Color color = new Color(rgb);

                        int red = 255 - color.getRed();
                        int blue = 255 - color.getBlue();
                        int green = 255 - color.getGreen();

                        Color newColor = new Color(red, green, blue);
                        image.setRGB(i, j, newColor.getRGB());
                    }
                }
                File output = new File("C:\\Users\\amits\\OneDrive\\שולחן העבודה\\1\\TREE2_Example.jpeg");
                ImageIO.write(image, "jpeg", output);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else System.out.println("no found :( ");

    }
}
