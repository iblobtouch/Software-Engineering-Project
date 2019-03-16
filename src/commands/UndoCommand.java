package commands;
import java.util.ResourceBundle;
import src.Resources;

public class UndoCommand extends Command{
    private final Resources sharedResource;
    private final ResourceBundle messages;
	
    /**
     *
     * @param messages
     * @param resources
     */
    public UndoCommand(ResourceBundle messages, Resources resources) {
	this.messages = messages;
	sharedResource = resources;
    }
	
    /**
     * Print out some help information. Here we print some useless, cryptic
     * message and a list of the command words.
     * @return Message stating the undo was completed
     */
    @Override
    public String execute() {
        if (sharedResource.getCurrentImageHistory().empty() == false) {
            sharedResource.undo();
            return messages.getString("undoComplete");
        } else {
            return messages.getString("noImgLoaded");
        }
    }
	
}
