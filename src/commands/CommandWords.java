package commands;

import java.util.*;
import src.ColorImage;
import src.Resources;

/**
 * The CommandWords Class holds an enumeration of all command words that are
 * valid. It is used to link command names with their corresponding Class
 * executors.
 *
 * @author Michael Kolling and David J. Barnes,
 * @version 2019.03.18
 */
public class CommandWords {

    private final LinkedHashMap<String, Object[]> commands;

    /**
     * Initialise the valid command words and pair them with their corresponding
     * Class executor.
     *
     * @param messages internationalisation resource which enables localisation
     * @param resources central resources shared within the application
     */
    public CommandWords(ResourceBundle messages, Resources resources) {
        commands = new LinkedHashMap<>();
        commands.put(messages.getString("openFunc"), new Object[]{messages.getString("openHelp"), new OpenCommand(messages, resources)});
        commands.put(messages.getString("saveFunc"), new Object[]{messages.getString("saveHelp"), new SaveCommand(messages, resources)});
        commands.put(messages.getString("lookFunc"), new Object[]{messages.getString("lookHelp"), new LookCommand(messages, resources)});
        commands.put(messages.getString("monoFunc"), new Object[]{messages.getString("monoHelp"), new MonoCommand(messages, resources)});
        commands.put(messages.getString("rot90Func"), new Object[]{messages.getString("rot90Help"), new Rotate90Command(messages, resources)});
        commands.put(messages.getString("helpFunc"), new Object[]{messages.getString("helpHelp"), new HelpCommand(this, messages)});
        commands.put(messages.getString("quitFunc"), new Object[]{messages.getString("quitHelp"), new QuitCommand(messages, resources)});
        commands.put(messages.getString("scriptFunc"), new Object[]{messages.getString("scriptHelp"), new ScriptCommand(messages, resources)});
        commands.put(messages.getString("flipHFunc"), new Object[]{messages.getString("flipHHelp"), new FlipHorizontalCommand(messages, resources)});
        commands.put(messages.getString("flipVFunc"), new Object[]{messages.getString("flipVHelp"), new FlipVerticalCommand(messages, resources)});
        commands.put(messages.getString("undoFunc"), new Object[]{messages.getString("undoHelp"), new UndoCommand(messages, resources)});
        commands.put(messages.getString("putFunc"), new Object[]{messages.getString("putHelp"), new PutImageCommand(messages, resources)});
        commands.put(messages.getString("getFunc"), new Object[]{messages.getString("getHelp"), new GetImageCommand(messages, resources)});
        commands.put(messages.getString("cacheFunc"), new Object[]{messages.getString("cacheHelp"), new ViewImageCacheCommand(messages, resources)});

    }

    /**
     * Determines whether a given String is a valid command word.
     *
     * @param firstWord first word from the user input
     * @return true if the given String is a valid command, false otherwise
     */
    private boolean isCommand(String firstWord) {
        return commands.containsKey(firstWord);
    }

    /**
     * Returns a command object that corresponds to the command name given
     *
     * @param command command name
     * @return retrieved command object
     */
    public Command get(String command) {
        if (isCommand(command)) {
            return (Command) commands.get(command)[1];
        }
        return null;
    }

    /**
     * Iterates through the LinkedHashMap of valid commands and returns them in
     * String format with instructions of their usage.
     *
     * @return a list of valid commands
     */
    public String getAllCommands() {
        String commandList = "";
        for (Map.Entry<String, Object[]> entry : commands.entrySet()) {
            commandList += "\n" + entry.getValue()[0];

        } 
        return commandList;
    }
}
