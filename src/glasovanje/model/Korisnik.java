/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

import java.security.SecureRandom;

/*
 * @author Sanja
 */

public abstract class Korisnik {
    
    public abstract int create ();
    public abstract void update ();
   
    
    //pravimo random username i random string koji predstavlja sifru
    
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    
    //od 1 do 10 000 random broj
    public String username(){
        return "glasac_" + (rnd.nextInt(10000) + 1);
    }
    
    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ ) 
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
            return sb.toString();
     }
   
    
}
