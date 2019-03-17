package src;
/**
 * This is the main class for the Fotoshop application
 * 
 * @author Joseph Williams
 * @version 2018.12.12
 */
import java.util.*;

/**
 *
 * @author regno
 */
public class Main {
    
    /**
     * @param args The first value args[0] refers to the language code eg. 'en'.
     * The second value args[1] refers to the country code eg. 'UK'. 
     * These arguments can be set under project properties.
     */
    public static void main(String[] args) {
        String language;
        String country;
        
        if (args.length != 2) {
            language = "en";
            country = "UK";
        } else {
            language = args[0];
            country = args[1];
        }
       
        Locale currentLocale;
        ResourceBundle messages;

        currentLocale = new Locale(language, country);
        
        messages = ResourceBundle.getBundle("langFiles.MessagesBundle", currentLocale);
        new Editor(messages).edit();
    }
}
