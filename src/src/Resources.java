package src;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Resources {
    private static Resources sharedResource = new Resources();
    private Stack<ColorImage> currentImage;
    private ArrayList<Stack<ColorImage>> imageCache;
    private boolean finished;
	 
    /** 
     * Uses Singleton Design Pattern to create a shared Resource between
     * the client class (Editor) and method executor Classes
     */
    private Resources() {
        this.currentImage = new Stack<>();
        this.imageCache = new ArrayList<>();
        this.finished = false;
    }
    
    /**
     * Creates an instance of the resource once and ensures it 
     * uses the same one throughout
     * @return current/created shared Resources
     */
    public static Resources getSharedResources() {
	if (sharedResource == null) {
            sharedResource = new Resources();
	}
        return sharedResource;
    }
	 
    /**
     *
     * @return Image at its current state
     */
    public ColorImage getCurrentImage() {
	try {
            return currentImage.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }
    
    /**
     *
     * Puts the current image in the cache, then sets it to the provided image.
     * @param img image to replace current image with.
     */
    public void setCurrentImage(Stack<ColorImage> img) {
	if (currentImage.isEmpty() == false) {
            addToImageCache(currentImage);
        }
        currentImage = img;
    }
    
    /**
     *
     * @return the image cache in its current state.
     */
    public ArrayList<Stack<ColorImage>> getImageCache () {
        return imageCache;
    }
    
    /**
     *
     * @param img to add to cache
     * @return the image cache in its current state.
     */
    public boolean addToImageCache (Stack<ColorImage> img) {
        if (!imageCache.contains(img)) {
            imageCache.add(img);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     *
     * @return The whole history of the current image;
     */
    public Stack<ColorImage> getCurrentImageHistory() {
        return currentImage;
    }
    
    /**
     *
     * @return Image name
     */
    public String getName() {
        if (!currentImage.isEmpty()) {
            return currentImage.peek().getName();
        } else {
            return null;
        }
    }
	
    /**
     *
     * @return Array of filters that is currently applied to the image
     */
    public String[] getFilters() {
        if (!currentImage.isEmpty()) {
            return currentImage.peek().getFilters();
        } else {
            return new String[4];
        }
    }
    
    /**
     *
     * @return a boolean value which determines whether further usage
     * of the application is required
     */
    public boolean getFinished() {
	return finished;
    }
    
    /**
     *
     * Gets an image from the cache by name and sets it as the current image.
     * @param itemName name of item to get.
     * @return boolean indicating the success of the method.
     */
    public Stack<ColorImage> getImageFromCache(String itemName) {
        for (int i = 0; i < imageCache.size(); i ++) {
            if (imageCache.get(i).peek().getName().equals(itemName)) {
                return imageCache.get(i);
            }
        }
        return null;
    }
    
    /**
     *
     * @param newImage - Image after being updated from an operation/filter
     * Adds the new contents of the current image to the cache.
     */
    public void updateImage(ColorImage newImage) {
	currentImage.push(newImage);
    }
    
    /**
     *
     * Removes the last operation performed on the current image.
     */
    public void undo() {
        try {
            if (!currentImage.isEmpty()) {
                currentImage.pop();
            }
        } catch (EmptyStackException e) {
        }
    }
	 
    /**
     *
     * @param newName - new name for the image
     * overwrites the current name of the image with newName
     */
    public void setName(String newName) {
        if (!currentImage.isEmpty()) {
            currentImage.peek().setName(newName);
        }
    }
	 
    /**
     *
     * @param fin - refers to whether the application is ready to terminate
     * Sets the 'finished' field with the value of 'fin'
     */
    public void setFinished(boolean fin) {
	finished = fin;
    } 
}
