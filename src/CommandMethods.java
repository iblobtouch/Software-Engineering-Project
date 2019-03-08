
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;

/**
 * This class contains all the methods that are available as commands.
 * To add a new command, simply define it here and it will be made available
 * automatically.
 *
 * @author Nickolas Kingsbury
 * @version 07/03/2019
 */
public class CommandMethods {
    
    private ResourceBundle messages;
    
    public CommandMethods(ResourceBundle messages) {
        this.messages = messages;
    }
    //----------------------------------
// Implementations of user commands:
//----------------------------------
    
    /**
     * Print out some help information. Here we print some useless, cryptic
     * message and a list of the command words.
     * @return Formatted text containing help.
     */
    public String getHelp(CommandWords c) {
        String helpText = "";
        helpText = helpText.concat(messages.getString("helpMsg1") + "\n");
        helpText = helpText.concat(messages.getString("helpMsg2") + "\n");
        helpText = helpText.concat(c.getCommands());
        return helpText;
    }

    /**
     * Load an image from a file.
     * @param name The name of the image file
     * @return a ColorImage containing the image
     */
    private ColorImage loadImage(String name) throws IOException {
        ColorImage img = null;
        try {
            img = new ColorImage(ImageIO.read(new File(name)));
        } catch (IOException e) {
            throw new IOException(messages.getString("imgNotFound") + name + "\n" + messages.getString("imgDir") + System.getProperty("user.dir"));
        }
        return img;
    }


    /**
     * "open" was entered. Open the file given as the second word of the command
     * and use as the current image. 
     * @param command the command given.
     */
    public ColorImage open(Command command) throws Exception {
        if (!command.hasSecondWord()) {
            throw new IllegalArgumentException(messages.getString("openWhat"));
        }
  
        String inputName = command.getSecondWord();
        try {
            return loadImage(inputName);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * "save" was entered. Save the current image to the file given as the 
     * second word of the command. 
     * @param command the command given
     */
    public void save(ColorImage currentImage, Command command) throws Exception {
        if (currentImage == null) {
            throw new IllegalArgumentException(messages.getString("imgNotFound"));
        }
        
        if (!command.hasSecondWord()) {
            throw new IllegalArgumentException(messages.getString("saveWhere"));
        }
  
        String outputName = command.getSecondWord();
        try {
            File outputFile = new File(outputName);
            ImageIO.write(currentImage, "jpg", outputFile);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    /**
     * "look" was entered. Report the status of the work bench. 
     * @param command the command given.
     */
    public String look(String name, ArrayList<String> filters) {
        String output = "";
        output = output.concat(messages.getString("currentImg") + name + "\n");
        output = output.concat(messages.getString("appliedFltrs") + "\n");
        for (int i = 0; i < filters.size(); i += 1) {
            output = output.concat(filters.get(i) + "\n");
        }
        return output;
    }

    /**
     * "mono" was entered. Converts a given ColorImage to monochrome 
     * @param command the command given.
     */
    public ColorImage mono(ColorImage currentImage) {
        
        ColorImage tmpImage = new ColorImage(currentImage);
        //Graphics2D g2 = currentImage.createGraphics();
        int height = tmpImage.getHeight();
        int width = tmpImage.getWidth();
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                Color pix = tmpImage.getPixel(x, y);
                int lum = (int) Math.round(0.299*pix.getRed()
                                         + 0.587*pix.getGreen()
                                         + 0.114*pix.getBlue());
                tmpImage.setPixel(x, y, new Color(lum, lum, lum));
            }
        }
        return tmpImage;
    }
    
    /**
     * "rot90" was entered. Rotate the current image 90 degrees. 
     * @param command the command given.
     */
    public ColorImage rot90(ColorImage currentImage) {
        
        // R90 = [0 -1, 1 0] rotates around origin
        // (x,y) -> (-y,x)
        // then transate -> (height-y, x)
        int height = currentImage.getHeight();
        int width = currentImage.getWidth();
        ColorImage rotImage = new ColorImage(height, width);
        for (int y=0; y<height; y++) { // in the rotated image
            for (int x=0; x<width; x++) {
                Color pix = currentImage.getPixel(x,y);
                rotImage.setPixel(height-y-1,x, pix);
            }
        }
        return rotImage;
    }
    
    
    
    /**
     * The 'script' command runs a sequence of commands from a
     * text file.
     * 
     * IT IS IMPORTANT THAT THIS COMMAND WORKS AS IT CAN BE USED FOR TESTING
     * 
     * @param command the script command, second word of which is the name of 
     * the script file.
     * @return What command to execute, null for no command.
     */
    public Command script(Command command, Parser parser) throws Exception{
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to open...
            throw new IllegalArgumentException(messages.getString("whichScript"));
        }
  
        String scriptName = command.getSecondWord();
        try (FileInputStream inputStream = new FileInputStream(scriptName)) {
            parser.setInputStream(inputStream);
            while (true) {
                try {
                    Command cmd = parser.getCommand();
                    return cmd;
                } catch (Exception ex) {
                    throw ex;
                }               
            }
        } 
        catch (FileNotFoundException ex) {
            throw new FileNotFoundException(messages.getString("cannotFind") + scriptName);
        }
        catch (IOException ex) {
            throw new IOException(messages.getString("scriptBarfed"), ex);
        }
    }
    
    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the editor.
     * @param command the command given.
     * @return true, if this command quits the editor, false otherwise.
     */
    public boolean quit(Command command) {
        if (command.hasSecondWord()) {
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
    
    public boolean test() {
        return true;
    }
}
