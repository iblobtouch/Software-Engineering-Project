package commands;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import src.ColorImage;
import src.Resources;

public class ViewImageCacheCommand extends Command{
    private final ResourceBundle messages;
    private final Resources sharedResource;
	
    /**
     * @param messages Contains the internationalisation resource which
     * enables localisation
     * @param resources
     */
    public ViewImageCacheCommand(ResourceBundle messages, Resources resources) {
	this.messages = messages;
        this.sharedResource = resources;
    }
	
    /**
     * "cache" was entered. Show the list of images stored in the cache
     * @return String output of list of caches stored
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
