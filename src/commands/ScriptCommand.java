package commands;

import commands.Command;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import src.Parser;
import src.Resources;

public class ScriptCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
    
    /**
     *
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public ScriptCommand(ResourceBundle messages) {
	this.messages = messages;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * The 'script' command runs a sequence of commands from a
     * text file.
     * 
     * IT IS IMPORTANT THAT THIS COMMAND WORKS AS IT CAN BE USED FOR TESTING
     * 
     * @return result after executing a script
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
        Parser scriptParser = new Parser(messages);
        
        try {
            File currentDir = new File(System.getProperty("user.dir"));
            String fileName = this.hasThirdWord() ? currentDir.getAbsolutePath() 
                    + "\\" + this.getThirdWord() + "\\" + scriptName 
                    : scriptName;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            
            try {
                String str;
                while ((str = br.readLine()) != null) {
                    Command cmd = scriptParser.getCommand(str);
                    // executes new commands from a script
                    output += executeScript(cmd);
                }
            } 
            finally {
                br.close();
            }
        }
        catch (FileNotFoundException fnf) {
            sharedResource.setFinished(false);  
            return fnf.getMessage() + "\n" + messages.getString("cannotFind") + scriptName;
        }
        catch (IOException io) {
            return io.getMessage() + "\n" + messages.getString("scriptBarfed");
        }
       
        return output;
    }
    
    /**
     *
     * @param command - user commands executed from a script
     * edit with a provided command as parameter. Used in scripts
     * @return result of executing a script
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
