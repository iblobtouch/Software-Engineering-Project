package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test flipV command related functionalities.
 */
public class FlipVCommandTest {

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
     * Tests message output after applying flipV filter in a loaded image.
     */
    @Test
    public void testFlipVMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("flipV").execute();
        assertTrue(output.equals("Image has been successfully flipped vertically."));
    }

    /**
     * Tests message output after calling flipV command while no image has been
     * loaded.
     */
    @Test
    public void testFlipVNoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("flipV").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    /**
     * Tests message output after applying flipV command on an image with the
     * maximum filters already applied.
     */
    @Test
    public void testExceededPipeFlipV() {
        assertTrue(TestUtility.automateExceededPipe(parser, "flipV").equals("Filter pipeline exceeded"));
    }
}
