/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    
    
    @FXML
    public void pozoviRegistraciju(ActionEvent event) throws IOException{
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Registriraj.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Registracija");
        stage.setScene(new Scene(root, 905, 648));
        stage.show();
        registracija.getScene().getWindow().hide();
    }


    
    @FXML
    public void pozoviPrijavu() throws IOException{
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Prijava.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Prijava");
        stage.setScene(new Scene(root, 905, 648));
        stage.show();
        prijava.getScene().getWindow().hide();
        
        //radi glasovanje osim prikazivanja poruka ono nisi prijavljen prijavljen si i to moram vidjeti
    }


    @FXML
    public void pozoviBrojacPrijavu(){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
