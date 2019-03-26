/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

import glasovanje.model.Baza;
import static glasovanje.model.Kontrola.hashPassword;
import glasovanje.model.Korisnik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sanja
 */
public class RegistracijaModel extends Korisnik {
    
    private int id;
    private String ime;
    private String prezime;
    private String jmbg;
    private String broj_osobne;
    private String grad;
    private String adresa;
    
    Baza db = new Baza();
    PreparedStatement query;
    
    public String poruka;
    public String username;
    public String password;
    
    
    public RegistracijaModel(String ime, String prezime, String jmbg, String broj_osobne, String grad, String adresa) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.broj_osobne = broj_osobne;
        this.grad = grad;
        this.adresa = adresa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getBroj_osobne() {
        return broj_osobne;
    }

    public void setBroj_osobne(String broj_osobne) {
        this.broj_osobne = broj_osobne;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    
    
    @Override
    public int create() {
        
        try {
            //prvo provjeri ima li vec zapis u bazi s jedinstvenim podacima -> jmbg
            query = db.exec("SELECT * FROM registrirani WHERE jmbg=?");
            query.setString(1, this.jmbg);
            ResultSet rs = query.executeQuery();
            
            if(!rs.next()){
                //ako nema upisi ga u bazu
   
            query = db.exec("INSERT INTO registrirani VALUES (null, ?, ?, ?, ?, ?, ?, ?)");
            query.setString(1, this.ime);
            query.setString(2, this.prezime);
            query.setString(3, this.jmbg);
            query.setString(4, this.broj_osobne);
            query.setString(5, this.grad);
            query.setString(6, this.adresa);
            query.setString(7, null);  //id korisnika kojeg cemo naknadno dodati -> strani kljuc
          
            query.executeUpdate();
            
            ResultSet generatedKeys = query.getGeneratedKeys();
            generatedKeys.next();
            this.id = generatedKeys.getInt(1);
          
            poruka = "Korisnik upisan !";
            //pozovi metodu u kojom cemo upisati username i password u tablicu korisnici
            upisiKorisnickePodatke();
            
            return 0;
            }else{
                
             poruka = "Već ste se registrirali !";
             
             return -1;
            }
        } catch (SQLException e) {
            System.out.println("Greska prilikom stvaranja korisnika u bazi:"
                    + e.getMessage());
        }
        
        return -1;
        
    }
    
    
    public void upisiKorisnickePodatke (){
       
        //generiraj mu username i password
        username = username();
        password = randomString(3);
        
        try {
        //upisi ga u bazu
                query = db.exec("INSERT INTO korisnici VALUES(null, ?, ?, ?,?)");
                query.setString(1, username);
                query.setString(2, hashPassword(password));
                query.setInt(3, 1); //1 -> uloga -> glasac
                query.setString(4, null); // -> status po kojem se prati je li glasovao
                query.executeUpdate();
  
                //update cemo tablicu glasaci dodat cemo id korisnika 
                update();
                
            } catch (SQLException ex) {
                System.out.println("Greska prilikom stvaranja korisnika u bazi:"
                    + ex.getMessage());            }
    }
        
    @Override
    public void update() {
            try {
            //dohvati max id zato sto mi je to id posljednjeg 
            //registriranog kojemu se generirao taj username i password i kojemu cu dodijeliti 
            //id korisnika
            query = db.exec("SELECT max(id) FROM registrirani");
           
               ResultSet idMax = query.executeQuery();
               int id2 = 0; 
               
                if ( idMax.next() ){
                    id2 = idMax.getInt(1);  
                }
                //dohvati max id jer je to id zadnjeg korisnika koji je upisan
                query = db.exec("SELECT max(id) FROM korisnici");
                ResultSet idLastUser = query.executeQuery();
                int idUser = 0; 
               
                if ( idLastUser.next() ){
                    idUser = idLastUser.getInt(1);  
                }

                query = db.exec("UPDATE registrirani SET korisnik_id=? WHERE id = ?");
                query.setInt(1, idUser);
                query.setInt(2, id2);
                query.executeUpdate();
                
            } catch (SQLException ex) {
                System.out.println("Greska prilikom azuriranja korisnika u bazi:"
                    + ex.getMessage());            
            }

    }

    

    
}
