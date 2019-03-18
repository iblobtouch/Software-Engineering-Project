package commands;

import java.awt.Color;
import java.util.ResourceBundle;
import src.ColorImage;
import src.Resources;

/**
 * Rotate90Command is an executor class which rotates the image currently being
 * edited to 90 degrees clockwise. It's an extention of the abstract class
 * Command and contains its main operation in its inherited execute() method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class Rotate90Command extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public Rotate90Command(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Rotates the current image to 90 degrees clockwise. Triggered after
     * 'rot90' was entered.
     *
     * @return message output after rotating image to 90 degrees
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

        // R90 = [0 -1, 1 0] rotates around origin
        // (x,y) -> (-y,x)
        // then transate -> (height-y, x)
        int height = sharedResource.getCurrentImage().getHeight();
        int width = sharedResource.getCurrentImage().getWidth();
        ColorImage rotImage = new ColorImage(height, width, sharedResource.getName(), sharedResource.getCurrentImage().getFilters());
        for (int y = 0; y < height; y++) { // in the rotated image
            for (int x = 0; x < width; x++) {
                Color pix = sharedResource.getCurrentImage().getPixel(x, y);
                rotImage.setPixel(height - y - 1, x, pix);
            }
        }

        rotImage.addFilter("rot90");

        sharedResource.updateImage(rotImage);
        output += messages.getString("rot90Res");
        return output;
    }
}
