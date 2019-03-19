/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author regno
 */
public class CacheCommandTest {

    ResourceBundle messages;
    Parser parser;
    Resources resources;

    @Before
    public void setUp() {
        messages = ResourceBundle.getBundle("langFiles.MessagesBundle", new Locale("en", "UK"));
        resources = Resources.getSharedResources();
        resources.resetResources();
        parser = new Parser(messages, resources);
    }

    @Test
    public void testCacheEmptyMessageOutput() {
        assertTrue(parser.getCommand("cache").execute().equals("Image cache list:"));
    }

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

    // also test undo, put and get
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
