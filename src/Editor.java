
import java.awt.Color;
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

    private Parser parser;
    private CommandMethods methods;
    private ColorImage currentImage;
    private String name;
    private ArrayList<String> filters;
    
    private ResourceBundle messages;
   
    /**
     * Create the editor and initialise its parser.
     */
    public Editor(ResourceBundle messages) {
        this.messages = messages;
        parser = new Parser(messages);
        this.methods = new CommandMethods(messages);
        filters = new ArrayList<String>(4);
    }

    /**
     * Main edit routine. Loops until the end of the editing session.
     */
    public void edit() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the editing session is over.
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
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
        System.out.println(messages.getString("currentImg") + name);
        System.out.print(messages.getString("appliedFltrs"));
        for (int i = 0; i < filters.size(); i += 1) {
            System.out.println(filters.get(i));
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Given a command, edit (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the editing session, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println(messages.getString("unclearMsg"));
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals(messages.getString("helpFunc"))) {
            System.out.println(methods.printHelp());
        } else if (commandWord.equals(messages.getString("openFunc"))) {
            try {
                currentImage = methods.open(command);
                name = command.getSecondWord();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (commandWord.equals(messages.getString("saveFunc"))) {
            try {
                methods.save(currentImage, command);
                System.out.println(messages.getString("imgSavedTo") + command.getSecondWord());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (commandWord.equals(messages.getString("monoFunc"))) {
            currentImage = methods.mono(currentImage);
            filters.add("mono");
        } else if (commandWord.equals(messages.getString("rotate90Func"))) {
            currentImage = methods.mono(currentImage);
            filters.add("flipH");
        } else if (commandWord.equals(messages.getString("lookFunc"))) {
            System.out.println(methods.look(name, filters));
        } else if (commandWord.equals(messages.getString("scriptFunc"))) {
            try {
                Command cmd = methods.script(command);
                wantToQuit = processCommand(cmd);
            } catch(Exception ex) {
                System.out.println(ex);
            }
        } else if (commandWord.equals(messages.getString("quitFunc"))) {
            wantToQuit = methods.quit(command);
            if (command.hasSecondWord()) {
                System.out.println(messages.getString("quitWhat"));
            }
        }
        return wantToQuit;
    }
}
