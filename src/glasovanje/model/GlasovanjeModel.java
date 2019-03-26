    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sanja
 */
public class GlasovanjeModel {
    
    private String glas;

    public GlasovanjeModel (String glas) {
     this.glas = glas;
    }
    
    Baza db = new Baza();
    PreparedStatement query;
    ResultSet rs;

    int br, id;
    
    public void pohraniGlas(){
        try {
            //dohvati id kandidata kojeg je odabrao
            query = db.exec("SELECT id FROM kandidati WHERE kandidat=?");
            query.setString(1, glas);
            query.executeQuery();
            rs = query.executeQuery();
            
            //spremi u tablicu glasovi izbor i id kandidata
            if(rs.next()){
                id = rs.getInt(1);
                
                query = db.exec("INSERT INTO glasovi VALUES(null,?,?)");
                query.setString(1, glas);
                query.setInt(2,id);
                query.executeUpdate();
            }
            
            //dohvati trenutni broj glasova odabranog kandidata
            query = db.exec("SELECT broj_glasova FROM kandidati WHERE kandidat=?");
            query.setString(1, glas);
            query.executeQuery();
            rs = query.executeQuery();
            
            if(rs.next()){
                br = rs.getInt(1);
                
                //uvecaj broj glasova za 1
                query = db.exec("UPDATE kandidati SET broj_glasova =? WHERE kandidat=?");
                query.setInt(1, br+1);
                query.setString(2, glas);
                query.executeUpdate();
            }
        } catch (SQLException ex) {
                System.out.println("Greška prilikom izvršavanja upita." + ex);
        }
    }
    
}

