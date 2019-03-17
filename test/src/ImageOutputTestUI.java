/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author regno
 */
public class ImageOutputTestUI {
    ResourceBundle messages;
    Parser parser;
    Resources resources;
    String testDir;
    
    @Before
    public void setUp() {
        messages = ResourceBundle.getBundle("langFiles.MessagesBundle", new Locale("en", "UK"));
        resources = Resources.getSharedResources();
        parser = new Parser(messages, resources); 
        testDir = System.getProperty("user.dir") + "\\testFiles\\images\\";
    }
    
    @Test
    public void testMonoImage() throws IOException{
        boolean result = automateImageTest("testMono.txt", "monoTest.jpg", "monoImage.jpg");
        assertTrue(result);
    }
    
    @Test
    public void testRotateImage() throws IOException{
        boolean result = automateImageTest("testRotate.txt", "rotateTest.jpg", "rotateImage.jpg");
        assertTrue(result);
    }
    
    @Test
    public void testFlipHImage() throws IOException{
        boolean result = automateImageTest("testFlipH.txt", "flipHTest.jpg", "flipHImage.jpg");
        assertTrue(result);
    }
    
    @Test
    public void testFlipVImage() throws IOException{
        boolean result = automateImageTest("testFlipV.txt", "flipVTest.jpg", "flipVImage.jpg");
        assertTrue(result);
    }
    
    @Test
    public void testMonoRotateImage() throws IOException{
        boolean result = automateImageTest("testMonoRotate.txt", "monoRotateTest.jpg", "monoRotateImage.jpg");
        assertTrue(result);
    }
    
    @Test
    public void testFlipHVImage() throws IOException{
        boolean result = automateImageTest("testFlipHV.txt", "flipHVTest.jpg", "flipHVImage.jpg");
        assertTrue(result);
    }
    
    /*
    * Automate image testing for filters.
    * @param scriptName Name of the script text file to run 
    * (should be located in the directory /testFiles/scripts)
    * @param savedFN fileName of the saved image being tested. 
    * Use the name inside the script file provided.
    * @param comparedImageFN Name of the expected output image file 
    * (should be located in the directory /testFiles/images)
    */
    private boolean automateImageTest(String scriptName, String savedFN, String comparedImageFN) throws IOException {
        String input = "script " + scriptName + " testFiles/scripts";
        parser.getCommand(input).execute();
        File savedFile = new File(savedFN);
        ColorImage actualImageOutput = new ColorImage(ImageIO.read(savedFile));
        File comparedFile = new File(testDir + comparedImageFN);
        ColorImage expectedImageOutput = new ColorImage(ImageIO.read(comparedFile));
        savedFile.delete();
        return imageMatched(0, actualImageOutput, expectedImageOutput);
    }
    
    /*
    * The saved image will not necessarily have the same exact pixel
    * values as the image being processed. This utility enables comparing two
    * image files that may be slightly similar by providing a pixel difference
    * allowance as one of its parameters.
    */
    private boolean imageMatched(int pixDiffAllowance, ColorImage a, ColorImage b) {
        boolean imageMatch = true;
        int width = a.getWidth();
        int height = a.getHeight();
        
        for (int y=0; y<height; y++) { 
            for (int x=0; x<width; x++) {
                Color ap = a.getPixel(x,y);
                Color bp = b.getPixel(x,y);

                int blueDiff = Math.abs(ap.getBlue() - bp.getBlue());
                int redDiff = Math.abs(ap.getRed() - bp.getRed());
                int greenDiff = Math.abs(ap.getGreen() - bp.getGreen());
                
                if (blueDiff > pixDiffAllowance || 
                        redDiff > pixDiffAllowance || 
                        greenDiff > pixDiffAllowance) {
                    imageMatch = false;
                }

            }
        }
        
        return imageMatch;
    }
}