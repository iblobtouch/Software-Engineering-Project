package commands;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Stack;
import javax.imageio.ImageIO;
import src.ColorImage;
import src.Resources;

/**
 *
 * @author regno
 */
public class OpenCommand extends Command{

    private final ResourceBundle messages;
    private final CommandWords commandWords;
    private final Resources sharedResource;
	
    /**
     * @param words instance of commandWords class which enables the
     * retrieval of all valid commands (used here when HelpCommand is called)
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public OpenCommand(CommandWords words, ResourceBundle messages, Resources resources) {
	this.messages = messages;
        this.commandWords = words;
	this.sharedResource = resources;
    }
	
    /**
     * "open 'filename'" was entered. Open the file given 
     * as the second word of the command and use as the current image. 
     * @return Message output after opening an image file
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
     * Load an image from a file.
     * @param name The name of the image file
     * @return A ColorImage containing the image
     */
    private ColorImage loadImage(String name) throws IOException {
        ColorImage img = null;
        try {
            img = new ColorImage(ImageIO.read(new File(name)));
        } catch (IOException e) {
            throw new IOException(messages.getString("imgNotFound") + name + "\n" + messages.getString("imgDir") + System.getProperty("user.dir"));
        }
        return img;
    }	
}
