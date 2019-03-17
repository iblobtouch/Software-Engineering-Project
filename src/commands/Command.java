package commands;
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
 * known) then the command word is null.
 *
 * If the command had only one word, then the second word is null.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public abstract class Command
{
    private String secondWord;
    private String thirdWord;

    /**
     * Create a command object and initialises second and third words to null
     */
    public Command()
    {
        this.secondWord = null;
        this.thirdWord = null;
    }

    /**
     * @return The second word of this command. Returns null if second word
     * is not set
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * @return The third word of this command. Returns null if third word
     * is not set
     */
    public String getThirdWord()
    {
        return thirdWord;
    }
    
    /**
     * @return The boolean true if a second word has been set. 
     * Returns false otherwise
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    
    /**
     * @return The boolean true if a third word has been set. 
     * Returns false otherwise
     */
    public boolean hasThirdWord()
    {
        return (thirdWord != null);
    }
    
    /**
     * @param secondWord Word to set to the secondWord field.
     * Sets the second word with a given parameter
     */
    public void setSecondWord(String secondWord) {
    	this.secondWord = secondWord;
    }
    
    /**
     * @param thirdWord Word to set to the thirdWord field.
     * Sets the third word with a given parameter
     */
    public void setThirdWord(String thirdWord) {
    	this.thirdWord = thirdWord;
    }
    
    /**
     * Abstract method which ensures each function from a command call performs
     * the corresponding operation.
     * @return Output for each operation executed
     */
    public abstract String execute();
}

