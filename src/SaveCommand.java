import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;

public class SaveCommand extends Command {
    private final ResourceBundle messages;
    private final CommandWords commandWords;
    private final Resources sharedResource;
	
    /**
     *
     * @param words - instance of commandWords class which enables the
     * retrieval of all valid commands (used here when HelpCommand is called)
     * @param messages - Contains the internalisation resource which
     * enables localisation
     */
    public SaveCommand(CommandWords words, ResourceBundle messages) {
	this.messages = messages;
	this.commandWords = words;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "save" was entered. Save the current image to the file given as the 
     * second word of the command. 
     */
    @Override
    public void execute() {
    	if (sharedResource.getCurrentImage() == null) {
            new HelpCommand(commandWords, messages).execute();
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
            System.out.println(messages.getString("imgSavedTo") + outputName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            new HelpCommand(commandWords, messages).execute();
        }
    }
}
