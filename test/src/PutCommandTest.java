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
public class PutCommandTest {

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

    @Test
    public void testPutMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // store an image in the image cache
        String output = parser.getCommand("put test2").execute();
        assertTrue(output.equals("test2 has been added to the cache"));
    }

    @Test
    public void testPutNoSecondWord() {
        assertTrue(parser.getCommand("put").execute().equals("save as what?"));
    }

    @Test
    public void testPutNoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("put test2").execute();
        assertTrue(output.equals("No Image Loaded"));
    }
}
