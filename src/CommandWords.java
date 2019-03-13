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

public class CommandWords
{
    private final LinkedHashMap<String, Command> commands;

    /**
     * Constructor - initialise the valid command words and pair them with
     * their corresponding Class instance
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public CommandWords(ResourceBundle messages)
    {   
        commands = new LinkedHashMap<>();
        commands.put(messages.getString("openFunc"), new OpenCommand(this, messages));
        commands.put(messages.getString("saveFunc"), new SaveCommand(this, messages));
        commands.put(messages.getString("lookFunc"), new LookCommand(messages));
        commands.put(messages.getString("monoFunc"), new MonoCommand(messages));
        commands.put(messages.getString("rot90Func"), new Rotate90Command(messages));
        commands.put(messages.getString("helpFunc"), new HelpCommand(this, messages));
        commands.put(messages.getString("quitFunc"), new QuitCommand(messages));
        commands.put(messages.getString("scriptFunc"), new ScriptCommand(messages));
        commands.put(messages.getString("flipHFunc"), new FlipHorizontalCommand(messages));
        commands.put(messages.getString("flipVFunc"), new FlipVerticalCommand(messages));
        
    }

    /**
     * Check whether a given String is a valid command word. 
     * @param command
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String command)
    {
        return commands.containsKey(command);
    }
    
    /**
     *
     * @param command
     * @return a key value pair which corresponds to the given command
     * parameter
     */
    public Command get(String command) {
    	return commands.get(command);
    }
    
    /**
     * Iterates through the HashMap of valid commands and returns them in
     * String format
     * @return valid commands in String format
     */
    public String getAll() {
    	String s = "";
    	for (Iterator<String> i = commands.keySet().iterator(); i.hasNext();) {
            s = s + i.next() + " ";
    	}
    	s = s + "\n";
    	return s;
    }
}
