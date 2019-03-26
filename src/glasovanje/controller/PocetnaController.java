/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sanja
 */
public class PocetnaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button registracija;
    @FXML
    Button prijava;
    @FXML
    Button brojac;
    
    Parent root;
    Stage stage = new Stage();

    @FXML
    public void pozoviRegistraciju(ActionEvent event) {
        
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Registriraj.fxml"));
            stage.setTitle("Registracija");
            stage.setScene(new Scene(root, 823, 506));
            stage.show();
            registracija.getScene().getWindow().hide();
        } catch (IOException ex) {
                System.out.println("Greška prilikom otvaranja prozora." + ex);
        }
    }

    
    @FXML
    public void pozoviPrijavu() {
       
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Prijava.fxml"));
            stage.setTitle("Prijava");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
            prijava.getScene().getWindow().hide();
            
            //radi glasovanje osim prikazivanja poruka ono nisi prijavljen prijavljen si i to moram vidjeti
        } catch (IOException ex) {
                System.out.println("Greška prilikom otvaranja prozora." + ex);
        }
    }

    @FXML
    public void prikaziUpute(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Upute.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Upute");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node)event.getSource()).getScene().getWindow() );
            stage.show();
        } catch (IOException ex) {
                System.out.println("Greška prilikom otvaranja prozora." + ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
