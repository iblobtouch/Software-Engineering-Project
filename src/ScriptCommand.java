import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class ScriptCommand extends Command{

	private ResourceBundle messages;
	private Resources sharedResource;
	
	public ScriptCommand(ResourceBundle messages) {
		this.messages = messages;
		sharedResource = Resources.getSharedResources();
		// TODO Auto-generated constructor stub
	}
	
	 /**
     * The 'script' command runs a sequence of commands from a
     * text file.
     * 
     * IT IS IMPORTANT THAT THIS COMMAND WORKS AS IT CAN BE USED FOR TESTING
     * 
     * @param command the script command, second word of which is the name of 
     * the script file.
     * @return What command to execute, null for no command.
     */
    @Override
    public void execute() {
    	if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            System.out.println(messages.getString("whichScript")); 
            sharedResource.setFinished(false);
        }
  
        String scriptName = this.getSecondWord();
        Parser scriptParser = new Parser(messages);
        try (FileInputStream inputStream = new FileInputStream(scriptName)) {
            scriptParser.setInputStream(inputStream);
            boolean finished = false;
            while (!finished) {
                try {
                    Command cmd = scriptParser.getCommand();
                    finished = true;//processCommand(cmd);
                } catch (Exception ex) {
                	sharedResource.setFinished(finished);
                }               
            }
            sharedResource.setFinished(finished);
        } 
        catch (FileNotFoundException ex) {
            System.out.println(messages.getString("cannotFind") + scriptName);
            sharedResource.setFinished(false);
        }
        catch (IOException ex) {
            throw new RuntimeException(messages.getString("scriptBarfed"));
        }
        
    }

   
	
}
