package commands;
import java.awt.Color;
import java.util.ResourceBundle;
import src.ColorImage;
import src.Resources;

public class MonoCommand extends Command {
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public MonoCommand(ResourceBundle messages, Resources resources) {
	this.messages = messages;
	this.sharedResource = resources;
    }
	
    /**
     * "mono" was entered. Converts a given ColorImage to monochrome.
     * @return Message output after adding a mono filter
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
        
        for (int i =0; i < tmpImage.getFilters().length; i++) {
            if (tmpImage.getFilters()[i] == null) {
                tmpImage.addFilter(i, "mono");
                break;
            }
        }
        
        sharedResource.updateImage(tmpImage);
        return output;
    }
}
