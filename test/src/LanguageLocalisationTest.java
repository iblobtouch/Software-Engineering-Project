/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Locale;
import java.util.ResourceBundle;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author regno
 */
public class LanguageLocalisationTest {

    ResourceBundle messages;
    Parser parser;
    Resources resources;

    @Before
    public void setUp() {
    	messages = ResourceBundle.getBundle("langFiles.MessagesBundle", new Locale("fr", "FR"));
        resources = Resources.getSharedResources();
        resources.resetResources();
        parser = new Parser(messages, resources);
    }

    @Test
    public void frenchLanguageTest() {
        // Change language to french
        // Test help function for help - "aider" in french
        String output = parser.getCommand("aider").execute();
        assertTrue(output.equals("Vous utilisez Fotoshop.\n" + 
        		"Vos mots de commande sont:\n" + 
        		"ouvrir [nom du fichier]: charge le fichier [nom du fichier] situé dans le répertoire racine du projet\n" + 
        		"sauvegarder [nom du fichier]: enregistre l'image dans le répertoire racine du projet avec un nom [nom du fichier]\n" + 
        		"examiner : affiche l'image chargée et ses filtres appliqués\n" + 
        		"mono : applique le filtre mono à l'image\n" + 
        		"tourner90 : Fait pivoter l'image de 90 degrés dans le sens des aiguilles d'une montre.\n" + 
        		"aider : affiche des instructions sur l'utilisation de l'application\n" + 
        		"quitter : quitte l'application\n" + 
        		"scripte [nom du fichier] [répertoire] *: exécute les commandes écrites dans le fichier texte [nom du fichier] situé dans le répertoire racine ou dans le [répertoire] fourni\n" + 
        		"feuilleterH : Inverse l'image horizontalement\n" + 
        		"feuilleterV : retourne l'image verticalement\n" + 
        		"annuler : supprime le filtre ajouté précédemment de l'image\n" + 
        		"mettre [nom] : enregistre une copie de l'image dans le cache des images avec le nom [nom]\n" + 
        		"obtenir [nom] : récupère une image du cache d'images\n" + 
        		"cache : affiche la liste des images actuellement stockées dans le cache d'images."));
    }
}
