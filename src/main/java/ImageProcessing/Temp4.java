package ImageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Temp4 {
    public static void main(String[] args) {
        File file = new File("\"C:\\Users\\amits\\OneDrive\\שולחן העבודה\\1\\TREE2.jpeg\"");

        if (file.exists()){
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                for (int i = 0; i < bufferedImage.getWidth() - 1; i++) {
                    for (int j = 0; j < bufferedImage.getHeight() - 1; j++) {

                            int rgb4 = bufferedImage.getRGB(i, j);
                            int rgb4Down = bufferedImage.getRGB(i, j + 1);

                            Color color1 = new Color(rgb4);
                            Color color2 = new Color(rgb4Down);

                            int red1 = color1.getRed();
                            int green1 = color1.getGreen();
                            int blue1 = color1.getBlue();

                            int red2 = color2.getRed();
                            int green2 = color2.getGreen();
                            int blue2 = color2.getBlue();

                            




                    }

                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
