package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import src.ColorImage;
import src.Resources;

/**
 * SaveCommand is an executor class which saves the image file currently being
 * edited to the project's root directory. It's an extention of the abstract
 * class Command and contains its main operation in its inherited execute()
 * method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class SaveCommand extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public SaveCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Saves the current image to the project's root directory with a given file
     * name. Triggered after 'save [fileName]' was entered.
     *
     * @return message output after saving image file
     */
    @Override
    public String execute() {
        String output = "";

        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know where to save...
            return messages.getString("saveAs");
        }

        if (sharedResource.getCurrentImage() == null) {
            return messages.getString("noImgLoaded");
        }

        String outputName = this.getSecondWord();
        File outputFile = new File(outputName);

        try {
            if (isValidPath(outputName)) {
                if (outputFile.isAbsolute()) {
                    // check if directory exists
                    if (outputFile.getParentFile().exists()) {
                        ImageIO.write(sharedResource.getCurrentImage(), "jpg", outputFile);
                    } else {
                        throw new Exception();
                    }
                } else {
                    ImageIO.write(sharedResource.getCurrentImage(), "jpg", outputFile);
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            return messages.getString("failedSave");
        }

        output += messages.getString("imgSavedTo") + " " + outputName;
        return output;
    }

    /**
     * Check if the name/path given is a valid path.
     *
     * @param path file name/path given
     * @return true if name/path is valid, false otherwise
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }
}
