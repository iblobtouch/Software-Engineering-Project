package commands;

/**
 * Command is an abstract class in charge of retrieving and setting user inputs
 * to their corresponding field values (secondWord and thirdWord). Both of these
 * fields are optional and are set to null when their values are not given. It
 * also contains the abstract method execute() to trigger command execution.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2019.03.17
 */

public abstract class Command {

    private String secondWord;
    private String thirdWord;

    /**
     * Initially sets its field values secondWord and thirdWord to null.
     */
    public Command() {
        this.secondWord = null;
        this.thirdWord = null;
    }

    /**
     * Returns the second word from the user input.
     *
     * @return secondWord
     */
    public String getSecondWord() {
        return secondWord;
    }

    /**
     * Returns the third word from the user input.
     *
     * @return thirdWord
     */
    public String getThirdWord() {
        return thirdWord;
    }

    /**
     * Determines whether the user has typed in a second word.
     *
     * @return true if secondWord was given, false otherwise
     */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }

    /**
     * Determines whether the user has typed in a third word.
     *
     * @return true if thirdWord was given, false otherwise
     */
    public boolean hasThirdWord() {
        return (thirdWord != null);
    }

    /**
     * Sets the secondWord field with the second word given by the user.
     *
     * @param secondWord second word from the user input
     */
    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }

    /**
     * Sets the thirdWord field with the third word given by the user.
     *
     * @param thirdWord third word from the user input
     */
    public void setThirdWord(String thirdWord) {
        this.thirdWord = thirdWord;
    }

    /**
     * Abstract method which executes the corresponding command Class executor
     * based on the first word given by the user input.
     *
     * @return message output for each operation executed
     */
    public abstract String execute();
}
