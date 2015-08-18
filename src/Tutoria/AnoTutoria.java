/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tutoria;

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
public class AnoTutoria {

    boolean terminou = false;
    String ano = "";
    public Timer t;
    int i = 0;
    public final BemVindoTutoria te = new BemVindoTutoria();

    public AnoTutoria() {
        String arquivo = "Res/Dados.txt", linha, f = "";
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
                Logger.getLogger(ProvaTutoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProvaTutoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        String texto[] = f.split("\n");
        ano = texto[4];
        if (ano.equals("1")) {
            PrimeiroAno();
        } else if (ano.equals("2")) {
            SegundoAno();
        } else if (ano.equals("3")) {
            TerceiroAno();
        } else if (ano.equals("4")) {
            QuartoAno();
        }
    }

    public boolean getTerminou() {
        return terminou;
    }

    public static void main(String[] args) {
        new AnoTutoria();
    }

    public void pararTempo() {
        if (t != null) {
            t.stop();
        }
        t = null;
    }

    private void PrimeiroAno() {
        final EscolhaTutoria esc = new EscolhaTutoria();
        esc.setVisible(true);
        ActionListener action;
        action = new ActionListener() {
            public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                if (esc.p.getTerminou()) {
                    esc.p.dispose();
                    esc.dispose();
                    terminou = true;
                }
            }
        };
        t = new Timer(50, action);
        t.start();
    }

    private void SegundoAno() {
        if (passou()) {
            ActionListener action;
            te.iniciar("2");
            te.setVisible(true);
            action = new ActionListener() {
                public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                    if (te.te.x.tutor.t.cont) {
                        terminou = true;
                        te.te.x.tutor.t.dispose();
                        te.te.dispose();
                        te.dispose();
                    }
                }
            };
            t = new Timer(50, action);
            t.start();
        } else {
            terminou = true;
        }
    }

    private void TerceiroAno() {
        if (passou()) {
            ActionListener action;
            te.iniciar("3");
            te.setVisible(true);
            action = new ActionListener() {
                public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                    if (te.te.x.tutor.t.cont) {
                        terminou = true;
                        te.te.x.tutor.t.dispose();
                        te.te.dispose();
                        te.dispose();
                    }
                }
            };
            t = new Timer(50, action);
            t.start();
        } else {
            terminou = true;
        }
    }

    private void QuartoAno() {
        if (passou()) {
            ActionListener action;
            te.iniciar("4");
            te.setVisible(true);
            action = new ActionListener() {
                public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                    if (te.te.x.tutor.t.cont) {
                        terminou = true;
                        te.te.x.tutor.t.dispose();
                        te.te.dispose();
                        te.dispose();
                    }
                }
            };
            t = new Timer(50, action);
            t.start();
        } else {
            terminou = true;
        }
    }

    private boolean passou() {
        String caminhoarquivo = "Res/Tutoria/Passou.txt", linha = "";
        FileReader fr;
        try {
            fr = new FileReader(caminhoarquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                }
                br.close();
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(AnoTutoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AnoTutoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (linha.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
}
