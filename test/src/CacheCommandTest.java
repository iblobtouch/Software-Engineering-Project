package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cache command related functionalities.
 */
public class CacheCommandTest {

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
     * Tests the message output if cache command is called while no images are
     * stored in the image cache.
     */
    @Test
    public void testCacheEmptyMessageOutput() {
        assertTrue(parser.getCommand("cache").execute().equals("Image cache list:"));
    }

    /**
     * Tests the message output if cache command is called to show its content
     * after adding images in the image cache.
     */
    @Test
    public void testCacheMessageOutput() {
        // insert some images in cache
        parser.getCommand("open input.jpg").execute();
        parser.getCommand("put test1").execute();
        parser.getCommand("put test2").execute();
        parser.getCommand("put test3").execute();
        String output = parser.getCommand("cache").execute();
        assertTrue(output.equals("Image cache list:"
                + "\ntest1"
                + "\ntest2"
                + "\ntest3"));
    }

    /**
     * Tests manipulating the content of image cache by using the get, put and
     * cache commands, as well as other filter commands to demonstrate correct
     * functionality.
     */
    @Test
    public void testCacheUsage() {
        String output = parser.getCommand("script testFiles/scripts/testCache.txt").execute();
        assertTrue(output.equals("Loaded input.jpg\n"
                + "The current image is input.jpg\n"
                + "Filters applied:\n"
                + "Mono filter has been successfully applied to the image.\n"
                + "test1 has been added to the cache\n"
                + "Image has been successfully rotated around 90 degrees.\n"
                + "Image has been successfully flipped horizontally.\n"
                + "test2 has been added to the cache\n"
                + "Undo Completed\n"
                + "Undo Completed\n"
                + "Image has been successfully flipped vertically.\n"
                + "test3 has been added to the cache\n"
                + "Image cache list:\n"
                + "test1\n"
                + "test2\n"
                + "test3\n"
                + "Loaded test1\n"
                + "The current image is test1\n"
                + "Filters applied: mono\n"
                + "Loaded test2\n"
                + "The current image is test2\n"
                + "Filters applied: mono rot90 flipH\n"
                + "Loaded test3\n"
                + "The current image is test3\n"
                + "Filters applied: mono flipV\n"));
    }

}
