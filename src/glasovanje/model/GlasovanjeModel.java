/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author Sanja
 */
public class GlasovanjeModel {
    
    Baza db = new Baza();
    PreparedStatement iskaz1, iskaz2;
    int br;
    
    private String glas;
    
    public GlasovanjeModel (String glas) {
     this.glas = glas;
    }
    
    public void pohraniGlas() throws SQLException{
        iskaz1 = db.exec("SELECT broj_glasova FROM kandidati WHERE kandidat=?");
        iskaz1.setString(1, glas);  
        iskaz1.executeQuery();
        
        ResultSet rs = iskaz1.executeQuery();
        
        if(rs.next()){
        br = rs.getInt(1);
        
        iskaz2 = db.exec("UPDATE kandidati SET broj_glasova =? WHERE kandidat=?");
        iskaz2.setInt(1, br+1);       
        iskaz2.setString(2, glas);   
        iskaz2.executeUpdate();
        }
    }
    
}

