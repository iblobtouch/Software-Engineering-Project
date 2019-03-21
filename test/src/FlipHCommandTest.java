package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test flipH command related functionalities.
 */
public class FlipHCommandTest {

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
     * Tests message output after applying flipH filter in a loaded image.
     */
    @Test
    public void testFlipHMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("flipH").execute();
        assertTrue(output.equals("Image has been successfully flipped horizontally."));
    }

    /**
     * Tests message output after calling flipH command while no image has been
     * loaded.
     */
    @Test
    public void testFlipHNoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("flipH").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    /**
     * Tests message output after applying flipH command on an image with the
     * maximum filters already applied.
     */
    @Test
    public void testExceededPipeFlipH() {
        assertTrue(TestUtility.automateExceededPipe(parser, "flipH").equals("Filter pipeline exceeded"));
    }
}
