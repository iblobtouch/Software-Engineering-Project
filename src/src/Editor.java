package src;

import commands.Command;
import java.util.*;

/**
 * This class is the main processing class of the Fotoshop application. Fotoshop
 * is a very simple image editing tool. Users can apply a number of filters to
 * an image. That's all. It should really be extended to make it more useful!
 *
 * To edit an image, create an instance of this class and call the "edit"
 * method.
 *
 * This main class creates and initialises all the others: it creates the parser
 * and evaluates and executes the commands that the parser returns.
 *
 * @author Joseph Williams
 * @version 2019.03.18
 */
public class Editor {

    private final Parser parser;
    private final Resources sharedResource;
    private final ResourceBundle messages;
    private final Scanner reader;

    /**
     * Create the editor and initialise its parser, shared Resource and
     * internationalised resource.
     *
     * @param messages contains the internationalisation resource which enables
     * localisation
     */
    public Editor(ResourceBundle messages) {
        this.sharedResource = Resources.getSharedResources();
        this.parser = new Parser(messages, this.sharedResource);
        this.messages = messages;
        this.reader = new Scanner(System.in);
    }

    /**
     * Main edit routine which loops until the end of the editing session.
     * Executes command operations until its requested termination.
     */
    public void edit() {
        printWelcome();
        String output;

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the editing session is over.
        while (!sharedResource.getFinished()) {
            System.out.print("> ");     // print prompt
            String inputLine = reader.nextLine(); // will hold the full input line
            Command command = parser.getCommand(inputLine);
            if (command == null) {
                System.out.println(messages.getString("unclearMsg"));
            } else {
                output = command.execute();
                System.out.println(output + "\n");
            }
        }
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
        String[] appliedFilters = sharedResource.getCurrentFilters();
        for (String appliedFilter : appliedFilters) {
            if (appliedFilter != null) {
                System.out.println(appliedFilter + " ");
            }
        }
        System.out.println();
    }
}
