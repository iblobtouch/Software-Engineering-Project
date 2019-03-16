package src;
/**
 * This class is taken from the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a three word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

import commands.CommandWords;
import commands.Command;
import java.util.Scanner;
import java.util.*;

public class Parser 
{
    private final CommandWords commands;  // holds all valid command words
    ResourceBundle messages;
    
    /**
     * Create a parser to read from the terminal window.
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     * @param resources
     */
    public Parser(ResourceBundle messages, Resources resources) 
    {
        commands = new CommandWords(messages, resources);
        this.messages = messages;
    }
    
    /**
     * @param inputLine - User input from the user
     * @return The next command from the user.
     */
    public Command getCommand(String inputLine) 
    {        
        String[] wordList = {null, null, null};

        // Find up to three words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        
        for(int i=0; tokenizer.hasNext() && i < 3; i++){
            wordList[i] = tokenizer.next();
        }

        Command command = commands.get(wordList[0]);
        // Only set the second and third word if the first word given
        // is found under valid commands in HashMap
        if(command != null) {
            command.setSecondWord(wordList[1]);
            command.setThirdWord(wordList[2]);  
        }
        return command;
    }

    /**
     *
     * @return a list of valid command words
     */
    public String getCommands() {
    	return commands.getAll();
    }
}
