/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author jonathan
 */
public class MenuComposer {

    public static JMenuBar Compose() {
        JMenuBar mainMenu = new JMenuBar();
        JMenu mnuUsuario = new javax.swing.JMenu();
        mnuUsuario.setText("Usuário");

        mainMenu.add(mnuUsuario);
        
        return mainMenu;
    }

}
