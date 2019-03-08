import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class MonoCommand extends Command{

	private ResourceBundle messages;
	private Resources sharedResource;
	
	public MonoCommand(ResourceBundle messages) {
		this.messages = messages;
		sharedResource = Resources.getSharedResources();
		// TODO Auto-generated constructor stub
	}
	
	/**
     * "mono" was entered. Converts a given ColorImage to monochrome 
     * @param command the command given.
     */
    @Override
    public void execute() {
    	
    	ArrayList<String> filterList = sharedResource.getFilters();
    	if (filterList.get(3) != null) {
            System.out.println(messages.getString("exceedPipe"));
            return;
        }
        
        ColorImage tmpImage = new ColorImage(sharedResource.getCurrentImage());
        //Graphics2D g2 = currentImage.createGraphics();
        int height = tmpImage.getHeight();
        int width = tmpImage.getWidth();
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                Color pix = tmpImage.getPixel(x, y);
                int lum = (int) Math.round(0.299*pix.getRed()
                                         + 0.587*pix.getGreen()
                                         + 0.114*pix.getBlue());
                tmpImage.setPixel(x, y, new Color(lum, lum, lum));
            }
        }
        
        sharedResource.setImage(tmpImage);
        if (filterList.get(0) == null) {
        	sharedResource.addFilter(0, "mono");
        } else if (filterList.get(1) == null) {
        	sharedResource.addFilter(1, "mono");
        } else if (filterList.get(2) == null) {
        	sharedResource.addFilter(2, "mono");
        } else if (filterList.get(3) == null) {
        	sharedResource.addFilter(3, "mono");
        } 
        
    }

   
	
}
