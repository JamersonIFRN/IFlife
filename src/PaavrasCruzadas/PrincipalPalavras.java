/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PaavrasCruzadas;

import CacaPalavras.*;
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
public class PrincipalPalavras {

    public PrincipalPalavras() {
        String arquivo = "Res/JogosExtras/Palavras Cruzadas/contadorpalavrasc.txt", linha = "", f = "", line;
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
        arquivo = "Res/JogosExtras/PAlavras Cruzadas/palavrasc.txt";
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
            Palavras_Cruzadas pa = new Palavras_Cruzadas(caça[0]);
            pa.setVisible(true);
        }
        if (linha.equals("2")) {
            Palavras_Cruzadas pa = new Palavras_Cruzadas(caça[1]);
            pa.setVisible(true);
        }
        if (linha.equals("3")) {
            Palavras_Cruzadas pa = new Palavras_Cruzadas(caça[2]);
            pa.setVisible(true);
        }
        if (linha.equals("4")) {
            Palavras_Cruzadas pa = new Palavras_Cruzadas(caça[3]);
            pa.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PrincipalPalavras();
    }
}
