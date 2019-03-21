package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test put command related functionalities.
 */
public class PutCommandTest {

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
     * Tests message output after overwriting an existing image in the image
     * cache.
     */
    @Test
    public void testPutOverwriteMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // store an image in the image cache
        parser.getCommand("put test3").execute();
        // make more changes
        parser.getCommand("rot90").execute();
        parser.getCommand("flipV").execute();
        // Overwrite existing image in the cache
        String output = parser.getCommand("put test3").execute();
        assertTrue(output.equals("The following cache has been overwritten: test3"));
    }

    /**
     * Tests the message after storing an image in the image cache.
     */
    @Test
    public void testPutMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // store an image in the image cache
        String output = parser.getCommand("put test2").execute();
        assertTrue(output.equals("test2 has been added to the cache"));
    }

    /**
     * Tests the message output after using put command without specifying a
     * second word/new name.
     */
    @Test
    public void testPutNoSecondWord() {
        assertTrue(parser.getCommand("put").execute().equals("save as what?"));
    }

    /**
     * Tests the massage output after using put command without any image loaded
     * first.
     */
    @Test
    public void testPutNoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("put test2").execute();
        assertTrue(output.equals("No Image Loaded"));
    }
}
