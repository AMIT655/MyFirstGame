package ImageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Temp2 {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\amits\\OneDrive\\שולחן העבודה\\1\\TREE2.jpeg");

        if (file.exists()){
            try {
                BufferedImage image2 = ImageIO.read(file);

                int height = image2.getHeight();
                int width = image2.getWidth();

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {

                        int rgb2 = image2.getRGB(i, j);
                        Color color = new Color(rgb2);

                        int average = 255 / 2;
                        int avg = (color.getRed() + color.getBlue() + color.getGreen()) / 3;

                        int red = avg > average ? 255 : 0;
                        int green = avg > average ? 255 : 0;
                        int blue = avg > average ? 255 : 0;

                        Color newColor = new Color(red, green, blue);
                        image2.setRGB(i, j, newColor.getRGB());
                    }
                }

                File output = new File("C:\\Users\\amits\\OneDrive\\שולחן העבודה\\1\\TREE2_Example.jpeg");
                ImageIO.write(image2, "jpeg", output);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
