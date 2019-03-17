package commands;

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
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public ScriptCommand(ResourceBundle messages, Resources resources) {
	this.messages = messages;
	this.sharedResource = resources;
    }
	
    /**
     * "script 'name'" was entered. Command runs a 
     * sequence of commands from a text file.
     * @return Message output after executing a script
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
     * Executes the content of a script file.
     * @param command User commands executed from a script
     * @return Result of executing a script appended to main function
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
