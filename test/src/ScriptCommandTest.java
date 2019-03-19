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
public class ScriptCommandTest {

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
    public void testScriptCannotFindMessageOutput() {
        String input = "script invalidName.txt";
        String output = parser.getCommand(input).execute();
        assertTrue(output.equals("Cannot find invalidName.txt"));

    }

    @Test
    public void testScriptFromProjectRootMessageOutput() {
        String input = "script testScript.txt";
        String output = parser.getCommand(input).execute();
        assertTrue(output.equals("Loaded input.jpg\n"
                + "The current image is input.jpg\n"
                + "Filters applied:\n"
                + "Image has been successfully rotated around 90 degrees.\n"
                + "Image has been successfully flipped vertically.\n"
                + "The current image is input.jpg\n"
                + "Filters applied: rot90 flipV\n"
                + "I don't know what you mean...\n"));
    }

    @Test
    public void testScriptRecursiveMessageOutput() {
        String input = "script testScript.txt testFiles/scripts";
        String output = parser.getCommand(input).execute();
        assertTrue(output.equals("Loaded input.jpg\n"
                + "Image has been successfully flipped horizontally.\n"
                + "Mono filter has been successfully applied to the image.\n"
                + "The current image is input.jpg\n"
                + "Filters applied: flipH mono\n"
                + "Undo Completed\n"
                + "Image has been successfully rotated around 90 degrees.\n"
                + "The current image is input.jpg\n"
                + "Filters applied: flipH rot90\n"
                + "Thank you for using Fotoshop.  Good bye.\n\n"));
    }

    @Test
    public void testScriptNoSecondWord() {
        assertTrue(parser.getCommand("script").execute().equals("which script?"));
    }
}
