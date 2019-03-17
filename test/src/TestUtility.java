package src;

import java.awt.Color;

public class TestUtility {

    /*
	    * The saved image will not necessarily have the same exact pixel
	    * values as the image being processed. This utility enables comparing two
	    * image files that may be slightly similar by providing a pixel difference
	    * allowance as one of its parameters.
     */
    public static boolean imageMatched(int pixDiffAllowance, ColorImage a, ColorImage b) {
        boolean imageMatch = true;
        int width = a.getWidth();
        int height = a.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color ap = a.getPixel(x, y);
                Color bp = b.getPixel(x, y);

                int blueDiff = Math.abs(ap.getBlue() - bp.getBlue());
                int redDiff = Math.abs(ap.getRed() - bp.getRed());
                int greenDiff = Math.abs(ap.getGreen() - bp.getGreen());

                if (blueDiff > pixDiffAllowance
                        || redDiff > pixDiffAllowance
                        || greenDiff > pixDiffAllowance) {
                    imageMatch = false;
                }

            }
        }

        return imageMatch;
    }

}
