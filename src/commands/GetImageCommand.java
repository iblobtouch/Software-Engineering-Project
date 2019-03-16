package commands;
import java.util.ResourceBundle;
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
        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            return messages.getString("openWhat") + "\n";
        }
        String inputName = this.getSecondWord();
        if (sharedResource.getImageFromCache(inputName) != null) {
            sharedResource.setCurrentImage(sharedResource.getImageFromCache(inputName));
            return messages.getString("loaded") + sharedResource.getName() + "\n";
        } else {
            return messages.getString("openWhat") + "\n";
        } 
    }
}
