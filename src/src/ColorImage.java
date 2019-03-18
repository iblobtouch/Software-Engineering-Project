package src;

import java.awt.*;
import java.awt.image.*;
import java.util.Arrays;

/**
 * Extends standard BufferedImage class with convenience functions for
 * getting/setting pixel values using the standard Color class and converting
 * the raster to a standard 24-bit direct colour format.
 *
 * Based on class OFImage described in chapter 11 of the book "Objects First
 * with Java" by David J Barnes and Michael Kolling (from 2nd edition onwards).
 *
 * @author Michael Kolling, David J. Barnes and Peter Kenny
 * @version 2019.03.18
 */
public class ColorImage extends BufferedImage {

    private String[] filters;

    /**
     * Constructor that makes a clone copy of a given ColorImage object.
     *
     * @param image object of ColorImage instance to clone
     */
    public ColorImage(ColorImage image) {
        super(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        int width = image.getWidth();
        int height = image.getHeight();
        this.filters = Arrays.copyOf(image.filters, image.filters.length);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setRGB(x, y, image.getRGB(x, y));
            }
        }
    }

    /**
     * Create a ColorImage copied from a BufferedImage. Convert to 24-bit direct
     * colour.
     *
     * @param image the image to copy
     */
    public ColorImage(BufferedImage image) {
        super(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        int width = image.getWidth();
        int height = image.getHeight();
        this.filters = new String[4];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setRGB(x, y, image.getRGB(x, y));
            }
        }
    }

    /**
     * Create a ColorImage with specified size and 24-bit direct colour.
     *
     * @param width the width of the image
     * @param height the height of the image
     * @param nme the name of the image
     * @param flters the filters applied to the image
     */
    public ColorImage(int width, int height, String nme, String[] flters) {
        super(width, height, TYPE_INT_RGB);
        this.filters = Arrays.copyOf(flters, flters.length);
    }

    /**
     * Set a given pixel of this image to a specified color. The color is
     * represented as an (r,g,b) value.
     *
     * @param x the x position of the pixel
     * @param y the y position of the pixel
     * @param col the color of the pixel
     */
    public void setPixel(int x, int y, Color col) {
        int pixel = col.getRGB();
        setRGB(x, y, pixel);
    }

    /**
     * Get the color value at a specified pixel position.
     *
     * @param x the x position of the pixel
     * @param y the y position of the pixel
     * @return the color of the pixel at the given position
     */
    public Color getPixel(int x, int y) {
        int pixel = getRGB(x, y);
        return new Color(pixel);
    }

    /**
     * Adds a filter from the array of filters.
     *
     * @param filtAdd name of the filter to be added
     */
    public void addFilter(String filtAdd) {
        for (int i = 0; i < filters.length; i++) {
            if (filters[i] == null) {
                filters[i] = filtAdd;
                break;
            }
        }
    }

    /**
     * Returns the list of filters applied to the current image.
     *
     * @return list of filters
     */
    public String[] getFilters() {
        return filters;
    }
}
