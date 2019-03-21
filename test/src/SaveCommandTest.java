package src;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.filechooser.FileSystemView;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test save command related functionalities.
 */
public class SaveCommandTest {

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
     * Tests message output after saving a file outside of the project's root
     * directory.
     */
    @Test
    public void testSaveToValidPathOutsideProjectMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String check = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        File savedFile = new File(check + "/temp/");
        String output = parser.getCommand("save " + savedFile.toString()).execute();
        savedFile.delete();
        assertTrue(output.equals("Image saved to " + savedFile.toString()));
    }

    /**
     * Tests message output after saving a file inside a path within the
     * project's root directory.
     */
    @Test
    public void testSaveToValidPathInsideProjectMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        File savedFile = new File("testFiles/test6.jpg");
        // provide an invalid save path/name
        String output = parser.getCommand("save testFiles/test6.jpg").execute();
        File actualFileLoc = new File(System.getProperty("user.dir") + "/" + savedFile.toString());
        actualFileLoc.delete();
        assertTrue(output.equals("Image saved to testFiles/test6.jpg"));
    }

    /**
     * Tests message output after saving a file with invalid characters.
     */
    @Test
    public void testSaveInvalidNameMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // provide an invalid save path/name
        String output = parser.getCommand("save '#?%^*s").execute();
        assertTrue(output.equals("Failed to save"));
    }

    /**
     * Tests message output after saving a file within an invalid file path.
     */
    @Test
    public void testSaveInvalidPathMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // provide an invalid save path/name
        String output = parser.getCommand("save //sdfsdf/dsfs").execute();
        assertTrue(output.equals("Failed to save"));
    }

    /**
     * Tests message output after saving a file in the project's root directory.
     */
    @Test
    public void testSaveMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        File savedFile = new File("test5.jpg");
        String output = parser.getCommand("save test5.jpg").execute();
        // clean generated files
        savedFile.delete();
        assertTrue(output.equals("Image saved to test5.jpg"));
    }

    /**
     * Tests message output after using the save command by itself without
     * referring to a file name/path.
     */
    @Test
    public void testSaveNoSecondWord() {
        assertTrue(parser.getCommand("save").execute().equals("save as what?"));
    }

    /**
     * Tests message output after using save command without any image loaded.
     */
    @Test
    public void testSaveNoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("save test1").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

}
