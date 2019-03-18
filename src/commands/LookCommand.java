package commands;

import java.util.ResourceBundle;
import src.Resources;

/**
 * LookCommand is an executor class which returns the list of filters currently
 * applied to the image being edited. It's an extention of the abstract class
 * Command and contains its main operation in its inherited execute() method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class LookCommand extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public LookCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Returns the list of filters currently applied to the image. Triggered
     * after 'look' was entered.
     *
     * @return list of currently applied filters
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
