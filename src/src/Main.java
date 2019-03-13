package src;
/**
 * This is the main class for the Fotoshop application
 * 
 * @author Joseph Williams
 * @version 2018.12.12
 */
import java.util.*;
public class Main {
    
    /**
     *
     * @param args - args[0] refers to the language code eg. 'en' and args[1]
     * refers to the country code eg. 'UK'. These arguments can be set under
     * properties
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
        
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        new Editor(messages).edit();
    }
}
