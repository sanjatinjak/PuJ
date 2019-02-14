/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

import glasovanje.model.Baza;
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
public class GlasačModel extends Korisnik {
    
    private int id;
    private String ime;
    private String prezime;
    private String jmbg;
    private String broj_osobne;
    private String grad;
    private String adresa;
    
    Baza db = new Baza();
    
    public String greska;
    public String username;
    public String password;
    
           
    public GlasačModel(String ime, String prezime, String jmbg, String broj_osobne, String grad, String adresa) {
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
    
    /**
     *
     * @return
     */
    @Override
    public int create() {
        
        try {
            //prvo provjeri ima li vec upis u bazi s jedinstvenim podacima tipa jmbg i broj osobne
            PreparedStatement stmnt;
            stmnt = db.exec("SELECT * FROM `glasaci` WHERE jmbg=?");
            stmnt.setString(1, jmbg);
            ResultSet rs = stmnt.executeQuery();
            
            if(!rs.next()){
                //ako nema upisi ga u bazu
            PreparedStatement query, qry;
            
            query = db.exec("INSERT INTO `glasaci` VALUES (null, ?, ?, ?, ?, ?, ?, ?)");
            
            query.setString(1, this.ime);
            query.setString(2, this.prezime);
            query.setString(3, this.jmbg);
            query.setString(4, this.broj_osobne);
            query.setString(5, this.adresa);
            query.setString(6, this.grad);
            query.setString(7, null);  //id korisnika kojeg cemo kasnije dodati
          
            query.executeUpdate();
            
            ResultSet generatedKeys = query.getGeneratedKeys();
            generatedKeys.next();
            this.id = generatedKeys.getInt(1);
          
            greska = "Korisnik upisan !";
            upisiKorisnickePodatke(); //pozovi funkciju u kojoj cemo upisati u tablicu korisnici
            
            return 0;
            }else{
                
             greska = "Već ste se registrirali !";
             
             return -1;
            }
        } catch (SQLException e) {
            System.out.println("Greska prilikom stvaranja korisnika u bazi:"
                    + e.getMessage());
        }
        
        return -1;
        
    }
    //bfbf
    public void upisiKorisnickePodatke (){
       
            
        PreparedStatement qry;
        //generiraj mu username i password
        username = randomString(5);
        password = randomString(3);
        
        try {
        //upisi ga u bazu
                qry = db.exec("INSERT INTO `korisnici` VALUES(null, ?, ?, ?,?)");
                qry.setString(1, username);
                qry.setString(2, hashPassword(password));
                qry.setInt(3, 1); //1 -> uloga
                qry.setString(4, null); //1 -> status
                qry.executeUpdate();
  
                //update cemo tablicu glasaci dodat cemo id korisnika 
                update();
                
            } catch (SQLException ex) {
            Logger.getLogger(GlasačModel.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
    @Override
    public void update() {
        PreparedStatement qry2, qry3, qry4;
            try {
            //dohvati max id zato sto mi je to id posljednjeg 
            //registriranog kojemu se generirao taj username i password i kojemu cu dodijeliti 
            //id korisnika
            qry2 = db.exec("select max(id) from `glasaci`");
           
               ResultSet idMax = qry2.executeQuery();
               int id2 = 0; 
               
                if ( idMax.next() ){
                    id2 = idMax.getInt(1);  
                }
                //dohvati mi max id jer mi je to id zadnjeg korisnika koji se upisao
                qry3 = db.exec("select max(id) from `korisnici`");
                ResultSet idLastUser = qry3.executeQuery();
                int idUser = 0; 
               
                if ( idLastUser.next() ){
                    idUser = idLastUser.getInt(1);  
                }

                qry4 = db.exec("UPDATE `glasaci` SET korisnik_id=? WHERE id = ?");
                qry4.setInt(1, idUser);
                qry4.setInt(2, id2);
                qry4.executeUpdate();
                
            } catch (SQLException ex) {
            Logger.getLogger(GlasačModel.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
