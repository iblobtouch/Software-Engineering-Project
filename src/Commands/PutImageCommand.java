package Commands;
import java.awt.Color;
import java.util.ResourceBundle;
import src.ColorImage;
import src.Resources;

public class PutImageCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     *
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public PutImageCommand(ResourceBundle messages) {
	this.messages = messages;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "put" was entered. Put a copy of the current image on the cache. 
     * @return result of adding a mono filter
     */
    @Override
    public String execute() {
        sharedResource.putImage();
    	return sharedResource.getName() + messages.getString("imgAdded");
    }
}
