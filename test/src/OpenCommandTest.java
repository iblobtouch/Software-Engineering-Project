package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test open command related functionalities.
 */
public class OpenCommandTest {

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
     * Tests message output after opening a non-image/unsupported file.
     */
    @Test
    public void testOpenUnsupportedFileMessageOutput() {
        String output = parser.getCommand("open script.txt").execute();
        assertTrue(output.equals("File is unsupported"));
    }

    /**
     * Tests message output after opening a non-existing file.
     */
    @Test
    public void testOpenNonExistingFileMessageOutput() {
        String output = parser.getCommand("open test4.jpg").execute();
        assertTrue(output.equals("Cannot find image file, test4.jpg"
                + "\ncwd is " + System.getProperty("user.dir")));
    }

    /**
     * Tests message output after opening an image file.
     */
    @Test
    public void testOpenMessageOutput() {
        String output = parser.getCommand("open input.jpg").execute();
        assertTrue(output.equals("Loaded input.jpg"));
    }

    /**
     * Tests message output after using open command by itself without referring
     * to any file.
     */
    @Test
    public void testOpenNoSecondWord() {
        assertTrue(parser.getCommand("open").execute().equals("open what?"));
    }
}
