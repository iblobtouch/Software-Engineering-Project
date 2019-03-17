package commands;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import src.ColorImage;
import src.Resources;

public class GetImageCommand extends Command{

    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources Central Resources shared within the application
     */
    public GetImageCommand(ResourceBundle messages, Resources resources) {
        this.messages = messages;
        this.sharedResource = resources;
    }
	
    /**
     * "get 'name'" was entered. Retrieve an image saved from the image cache.
     * @return Message output after retrieving an image from the cache
     */
    @Override
    public String execute() {
        if (!this.hasSecondWord()) {
            return messages.getString("getWhat");
        }
        String inputName = this.getSecondWord();
        Map.Entry<String, Stack<ColorImage>> foundItem = getImageFromCache(inputName); 
        if (foundItem != null) {
            sharedResource.setCurrentImageHistory((Stack<ColorImage>)foundItem.getValue().clone());
            sharedResource.setName(foundItem.getKey());
            return messages.getString("loaded") + sharedResource.getName();
        } else {
            return messages.getString("cacheNotFound");
        } 
    }
    
    /**
     * Gets an image from the cache by name and sets it as the current image.
     * @param itemName Name of item to retrieve from the cache
     * @return Image that was retrieved from the cache
     */
    public Map.Entry<String, Stack<ColorImage>> getImageFromCache(String itemName) {
        LinkedHashMap<String, Stack<ColorImage>> imageCache = sharedResource.getImageCache(); 
        for (Map.Entry<String, Stack<ColorImage>> entry : imageCache.entrySet()) {
            if (itemName.equals(entry.getKey())) {
                return entry;
            }
        }
        return null;
    }
}
