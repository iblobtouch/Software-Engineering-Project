package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test mono command related functionalities.
 */
public class MonoCommandTest {

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
     * Tests message output after applying mono filter in a loaded image.
     */
    @Test
    public void testMonoMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("mono").execute();
        assertTrue(output.equals("Mono filter has been successfully applied to the image."));
    }

    /**
     * Tests message output after calling mono command while no image has been
     * loaded.
     */
    @Test
    public void testMonoNoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("mono").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    /**
     * Tests message output after applying mono command on an image with the
     * maximum filters already applied.
     */
    @Test
    public void testExceededPipeMono() {
        assertTrue(TestUtility.automateExceededPipe(parser, "mono").equals("Filter pipeline exceeded"));
    }
}
