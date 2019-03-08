/**
 * This class is derived from the "World of Zuul" application,
 * author Michael Kolling and David J. Barnes,
 * version 2006.03.30
 * This class holds an enumeration of all command words known to the editor.
 * It is used to recognise commands as they are typed in.
 *
 * @version 2013.09.09
 */
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
public class CommandWords
{
    // a constant array that holds all valid command words
    private HashMap<String, Command> commands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords(ResourceBundle messages)
    {
    	
    	commands = new HashMap<String, Command>();
        
        commands.put(messages.getString("openFunc"), new OpenCommand(this, messages));
        commands.put(messages.getString("saveFunc"), new SaveCommand(this, messages));
        commands.put(messages.getString("lookFunc"), new LookCommand(messages));
        commands.put(messages.getString("monoFunc"), new MonoCommand(messages));
        commands.put(messages.getString("rot90Func"), new Rotate90Command(messages));
        commands.put(messages.getString("helpFunc"), new HelpCommand(this, messages));
        commands.put(messages.getString("quitFunc"), new QuitCommand(messages));
        commands.put(messages.getString("scriptFunc"), new ScriptCommand(messages));
        // commands.put(messages.getString("flipHFunc"), new HelpCommand(this, messages));
        
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String command)
    {
        return commands.containsKey(command);
    }
    
    public Command get(String command) {
    	return commands.get(command);
    }
    
    public String getAll() {
    	String s = "";
    	for (Iterator<String> i = commands.keySet().iterator(); i.hasNext(); )
    	{
    		s = s + i.next() + " ";
    	}
    	s = s + "\n";
    	return s;
    }
}
