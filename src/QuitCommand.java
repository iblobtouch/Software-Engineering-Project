import java.util.ResourceBundle;

public class QuitCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     *
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public QuitCommand(ResourceBundle messages) {
	this.messages = messages;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the editor.
     * @return output after quitting the application
     */
    @Override
    public String execute() {
        String output = "";
    	if (this.hasSecondWord()) {
            sharedResource.setFinished(false);
            output += messages.getString("quitWhat");
        } else {
            sharedResource.setFinished(true);  // signal that we want to quit
        }
        return output;
    }
}
