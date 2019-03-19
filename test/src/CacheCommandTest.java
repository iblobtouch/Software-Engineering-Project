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
}
