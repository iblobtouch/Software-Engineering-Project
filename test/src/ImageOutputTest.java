package src;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;

/**
 * Test that the filters are being applied correctly to the images.
 */
public class ImageOutputTest {

    private ResourceBundle messages;
    private Parser parser;
    private Resources resources;
    private String testDir;

    /**
     * Initialises and resets each resources for each test run.
     */
    @Before
    public void setUp() {
        messages = ResourceBundle.getBundle("langFiles.MessagesBundle", new Locale("en", "UK"));
        resources = Resources.getSharedResources();
        resources.resetResources();
        parser = new Parser(messages, resources);
        testDir = System.getProperty("user.dir") + "\\testFiles\\images\\";
    }

    /**
     * Tests that the mono filter has been applied to the image correctly.
     *
     * @throws IOException
     */
    @Test
    public void testMonoImage() throws IOException {
        boolean result = automateImageTest("testMono.txt", "monoTest.jpg", "monoImage.jpg");
        assertTrue(result);
    }

    /**
     * Tests that the rot90 filter has been applied to the image correctly.
     *
     * @throws IOException
     */
    @Test
    public void testRotateImage() throws IOException {
        boolean result = automateImageTest("testRotate.txt", "rotateTest.jpg", "rotateImage.jpg");
        assertTrue(result);
    }

    /**
     * Tests that the flipH filter has been applied to the image correctly.
     *
     * @throws IOException
     */
    @Test
    public void testFlipHImage() throws IOException {
        boolean result = automateImageTest("testFlipH.txt", "flipHTest.jpg", "flipHImage.jpg");
        assertTrue(result);
    }

    /**
     * Tests that the flipV filter has been applied to the image correctly.
     *
     * @throws IOException
     */
    @Test
    public void testFlipVImage() throws IOException {
        boolean result = automateImageTest("testFlipV.txt", "flipVTest.jpg", "flipVImage.jpg");
        assertTrue(result);
    }

    /**
     * Tests that the mono followed by the rot90 filter has been applied to the
     * image correctly.
     *
     * @throws IOException
     */
    @Test
    public void testMonoRotateImage() throws IOException {
        boolean result = automateImageTest("testMonoRotate.txt", "monoRotateTest.jpg", "monoRotateImage.jpg");
        assertTrue(result);
    }

    /**
     * Tests that the flipH followed by the flipV filter has been applied to the
     * image correctly.
     *
     * @throws IOException
     */
    @Test
    public void testFlipHVImage() throws IOException {
        boolean result = automateImageTest("testFlipHV.txt", "flipHVTest.jpg", "flipHVImage.jpg");
        assertTrue(result);
    }

    /**
     * Automate image testing for filters.
     *
     * @param scriptName Name of the script text file to run (should be located
     * in the directory /testFiles/scripts)
     * @param savedFN fileName of the saved image being tested. Use the name
     * inside the script file provided.
     * @param comparedImageFN Name of the expected output image file (should be
     * located in the directory /testFiles/images)
     */
    private boolean automateImageTest(String scriptName, String savedFN, String comparedImageFN) throws IOException {
        String input = "script " + scriptName + " testFiles/scripts";
        parser.getCommand(input).execute();
        File savedFile = new File(savedFN);
        ColorImage actualImageOutput = new ColorImage(ImageIO.read(savedFile));
        File comparedFile = new File(testDir + comparedImageFN);
        ColorImage expectedImageOutput = new ColorImage(ImageIO.read(comparedFile));
        savedFile.delete();
        return TestUtility.imageMatched(0, actualImageOutput, expectedImageOutput);
    }

}
