package Commands;
import java.awt.Color;
import java.util.ResourceBundle;
import src.ColorImage;
import src.Resources;

public class FlipVerticalCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     *
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public FlipVerticalCommand(ResourceBundle messages) {
	this.messages = messages;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "flipV" was entered. Flip the current image vertically. 
     * @return result after flipping the image vertically
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
        
        int height = sharedResource.getCurrentImage().getHeight();
        int width = sharedResource.getCurrentImage().getWidth();
        ColorImage flipImage = new ColorImage(width, height);
        int maxH = height - 1;
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                Color pix = sharedResource.getCurrentImage().getPixel(x,maxH);
                flipImage.setPixel(x, y, pix);
            }
            maxH--;
        }
        
        sharedResource.updateImage(flipImage);
        
        for (int i =0; i < sharedResource.getFilters().length; i++) {
            if (sharedResource.getFilters()[i] == null) {
                sharedResource.addFilter(i, "flipV");
                break;
            }
        }
        
        return output;
    }
}