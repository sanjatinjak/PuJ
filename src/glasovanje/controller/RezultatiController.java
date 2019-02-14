/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.controller;

import glasovanje.model.RezultatiModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Sanja
 */
public class RezultatiController implements Initializable {
   
    
    
    @FXML
    Button prikazi;
    
    @FXML
    PieChart pieChart;
    
    @FXML
    TableView tablica;
    
    @FXML
    TableColumn kandidat;
    @FXML
    TableColumn br_glasova;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        obrada();
    }
    
    @FXML
    public void obrada(){
        ObservableList<RezultatiModel> data;
        data = RezultatiModel.listaKontakata();
        
        kandidat.setCellValueFactory(new PropertyValueFactory<RezultatiModel, String>("Kandidat"));
        br_glasova.setCellValueFactory(new PropertyValueFactory<RezultatiModel, Integer>("Broj_glasova"));
        
        tablica.setItems(data);
        
        RezultatiModel rm = new RezultatiModel();
        ObservableList<Data> pieData = rm.buildData();
     
        pieChart.setData(pieData);
        pieChart.setVisible(true);
        pieChart.setTitle("Glasovi");
        
    }
    
}
