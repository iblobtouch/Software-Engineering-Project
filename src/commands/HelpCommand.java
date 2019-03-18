package commands;

import java.util.ResourceBundle;

/**
 * HelpCommand is an executor class which returns a help message to help the
 * user navigate and use the application. It's an extention of the abstract
 * class Command and contains its main operation in its inherited execute()
 * method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class HelpCommand extends Command {

    private final CommandWords commandWords;
    private final ResourceBundle messages;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param words instance of commandWords
     * @param messages contains the internationalisation resource which enables
     * localisation
     */
    public HelpCommand(CommandWords words, ResourceBundle messages) {
        this.commandWords = words;
        this.messages = messages;
    }

    /**
     * Returns some help information including the list of available
     * commands. Triggered after 'help' was entered.
     *
     * @return help message and valid commands
     */
    @Override
    public String execute() {
        String output = "";
        output = messages.getString("helpMsg1")
                + "\n" + messages.getString("helpMsg2")
                + "\n" + commandWords.getAll();
        return output;
    }

}
