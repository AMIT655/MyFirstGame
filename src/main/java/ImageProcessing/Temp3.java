package ImageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Temp3 {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\amits\\OneDrive\\שולחן העבודה\\1\\TREE2.jpeg");
        if (file.exists()){

            try {
                BufferedImage image3 = ImageIO.read(file);

                int width = image3.getWidth();
                int height = image3.getHeight();
                final float bright = 1.3f;

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {

                        int rgb = image3.getRGB(i, j);
                        Color color = new Color(rgb);

                        int red = color.getRed() * bright > 255 ? 255 : (int) (color.getRed() * bright);
                        int green = color.getGreen() * bright > 255 ? 255 : (int) (color.getGreen() * bright);
                        int blue = color.getBlue() * bright > 255 ? 255 : (int) (color.getBlue() * bright);

                        Color newColor = new Color(red, green, blue);
                        image3.setRGB(i, j, newColor.getRGB());
                    }
                }

                File temp = new File("C:\\Users\\amits\\OneDrive\\שולחן העבודה\\1\\TREE2_Example.jpeg");
                ImageIO.write(image3, "jpeg", temp);






            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
