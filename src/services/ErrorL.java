/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.HashMap;

/**
 *
 * @author Steph
 */
public class ErrorL {
    private static ErrorL singleton;
    private static HashMap map;
    
    /**
     * Liste erreur 
     * 1 : "Un des arguments est vide"
     */
    private ErrorL(){
        map = new HashMap();
        map.put(1,"Un des arguments est vide");
    }
    
    /**
     *
     */
    public static String getErrorL(int i){
        if(singleton==null){
            singleton=new ErrorL();
        }
        return (String) map.get(i);
    }                
}
