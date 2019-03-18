package commands;

import java.util.*;
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

    private final LinkedHashMap<String, Command> commands;

    /**
     * Initialise the valid command words and pair them with their corresponding
     * Class executor.
     *
     * @param messages internationalisation resource which enables localisation
     * @param resources central resources shared within the application
     */
    public CommandWords(ResourceBundle messages, Resources resources) {
        commands = new LinkedHashMap<>();
        commands.put(messages.getString("openFunc"), new OpenCommand(this, messages, resources));
        commands.put(messages.getString("saveFunc"), new SaveCommand(this, messages, resources));
        commands.put(messages.getString("lookFunc"), new LookCommand(messages, resources));
        commands.put(messages.getString("monoFunc"), new MonoCommand(messages, resources));
        commands.put(messages.getString("rot90Func"), new Rotate90Command(messages, resources));
        commands.put(messages.getString("helpFunc"), new HelpCommand(this, messages));
        commands.put(messages.getString("quitFunc"), new QuitCommand(messages, resources));
        commands.put(messages.getString("scriptFunc"), new ScriptCommand(messages, resources));
        commands.put(messages.getString("flipHFunc"), new FlipHorizontalCommand(messages, resources));
        commands.put(messages.getString("flipVFunc"), new FlipVerticalCommand(messages, resources));
        commands.put(messages.getString("undoFunc"), new UndoCommand(messages, resources));
        commands.put(messages.getString("putFunc"), new PutImageCommand(messages, resources));
        commands.put(messages.getString("getFunc"), new GetImageCommand(messages, resources));
        commands.put(messages.getString("cacheFunc"), new ViewImageCacheCommand(messages, resources));

    }

    /**
     * Determines whether a given String is a valid command word.
     *
     * @param firstWord first word from the user input
     * @return true if the given String is a valid command, false otherwise
     */
    public boolean isCommand(String firstWord) {
        return commands.containsKey(firstWord);
    }

    /**
     * Returns a command object that corresponds to the command name given
     *
     * @param command command name
     * @return retrieved command object
     */
    public Command get(String command) {
        return commands.get(command);
    }

    /**
     * Iterates through the LinkedHashMap of valid commands and returns them in
     * String format.
     *
     * @return a list of valid commands
     */
    public String getAll() {
        String commandList = "";
        for (Iterator<String> i = commands.keySet().iterator(); i.hasNext();) {
            commandList = commandList + i.next() + " ";
        }
        return commandList;
    }
}
