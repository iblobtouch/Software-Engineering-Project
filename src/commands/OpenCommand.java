package commands;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Stack;
import javax.imageio.ImageIO;
import src.ColorImage;
import src.Resources;

/**
 * OpenCommand is an executor class which loads an image file located in the
 * root directory to the application. It's an extention of the abstract class
 * Command and contains its main operation in its inherited execute() method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class OpenCommand extends Command {

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
    public OpenCommand(CommandWords words, ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.commandWords = words;
        this.sharedResource = resources;
    }

    /**
     * Loads an image file from the root directory. Triggered after 'open
     * [fileName]' was entered.
     *
     * @return message output after opening an image file
     */
    @Override
    public String execute() {
        String output = "";
        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            return messages.getString("openWhat");
        }
        String inputName = this.getSecondWord();
        try {
            ColorImage img = loadImage(inputName);
            sharedResource.setName(inputName);
            if (img == null) {
                output += new HelpCommand(commandWords, messages).execute();
            } else {
                Stack<ColorImage> tmp = new Stack<ColorImage>();
                tmp.push(img);
                sharedResource.setCurrentImageHistory(tmp);
                // Initialise array list
                output += messages.getString("loaded") + inputName;
            }
        } catch (IOException e) {
            return e.getMessage();
        }

        return output;
    }

    /**
     * Loads an image from a given file name.
     *
     * @param name the name of the image file
     * @return instance of ColorImage containing the loaded image
     */
    private ColorImage loadImage(String fileName) throws IOException {
        ColorImage img = null;
        try {
            img = new ColorImage(ImageIO.read(new File(fileName)));
        } catch (IOException e) {
            throw new IOException(messages.getString("imgNotFound") + fileName + "\n" + messages.getString("imgDir") + System.getProperty("user.dir"));
        }
        return img;
    }
}
