/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CacaPalavras;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANIEL
 */
public class Principalcaça {

    public Principalcaça() {
        String arquivo = "Res/JogosExtras/CacaPalavras/contadorcaca.txt", linha = "", f = "", line;
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(PrincipalCaca.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalCaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        arquivo = "Res/JogosExtras/CacaPalavras/caça.txt";
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    line = "";
                    line += br.readLine();
                    if (!line.equals("")) {
                        f += line + "\n";
                    }
                }
                f = f.substring(0, f.length() - 1);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalCaca.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalCaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        String caça[] = f.split("\n");
        if (linha.equals("1")) {
            CaçaPalavras ca = new CaçaPalavras(caça[0], 0);
            ca.setVisible(true);
        }
        if (linha.equals("2")) {
            CaçaPalavras ca = new CaçaPalavras(caça[1], 1);
            ca.setVisible(true);
        }
        if (linha.equals("3")) {
            CaçaPalavras ca = new CaçaPalavras(caça[2], 2);
            ca.setVisible(true);
        }
        if (linha.equals("4")) {
            CaçaPalavras ca = new CaçaPalavras(caça[3], 3);
            ca.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Principalcaça();
    }
}
