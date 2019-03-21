package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test undo command related functionalities.
 */
public class UndoCommandTest {

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
     * Tests message output upon executing undo command on an image with filters
     * applied to it.
     */
    @Test
    public void testUndoMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // Add some filters
        parser.getCommand("mono").execute();
        parser.getCommand("flipH").execute();
        String output = parser.getCommand("undo").execute();
        assertTrue(output.equals("Undo Completed"));
    }

    /**
     * Tests message output upon executing undo command on an image without any
     * filters applied to it.
     */
    @Test
    public void testUndoNoFiltersMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // Don't add any filters
        String output = parser.getCommand("undo").execute();
        assertTrue(output.equals("No filters to undo"));
    }

    /**
     * Tests message output after executing undo command without any image
     * loaded to the application.
     */
    @Test
    public void testUndoNoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("undo").execute();
        assertTrue(output.equals("No Image Loaded"));
    }
}
