/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Before;
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
    public void testMonoMessageOutput(){
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("mono").execute();
        assertTrue(output.equals("Mono filter has been successfully applied to the image."));   
    }
    
    @Test
    public void testRotateMessageOutput(){
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("rot90").execute();
        assertTrue(output.equals("Image has been successfully rotated around 90 degrees."));
    }
    
    @Test
    public void testFlipHMessageOutput(){
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("flipH").execute();
        assertTrue(output.equals("Image has been successfully flipped horizontally."));
    }
    
    @Test
    public void testFlipVMessageOutput(){
        parser.getCommand("open input.jpg").execute();
        String output = parser.getCommand("flipV").execute();
        assertTrue(output.equals("Image has been successfully flipped vertically."));
    }
    
    
}
