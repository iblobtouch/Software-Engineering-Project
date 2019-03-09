import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;

public class ScriptCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
    
    /**
     *
     * @param messages - Contains the internalisation resource which
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
     */
    @Override
    public void execute() {
    	if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            System.out.println(messages.getString("whichScript")); 
            sharedResource.setFinished(false);
            return;
        }
  
        String scriptName = this.getSecondWord();
        Parser scriptParser = new Parser(messages);
        try (FileInputStream inputStream = new FileInputStream(scriptName)) {
            scriptParser.setInputStream(inputStream);
            boolean finished = false;
            while (!finished) {
                try {
                    Command cmd = scriptParser.getCommand();
                    // executes new commands from a script
                    new Editor(messages).edit(cmd);
                } catch (Exception ex) {
                    // No further script to read. Set local 'finished' to true
                    finished = true;
                }               
            }
            // Set global 'finished' to false
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
