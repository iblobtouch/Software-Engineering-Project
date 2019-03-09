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
     */
    @Override
    public void execute() {
    	System.out.println(messages.getString("currentImg") + sharedResource.getName());
        System.out.print(messages.getString("appliedFltrs"));
        if (sharedResource.getFilters()[0] != null) {
            System.out.print(sharedResource.getFilters()[0] + " ");
        }
        if (sharedResource.getFilters()[1]!= null) {
            System.out.print(sharedResource.getFilters()[1] + " ");
        }
        if (sharedResource.getFilters()[2] != null) {
            System.out.print(sharedResource.getFilters()[2] + " ");
        }
        if (sharedResource.getFilters()[3] != null) {
            System.out.print(sharedResource.getFilters()[3] + " ");
        }
        System.out.println();
    }	
}
