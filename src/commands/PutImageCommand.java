package commands;

import java.util.ResourceBundle;
import java.util.Stack;
import src.ColorImage;
import src.Resources;

/**
 * PutImageCommand is an executor class which saves a copy of the current loaded
 * image to the image cache. It's an extention of the abstract class Command and
 * contains its main operation in its inherited execute() method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class PutImageCommand extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public PutImageCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Saves a copy of the current image to the image cache. Triggered after
     * 'put [newName]' was entered.
     *
     * @return message output after saving an image to the image cache
     */
    @Override
    public String execute() {
        String output = "";
        if (!this.hasSecondWord()) {
            return messages.getString("saveAs");
        }
        String inputName = this.getSecondWord();
        Stack<ColorImage> tempImg = new Stack<ColorImage>();
        tempImg = (Stack<ColorImage>) sharedResource.getCurrentImageHistory().clone();
        if (sharedResource.getCurrentImage() == null) {
            return messages.getString("noImgLoaded");
        }

        if (sharedResource.getImageCache().containsKey(inputName)) {
            output += messages.getString("cacheOverwrite") + inputName;
        } else {
            output += inputName + " " + messages.getString("imgAdded");
        }

        sharedResource.addToImageCache(inputName, tempImg);

        return output;
    }
}
