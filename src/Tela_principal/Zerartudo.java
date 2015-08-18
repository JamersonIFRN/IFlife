/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tela_principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANIEL
 */
public class Zerartudo {

    public Zerartudo() {
    }

    public void zerar() {
        apagar("Res/Dados.txt", "");
        apagar("Res/Save.txt", "");
        apagar("Res/Tutoria/Materia.txt", "");
        apagar("Res/Tutoria/Passou.txt", "false");
        apagar("Res/Dados/NumeroBonus.txt", "3@3");
        apagar("Res/Dados/ProximoBonus.txt", "memoria1");
        apagar("Res/Dados/Virus.txt", "");
        apagar("Res/JogosExtras/CacaPalavras/Recordes.txt", "1800-CPU\n"
                + "\n"
                + "1600-CPU\n"
                + "\n"
                + "1400-CPU\n"
                + "\n"
                + "1200-CPU\n"
                + "\n"
                + "1000-CPU");
        apagar("Res/JogosExtras/CacaPalavras/contadorcaca", "1");
        apagar("Res/JogosExtras/Forca/Recordes.txt", "1800-CPU\n"
                + "\n"
                + "1600-CPU\n"
                + "\n"
                + "1400-CPU\n"
                + "\n"
                + "1200-CPU\n"
                + "\n"
                + "1000-CPU");
        apagar("Res/JogosExtras/Palavras Cruzadas/contadorpalavrasc", "1");
    }

    public void ZerarExtras() {
        apagar("Res/Dados/JogosDesbloqueados.txt", "");
    }

    private void apagar(String endereço, String texto) {
        FileWriter fw;
        try {
            fw = new FileWriter(endereço);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Zerartudo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Zerartudo();
    }
}
