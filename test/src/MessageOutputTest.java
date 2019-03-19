package src;

import java.io.File;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.filechooser.FileSystemView;

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
        resources.resetResources();
        parser = new Parser(messages, resources);
    }

    // Test General Message Output - mono, rot90, flipH, flipV, help, quit, look, cache, undo, put, get, save, script
    
    
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

    //Failed to save
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
        File actualFileLoc = new File (System.getProperty("user.dir") + "/" + savedFile.toString());
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
    public void testOpenUnsupportedFileMessageOutput() {
        String output = parser.getCommand("open script.txt").execute();
        assertTrue(output.equals("File is unsupported"));
    }

    @Test
    public void testOpenNonExistingFileMessageOutput() {
        String output = parser.getCommand("open test4.jpg").execute();
        assertTrue(output.equals("Cannot find image file, test4.jpg"
                + "\ncwd is " + System.getProperty("user.dir")));
    }

    @Test
    public void testOpenMessageOutput() {
        String output = parser.getCommand("open input.jpg").execute();
        assertTrue(output.equals("Loaded input.jpg"));
    }

    @Test
    public void testPutOverwriteMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // store an image in the image cache
        parser.getCommand("put test3").execute();
        // make more changes
        parser.getCommand("rot90").execute();
        parser.getCommand("flipV").execute();
        // Overwrite existing image in the cache
        String output = parser.getCommand("put test3").execute();
        assertTrue(output.equals("The following cache has been overwritten: test3"));
    }

    @Test
    public void testPutMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // store an image in the image cache
        String output = parser.getCommand("put test2").execute();
        assertTrue(output.equals("test2 has been added to the cache"));
    }

    @Test
    public void testGetMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // store an image in the image cache
        parser.getCommand("put test1").execute();
        String output = parser.getCommand("get test1").execute();
        assertTrue(output.equals("Loaded test1"));
    }

    @Test
    public void testGetNonExistingImageMessageOutput() {
        parser.getCommand("open input.jpg").execute();
        // get a non-existing image from the image cache
        String output = parser.getCommand("get test1").execute();
        assertTrue(output.equals("Cannot find cache image"));
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
    public void testCacheEmptyMessageOutput() {
        assertTrue(parser.getCommand("cache").execute().equals("Image cache list:"));
    }

    @Test
    public void testCacheMessageOutput() {
        // insert some images in cache
        parser.getCommand("open input.jpg").execute();
        parser.getCommand("put test1").execute();
        parser.getCommand("put test2").execute();
        parser.getCommand("put test3").execute();
        String output = parser.getCommand("cache").execute();
        assertTrue(output.equals("Image cache list:"
                + "\ntest1"
                + "\ntest2"
                + "\ntest3"));
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
    public void testQuitMessageOutput() {
        String output = parser.getCommand("quit").execute();
        assertTrue(output.equals("Thank you for using Fotoshop.  Good bye."));
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

    // Exceeded Pipes Tests - mono, rot90, flipH, flipV
    @Test
    public void testExceededPipeMono() {
        assertTrue(automateExceededPipe("mono").equals("Filter pipeline exceeded"));
    }

    @Test
    public void testExceededPipeRot90() {
        assertTrue(automateExceededPipe("rot90").equals("Filter pipeline exceeded"));
    }

    @Test
    public void testExceededPipeFlipH() {
        assertTrue(automateExceededPipe("flipH").equals("Filter pipeline exceeded"));
    }

    @Test
    public void testExceededPipeFlipV() {
        assertTrue(automateExceededPipe("flipV").equals("Filter pipeline exceeded"));
    }

    private String automateExceededPipe(String command) {
        parser.getCommand("open input.jpg").execute();
        // Fill pipes
        parser.getCommand("mono").execute();
        parser.getCommand("rot90").execute();
        parser.getCommand("flipH").execute();
        parser.getCommand("flipV").execute();
        String output = parser.getCommand(command).execute();
        return output;
    }

    // No Image Loaded Test - mono, rot90, flipH, flipV, save, undo, put
    @Test
    public void testNoImageLoadedMono() {
        // No Image has been loaded
        String output = parser.getCommand("mono").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    @Test
    public void testNoImageLoadedRot90() {
        // No Image has been loaded
        String output = parser.getCommand("rot90").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    @Test
    public void testNoImageLoadedFlipH() {
        // No Image has been loaded
        String output = parser.getCommand("flipH").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    @Test
    public void testNoImageLoadedflipV() {
        // No Image has been loaded
        String output = parser.getCommand("flipV").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    @Test
    public void testNoImageLoadedSave() {
        // No Image has been loaded
        String output = parser.getCommand("save test1").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    @Test
    public void testNoImageLoadedUndo() {
        // No Image has been loaded
        String output = parser.getCommand("undo").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    @Test
    public void testNoImageLoadedPut() {
        // No Image has been loaded
        String output = parser.getCommand("put test2").execute();
        assertTrue(output.equals("No Image Loaded"));
    }

    // No second word provided tests - open, save, script, put, get
    @Test
    public void testOpenNoSecondWord() {
        assertTrue(parser.getCommand("open").execute().equals("open what?"));
    }

    @Test
    public void testSaveNoSecondWord() {
        assertTrue(parser.getCommand("save").execute().equals("save as what?"));
    }

    @Test
    public void testScriptNoSecondWord() {
        assertTrue(parser.getCommand("script").execute().equals("which script?"));
    }

    @Test
    public void testPutNoSecondWord() {
        assertTrue(parser.getCommand("put").execute().equals("save as what?"));
    }

    @Test
    public void testGetNoSecondWord() {
        assertTrue(parser.getCommand("get").execute().equals("get what?"));
    }

    // test unrecognized command
}
