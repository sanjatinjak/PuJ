/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.controller;

import glasovanje.model.PrijavaModel;
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
    Parent root;
    Stage stage = new Stage();

    @FXML
    TextField username;
    
    @FXML
    PasswordField password;
    
    @FXML
    Label status;
    
    @FXML
    Button prijavaBtn;
    
       
    public void prijavise(){
    
        try {
            String usr = this.username.getText();
            String pass = this.password.getText();
            
            PrijavaModel pm = new PrijavaModel(usr, pass);
            
            //pozovi funkciju za prijavu, ako je uspjesno izvrsena prikazi mu prozor za glasovanje
            if(pm.prijava() == 1){
                
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Izbor.fxml"));
                    stage.setTitle("Glasovanje");
                    stage.setScene(new Scene(root, 895, 501));
                    stage.show();
                    prijavaBtn.getScene().getWindow().hide();
                } catch (IOException ex) {
                System.out.println("Greška prilikom otvaranja prozora." + ex);
                }
                
            } else if(pm.prijava() == 2){
                
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Rezultati.fxml"));
                    stage.setTitle("Rezultati");
                    stage.setScene(new Scene(root, 895, 501));
                    stage.show();
                    prijavaBtn.getScene().getWindow().hide();
                } catch (IOException ex) {
                System.out.println("Greška prilikom otvaranja prozora." + ex);
                }
            } else this.status.setText(pm.lbl);
        } catch (IOException ex) {
                System.out.println("Greška prilikom izvrsavanja metode." + ex);
        }

    }
    
    public void goBack(){
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Pocetna.fxml"));
            stage.setTitle("Pocetna");
            stage.setScene(new Scene(root, 891, 505));
            stage.show();
            prijavaBtn.getScene().getWindow().hide();
        } catch (IOException ex) {
                System.out.println("Greška prilikom otvaranja prozora." + ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
