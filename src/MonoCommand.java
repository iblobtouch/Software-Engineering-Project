import java.awt.Color;
import java.util.ResourceBundle;

public class MonoCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     *
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public MonoCommand(ResourceBundle messages) {
	this.messages = messages;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "mono" was entered. Converts a given ColorImage to monochrome 
     * @return result of adding a mono filter
     */
    @Override
    public String execute() {
    	String output = "";
    	if (sharedResource.getFilters()[3] != null) {
            return messages.getString("exceededPipe") + "\n";
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
        for (int i =0; i < sharedResource.getFilters().length; i++) {
            if (sharedResource.getFilters()[i] == null) {
                sharedResource.addFilter(i, "mono");
                break;
            }
        }
        
        return output;
    }
}
