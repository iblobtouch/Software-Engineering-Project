import java.util.ResourceBundle;

public class HelpCommand extends Command{

    private final CommandWords commandWords;
    private final ResourceBundle messages;
	
    /**
     *
     * @param words - instance of commandWords class which enables the
     * retrieval of all valid commands
     * @param messages - Contains the internationalisation resource which
     * enables localisation
     */
    public HelpCommand(CommandWords words, ResourceBundle messages) {
        commandWords = words;
	this.messages = messages;
    }
	
    /**
     * Print out some help information. Here we print some useless, cryptic
     * message and a list of the command words.
     * @return help message and commands
     */
    @Override
    public String execute() {
        String output = "";
        output = messages.getString("helpMsg1") 
                + "\n" + messages.getString("helpMsg2")
                + "\n" + commandWords.getAll();
        return output;
    }
	
}
