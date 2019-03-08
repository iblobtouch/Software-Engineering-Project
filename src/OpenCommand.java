import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class OpenCommand extends Command{

	private ResourceBundle messages;
	private CommandWords commandWords;
	private Resources sharedResource;
	
	public OpenCommand(CommandWords words, ResourceBundle messages) {
		this.messages = messages;
		this.commandWords = words;
		sharedResource = Resources.getSharedResources();
		// TODO Auto-generated constructor stub
	}
	
	 /**
     * "open" was entered. Open the file given as the second word of the command
     * and use as the current image. 
     * @param command the command given.
     */
    @Override
    public void execute() {
        if (!this.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            System.out.println(messages.getString("openWhat"));
            return ;
        }
  
        String inputName = this.getSecondWord();
        
		try {
			ColorImage img = loadImage(inputName);

			if (img == null) {
	            new HelpCommand(commandWords, messages);
	        } else {
	        	sharedResource.setImage(img);
	            sharedResource.setName(inputName);
	            //Initialise array list
	            sharedResource.getFilters().add(null);
	            sharedResource.getFilters().add(null);
	            sharedResource.getFilters().add(null);
	            sharedResource.getFilters().add(null);
	            System.out.println("Loaded " + sharedResource.getName());
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
	/**
     * Load an image from a file.
     * @param name The name of the image file
     * @return a ColorImage containing the image
     */
    private ColorImage loadImage(String name) throws IOException {
        ColorImage img = null;
        try {
            img = new ColorImage(ImageIO.read(new File(name)));
        } catch (IOException e) {
            throw new IOException(messages.getString("imgNotFound") + name + "\n" + messages.getString("imgDir") + System.getProperty("user.dir"));
        }
        return img;
    }


   
	
}
