/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.controller;

import glasovanje.model.KorisniciModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sanja
 */
public class KorisniciController implements Initializable {

    /**
     * Initializes the controller class.
     */
     
    @FXML
    TableView korisniciTbl;
    @FXML
    TableColumn imeTblCol;
    @FXML
    TableColumn prezimeTblCol;
    @FXML
    TableColumn jmbgTblCol;
    @FXML
    TableColumn brojOsobneTblCol;
    @FXML
    TableColumn gradTblCol;
    @FXML
    TableColumn adresaTblCol;
    
    @FXML
    Button natrag;
    
    
    Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readKorisnici();
    }   
    
    public void readKorisnici(){
    ObservableList<KorisniciModel> data = KorisniciModel.listaKorisnika();
        
        imeTblCol.setCellValueFactory(new PropertyValueFactory<KorisniciModel, String>("Ime"));
        prezimeTblCol.setCellValueFactory(new PropertyValueFactory<KorisniciModel, String>("Prezime"));
        jmbgTblCol.setCellValueFactory(new PropertyValueFactory<KorisniciModel, String>("jmbg"));
        brojOsobneTblCol.setCellValueFactory(new PropertyValueFactory<KorisniciModel, String>("Broj_osobne"));
        gradTblCol.setCellValueFactory(new PropertyValueFactory<KorisniciModel, String>("Grad"));
        adresaTblCol.setCellValueFactory(new PropertyValueFactory<KorisniciModel, String>("Adresa"));
        
        
        korisniciTbl.setItems(data);
    }
    
    @FXML
    public void goBack() throws IOException{
        root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Rezultati.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Pocetna");
        stage.setScene(new Scene(root, 895, 501));
        stage.show();
        natrag.getScene().getWindow().hide();
    }
    
    @FXML
    public void obrisi(){
       
       KorisniciModel km = (KorisniciModel) korisniciTbl.getSelectionModel().getSelectedItem();
        
       korisniciTbl.getItems().removeAll(
                korisniciTbl.getSelectionModel().getSelectedItems()
        );
       
       km.obrisiKorisnika(km);
       
       readKorisnici();

    }
  
    
}
