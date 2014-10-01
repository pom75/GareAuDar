package services;

import java.util.Collection;

/**
 *
 * @author Steph
 */
public class ControlString {
    //méthode static :
    //Chaine non vide ex : "   "  (on peux imagine lui passer un ListLinked de String a tester (pour tout nos service)
    //Chaine supérieur ou égale a n charactere
    //format email

    /**
     *
     * @param param
     * @return true si la chaine est correcte ou false si un parametre est vide
     * et/ou null
     */
    public static boolean controlString(Collection<String> param) {
        for (String s : param) {
            if (s == null) {
                return false;
            }
            s = s.trim(); //supprime les espaces avant et apres la chaine
            if (s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmail(String mail) {
            boolean t1 = false;
            boolean t2 = false;

            for (int t = 0; t < mail.length(); t++) {
                if (mail.charAt(t) >= 48 && mail.charAt(t) <= 57 || mail.charAt(t) >= 65 && mail.charAt(t) <= 90 || mail.charAt(t) >= 97 && mail.charAt(t) <= 122 || mail.charAt(t) == 46 || mail.charAt(t) == 45 || mail.charAt(t) == 95) {
                    
                }else if(mail.charAt(t) == 64){
                    t1=true;
                    for (int p = 1+t; p < mail.length(); p++) {
                      
                        if (mail.charAt(p) >= 48 && mail.charAt(p) <= 57 || mail.charAt(p) >= 65 && mail.charAt(p) <= 90 || mail.charAt(p) >= 97 && mail.charAt(p) <= 122 ||  mail.charAt(p) == 45 || mail.charAt(p) == 95) {
                           
                        } else if(mail.charAt(p) == 46){
                            t2 = true;
                            for (int k = 1+p; k < mail.length(); k++) {
                                
                                if ( mail.charAt(k) >= 65 && mail.charAt(k) <= 90 || mail.charAt(k) >= 97 && mail.charAt(k) <= 122 ) {
                           
                                }else{
                                    return false;
                                }
                            }

                        }else {
                            return false;
                        }
                        
                    }
                }else{
                       return false;
                
               }

            }
            if( t1 && t2){
            
            return true;
            }else {
                return false;
            }
        }
    }
