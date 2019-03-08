import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class Rotate90Command extends Command{

	private ResourceBundle messages;
	private Resources sharedResource;
	
	public Rotate90Command(ResourceBundle messages) {
		this.messages = messages;
		sharedResource = Resources.getSharedResources();
		// TODO Auto-generated constructor stub
	}
	
	/**
     * "rot90" was entered. Rotate the current image 90 degrees. 
     * @param command the command given.
     */
    @Override
    public void execute() {
    	ArrayList<String> filterList = sharedResource.getFilters();
    	if (filterList.get(3) != null) {
            System.out.println(messages.getString("exceedPipe"));
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
        if (filterList.get(0) == null) {
        	sharedResource.addFilter(0, "flipH");
        } else if (filterList.get(1) == null) {
        	sharedResource.addFilter(1, "flipH");
        } else if (filterList.get(2) == null) {
        	sharedResource.addFilter(2, "flipH");
        } else if (filterList.get(3) == null) {
        	sharedResource.addFilter(3, "flipH");
        }
        
    }

   
	
}
