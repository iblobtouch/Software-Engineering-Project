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
    private ArrayList<String> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords(ResourceBundle messages)
    {
        // nothing to do at the moment...
        Class c = CommandMethods.class;
        Method[] m = c.getDeclaredMethods();
        System.out.println(Arrays.toString(m));
        validCommands = new ArrayList<String>(m.length);
        
        for (int i = 0; i < m.length; i += 1) {
            if (Modifier.isPublic(m[i].getModifiers())) {
                validCommands.add(messages.getString(m[i].getName() + "Func"));
            }
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        if(validCommands.contains(aString)) {
            return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    public String getCommands() {
        return validCommands.toString();
    }
}
