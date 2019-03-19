package src;

import java.util.EmptyStackException;
import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * The Resources class uses a Singleton Design Pattern to create a shared
 * resource between the client class (Editor) and method executor classes.
 *
 * @author Gerron Tinoy
 * @version 2019.03.18
 */
public class Resources {

    private static Resources sharedResource = new Resources();
    private Stack<ColorImage> currentImage;
    private LinkedHashMap<String, Stack<ColorImage>> imageCache;
    private String name;
    private boolean finished;

    /**
     * Initialises the fields in the resource class.
     */
    private Resources() {
        this.currentImage = new Stack<>();
        this.imageCache = new LinkedHashMap<>();
        this.finished = false;
        this.name = null;
    }

    /**
     * Returns the one and only instantiated sharedResource.
     *
     * @return current/created shared resource
     */
    public static Resources getSharedResources() {
        return sharedResource;
    }

    /**
     * Retrieves the ColorImage Stack containing its history of changes.
     *
     * @return The whole history of the current image
     */
    public Stack<ColorImage> getCurrentImageHistory() {
        return currentImage;
    }

    /**
     * Sets the ColorImage Stack to the provided parameter.
     *
     * @param img the ColorImage Stack to replace current image with
     */
    public void setCurrentImageHistory(Stack<ColorImage> img) {
        currentImage = img;
    }

    /**
     * Retrieves the top ColorImage version within the current ColorImage Stack.
     *
     * @return the top ColorImage object in the current image stack
     */
    public ColorImage getCurrentImage() {
        try {
            return currentImage.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    /**
     * Adds a new ColorImage to the current ColorImage stack.
     *
     * @param newImage the ColorImage object after being updated from an
     * operation/filter
     */
    public void updateImage(ColorImage newImage) {
        currentImage.push(newImage);
    }

    /**
     * Retrieves the image cache.
     *
     * @return the image cache in its current state
     */
    public LinkedHashMap<String, Stack<ColorImage>> getImageCache() {
        return imageCache;
    }

    /**
     * Retrieves the name of the current loaded image
     *
     * @return the name of the current image
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the current loaded image
     *
     * @param newName the new name to change the current name to
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Adds a ColorImage Stack to the image cache.
     *
     * @param name key value which is also the name of the image
     * @param img the ColorImage Stack to add to the cache
     */
    public void addToImageCache(String name, Stack<ColorImage> img) {
        imageCache.put(name, img);
    }

    /**
     * Retrieves the value of the 'finished' field.
     *
     * @return true if the application is ready to terminate, false otherwise
     */
    public boolean getFinished() {
        return finished;
    }

    /**
     * Sets the 'finished' field with the value given in the parameter.
     *
     * @param fin determines whether the application should be terminated
     */
    public void setFinished(boolean fin) {
        finished = fin;
    }

    /**
     * Retrieves the list of filters that is applied to the top of the current
     * ColorImage loaded.
     *
     * @return a list of filters currently applied to the current image
     */
    public String[] getCurrentFilters() {
        if (!currentImage.empty()) {
            return currentImage.peek().getFilters();
        } else {
            return new String[4];
        }
    }

    /**
     * Resets the the state of resources. This function will used for test
     * purposes to remove independence between test cases.
     */
    public void resetResources() {
        sharedResource = new Resources();
    }

}
