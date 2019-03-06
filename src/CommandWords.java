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
    // a constant array that holds all valid command words
    private String[] validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords(ResourceBundle messages)
    {
        // nothing to do at the moment...
        validCommands = new String[] {
            messages.getString("openFunc"),
            messages.getString("saveFunc"),
            messages.getString("lookFunc"),
            messages.getString("monoFunc"),
            messages.getString("rotate90Func"),
            messages.getString("helpFunc"),
            messages.getString("quitFunc"),
            messages.getString("scriptFunc")};
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
}
