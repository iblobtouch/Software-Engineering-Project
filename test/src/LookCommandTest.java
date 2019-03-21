package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test look command related functionalities.
 */
public class LookCommandTest {

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
     * Tests the look command on an image without any filter applied.
     */
    @Test
    public void testLookNoFiltersMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("look").execute();
        assertTrue(output.equals("The current image is input.jpg\n"
                + "Filters applied:"));
    }

    /**
     * Tests the look command on an image with four filters applied.
     */
    @Test
    public void testLook4FiltersMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        parser.getCommand("mono").execute();
        parser.getCommand("rot90").execute();
        parser.getCommand("flipH").execute();
        parser.getCommand("flipV").execute();
        String output = parser.getCommand("look").execute();
        assertTrue(output.equals("The current image is input.jpg\n"
                + "Filters applied: mono rot90 flipH flipV"));
    }
}
