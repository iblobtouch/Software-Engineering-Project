import java.awt.Color;
import java.util.ResourceBundle;

public class Rotate90Command extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     *
     * @param messages - Contains the internalisation resource which
     * enables localisation
     */
    public Rotate90Command(ResourceBundle messages) {
	this.messages = messages;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "rot90" was entered. Rotate the current image 90 degrees. 
     */
    @Override
    public void execute() {
    	if (sharedResource.getFilters()[3] != null) {
            System.out.println(messages.getString("exceededPipe"));
            return;
        }
        
        // R90 = [0 -1, 1 0] rotates around origin
        // (x,y) -> (-y,x)
        // then transate -> (height-y, x)
        int height = sharedResource.getCurrentImage().getHeight();
        int width = sharedResource.getCurrentImage().getWidth();
        ColorImage rotImage = new ColorImage(height, width);
        for (int y=0; y<height; y++) { // in the rotated image
            for (int x=0; x<width; x++) {
                Color pix = sharedResource.getCurrentImage().getPixel(x,y);
                rotImage.setPixel(height-y-1,x, pix);
            }
        }
        
        sharedResource.setImage(rotImage);
        if (sharedResource.getFilters()[0] == null) {
            sharedResource.addFilter(0, "rot90");
        } else if (sharedResource.getFilters()[1] == null) {
            sharedResource.addFilter(1, "rot90");
        } else if (sharedResource.getFilters()[2] == null) {
            sharedResource.addFilter(2, "rot90");
        } else if (sharedResource.getFilters()[3] == null) {
            sharedResource.addFilter(3, "rot90");
        }
    }
}
