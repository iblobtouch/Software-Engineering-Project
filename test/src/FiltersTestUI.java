/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author regno
 */
public class FiltersTestUI {
    ResourceBundle messages;
    Parser parser;
    Resources resources;
    
    @Before
    public void setUp() {
        messages = ResourceBundle.getBundle("langFiles.MessagesBundle", new Locale("en", "UK"));
        resources = Resources.getSharedResources();
        parser = new Parser(messages, resources); 
    }
    
    @Test
    public void testMono(){
        String input = "script testMono.txt testScripts";
        String output = parser.getCommand(input).execute();
        System.out.println(output);
        assertTrue(output.contains("Loaded input.jpg\n" +
                "The current image is input.jpg\n" +
                "Filters applied:mono"));
        
    }
    
    @Test
    public void testRotate(){
        String input = "script testRotate90.txt testScripts";
        String output = parser.getCommand(input).execute();
        System.out.println(output);
        assertTrue(output.contains("Loaded input.jpg\n" +
                "The current image is input.jpg\n" +
                "Filters applied:rot90"));
    }
    
    @Test
    public void testMonoAndRotate(){
        String input = "script testMonoRotate.txt testScripts";
        String output = parser.getCommand(input).execute();
        System.out.println(output);
        assertTrue(output.contains("Loaded input.jpg\n" +
                "The current image is input.jpg\n" +
                "Filters applied:mono rot90"));
    }
}
