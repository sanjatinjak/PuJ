package glasovanje.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Daniel on 4.5.2016..
 */
public class Baza extends Konekcija {

    private Statement upit;
    private PreparedStatement execUpit;
    
    public static final Baza DB = new Baza();

    public Baza () {
        super();
        try {
            this.upit = this.konekcija.createStatement();
            this.upit.execute("SET NAMES utf8");
        } catch (SQLException ex) {
            System.out.println ("Nastala je greška prilikom izvršavanja upita " + ex.getMessage());
        }
    }

    public ResultSet select (String sql) {
        try {
            this.upit = this.konekcija.createStatement();
            return this.upit.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println ("Nastala je greška prilikom izvršavanja upita " + e.getMessage());
            return null;
        }
    }

    public PreparedStatement exec (String sql) {
        try {
            
            this.execUpit = this.konekcija.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return this.execUpit;
        } catch (SQLException e) {
            System.out.println("Nisam uspio izvršiti upit " + e.getMessage());
        }
        return null;
    }
}
