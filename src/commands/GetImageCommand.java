package commands;
import commands.Command;
import commands.HelpCommand;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import src.ColorImage;
import src.Resources;

public class GetImageCommand extends Command{

    private final ResourceBundle messages;
    private final CommandWords commandWords;
    private final Resources sharedResource;
	
    /**
     *
     * @param words - instance of commandWords class which enables the
     * retrieval of all valid commands (used here when HelpCommand is called)
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public GetImageCommand(CommandWords words, ResourceBundle messages) {
        this.messages = messages;
        this.commandWords = words;
        sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "open" was entered. Open the file given as the second word of the command
     * and use as the current image. 
     * @return the result of opening an image file
     */
    @Override
    public String execute() {
        String output = "";
        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            return messages.getString("openWhat") + "\n";
        }
        String inputName = this.getSecondWord();
            boolean res = sharedResource.getImage(inputName);
            if (res == false) {
                output += res;
	    } else {
                output += messages.getString("loaded") + sharedResource.getName() + "\n";
	    }
        
        return output;
    }
}
