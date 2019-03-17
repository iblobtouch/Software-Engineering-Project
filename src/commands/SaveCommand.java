package commands;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import src.Resources;

public class SaveCommand extends Command {
    private final ResourceBundle messages;
    private final CommandWords commandWords;
    private final Resources sharedResource;
	
    /**
     * @param words instance of commandWords class which enables the
     * retrieval of all valid commands (used here when HelpCommand is called)
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public SaveCommand(CommandWords words, ResourceBundle messages, Resources resources) {
	this.messages = messages;
	this.commandWords = words;
	this.sharedResource = resources;
    }
	
    /**
     * "save 'name'" was entered. Save the current image to the file 
     * given as the second word of the command. 
     * @return Message output after saving image file
     */
    @Override
    public String execute() {
        String output = "";
    	if (sharedResource.getCurrentImage() == null) {
            return messages.getString("noImgLoaded");
        }
        
        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know where to save...
            return messages.getString("saveAs");
        }
  
        String outputName = this.getSecondWord();
        try {
            File outputFile = new File(outputName);
            ImageIO.write(sharedResource.getCurrentImage(), "jpg", outputFile);
            output += messages.getString("imgSavedTo") + outputName;
        } catch (IOException e) {
            output += e.getMessage();
            output += new HelpCommand(commandWords, messages).execute();
            return output;
        }
        return output;
    }
}
