package commands;
import java.awt.Color;
import java.util.ResourceBundle;
import src.ColorImage;
import src.Resources;

public class FlipVerticalCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public FlipVerticalCommand(ResourceBundle messages, Resources resources) {
	this.messages = messages;
	this.sharedResource = resources;
    }
	
    /**
     * "flipV" was entered. Flip the current image vertically. 
     * @return Message output after flipping the image vertically
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
        
        int height = sharedResource.getCurrentImage().getHeight();
        int width = sharedResource.getCurrentImage().getWidth();
        ColorImage flipImage = new ColorImage(sharedResource.getCurrentImage());
        int maxH = height - 1;
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                Color pix = sharedResource.getCurrentImage().getPixel(x,maxH);
                flipImage.setPixel(x, y, pix);
            }
            maxH--;
        }
        
        flipImage.addFilter("flipV");
        
        sharedResource.updateImage(flipImage);
        output += messages.getString("flipVRes");
        return output;
    }
}
