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
public class HelpCommandTest {

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
}
