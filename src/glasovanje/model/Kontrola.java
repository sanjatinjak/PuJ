/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasovanje.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 *
 * @author Sanja
 */
public interface Kontrola {
    
     public static String checkData(String jmbg, String ime, String prezime, String grad) {
      String status="";
      
        if(jmbg.length() != 13){
            status = "JMBG treba imati 13 znamenki ! ";
            return status;
        }
        
        String day = jmbg.substring(0, 2);
        
        String month = jmbg.substring(2, 4);
        
        String year = jmbg.substring(4,7);
        
        
        int dan = Integer.parseInt(day);
        int mjesec = Integer.parseInt(month);
        int godina = Integer.parseInt(year);
        
        //check jmbg it goes like DD->day   MM->month  YYY-year  kontrol numbers->OO  BBB  K
        if(godina >= 920 && godina <= 999 || godina >= 000 && godina <= 001){
            
            if(mjesec <= 0 && mjesec > 13) {
                status = " Pogrešan unos mjeseca pri upisu JMBG !";
            } else{
                
                if(mjesec == 1 || mjesec == 3 ||mjesec == 5 ||mjesec == 7 ||mjesec == 8 ||mjesec == 10 || mjesec ==12){
                    if(dan <= 0 || dan > 32){
                        status = "Pogrešan unos dana pri upisu JMBG !";
                    }
            } else if(mjesec == 4 || mjesec == 6 || mjesec == 9 || mjesec == 11){
                    if(dan <= 0 || dan > 31){
                        status = "Pogrešan unos dana pri upisu JMBG !";
                    }
            } else if( mjesec == 2 && godina%4==0 ){
                    if(dan <= 0 || dan > 30){
                        status = "Pogrešan unos dana pri upisu JMBG !";
                    }
            } else if( mjesec == 2 && godina%4!=0){
                    if(dan <= 0 || dan > 29){
                        status = "Pogrešan unos dana pri upisu JMBG !";
                    }
            }
                
            } 
        } else status = "Pogrešan unos godine pri upisu JMBG !";
        
        
        //check if name, lastaname or city has numbers
        if(Pattern.compile( "[0-9]" ).matcher( ime ).find()) status = "Pogrešan unos imena !";
        if(Pattern.compile( "[0-9]" ).matcher( prezime ).find()) status = "Pogrešan unos prezimena !";
        if(Pattern.compile( "[0-9]" ).matcher( grad ).find()) status = "Pogrešan unos imena grada !";
        
        
        return status;
    }
     
     public static String md5(String input) {
		
		String md5 = null;
		
		if(null == input) return null;
		
		try {
			
		//Create MessageDigest object for MD5
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		//Update input string in message digest
		digest.update(input.getBytes(), 0, input.length());

		//Converts message digest value in base 16 (hex) 
		md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return md5;
	}
     
      public static String hashPassword(String password){
        
        String salt = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";
        String hash = md5(password + salt);

        return hash;
    
    }
     
}
