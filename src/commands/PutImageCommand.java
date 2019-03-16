package commands;
import java.util.ResourceBundle;
import java.util.Stack;
import src.ColorImage;
import src.Resources;

public class PutImageCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
    private final CommandWords commandWords;
	
    /**
     *
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public PutImageCommand(CommandWords words, ResourceBundle messages) {
	this.messages = messages;
        this.commandWords = words;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "put" was entered. Put a copy of the current image on the cache. 
     * @return result of adding a mono filter
     */
    @Override
    public String execute() {
        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            return messages.getString("saveAs") + "\n";
        }
        String inputName = this.getSecondWord();
        Stack<ColorImage> tempImg = sharedResource.getCurrentImageHistory();
        if (tempImg.isEmpty()) {
            return messages.getString("noImgLoaded");
        }
        ColorImage topImage = tempImg.peek();
        topImage.setName(inputName);
        tempImg.push(topImage);
        sharedResource.addToImageCache(tempImg);
        return sharedResource.getName() + messages.getString("imgAdded");
    }
}