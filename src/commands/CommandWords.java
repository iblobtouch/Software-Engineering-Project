package commands;
/**
 * This class is derived from the "World of Zuul" application,
 * author Michael Kolling and David J. Barnes,
 * version 2006.03.30
 * This class holds an enumeration of all command words known to the editor.
 * It is used to recognise commands as they are typed in.
 *
 * @version 2013.09.09
 */
import java.util.*;
import src.Resources;

/**
 *
 * @author regno
 */
public class CommandWords
{
    private final LinkedHashMap<String, Command> commands;

    /**
     * Initialise the valid command words and pair them with
     * their corresponding Class instance.
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public CommandWords(ResourceBundle messages, Resources resources)
    {   
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
     * Check whether a given String is a valid command word. 
     * @param command Command name
     * @return The boolean true if a given string is a valid command,
     * false if it isn't
     */
    public boolean isCommand(String command)
    {
        return commands.containsKey(command);
    }
    
    /**
     * @param command Command name
     * @return A key value pair which corresponds to the given command
     * parameter
     */
    public Command get(String command) {
    	return commands.get(command);
    }
    
    /**
     * Iterates through the HashMap of valid commands and returns them in
     * String format.
     * @return Valid commands in String format
     */
    public String getAll() {
    	String s = "";
    	for (Iterator<String> i = commands.keySet().iterator(); i.hasNext();) {
            s = s + i.next() + " ";
    	}
    	return s;
    }
}
