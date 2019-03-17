package commands;
import java.util.ResourceBundle;
import src.Resources;

/**
 *
 * @author regno
 */
public class QuitCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public QuitCommand(ResourceBundle messages, Resources resources) {
	this.messages = messages;
	this.sharedResource = resources;
    }
	
    /**
     * "quit" was entered. Check the rest of the command to see whether we
     * really quit the editor.
     * @return Message output after quitting the application
     */
    @Override
    public String execute() {
        String output = "";
    	if (this.hasSecondWord()) {
            sharedResource.setFinished(false);
            output += messages.getString("quitWhat");
        } else {
            sharedResource.setFinished(true);  // signal that we want to quit
        }
        return output;
    }
}
