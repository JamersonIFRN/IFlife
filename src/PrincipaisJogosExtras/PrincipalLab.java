/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrincipaisJogosExtras;

import JogosEFasesExtras.Lab;
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
public class PrincipalLab {

    public PrincipalLab() {
        String arquivo = "Res/Dados.txt", linha = "", f = "", line;
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(PrincipalLab.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalLab.class.getName()).log(Level.SEVERE, null, ex);
        }
        arquivo = "Res/Labirinto.txt";
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
                Logger.getLogger(PrincipalLab.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalLab.class.getName()).log(Level.SEVERE, null, ex);
        }
        String labirinto[] = f.split("\n");
        //Alterar posicao vetor labirinto 0,1,2,3
        if (linha.equals("1")) {

            Lab l = new Lab(labirinto[0], "1");
            l.setVisible(true);
        }
        if (linha.equals("2")) {
            Lab l = new Lab(labirinto[1], "2");
            l.setVisible(true);
        }
        if (linha.equals("3")) {
            Lab l = new Lab(labirinto[2], "3");
            l.setVisible(true);
        }
        if (linha.equals("4")) {
            Lab l = new Lab(labirinto[3], "4");
            l.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PrincipalLab();
    }
}
