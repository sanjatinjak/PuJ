/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class Glasovanje extends Application {
    
    //prvo nam se otvara reigster panel. ako imamo već račun dolje ce mu biti link za otici na prijavu
    //login panel imamo 
    
    //nakon toga ide na izbor panel tu odabere i pohrani
    
    //ako se logira kao "admin" ide na insight panel i gleda rezultate
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("view/Pocetna.fxml"));
        
        Scene scene = new Scene(root, 905, 648);
        
        primaryStage.setTitle("Registrirajte se na sustav!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
