package services.auth;

import bd.auth.ExampleAuthBD;
import java.util.LinkedList;
import org.json.JSONObject;
import services.ControlString;
import services.ErrorL;
import services.ServicesTools;


    /**
 * La class NewUser permet de controler les informations envoye par le client
 * <br> demander a une methode l'enregistrement dans la base et renvois un
 * message JSON
 */
public class NewUserService {

    public String login;
    public String pass;
    public String name;
    public String mail;
    public String fname;

    /**
     * Constructeur
     */
    public NewUserService(String login, String pass, String name, String fname, String mail) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.fname = fname;
        this.mail = mail;
    }

    /**
     * methode principal qui effectue tous les test
     */
    public JSONObject service() {
        LinkedList<String> test = new LinkedList<String>();
        test.add(login);
        test.add(pass);
        test.add(name);
        test.add(fname);
        test.add(mail);

        if(!ControlString.controlString(test)) {
            return ServicesTools.erreur(ErrorL.getErrorL(1), 1);
        }else if (!ControlString.isEmail(mail)){
            System.out.println(mail);
            return ServicesTools.erreur(ErrorL.getErrorL(9), 9);
        }
        else if (ExampleAuthBD.userExist(login)){
            return ServicesTools.erreur(ErrorL.getErrorL(10), 10);
        }       
        else if (ExampleAuthBD.addUser(login, pass, name, fname, mail)){
            return ServicesTools.ok();
        }
        else {
            return ServicesTools.erreur(ErrorL.getErrorL(11), 11);
        }
    }

    private boolean existPSql() {
        return false;
    }

    /**
     * methode qui appel la classe de jdbc pour cree les login renvois false en
     * cas d'erreur
     */

}