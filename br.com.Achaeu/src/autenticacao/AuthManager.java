/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacao;

import java.util.prefs.Preferences;

/**
 *
 * @author jonathan
 */
public class AuthManager {

    private static final String LOGGED_USER = "logged_user";

    public static boolean IsUserLogged() {
        Preferences prefs = Preferences.userNodeForPackage(br.com.achaeu.BrComAchaeu.class);
        
        int usuarioLogado = prefs.getInt(LOGGED_USER, 0);
        return usuarioLogado > 0;
    }
}
