/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public RezultatiModel(String kandidat, int broj_glasova){
        this.kandidat = new SimpleStringProperty (kandidat);
        this.broj_glasova = new SimpleIntegerProperty (broj_glasova);
    }
    
    public RezultatiModel(){}
    
    public static PreparedStatement query;
    private ObservableList pieChartData;
    
    int brojGlasova;

   
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
        
   
    static Baza db = new Baza();
    PreparedStatement stmnt;
    static ResultSet rs;
    int i;
 
    //read
    public static ObservableList<RezultatiModel> listaKandidata () {
        ObservableList<RezultatiModel> lista = FXCollections.observableArrayList();
        
        //dohvati sve kandidate
        try {
            query = db.exec("SELECT * FROM kandidati");
            rs = query.executeQuery();
            
            while (rs.next()) {
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
              
            query = db.exec("SELECT * FROM kandidati");
            rs = query.executeQuery();
 
            while(rs.next()){
                //adding data on piechart data
                pieChartData.add(new PieChart.Data(rs.getString(2),rs.getInt(3)));
            }
          }catch(Exception e){
              System.out.println("Greška pri izvršavanju upita." + e.getMessage());
          }
          return pieChartData;
      }
    
    public int brojGlasaca(){
        query = db.exec("SELECT count(*) FROM glasovi");
        try {
            rs = query.executeQuery();
            if(rs.next()) brojGlasova= rs.getInt(1);
        } catch (SQLException ex) {
              System.out.println("Greška pri izvršavanju upita." + ex.getMessage());
        }
        return brojGlasova;
    }
 
}
