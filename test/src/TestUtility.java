package src;

import java.awt.Color;

/**
 * Contains utility methods for the test package.
 */
public class TestUtility {

    /**
     * This utility enables comparing two image files that may be slightly
     * similar by providing a pixel difference allowance as one of its
     * parameters. The saved image will not necessarily have the same exact
     * pixel values as the image being processed.
     *
     * @param pixDiffAllowance pixel difference allowance during comparison
     * @param a first image to compare
     * @param b second image to compare
     * @return true if both images are relatively similar, false otherwise
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

    /**
     * This function automates loading an image and applying the maximum amount
     * of filters and then applying another given filter command.
     *
     * @param parser the Parser object being used
     * @param command the command to execute to trigger the exceeded filter
     * message
     * @return output message after applying a filter on an image that already
     * has the maximum amount of filters applied to it.
     */
    public static String automateExceededPipe(Parser parser, String command) {
        parser.getCommand("open input.jpg").execute();
        // Fill pipes
        parser.getCommand("mono").execute();
        parser.getCommand("rot90").execute();
        parser.getCommand("flipH").execute();
        parser.getCommand("flipV").execute();
        String output = parser.getCommand(command).execute();
        return output;
    }
}
