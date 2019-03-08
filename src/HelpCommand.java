import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelpCommand extends Command{

	private CommandWords commandWords;
	private ResourceBundle messages;
	private Resources sharedResource;
	
	public HelpCommand(CommandWords words, ResourceBundle messages) {
		commandWords = words;
		this.messages = messages;
		sharedResource = Resources.getSharedResources();
		// TODO Auto-generated constructor stub
	}
	
	 /**
     * Print out some help information. Here we print some useless, cryptic
     * message and a list of the command words.
     * @return Formatted text containing help.
     */
	@Override
    public void execute() {
        System.out.println(messages.getString("helpMsg1"));
        System.out.println(messages.getString("helpMsg2"));
        System.out.println(commandWords.getAll());

    }
	
}
