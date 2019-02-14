package glasovanje.controller;

import javafx.scene.control.TextField;
import glasovanje.model.GlasačModel;
import static java.awt.Color.green;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanja
 */
public class RegistracijaController implements Initializable{
    
    Parent root;
    
    @FXML
    TextField ime;
    
    @FXML
    TextField prezime;
    
    @FXML
    TextField jmbg;
    
    @FXML
    TextField broj_osobne;
    
    @FXML
    TextField grad;
    
    @FXML
    TextField adresa;
   
    @FXML
    Button registracija;
    
    @FXML
    Label greska;
    
    @FXML
    Label username;
    
    @FXML
    Label password;
    
    @FXML
    Label status;
    

    public void registerAction(ActionEvent e) throws IOException {
        
        String ime = this.ime.getText();
        String prezime = this.prezime.getText();
        String JMBG = this.jmbg.getText();
        String broj_osobne = this.broj_osobne.getText();
        String grad = this.grad.getText();
        String adresa = this.adresa.getText();

        GlasačModel glasac = new GlasačModel(ime, prezime, JMBG, broj_osobne, grad, adresa);
        if(glasac.create() == 0){
        this.username.setText("Vaše korisničko ime je : " + glasac.username);
        this.password.setText("Šifra : " + glasac.password);
        this.status.setText("Nakon 5 sekundi bit ćete preusmjereni na prijavu. Prijavljujete se s podacima koji su vam dani. Ako ne želite sada glasovati molimo vas da izađete i ne prijavljujete se jer možete samo jednom glasovati !");
        
        root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Prijava.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Glasovanje");
        stage.setScene(new Scene(root, 905, 648));
        
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(
              new Runnable() {
                @Override 
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override 
                        public void run() { 
                            registracija.getScene().getWindow().hide();
                            stage.show(); 
                        }
                    });
        }
        }, 20, TimeUnit.SECONDS);

        
        } else this.status.setText(glasac.greska);

        this.ime.setText("");
        this.prezime.setText("");
        this.jmbg.setText("");
        this.broj_osobne.setText("");
        this.grad.setText("");
        this.adresa.setText("");
        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
}
