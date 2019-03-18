package commands;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import src.ColorImage;
import src.Resources;

/**
 * ViewImageCacheCommand is an executor class which returns the list of image
 * files currently saved in the image cache. It's an extention of the abstract
 * class Command and contains its main operation in its inherited execute()
 * method.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class ViewImageCacheCommand extends Command {

    private final ResourceBundle messages;
    private final Resources sharedResource;

    /**
     * Initialises the pre-requisite resources for the command execution.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     * @param resources central resources shared within the application
     */
    public ViewImageCacheCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }

    /**
     * Returns the list of images stored in the cache. Triggered after 'cache'
     * was entered.
     *
     * @return message output containing the list of images stored in the cache
     */
    @Override
    public String execute() {
        String output;
        output = messages.getString("cacheList");
        LinkedHashMap<String, Stack<ColorImage>> imageCache = sharedResource.getImageCache();
        for (Map.Entry<String, Stack<ColorImage>> entry : imageCache.entrySet()) {
            output += "\n" + entry.getKey();
        }
        return output;
    }
}
