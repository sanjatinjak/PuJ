/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


/**
 *
 * @author Sanja
 */

public class RezultatiModel {
    SimpleStringProperty kandidat = new SimpleStringProperty();
    SimpleIntegerProperty broj_glasova = new SimpleIntegerProperty();
    public static PreparedStatement qry, qry2;
    private ObservableList pieChartData;
    
    public String getKandidat() {
        return kandidat.get();
    }

    public void setKandidat(String kandidat) {
        this.kandidat = new SimpleStringProperty(kandidat);
    }

    public int getBroj_glasova() {
        return broj_glasova.get();
    }

    public void setBroj_glasova(Integer br) {
        this.broj_glasova = new SimpleIntegerProperty(br);
    }
        
   
    public static Baza db = new Baza();
    PreparedStatement stmnt;
    int i;
 
    public RezultatiModel(){}
    
    public RezultatiModel(String kandidat, int broj_glasova){
        this.kandidat = new SimpleStringProperty (kandidat);
        this.broj_glasova = new SimpleIntegerProperty (broj_glasova);
    }
   
    public static ObservableList<RezultatiModel> listaKontakata () {
        ObservableList<RezultatiModel> lista = FXCollections.observableArrayList();
        
        try {
            qry = db.exec("SELECT * FROM kandidati");
            ResultSet rs = qry.executeQuery();
            
            while (rs.next()) {
                System.out.println(rs.getInt(3) + " kandidat " + rs.getString(2));// -> prolazi sve je u redu
                lista.add(new RezultatiModel( rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
    
    public ObservableList buildData(){
          pieChartData = FXCollections.observableArrayList();
          try{
            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            qry2 = db.exec("SELECT * FROM kandidati");
            ResultSet rs = qry2.executeQuery();
 
            while(rs.next()){
                //adding data on piechart data
                pieChartData.add(new PieChart.Data(rs.getString(2),rs.getInt(3)));
            }
          }catch(Exception e){
              System.out.println("Error on DB connection" + e.getMessage());
          }
          return pieChartData;
      }
}
