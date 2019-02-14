/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.controller;

import glasovanje.model.Korisnik;
import glasovanje.model.LogiraniKorisnikModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sanja
 */
public class PrijavaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextField username;
    
    @FXML
    PasswordField password;
    
    @FXML
    Label statuslbl;
    
    @FXML
    Button prijavaBtn;
        
    @FXML          
    public void prijavise() throws IOException{
    Parent root;
    String usr = this.username.getText();
    String pass = this.password.getText();
    
    LogiraniKorisnikModel lkm = new LogiraniKorisnikModel(usr, pass);
    
    //pozovi funkciju za prijavu, ako je uspjesno izvrsena prikazi mu prozor za glasovanje
    if(lkm.prijava() == 1){
        
        root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Izbor.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Glasovanje");
        stage.setScene(new Scene(root, 905, 648));
        stage.show();
        prijavaBtn.getScene().getWindow().hide();
    
    } else if(lkm.prijava() == 2){
 
        root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Rezultati.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Rezultati");
        stage.setScene(new Scene(root, 905, 648));
        stage.show();
    } 

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
