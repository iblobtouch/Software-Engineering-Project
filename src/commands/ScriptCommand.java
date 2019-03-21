package commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import src.Parser;
import src.Resources;

/**
 * ScriptCommand is an executor class which opens and executes a script text
 * file that contains a series of commands. It's an extention of the abstract
 * class Command and contains its main operation in its inherited execute()
 * method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class ScriptCommand extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public ScriptCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Runs a sequence of commands from a text file. Triggered after 'script
     * [textFile] *[directory]' was entered. A directory within the project's
     * root directory can also be given as the third word in the user input.
     * This command can also be used recursively e.g. calling script command
     * within a script text file.
     *
     * @return message output after executing a script
     */
    @Override
    public String execute() {
        String output = "";

        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            sharedResource.setFinished(false);
            return messages.getString("whichScript");
        }

        String scriptName = this.getSecondWord();
        Parser scriptParser = new Parser(messages, sharedResource);

        try {
            File currentDir = new File(System.getProperty("user.dir"));
            String fileName = this.hasThirdWord() ? currentDir.getAbsolutePath()
                    + "\\" + this.getThirdWord() + "\\" + scriptName
                    : scriptName;
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String str;
                while ((str = br.readLine()) != null) {
                    Command cmd = scriptParser.getCommand(str);
                    // executes new commands from a script
                    output += executeScript(cmd) + "\n";
                }
            }
        } catch (FileNotFoundException fnf) {
            sharedResource.setFinished(false);
            return messages.getString("cannotFind") + scriptName;
        } catch (IOException io) {
            return messages.getString("scriptBarfed");
        }

        return output;
    }

    /**
     * Executes the content of a script file.
     *
     * @param command user command extracted from a line in the script file
     * @return message result after executing a script appended and returned to
     * the executor function
     */
    public String executeScript(Command command) {
        String output = "";
        if (command == null) {
            output += messages.getString("unclearMsg");
        } else {
            output += command.execute();
        }
        return output;
    }

}
