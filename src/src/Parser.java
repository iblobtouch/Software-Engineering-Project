package src;

import commands.CommandWords;
import commands.Command;
import java.util.Scanner;
import java.util.*;

/**
 * This parser class reads user input and tries to interpret it as an
 * "Adventure" command. Every time it is called it reads a line from the
 * terminal and tries to interpret the line as a three word command. It returns
 * the command as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against the
 * known commands, and if the input is not one of the known commands, it returns
 * a command object that is marked as an unknown command.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2019.03.18
 */
public class Parser {

    private final CommandWords commands;  // holds all valid command words
    ResourceBundle messages;

    /**
     * Create a parser to read from the terminal window.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public Parser(ResourceBundle messages, Resources resources) {
        commands = new CommandWords(messages, resources);
        this.messages = messages;
    }

    /**
     * Constructs and returns a Command object based on the user input read.
     *
     * @param inputLine user input from the user
     * @return constructed command object
     */
    public Command getCommand(String inputLine) {
        String[] wordList = {null, null, null};

        // Find up to three words on the line.
        Scanner tokenizer = new Scanner(inputLine);

        for (int i = 0; tokenizer.hasNext() && i < 3; i++) {
            wordList[i] = tokenizer.next();
        }

        Command command = commands.get(wordList[0]);
        // Only set the second and third word if the first word given
        // is found under valid commands in HashMap
        if (command != null) {
            command.setSecondWord(wordList[1]);
            command.setThirdWord(wordList[2]);
        }

        return command;
    }
}
