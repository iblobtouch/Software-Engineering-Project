package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test quit command related functionalities.
 */
public class QuitCommandTest {

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
     * Tests message output after quitting the application.
     */
    @Test
    public void testQuitMessageOutput() {
        String output = parser.getCommand("quit").execute();
        assertTrue(output.equals("Thank you for using Fotoshop.  Good bye."));
    }
}
