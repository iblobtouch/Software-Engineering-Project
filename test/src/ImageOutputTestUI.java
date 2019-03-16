/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
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
public class ImageOutputTestUI {
    ResourceBundle messages;
    Parser parser;
    Resources resources;
    
    public ImageOutputTestUI() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        messages = ResourceBundle.getBundle("langFiles.MessagesBundle", new Locale("en", "UK"));
        resources = Resources.getSharedResources();
        parser = new Parser(messages, resources); 
    }
    
    @Test
    public void testMono() throws IOException{
        String input = "script testMono.txt testFiles/scripts";
        parser.getCommand(input).execute();
        ColorImage actualImageOutput = resources.getCurrentImage();
        ColorImage expectedImageOutput = new ColorImage(ImageIO.read(new File("testFiles/main/mono.jpg")));
        assertTrue(actualImageOutput.equals(expectedImageOutput));
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}