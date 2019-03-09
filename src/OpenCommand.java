import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;

public class OpenCommand extends Command{

    private final ResourceBundle messages;
    private final CommandWords commandWords;
    private final Resources sharedResource;
	
    /**
     *
     * @param words - instance of commandWords class which enables the
     * retrieval of all valid commands (used here when HelpCommand is called)
     * @param messages - Contains the internalisation resource which
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
     */
    @Override
    public void execute() {
        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            System.out.println(messages.getString("openWhat"));
            return ;
        }
        String inputName = this.getSecondWord();
        try {
            ColorImage img = loadImage(inputName);
            if (img == null) {
                new HelpCommand(commandWords, messages).execute();
	    } else {
                sharedResource.setImage(img);
	        sharedResource.setName(inputName);
	        // Initialise array list
	        sharedResource.initialiseFilters();
	        System.out.println(messages.getString("loaded") + sharedResource.getName());
	    }
	} catch (IOException e) {
            // Peform logging
            e.printStackTrace();
	}
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
