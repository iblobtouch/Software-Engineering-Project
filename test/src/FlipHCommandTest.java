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
public class FlipHCommandTest {

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
    public void testFlipHMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("flipH").execute();
        assertTrue(output.equals("Image has been successfully flipped horizontally."));
    }

    @Test
    public void testFlipHNoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("flipH").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    @Test
    public void testExceededPipeFlipH() {
        assertTrue(TestUtility.automateExceededPipe(parser, "flipH").equals("Filter pipeline exceeded"));
    }
}
