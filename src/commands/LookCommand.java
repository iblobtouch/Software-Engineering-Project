package commands;
import java.util.ResourceBundle;
import src.Resources;

/**
 *
 * @author regno
 */
public class LookCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public LookCommand(ResourceBundle messages, Resources resources) {
	this.messages = messages;
	this.sharedResource = resources;
    }
	
    /**
     * "look" was entered. Report the status of the work bench. 
     * @return Message output after entailing the current state of image
     */
    @Override
    public String execute() {
        String output;
        output = messages.getString("currentImg") + sharedResource.getName()
                + "\n" + messages.getString("appliedFltrs");
        
        for (String filter : sharedResource.getCurrentFilters()) {
            if (filter != null) {
                output += " " + filter;
            }
        }
        
        return output;
    }	
}
