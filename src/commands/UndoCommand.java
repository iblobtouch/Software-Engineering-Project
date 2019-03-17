package commands;
import java.util.EmptyStackException;
import java.util.ResourceBundle;
import src.Resources;

public class UndoCommand extends Command{
    private final Resources sharedResource;
    private final ResourceBundle messages;
	
    /**
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public UndoCommand(ResourceBundle messages, Resources resources) {
	this.messages = messages;
	this.sharedResource = resources;
    }
	
    /**
     * "undo" was entered. Undo the previous operation to
     * go back on the previous state.
     * @return Message output stating if the undo was completed
     */
    @Override
    public String execute() {
        if (sharedResource.getCurrentImageHistory().empty()) {
            return messages.getString("noImgLoaded");
        } else if (sharedResource.getCurrentImageHistory().size() == 1) {
            return messages.getString("noFiltersUndo");
        } else {
            undo();
            return messages.getString("undoComplete");
        }           
    }
    
    /**
     * Removes the last operation performed on the current image.
     */
    public void undo() {
        try {
            if (!sharedResource.getCurrentImageHistory().isEmpty()) {
                sharedResource.getCurrentImageHistory().pop();
            }
        } catch (EmptyStackException e) {
        }
    }
	
}
