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
public class GetCommandTest {

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
    public void testGetMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // store an image in the image cache
        parser.getCommand("put test1").execute();
        String output = parser.getCommand("get test1").execute();
        assertTrue(output.equals("Loaded test1"));
    }

    @Test
    public void testGetNonExistingImageMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // get a non-existing image from the image cache
        String output = parser.getCommand("get test1").execute();
        assertTrue(output.equals("Cannot find cache image"));
    }

    @Test
    public void testGetNoSecondWord() {
        assertTrue(parser.getCommand("get").execute().equals("get what?"));
    }
}
