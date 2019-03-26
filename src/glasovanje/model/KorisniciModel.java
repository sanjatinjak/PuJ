/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

import static glasovanje.model.RezultatiModel.db;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sanja
 */
public class KorisniciModel {
    SimpleStringProperty ime = new SimpleStringProperty();
    SimpleStringProperty prezime = new SimpleStringProperty();
    SimpleStringProperty jmbg = new SimpleStringProperty();
    SimpleStringProperty broj_osobne = new SimpleStringProperty();
    SimpleStringProperty grad = new SimpleStringProperty();
    SimpleStringProperty adresa = new SimpleStringProperty();
    
    public KorisniciModel (String ime, String prezime, String jmbg, String broj_osobne, String grad, String adresa) {
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.jmbg = new SimpleStringProperty(jmbg);
        this.broj_osobne = new SimpleStringProperty(broj_osobne);
        this.grad = new SimpleStringProperty(grad);
        this.adresa = new SimpleStringProperty(adresa);
    }

    public KorisniciModel(){
        
    }
    
    public String getIme() {
        return ime.get();
    }

    public void setIme(String ime) {
        this.ime = new SimpleStringProperty(ime);
    }

    public String getPrezime() {
        return prezime.get();
    }

    public void setPrezime(String prezime) {
        this.prezime = new SimpleStringProperty(prezime);
    }

    public String getJmbg() {
        return jmbg.get();
    }

    public void setJmbg(String jmbg) {
        this.jmbg = new SimpleStringProperty(jmbg);
    }

    public String getBroj_osobne() {
        return broj_osobne.get();
    }

    public void setBroj_osobne(String broj_osobne) {
        this.broj_osobne = new SimpleStringProperty(broj_osobne);
    }

    public String getGrad() {
        return grad.get();
    }

    public void setGrad(String grad) {
        this.grad = new SimpleStringProperty(grad);
    }

    public String getAdresa() {
        return adresa.get();
    }

    public void setAdresa(String adresa) {
        this.adresa = new SimpleStringProperty(adresa);
    }
    
    PreparedStatement query;
    ResultSet rs;
    
    int korisnik_id = 0;

    public static ObservableList<KorisniciModel> listaKorisnika () {
        ObservableList<KorisniciModel> lista = FXCollections.observableArrayList();
        
        ResultSet r = Baza.DB.select("SELECT * FROM registrirani");
        
        try {
            while (r.next()) {
                lista.add(new KorisniciModel(r.getString("ime"), r.getString("prezime"), r.getString("jmbg"), r.getString("broj_osobne"), r.getString("grad"), r.getString("adresa")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
     
    //delete
    public void obrisiKorisnika(KorisniciModel korisnik){
        
        try {
            //dohvati korisnik id
            query = db.exec("SELECT korisnik_id FROM registrirani WHERE jmbg=?");
            query.setString(1, korisnik.getJmbg());
            query.executeQuery();
            
            rs = query.executeQuery();
            if(rs.next()) korisnik_id= rs.getInt(1);
            
            //izbrisati iz registrirani
            query = db.exec("DELETE FROM registrirani WHERE jmbg=?");
            query.setString(1, korisnik.getJmbg());
            query.executeUpdate();
            
            //izbrisati iz tablice korisnik
            query = db.exec("DELETE FROM korisnici WHERE id=?");
            query.setInt(1, korisnik_id);
            query.executeUpdate();
            
           
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
        }
    }
    
}
