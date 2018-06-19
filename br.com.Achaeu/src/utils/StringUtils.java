/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author jonathan
 */
public class StringUtils {

    public static String getTextFromCharArray(char[] array) {
        String retorno = "";
        for (char c : array) {
            retorno += c;
        }
        return retorno;
    }

}
