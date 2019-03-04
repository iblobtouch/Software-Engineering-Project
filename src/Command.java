/**
 * This class is taken from the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Command
{
    private String[] commandList = {null, null, null};

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognized.
     * @param secondWord The second word of the command.
     * @param thirdWord The second word of the command.
     */
    public Command(String firstWord, String secondWord, String thirdWord)
    {   
        commandList[0] = firstWord;
        commandList[1] = secondWord;
        commandList[2] = thirdWord;
    }
    
    /**
     * Check the value at the given position is not null.
     * @param pos
     * @return true if the value at the specified position is not null.
     */
    
    public boolean hasWord(int pos){
        
        return commandList[pos] != null;
    }
    
     /**
     * Get the word at the specified position.
     * @param pos
     * @return the word at the specified position.
     */
    
    public String getWord(int pos){
        
        return commandList[pos];
    }
}