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
        this.currentImage = new Stack<ColorImage>();
        this.imageCache = new ArrayList<Stack<ColorImage>>();
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
     * Puts a copy of the current image on image cache along with its filters
     */
    public void putImage() {
        if (currentImage.isEmpty()) {
            return;
        }
        if (!imageCache.contains(currentImage)) {
            imageCache.add(currentImage);
        }
    }
    
    /**
     *
     * Gets an image from the cache by name and sets it as the current image.
     * @return boolean indicating the success of the method.
     */
    public boolean getImage(String itemName) {
        for (int i = 0; i < imageCache.size(); i ++) {
            if (imageCache.get(i).peek().getName().equals(itemName)) {
                if (!imageCache.contains(currentImage)) {
                    imageCache.add(currentImage);
                }
                currentImage = imageCache.get(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     *
     * @param updatedImage - Image to replace currentImage with.
     * Puts current image on image cache then overwrites the current Image with updatedImage
     */
    public void setImage(ColorImage updatedImage) {
        putImage();
	currentImage = new Stack<ColorImage>();
        currentImage.push(updatedImage);
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
     * Initialise the values of the array of filters to null
     */
    public void initialiseFilters(){
        if (!currentImage.isEmpty()) {
            currentImage.peek().initialiseFilters();
        }
    }
    
    /**
     *
     * @param filterNum - refers the index of the filter to add/replace in array
     * @param filtAdd - Name of the filter to be added
     * Adds a filter from the array of filters
     */
    public void addFilter(int filterNum, String filtAdd) {
        if (!currentImage.isEmpty()) {
            currentImage.peek().addFilter(filterNum, filtAdd);
        }
    }
	 
    /**
     *
     * @param filtRem - refers to the name of the filter to be removed
     * Removes a particular filter from the array of filters
     */
    public void removeFilter(String filtRem) {
        if (!currentImage.isEmpty()) {
            currentImage.peek().removeFilter(filtRem);
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
