package src;
import java.awt.Color;
import java.util.ResourceBundle;

public class FlipHorizontalCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     *
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public FlipHorizontalCommand(ResourceBundle messages) {
	this.messages = messages;
	sharedResource = Resources.getSharedResources();
    }
	
    /**
     * "flipH" was entered. Flip the current image horizontally. 
     * @return result after flipping the image horizontally
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
        for (int y=0; y<height; y++) {
            int maxW = width - 1;
            for (int x=0; x<width; x++) {
                Color pix = sharedResource.getCurrentImage().getPixel(maxW,y);
                flipImage.setPixel(x, y, pix);
                maxW--;
            }
        }
        
        sharedResource.setImage(flipImage);
        
        for (int i =0; i < sharedResource.getFilters().length; i++) {
            if (sharedResource.getFilters()[i] == null) {
                sharedResource.addFilter(i, "flipH");
                break;
            }
        }
        
        return output;
    }
}
