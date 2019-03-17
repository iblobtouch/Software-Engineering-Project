package commands;
import java.awt.Color;
import java.util.ResourceBundle;
import src.ColorImage;
import src.Resources;

public class Rotate90Command extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     *
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     * @param resources - Central Resources shared within the application
     */
    public Rotate90Command(ResourceBundle messages, Resources resources) {
	this.messages = messages;
	sharedResource = resources;
    }
	
    /**
     * "rot90" was entered. Rotate the current image 90 degrees. 
     * @return result after rotating image to 90 degrees
     */
    @Override
    public String execute() {
        String output = "";
    	if (sharedResource.getFilters()[3] != null) {
            return messages.getString("exceededPipe") + "\n";
        }
        
        if (sharedResource.getCurrentImage() == null) {
            return messages.getString("noImgLoaded");
        }
        
        // R90 = [0 -1, 1 0] rotates around origin
        // (x,y) -> (-y,x)
        // then transate -> (height-y, x)
        
        int height = sharedResource.getCurrentImage().getHeight();
        int width = sharedResource.getCurrentImage().getWidth();
        ColorImage rotImage = new ColorImage(height, width, sharedResource.getCurrentImage().getName(), sharedResource.getCurrentImage().getFilters());
        for (int y=0; y<height; y++) { // in the rotated image
            for (int x=0; x<width; x++) {
                Color pix = sharedResource.getCurrentImage().getPixel(x,y);
                rotImage.setPixel(height-y-1,x, pix);
            }
        }
        
        
        for (int i =0; i < rotImage.getFilters().length; i++) {
            if (rotImage.getFilters()[i] == null) {
                rotImage.addFilter(i, "rot90");
                break;
            }
        }
        sharedResource.updateImage(rotImage);
        return output;
    }
}
