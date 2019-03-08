import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class SaveCommand extends Command{

	private ResourceBundle messages;
	private CommandWords commandWords;
	private Resources sharedResource;
	
	public SaveCommand(CommandWords words, ResourceBundle messages) {
		this.messages = messages;
		this.commandWords = words;
		sharedResource = Resources.getSharedResources();
		// TODO Auto-generated constructor stub
	}
	
	/**
     * "save" was entered. Save the current image to the file given as the 
     * second word of the command. 
     * @param command the command given
     */
    @Override
    public void execute() {
    	if (sharedResource.getCurrentImage() == null) {
            new HelpCommand(commandWords, messages);
            return;
        }
        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know where to save...
        	System.out.println(messages.getString("saveWhere"));
            return ;
        }
  
        String outputName = this.getSecondWord();
        try {
            File outputFile = new File(outputName);
            ImageIO.write(sharedResource.getCurrentImage(), "jpg", outputFile);
            System.out.println("Image saved to " + outputName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            new HelpCommand(commandWords, messages);
        }
        
    }

   
	
}
