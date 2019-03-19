/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.filechooser.FileSystemView;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author regno
 */
public class SaveCommandTest {

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
    public void testSaveToValidPathOutsideProjectMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String check = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        File savedFile = new File(check + "/temp/");
        String output = parser.getCommand("save " + savedFile.toString()).execute();
        savedFile.delete();
        assertTrue(output.equals("Image saved to " + savedFile.toString()));
    }

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

    @Test
    public void testSaveInvalidNameMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // provide an invalid save path/name
        String output = parser.getCommand("save '#?%^*s").execute();
        assertTrue(output.equals("Failed to save"));
    }

    @Test
    public void testSaveInvalidPathMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // provide an invalid save path/name
        String output = parser.getCommand("save //sdfsdf/dsfs").execute();
        assertTrue(output.equals("Failed to save"));
    }

    @Test
    public void testSaveMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        File savedFile = new File("test5.jpg");
        String output = parser.getCommand("save test5.jpg").execute();
        // clean generated files
        savedFile.delete();
        assertTrue(output.equals("Image saved to test5.jpg"));
    }

    @Test
    public void testSaveNoSecondWord() {
        assertTrue(parser.getCommand("save").execute().equals("save as what?"));
    }

    @Test
    public void testSaveNoImageLoaded() {
        // No Image has been loaded
        String output = parser.getCommand("save test1").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

}
