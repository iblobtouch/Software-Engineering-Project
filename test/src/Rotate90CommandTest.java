package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test rot90 command related functionalities.
 */
public class Rotate90CommandTest {

    private ResourceBundle messages;
    private Parser parser;
    private Resources resources;

    /**
     * Initialises and resets each resources for each test run.
     */
    @Before
    public void setUp() {
        messages = ResourceBundle.getBundle("langFiles.MessagesBundle", new Locale("en", "UK"));
        resources = Resources.getSharedResources();
        resources.resetResources();
        parser = new Parser(messages, resources);
    }

    /**
     * Tests message output after applying rot90 filter in a loaded image.
     */
    @Test
    public void testRotateMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("rot90").execute();
        assertTrue(output.equals("Image has been successfully rotated around 90 degrees."));
    }

    /**
     * Tests message output after calling rot90 command while no image has been
     * loaded.
     */
    @Test
    public void testRot90NoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("rot90").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    /**
     * Tests message output after applying rot90 command on an image with the
     * maximum filters already applied.
     */
    @Test
    public void testExceededPipeRot90() {
        assertTrue(TestUtility.automateExceededPipe(parser, "rot90").equals("Filter pipeline exceeded"));
    }
}
