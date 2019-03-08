
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class is the main processing class of the Fotoshop application. 
 * Fotoshop is a very simple image editing tool. Users can apply a number of
 * filters to an image. That's all. It should really be extended to make it more
 * useful!
 *
 * To edit an image, create an instance of this class and call the "edit"
 * method.
 *
 * This main class creates and initialises all the others: it creates the parser
 * and  evaluates and executes the commands that the parser returns.
 *
 * @author Joseph Williams
 * @version 2018.12.12
 */
import java.util.*;
public class Editor {

    private CommandWords commands;
    private Parser parser;
    private CommandMethods methods;
    private Resources sharedResource;
    private ResourceBundle messages;
   
    /**
     * Create the editor and initialise its parser.
     */
    public Editor(ResourceBundle messages) {
        this.messages = messages;
        commands = new CommandWords(messages);
        parser = new Parser(messages);
        this.methods = new CommandMethods(messages);
        
        sharedResource = Resources.getSharedResources();
    }

    /**
     * Main edit routine. Loops until the end of the editing session.
     */
    public void edit() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the editing session is over.
        while (!sharedResource.getFinished()) {
            Command command = parser.getCommand();
            if (command == null) {
                System.out.println(messages.getString("unclearMsg"));
            } else {
            	command.execute();
            }
        }
        System.out.println(messages.getString("finishMsg"));
    }

    /**
     * Print out the opening message for the user.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println(messages.getString("welcomeMsg"));
        System.out.println(messages.getString("introMsg"));
        System.out.println(messages.getString("helpIns"));
        System.out.println();
        System.out.println(messages.getString("currentImg") + sharedResource.getName());
        System.out.print(messages.getString("appliedFltrs"));
        ArrayList<String> appliedFilters = sharedResource.getFilters();
        for (int i = 0; i < appliedFilters.size(); i += 1) {
            System.out.println(appliedFilters.get(i));
            System.out.println();
        }
        System.out.println();
    }

    
    /**
     * DEPRECATED
     * Given a command, edit (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the editing session, false otherwise.
     */
    /*
    private boolean processCommand(Command command) {
		return false;

    }*/
    
}
