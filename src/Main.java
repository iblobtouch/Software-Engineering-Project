/**
 * This is the main class for the Fotoshop application
 * 
 * @author Joseph Williams
 * @version 2018.12.12
 */
import java.util.*;
public class Main {
    
   public static void main(String[] args) {
        String language;
        String country;
        
        if (args.length != 2) {
            language = new String("fr");
            country = new String("FR");
        } else {
            language = new String(args[0]);
            country = new String(args[1]);
        }
       
        Locale currentLocale;
        ResourceBundle messages;

        currentLocale = new Locale(language, country);
        
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        new Editor(messages).edit();
    }
}
