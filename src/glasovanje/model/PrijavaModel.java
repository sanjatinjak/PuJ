/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

import static glasovanje.model.Kontrola.hashPassword;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;

/**
 *
 * @author Sanja
 */
public class PrijavaModel {
    
    Baza db = new Baza();
    private String korisnickoime;
    private String sifra;
    public String lbl;
    int uloga, id;
    
    public PrijavaModel(String username, String password) {
        this.korisnickoime=username;
        this.sifra = password;
    }
    
   @FXML
    public int prijava() throws IOException {
        String status;
        
        //provjeri je li ista unio
        if (korisnickoime.equals("") && sifra.equals("")) {
            lbl="Greška ! Niste unjeli korisničko ime i lozinku.";
        } else if (korisnickoime.equals("")) {
            lbl="Greška ! Niste unijeli korisničko ime";
        } else if (sifra.equals("")) {
            lbl="Greska ! Niste unijeli lozinku.";
        } else {
            try {
                //ovdje provjeravamo korisnika u bazi
                PreparedStatement iskaz = db.exec("SELECT * FROM `korisnici` WHERE korisnicko_ime=? AND sifra=?");
                iskaz.setString(1, korisnickoime);
                iskaz.setString(2, hashPassword(sifra));
                
                ResultSet rs = iskaz.executeQuery();
                
                if (!rs.next()) {
                    lbl = "Pogrešni korisnički podaci.";
                } else {
                    //provjeri id uloge
                    uloga = rs.getInt("id_uloge");
                    if(uloga == 1){    
                        id = rs.getInt("id");
                    //provjeri je li glasovao    
                        status = rs.getString("status");
                   
                        if(status == null){
                    //ako mu je id uloge 1 i ako nije glasovao prikazi prozor za glasovanje
                    //prvo update status        
                     PreparedStatement qry = db.exec("UPDATE korisnici SET status=?  WHERE id=?");
                     qry.setString(1, "Glasovao");
                     qry.setInt(2, id);
                     qry.executeUpdate();
                     
                        return 1;
                        } else lbl="Već ste glasovali !";
                        
                   }else if(uloga == 2)
                    //prebaci ga na prozor za rezultate -> uloga 2 je brojac glasova
                        return 2;
                }
           }catch (SQLException ex) {
                System.out.println("Greška prilikom provjere korisnika, neispravan upit." + ex);
            
           }
        }
        return -1;
    }
    
   public String label(){
   return lbl;
   }


}
