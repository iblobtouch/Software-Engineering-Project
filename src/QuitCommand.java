import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class QuitCommand extends Command{

	private ResourceBundle messages;
	private Resources sharedResource;
	
	public QuitCommand(ResourceBundle messages) {
		this.messages = messages;
		sharedResource = Resources.getSharedResources();
		// TODO Auto-generated constructor stub
	}
	
	/**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the editor.
     * @param command the command given.
     * @return true, if this command quits the editor, false otherwise.
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
