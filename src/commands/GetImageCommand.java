package commands;
import java.util.ResourceBundle;
import src.Resources;

public class GetImageCommand extends Command{

    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public GetImageCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }
	
    /**
     * "get 'name'" was entered. Retrieve an image saved from the image cache.
     * @return Message output after retrieving an image from the cache
     */
    @Override
    public String execute() {
        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            return messages.getString("getWhat") + "\n";
        }
        String inputName = this.getSecondWord();
        if (sharedResource.getImageFromCache(inputName) != null) {
            sharedResource.setCurrentImage(sharedResource.getImageFromCache(inputName));
            return messages.getString("loaded") + sharedResource.getName() + "\n";
        } else {
            return messages.getString("openWhat") + "\n";
        } 
    }
}
