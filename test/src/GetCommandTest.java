package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test get command related functionalities.
 */
public class GetCommandTest {

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
     * Tests message output after retrieving an existing image in the image
     * cache.
     */
    @Test
    public void testGetMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // store an image in the image cache
        parser.getCommand("put test1").execute();
        String output = parser.getCommand("get test1").execute();
        assertTrue(output.equals("Loaded test1"));
    }

    /**
     * Tests message output after trying to retrieve an image in the image cache
     * that doesn't exist.
     */
    @Test
    public void testGetNonExistingImageMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // get a non-existing image from the image cache
        String output = parser.getCommand("get test1").execute();
        assertTrue(output.equals("Cannot find cache image"));
    }

    /**
     * Tests message output after calling the get command by itself without
     * referring to a name of an image file.
     */
    @Test
    public void testGetNoSecondWord() {
        assertTrue(parser.getCommand("get").execute().equals("get what?"));
    }
}
