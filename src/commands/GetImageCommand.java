package commands;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import src.ColorImage;
import src.Resources;

/**
 * GetImageCommand is an executor class which retrieves an image from the cache
 * memory. It's an extention of the abstract class Command and contains its main
 * operation in its inherited execute() method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class GetImageCommand extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public GetImageCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Retrieves an image saved from the image cache. Triggered after 'get
     * [imageName]' was entered.
     *
     * @return message output after retrieving an image from the image cache
     */
    @Override
    public String execute() {
        if (!this.hasSecondWord()) {
            return messages.getString("getWhat");
        }
        String inputName = this.getSecondWord();
        
        
        Map.Entry<String, Stack<ColorImage>> foundItem = null;
        
        LinkedHashMap<String, Stack<ColorImage>> imageCache = sharedResource.getImageCache();
        for (Map.Entry<String, Stack<ColorImage>> entry : imageCache.entrySet()) {
            if (inputName.equals(entry.getKey())) {
                foundItem = entry;
            }
        }  
        
        if (foundItem != null) {
            sharedResource.setCurrentImageHistory((Stack<ColorImage>) foundItem.getValue().clone());
            sharedResource.setName(foundItem.getKey());
            return messages.getString("loaded") + sharedResource.getName();
        } else {
            return messages.getString("cacheNotFound");
        }
    }
}
