package glasovanje.controller;


import glasovanje.model.RegistracijaModel;
import static glasovanje.model.Kontrola.checkData;
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
    Button prijava;
    @FXML
    Button natrag;
    
    @FXML
    Label greska;
    @FXML
    Label username;
    @FXML
    Label password;
    @FXML
    Label status;

    @FXML
    public void registerAction(ActionEvent e) {
        
        //dohvat unesenih podataka
        String ime = this.ime.getText();
        String prezime = this.prezime.getText();
        String JMBG = this.jmbg.getText();
        String broj_osobne = this.broj_osobne.getText();
        String grad = this.grad.getText();
        String adresa = this.adresa.getText();

        //provjera unesenih podatke
        String status = checkData(JMBG, ime, prezime, grad);
        
        if(status == ""){
        
        //novi registrirani glasac
        RegistracijaModel glasac = new RegistracijaModel(ime, prezime, JMBG, broj_osobne, grad, adresa);
        if(glasac.create() == 0){
            
        this.username.setText("Vaše korisničko ime je : " + glasac.username);
        this.password.setText("Šifra : " + glasac.password);
      
        registracija.setVisible(false);
        prijava.setVisible(true);
       
        } else this.status.setText(glasac.poruka);

        this.ime.setText("");
        this.prezime.setText("");
        this.jmbg.setText("");
        this.broj_osobne.setText("");
        this.grad.setText("");
        this.adresa.setText("");
        } else this.status.setText(status);
        
    }

    @FXML
    public void goBack() throws IOException{
        root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Pocetna.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Pocetna");
        stage.setScene(new Scene(root, 891, 505));
        stage.show();
        registracija.getScene().getWindow().hide();
    }
    
    @FXML
    public void prijaviSe() throws IOException{
        root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Prijava.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Prijava");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        registracija.getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       prijava.setVisible(false);
    }
    
}
