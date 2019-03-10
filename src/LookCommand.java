import java.util.ResourceBundle;

public class LookCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     *
     * @param messages
     */
    public LookCommand(ResourceBundle messages) {
	this.messages = messages;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "look" was entered. Report the status of the work bench. 
     * @return current state of image
     */
    @Override
    public String execute() {
        String output;
        output = messages.getString("currentImg") + sharedResource.getName()
                + "\n" + messages.getString("appliedFltrs");
        
        for (String filter : sharedResource.getFilters()) {
            if (filter != null) {
                output += filter + " ";
            }
        }
        output += "\n";
        
        return output;
    }	
}
