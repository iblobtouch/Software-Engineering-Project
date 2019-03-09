import java.util.ResourceBundle;

public class HelpCommand extends Command{

    private final CommandWords commandWords;
    private final ResourceBundle messages;
	
    /**
     *
     * @param words - instance of commandWords class which enables the
     * retrieval of all valid commands
     * @param messages - Contains the internalisation resource which
     * enables localisation
     */
    public HelpCommand(CommandWords words, ResourceBundle messages) {
        commandWords = words;
	this.messages = messages;
    }
	
    /**
     * Print out some help information. Here we print some useless, cryptic
     * message and a list of the command words.
     */
    @Override
    public void execute() {
        System.out.println(messages.getString("helpMsg1"));
        System.out.println(messages.getString("helpMsg2"));
        System.out.println(commandWords.getAll());
    }
	
}
