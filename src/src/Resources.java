package src;

import java.util.EmptyStackException;
import java.util.LinkedHashMap;
import java.util.Stack;

/**
 *
 * @author regno
 */
public class Resources {
    private static Resources sharedResource = new Resources();
    private Stack<ColorImage> currentImage;
    private LinkedHashMap<String, Stack<ColorImage>> imageCache;
    private String name;
    private boolean finished;
	 
    /** 
     * Uses Singleton Design Pattern to create a shared Resource between
     * the client class (Editor) and method executor Classes.
     */
    private Resources() {
        this.currentImage = new Stack<>();
        this.imageCache = new LinkedHashMap<>();
        this.finished = false;
        this.name = null;
    }
    
    /**
     * Creates an instance of the resource once and ensures it 
     * uses the same one throughout.
     * @return Current/created shared Resources
     */
    public static Resources getSharedResources() {
	if (sharedResource == null) {
            sharedResource = new Resources();
	}
        return sharedResource;
    }
    
    /**
     * @return The whole history of the current image
     */
    public Stack<ColorImage> getCurrentImageHistory() {
        return currentImage;
    }
    
    /**
     * Puts the loaded image in the cache, then sets it to the provided image.
     * @param img Image to replace current image with
     */
    public void setCurrentImageHistory(Stack<ColorImage> img) {
        currentImage = img;
    }
	 
    /**
     * @return The top image in the current image stack
     */
    public ColorImage getCurrentImage() {
	try {
            return currentImage.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }
    
    /**
     * Adds the new contents of the current image to the cache.
     * @param newImage Image after being updated from an operation/filter
     */
    public void updateImage(ColorImage newImage) {
	currentImage.push(newImage);
    }
    
    /**
     * @return The image cache in its current state
     */
    public LinkedHashMap<String, Stack<ColorImage>> getImageCache () {
        return imageCache;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param fN
     */
    public void setName(String fN){
        this.name = fN;
    }
    
    /**
     * @param name
     * @param img The image to add to the cache
     */
    public void addToImageCache (String name, Stack<ColorImage> img) {
        imageCache.put(name, img);
    }
    
    /**
     * @return A boolean value which determines whether further usage
     * of the application is required
     */
    public boolean getFinished() {
	return finished;
    }
    
    /**
     * Sets the 'finished' field with the value of 'fin'.
     * @param fin Refers to whether the application is ready to terminate
     */
    public void setFinished(boolean fin) {
	finished = fin;
    } 
    
    /**
     *
     * @return
     */
    public String[] getCurrentFilters() {
        if (!currentImage.empty()) {
            return currentImage.peek().getFilters();
        } else {
            return new String[4];
        }
    }
    
}
