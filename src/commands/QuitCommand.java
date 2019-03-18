package commands;

import java.util.ResourceBundle;
import src.Resources;

/**
 * QuitCommand is an executor class which terminates the application upon user
 * request. It's an extention of the abstract class Command and contains its
 * main operation in its inherited execute() method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class QuitCommand extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public QuitCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Quits the application upon request. Triggered after 'quit' was entered.
     *
     * @return message output after quitting the application
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
