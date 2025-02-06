package Switch;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageAdvance {
    public static void main(String[] args) {
        try {
            // Specify the image file path
            File file = new File("C:\\Users\\Admin\\Desktop\\Qspider\\ganapti.png");
            BufferedImage image = ImageIO.read(file);

            // Loop through each pixel in the image
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color color = new Color(image.getRGB(j, i));

                    // Check if the red component is 0 and green equals blue
                    if (color.getRed() == 0 && color.getGreen() == color.getBlue())
                        System.out.print(" ");
                    else
                        System.out.print("*");
                }
                // Move to the next line after finishing a row of pixels
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
