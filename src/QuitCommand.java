import java.util.ResourceBundle;

public class QuitCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     *
     * @param messages - Contains the internalisation resource which
     * enables localisation
     */
    public QuitCommand(ResourceBundle messages) {
	this.messages = messages;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the editor.
     */
    @Override
    public void execute() {
    	if (this.hasSecondWord()) {
            System.out.println(messages.getString("quitWhat"));
            sharedResource.setFinished(false);
        } else {
            sharedResource.setFinished(true);  // signal that we want to quit
        }
    }
}
