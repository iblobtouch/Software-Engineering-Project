package commands;

import java.awt.Color;
import java.util.ResourceBundle;
import src.ColorImage;
import src.Resources;

/**
 * MonoCommand is an executor class which converts the image to its
 * monochromatic version. It's an extention of the abstract class Command and
 * contains its main operation in its inherited execute() method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class MonoCommand extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public MonoCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Applies the monochromatic filter to the image. Triggered after 'mono' was
     * entered.
     *
     * @return message output after applying the mono filter
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

        ColorImage tmpImage = new ColorImage(sharedResource.getCurrentImage());
        //Graphics2D g2 = currentImage.createGraphics();
        int height = tmpImage.getHeight();
        int width = tmpImage.getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pix = tmpImage.getPixel(x, y);
                int lum = (int) Math.round(0.299 * pix.getRed()
                        + 0.587 * pix.getGreen()
                        + 0.114 * pix.getBlue());
                tmpImage.setPixel(x, y, new Color(lum, lum, lum));
            }
        }

        tmpImage.addFilter("mono");

        sharedResource.updateImage(tmpImage);
        output += messages.getString("monoRes");
        return output;
    }
}
