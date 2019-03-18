package src;

import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

public class MessageOutputTest {

    ResourceBundle messages;
    Parser parser;
    Resources resources;

    @Before
    public void setUp() {
        messages = ResourceBundle.getBundle("langFiles.MessagesBundle", new Locale("en", "UK"));
        resources = Resources.getSharedResources();
        parser = new Parser(messages, resources);
    }

    @Test
    public void testMonoMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("mono").execute();
        assertTrue(output.equals("Mono filter has been successfully applied to the image."));
    }

    @Test
    public void testRotateMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("rot90").execute();
        assertTrue(output.equals("Image has been successfully rotated around 90 degrees."));
    }

    @Test
    public void testFlipHMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("flipH").execute();
        assertTrue(output.equals("Image has been successfully flipped horizontally."));
    }

    @Test
    public void testFlipVMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("flipV").execute();
        assertTrue(output.equals("Image has been successfully flipped vertically."));
    }

    @Test
    public void testHelpMessageOutput() {
        String output = parser.getCommand("help").execute();
        assertTrue(output.equals("You are using Fotoshop.\n"
                + "Your command words are:\n"
                + "open [file Name] : Loads the file [file Name] located in the project's root directory\n"
                + "save [file Name] : Saves the image in the project's root directory with a name [file Name]\n"
                + "look : Views the loaded image and its currently applied filters\n"
                + "mono : Applies the mono filter to the image\n"
                + "rot90 : Rotates the image 90 degrees clockwise\n"
                + "help : Shows instructions on how to use the application\n"
                + "quit : Quits the application\n"
                + "script [file Name] [directory]* : Executes the commands written in the text file [file Name] in the root directory or the provided [directory]\n"
                + "flipH : Flips the image horizontally\n"
                + "flipV : Flips the image vertically\n"
                + "undo : Removes the previously added filter from the image\n"
                + "put [name] : Saves a copy of the image to the image cache with the name [name]\n"
                + "get [name] : Retrieves an image from the image cache\n"
                + "cache : Views the list of images currently stored in the image cache"));
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

    //exceeded pipe
}
