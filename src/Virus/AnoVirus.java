/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Virus;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author DANIEL
 */
public class AnoVirus {

    boolean terminou, doente = true;
    String ano;
    Timer t;
    public final TelaVirus te = new TelaVirus();

    public AnoVirus() {
        String arquivo = "Res/Dados.txt", linha = "", f = "";
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                    if (!linha.equals("")) {
                        f += linha + "\n";
                    }
                }
                f = f.substring(0, f.length() - 1);
            } catch (IOException ex) {
                Logger.getLogger(AnoVirus.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AnoVirus.class.getName()).log(Level.SEVERE, null, ex);
        }
        ano = linha;

        arquivo = "Res/Dados/Virus.txt";
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(AnoVirus.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AnoVirus.class.getName()).log(Level.SEVERE, null, ex);
        }
        String anosvirus[] = linha.split("-");
        if (ano.equals(anosvirus[0]) || ano.equals(anosvirus[1])) {
            ActionListener action;

            te.iniciar();
            te.setVisible(true);
            action = new ActionListener() {
                public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                    if (te.C.F.isEmTerminou()) {
                        terminou = true;
                        te.C.F.TimerJogo.stop();
                        te.C.dispose();
                        te.dispose();
                    }
                }
            };
            t = new Timer(50, action);
            t.start();
        } else {
            doente = false;
            terminou = true;
        }
    }

    public boolean getTerminou() {
        return terminou;
    }

    public boolean getDoente() {
        return doente;
    }

    public void pararTempo() {
        t.stop();
        t = null;
    }

    public static void main(String[] args) {
        new AnoVirus();
    }
}
