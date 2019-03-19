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
public class UndoCommandTest {

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
    public void testUndoMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // Add some filters
        parser.getCommand("mono").execute();
        parser.getCommand("flipH").execute();
        String output = parser.getCommand("undo").execute();
        assertTrue(output.equals("Undo Completed"));
    }

    @Test
    public void testUndoNoFiltersMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // Don't add any filters
        String output = parser.getCommand("undo").execute();
        assertTrue(output.equals("No filters to undo"));
    }

    @Test
    public void testUndoNoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("undo").execute();
        assertTrue(output.equals("No Image Loaded"));
    }
}
