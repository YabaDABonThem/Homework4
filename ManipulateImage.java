// Allen Bao
// 11/12/21
// A program that takes in user's input and manipulates it in any way that they desire. 

// Import Statements 
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class ManipulateImage {

    // A method that takes as input a filename of type String, and returns an 
    // object of type BufferedImage
    public static BufferedImage loadImage(String filename) {
        BufferedImage b1 = null;
        try {
            b1 = ImageIO.read(new File(filename));
        } 
        catch (IOException e) {
            System.out.println("error loading the image: " + filename + " : " + e);
        }
        return b1;
    }
    
    // The first input paramter to the method is img, which is of type BufferedImage
    //
    // If the second parameter passed to the method is the String "w", 
    // then the method changes all pixels in the image to white, and returns the manipulated image
    //
    // If the second parameter passed to the method is the String "b", 
    // then the method changes all pixes in the image to black, and returns the manipulated image
    //
    // The method returns a BufferedImage
    public static BufferedImage blackoutWhiteoutImage(BufferedImage img, String action) {

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rGB = img.getRGB(x, y);

                int r, g, b;
                r = 0;
                g = 0;
                b = 0;

                // Sets all rgb values to 255, which generates white
                if (action.equals("w")) {
                    r = 255;
                    g = 255;
                    b = 255;
                } else if (action.equals("b")) {
                    r = 0;
                    g = 0;
                    b = 0;
                }

                // transform back to pixel value and set it
                img.setRGB(x, y, (r << 16) | (g << 8) | b);
            }
        }
        return img;
    }

    // A method that makes all white pixels in an image ALMOST black, so that they are
    // almost not visible, in essence "hiding" the image
    public static BufferedImage hideImage(BufferedImage img) {

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {

                int rGB = img.getRGB(x, y);

                // if r + g + b > 765
                int r = ((rGB >> 16) & 0xFF);  // red inverted
                int g = ((rGB >> 8) & 0xFF);   // green inverted
                int b = (rGB & 0xFF); 			 // blue inverted

                if (r + g + b > 750) {
                    r = 10;
                    g = 10;
                    b = 10;
                }
                // transform back to pixel value and set it
                img.setRGB(x, y, (r << 16) | (g << 8) | b);
            }
        }
        return img;
    }

    // A method that receives a BufferedImage, and changes all very dim pixels to white, 
    // in essence "unhiding" the image
    public static BufferedImage unHideImage(BufferedImage img) {

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {

                int rGB = img.getRGB(x, y);

                // subtract 250 from each value
                int r = ((rGB >> 16) & 0xFF);  // red inverted
                int g = ((rGB >> 8) & 0xFF);   // green inverted
                int b = (rGB & 0xFF); 			 // blue inverted

                if ((r + g + b) > 20) {
                    r = 255;
                    g = 255;
                    b = 255;
                }

                // transform back to pixel value and set it
                img.setRGB(x, y, (r << 16) | (g << 8) | b);
            }
        }
        return img;
    }

    // This method make all white pixels black, and all black pixels white, in an image
    public static BufferedImage invertImage(BufferedImage img) {

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rGB = img.getRGB(x, y);

                //ivnerst/swap RGB values
                int r = Math.abs(((rGB >>> 16) & 0xFF) - 255);  // red inverted
                int g = Math.abs(((rGB >>> 8) & 0xFF) - 255); 	// green inverted
                int b = Math.abs((rGB & 0xFF) - 255); 				// blue inverted

                // transform back to pixel value and set it
                img.setRGB(x, y, (r << 16) | (g << 8) | b);
            }
        }
        return img;
    }

    // A method that saves an image inside of a BufferedImage object to an image file
    // of type jpg	
    public static void saveImage(BufferedImage buff, String dest) {

        try {
            File outputfile = new File(dest);
            ImageIO.write(buff, "jpg", outputfile);
        } 
        catch (IOException e) {
            System.out.print("error saving the image: " + dest + ": " + e);
        }
    }

    // A method that prints information about an image; it takes as input the
    // bufferedImage, and the name of the original image
    public static void printImageInfo(BufferedImage buff, String imageFilename) {
        int imageHeight = buff.getHeight();
        System.out.println("The image " + imageFilename + " has a height of " + imageHeight + " pixels.");
        int imageWidth = buff.getWidth();
        System.out.println("The image " + imageFilename + " has a width of " + imageWidth + " pixels.");
        int imageType = buff.getType();
        System.out.println("The image " + imageFilename + " is a type " + imageType + " image.");
    }

    // The main method/routine
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        while (true) {

            System.out.print("What image do you want to edit? Type q to quit: ");
            String fileName = keyboard.nextLine();

            // Checks if a user has entered the letter q, in 
            // which case issue a break statement, which will cause the while 
            // loop to terminate and hence the program to end.
            
            if (fileName.equals("q")) {
               break;
            }

            BufferedImage inputImage = loadImage(fileName);
            BufferedImage outputImage = null;

            System.out.print("What action? Invert (v), blackout (b), hide (h), unhide (u), "
                    + "whiteout (w), information (i): ");
            String action = keyboard.nextLine();

            String fileNameOutput = "";
            if (!action.equals("i")) {
                System.out.print("What is the name of the output image? ");
                fileNameOutput = keyboard.nextLine();
            }
            // METHOD INVOCATION SECTION
            if (action.equals("v")) {
                outputImage = invertImage(inputImage);

                saveImage(outputImage, fileNameOutput);
                System.out.println("The image " + fileName + " has been invereted, and saved to " + fileNameOutput);
            }
            else if (action.equals("b")) {
                outputImage = blackoutWhiteoutImage(inputImage, "b");


                saveImage(outputImage, fileNameOutput);
                System.out.println("The image " + fileName + " has been blackened, and saved to " + fileNameOutput);
            } 
            else if (action.equals("h")) {
                outputImage = hideImage(inputImage);

                saveImage(outputImage, fileNameOutput);
                System.out.println("The image " + fileName + " has been 'hidden', and saved to " + fileNameOutput);
            } 
            else if (action.equals("u")) {
                outputImage = unHideImage(inputImage);

                saveImage(outputImage, fileNameOutput);
                System.out.println("The image " + fileName + " has been 'un-hidden', and saved to " + fileNameOutput);
            } 
            else if (action.equals("w")) { 
                outputImage = blackoutWhiteoutImage(inputImage, "w");

                saveImage(outputImage, fileNameOutput);
                System.out.println("The image " + fileName + " has been whited, and saved to " + fileNameOutput);
            } 
            else if (action.equals("i")) {
                // Invoke the method printImageInfo, which expects two paramters, and returns nothing 
                printImageInfo(inputImage, fileName);
            } 
            else {
                System.out.println("An incorrect choice has been entered. Try again");
            }
        }
    }
}