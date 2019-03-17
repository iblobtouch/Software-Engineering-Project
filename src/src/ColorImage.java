package src;

import java.awt.*;
import java.awt.image.*;
import java.util.Arrays;

/**
 * Extends standard BufferedImage class with convenience functions
 * for getting/setting pixel values using the standard Color class
 * and converting the raster to a standard 24-bit direct colour format.
 *
 * Based on class OFImage described in chapter 11 of the book
 * "Objects First with Java" by David J Barnes and Michael Kolling
 * (from 2nd edition onwards).
 *
 * @author  Michael Kolling, David J. Barnes and Peter Kenny
 * @version 1.0
 */

public class ColorImage extends BufferedImage
{
    String name;
    String[] filters;
    
    public ColorImage(ColorImage image) {
        super(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        int width = image.getWidth();
        int height = image.getHeight();
        name = image.name;
        filters = image.filters;
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                setRGB(x, y, image.getRGB(x,y));
    }
    
    /**
     * Create a ColorImage copied from a BufferedImage.
     * Convert to 24-bit direct colour.
     * @param image The image to copy.
     */
    public ColorImage(BufferedImage image)
    {
        super(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        int width = image.getWidth();
        int height = image.getHeight();
        name = null;
        filters = new String[4];
        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                setRGB(x, y, image.getRGB(x,y));
    }

    /**
     * Create a ColorImage with specified size and 24-bit direct colour.
     * @param width The width of the image.
     * @param height The height of the image.
     * @param nme The name of the image.
     * @param flters The filters applied to the image.
     */
    public ColorImage(int width, int height, String nme, String[] flters)
    {
        super(width, height, TYPE_INT_RGB);
        name = nme;
        filters = Arrays.copyOf(flters, flters.length);
    }

    /**
     * Set a given pixel of this image to a specified color.
     * The color is represented as an (r,g,b) value.
     * @param x The x position of the pixel.
     * @param y The y position of the pixel.
     * @param col The color of the pixel.
     */
    public void setPixel(int x, int y, Color col)
    {
        int pixel = col.getRGB();
        setRGB(x, y, pixel);
    }

    /**
     * Get the color value at a specified pixel position.
     * @param x The x position of the pixel.
     * @param y The y position of the pixel.
     * @return The color of the pixel at the given position.
     */
    public Color getPixel(int x, int y)
    {
        int pixel = getRGB(x, y);
        return new Color(pixel);
    }
    
    /**
     * Overwrites the current name of the image with newName.
     * @param newName New name for the image.
     */
    public void setName(String newName) {
	name = newName;
    }

    /**
     * Initialise the values of the array of filters to null.
     */
    public void initialiseFilters(){
        for (int i=0; i < filters.length; i++) {
            filters[i] = null;
	}
    }
    
    /**
     * Adds a filter from the array of filters.
     * @param filterNum Refers the index of the filter to add/replace in array.
     * @param filtAdd Name of the filter to be added.
     */
    public void addFilter(int filterNum, String filtAdd) {
	filters[filterNum] = filtAdd;
    }
	 
    /**
     * Removes a particular filter from the array of filters.
     * @param filtRem Refers to the name of the filter to be removed.
     */
    public void removeFilter(String filtRem) {
        for (int i=0; i < filters.length; i++) {
            if (filters[i].equals(filtRem)){
		filters[i] = null;
            }
	}
    }
    /**
     * Removes the previously added filter.
     */
    public void removeLastFilter() {
        for (int i = filters.length - 1; i >= 0; i--) {
            if (filters[i] != null) {
                filters[i] = null;
                return;
            }
        }
    }
    
    /**
     * @return Image name.
     */
    public String getName() {
        return name;
    }
	
    /**
     * @return Array of filters that is currently applied to the image.
     */
    public String[] getFilters() {
	return filters;
    }
}
