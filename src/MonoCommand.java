import java.awt.Color;
import java.util.ResourceBundle;

public class MonoCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     *
     * @param messages - Contains the internalisation resource which
     * enables localisation
     */
    public MonoCommand(ResourceBundle messages) {
	this.messages = messages;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "mono" was entered. Converts a given ColorImage to monochrome 
     */
    @Override
    public void execute() {
    	
    	if (sharedResource.getFilters()[3] != null) {
            System.out.println(messages.getString("exceededPipe"));
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
        if (sharedResource.getFilters()[0] == null) {
            sharedResource.addFilter(0, "mono");
        } else if (sharedResource.getFilters()[1] == null) {
            sharedResource.addFilter(1, "mono");
        } else if (sharedResource.getFilters()[2] == null) {
            sharedResource.addFilter(2, "mono");
        } else if (sharedResource.getFilters()[3] == null) {
            sharedResource.addFilter(3, "mono");
        }    
    }
}
