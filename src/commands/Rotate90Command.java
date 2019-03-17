package commands;
import java.awt.Color;
import java.util.ResourceBundle;
import src.ColorImage;
import src.Resources;

public class Rotate90Command extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public Rotate90Command(ResourceBundle messages, Resources resources) {
	this.messages = messages;
	this.sharedResource = resources;
    }
	
    /**
     * "rot90" was entered. Rotate the current image 90 degrees. 
     * @return Message output after rotating image to 90 degrees
     */
    @Override
    public String execute() {
        String output = "";
    	if (sharedResource.getCurrentFilters()[3] != null) {
            return messages.getString("exceededPipe");
        }
        
        if (sharedResource.getCurrentImage() == null) {
            return messages.getString("noImgLoaded");
        }
        
        // R90 = [0 -1, 1 0] rotates around origin
        // (x,y) -> (-y,x)
        // then transate -> (height-y, x)
        
        int height = sharedResource.getCurrentImage().getHeight();
        int width = sharedResource.getCurrentImage().getWidth();
        ColorImage rotImage = new ColorImage(height, width, sharedResource.getName(), sharedResource.getCurrentImage().getFilters());
        for (int y=0; y<height; y++) { // in the rotated image
            for (int x=0; x<width; x++) {
                Color pix = sharedResource.getCurrentImage().getPixel(x,y);
                rotImage.setPixel(height-y-1,x, pix);
            }
        }
        
        
        rotImage.addFilter("rot90");
        
        sharedResource.updateImage(rotImage);
        output += messages.getString("rot90Res");
        return output;
    }
}
