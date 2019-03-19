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
public class LookCommandTest {

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
    public void testLookNoFiltersMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("look").execute();
        assertTrue(output.equals("The current image is input.jpg\n"
                + "Filters applied:"));
    }

    @Test
    public void testLook4FiltersMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        parser.getCommand("mono").execute();
        parser.getCommand("rot90").execute();
        parser.getCommand("flipH").execute();
        parser.getCommand("flipV").execute();
        String output = parser.getCommand("look").execute();
        assertTrue(output.equals("The current image is input.jpg\n"
                + "Filters applied: mono rot90 flipH flipV"));
    }
}
