package commands;

import java.awt.Color;
import java.util.ResourceBundle;
import src.ColorImage;
import src.Resources;

/**
 * FlipVerticalCommand is an executor class which flips the image currently
 * being edited vertically. It's an extention of the abstract class Command and
 * contains its main operation in its inherited execute() method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class FlipVerticalCommand extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public FlipVerticalCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Flips the current image vertically. Triggered after 'flipV' was entered.
     *
     * @return message output after flipping the image vertically
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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pix = sharedResource.getCurrentImage().getPixel(x, maxH);
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
