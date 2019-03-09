public class Resources {
    private static Resources sharedResource = new Resources();
    private ColorImage currentImage;
    private String name;
    private String filters[];
    private boolean finished;
	 
    /** 
     * Uses Singleton Design Pattern to create a shared Resource between
     * the client class (Editor) and method executor Classes
     */
    private Resources() {
        this.currentImage = null;
        this.name = null;
	this.filters = new String[4];
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
	return currentImage;
    }
    
    /**
     *
     * @return Image name
     */
    public String getName() {
        return name;
    }
	
    /**
     *
     * @return Array of filters that is currently applied to the image
     */
    public String[] getFilters() {
	return filters;
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
     * @param updatedImage - Image after being updated from an operation/filter
     * overwrites the current Image with updatedImage
     */
    public void setImage(ColorImage updatedImage) {
	currentImage = updatedImage;
    }
	 
    /**
     *
     * @param newName - new name for the image
     * overwrites the current name of the image with newName
     */
    public void setName(String newName) {
	name = newName;
    }

    /**
     * Initialise the values of the array of filters to null
     */
    public void initialiseFilters(){
        for (int i=0; i < filters.length; i++) {
            filters[i] = null;
	}
    }
    
    /**
     *
     * @param filterNum - refers the index of the filter to add/replace in array
     * @param filtAdd - Name of the filter to be added
     * Adds a filter from the array of filters
     */
    public void addFilter(int filterNum, String filtAdd) {
	filters[filterNum] = filtAdd;
    }
	 
    /**
     *
     * @param filtRem - refers to the name of the filter to be removed
     * Removes a particular filter from the array of filters
     */
    public void removeFilter(String filtRem) {
        for (int i=0; i < filters.length; i++) {
            if (filters[i].equals(filtRem)){
		filters[i] = null;
            }
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
