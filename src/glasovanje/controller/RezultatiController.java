/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.controller;

import glasovanje.model.RezultatiModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Sanja
 */
public class RezultatiController implements Initializable {
   
    @FXML
    Button prikazi;
    @FXML
    Button korisnici;
    @FXML
    Button obrisi;
    
    @FXML
    PieChart pieChart;
    
    @FXML
    TableView tablica;
    @FXML
    TableColumn kandidat;
    @FXML
    TableColumn br_glasova;
    
    @FXML
    Label brojGlasaca;
    
    RezultatiModel rm = new RezultatiModel();
    
    Parent root;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        obrada();
    }
    
    @FXML
    public void obrada(){
        ObservableList<RezultatiModel> data;
        data = RezultatiModel.listaKandidata();
        
        kandidat.setCellValueFactory(new PropertyValueFactory<RezultatiModel, String>("Kandidat"));
        br_glasova.setCellValueFactory(new PropertyValueFactory<RezultatiModel, Integer>("Broj_glasova"));
        
        tablica.setItems(data);
        
        ObservableList<Data> pieData = rm.buildData();
     
        pieChart.setData(pieData);
        pieChart.setVisible(true);
        
        this.brojGlasaca.setText("Glasovalo " + rm.brojGlasaca());
    }
    
    @FXML
    public void prikaziKorisnike() throws IOException{
        root = FXMLLoader.load(getClass().getClassLoader().getResource("glasovanje/view/Korisnici.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Registrirani korisnici");
        stage.setScene(new Scene(root, 791, 414));
        stage.show();
        korisnici.getScene().getWindow().hide();
    }
    
    
    
}
     
  