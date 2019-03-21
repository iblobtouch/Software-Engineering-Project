package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test script command related functionalities.
 */
public class ScriptCommandTest {

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
     * Tests message output after executing a non-existing script text file.
     */
    @Test
    public void testScriptCannotFindMessageOutput() {
        String input = "script invalidName.txt";
        String output = parser.getCommand(input).execute();
        assertTrue(output.equals("Cannot find invalidName.txt"));

    }

    /**
     * Tests message output after executing a script file in the project's root
     * directory.
     */
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

    /**
     * Tests executing a script text file that also uses the script command.
     */
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

    /**
     * Tests message output after executing the script command by itself without
     * referring to any file.
     */
    @Test
    public void testScriptNoSecondWord() {
        assertTrue(parser.getCommand("script").execute().equals("which script?"));
    }
}
