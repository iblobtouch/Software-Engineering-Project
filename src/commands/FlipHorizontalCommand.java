package commands;
import java.awt.Color;
import java.util.ResourceBundle;
import src.ColorImage;
import src.Resources;

public class FlipHorizontalCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public FlipHorizontalCommand(ResourceBundle messages, Resources resources) {
	this.messages = messages;
	this.sharedResource = resources;
    }
	
    /**
     * "flipH" was entered. Flip the current image horizontally.
     * @return Message output after flipping the image horizontally
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
        for (int y=0; y<height; y++) {
            int maxW = width - 1;
            for (int x=0; x<width; x++) {
                Color pix = sharedResource.getCurrentImage().getPixel(maxW,y);
                flipImage.setPixel(x, y, pix);
                maxW--;
            }
        }
        
        flipImage.addFilter("flipH");
        
        sharedResource.updateImage(flipImage);
        output += messages.getString("flipHRes");
        return output;
    }
}
