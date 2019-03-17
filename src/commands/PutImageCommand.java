package commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import src.ColorImage;
import src.Resources;

public class PutImageCommand extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * @param messages Contains the internationalisation resource which enables
     * localisation
     * @param resources Central Resources shared within the application
     */
    public PutImageCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * "put 'name'" was entered. Put a copy of the current image on the cache.
     * @return Message output after adding an image in the cache
     */
    @Override
    public String execute() {
        String output = "";
        if (!this.hasSecondWord()) {
            return messages.getString("saveAs");
        }
        String inputName = this.getSecondWord();
        Stack<ColorImage> tempImg = new Stack<ColorImage>();
        tempImg = (Stack<ColorImage>) sharedResource.getCurrentImageHistory().clone();
        if (sharedResource.getCurrentImage() == null) {
            return messages.getString("noImgLoaded");
        } 
        
        if (sharedResource.getImageCache().containsKey(inputName)) {
            output += messages.getString("cacheOverwrite") + inputName;
        } else {
            output += inputName + " " + messages.getString("imgAdded");
        }
        
        sharedResource.addToImageCache(inputName, tempImg);
        
        return output;
    }
}
