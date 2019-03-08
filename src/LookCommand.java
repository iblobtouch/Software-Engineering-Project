import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class LookCommand extends Command{

	private ResourceBundle messages;
	private Resources sharedResource;
	
	public LookCommand(ResourceBundle messages) {
		this.messages = messages;
		sharedResource = Resources.getSharedResources();
		// TODO Auto-generated constructor stub
	}
	
	/**
     * "look" was entered. Report the status of the work bench. 
     * @param command the command given.
     */
    @Override
    public void execute() {
    	System.out.println(messages.getString("currentImg") + sharedResource.getName());
        System.out.print(messages.getString("appliedFltrs"));
        ArrayList<String> filterList = sharedResource.getFilters();
        if (filterList.get(0) != null) {
            System.out.print(filterList.get(0) + " ");
        }
        if (filterList.get(1)!= null) {
            System.out.print(filterList.get(1) + " ");
        }
        if (filterList.get(2) != null) {
            System.out.print(filterList.get(2) + " ");
        }
        if (filterList.get(3) != null) {
            System.out.print(filterList.get(3) + " ");
        }
        System.out.println();
    }

   
	
}
