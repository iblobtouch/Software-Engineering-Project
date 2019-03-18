package commands;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
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
    private final CommandWords commandWords;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param words instance of commandWords
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public SaveCommand(CommandWords words, ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.commandWords = words;
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
        if (sharedResource.getCurrentImage() == null) {
            return messages.getString("noImgLoaded");
        }

        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know where to save...
            return messages.getString("saveAs");
        }

        String outputName = this.getSecondWord();
        try {
            File outputFile = new File(outputName);
            ImageIO.write(sharedResource.getCurrentImage(), "jpg", outputFile);
            output += messages.getString("imgSavedTo") + outputName;
        } catch (IOException e) {
            output += e.getMessage();
            output += new HelpCommand(commandWords, messages).execute();
            return output;
        }
        return output;
    }
}
