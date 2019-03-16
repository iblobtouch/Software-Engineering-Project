package commands;
import commands.Command;
import commands.HelpCommand;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Stack;
import javax.imageio.ImageIO;
import src.ColorImage;
import src.Resources;

public class OpenCommand extends Command{

    private final ResourceBundle messages;
    private final CommandWords commandWords;
    private final Resources sharedResource;
	
    /**
     *
     * @param words - instance of commandWords class which enables the
     * retrieval of all valid commands (used here when HelpCommand is called)
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public OpenCommand(CommandWords words, ResourceBundle messages) {
        this.messages = messages;
        this.commandWords = words;
        sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "open" was entered. Open the file given as the second word of the command
     * and use as the current image. 
     * @return the result of opening an image file
     */
    @Override
    public String execute() {
        String output = "";
        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            return messages.getString("openWhat") + "\n";
        }
        String inputName = this.getSecondWord();
        try {
            ColorImage img = loadImage(inputName);
            img.setName(inputName);
            if (img == null) {
                output += new HelpCommand(commandWords, messages).execute();
	    } else {
                Stack<ColorImage> tmp = new Stack<ColorImage>();
                tmp.push(img);
                sharedResource.setCurrentImage(tmp);
	        // Initialise array list
                output += messages.getString("loaded") + inputName + "\n";
	    }
	} catch (IOException e) {
            return e.getMessage();
	}
        
        return output;
    }
    
    /**
     * Load an image from a file.
     * @param name The name of the image file
     * @return a ColorImage containing the image
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
