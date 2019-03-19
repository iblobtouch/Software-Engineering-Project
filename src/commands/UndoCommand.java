package commands;

import java.util.EmptyStackException;
import java.util.ResourceBundle;
import src.Resources;

/**
 * UndoCommand is an executor class which removes the previously added filter to
 * the image. It's an extention of the abstract class Command and contains its
 * main operation in its inherited execute() method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class UndoCommand extends Command {

    private final Resources sharedResource;
    private final ResourceBundle messages;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public UndoCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Undo the previous operation to remove recently added filter. Triggered
     * after 'undo' was entered.
     *
     * @return message output stating if the undo was completed
     */
    @Override
    public String execute() {
        if (sharedResource.getCurrentImageHistory().empty()) {
            return messages.getString("noImgLoaded");
        } else if (sharedResource.getCurrentImageHistory().size() == 1) {
            return messages.getString("noFiltersUndo");
        } else {
            // removes previous image state
            sharedResource.getCurrentImageHistory().pop();
            return messages.getString("undoComplete");
        }
    }

}
