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
public class OpenCommandTest {

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
    public void testOpenUnsupportedFileMessageOutput() {
        String output = parser.getCommand("open script.txt").execute();
        assertTrue(output.equals("File is unsupported"));
    }

    @Test
    public void testOpenNonExistingFileMessageOutput() {
        String output = parser.getCommand("open test4.jpg").execute();
        assertTrue(output.equals("Cannot find image file, test4.jpg"
                + "\ncwd is " + System.getProperty("user.dir")));
    }

    @Test
    public void testOpenMessageOutput() {
        String output = parser.getCommand("open input.jpg").execute();
        assertTrue(output.equals("Loaded input.jpg"));
    }

    @Test
    public void testOpenNoSecondWord() {
        assertTrue(parser.getCommand("open").execute().equals("open what?"));
    }
}
