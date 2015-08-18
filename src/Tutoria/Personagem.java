package Tutoria;

import TFasePrincipal.ImagemMasculinoOuFeminino;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Personagem {

    int x, y, z, x1, y1;
    int dx = 10, dy;
    int xm, ym;
    private boolean cl = true;
    static ImageIcon referencia = new ImageIcon("Res/PersonagenDuvidas.gif");
    static ImageIcon referencia1;
    static ImageIcon referencia3 = new ImageIcon("Res/PersonagenDuvidas1.png");
    static ImageIcon referencia2 = new ImageIcon("Res/2015-02-25 09.jpg");
    private Image personagemj, mundo, personagemj1;
    String materia;
    public TelaDePergunta t = new TelaDePergunta();

    public Personagem() {
        ImagemMasculinoOuFeminino img = new ImagemMasculinoOuFeminino();
        img.setarJogo("tutoria");
        referencia1 = new ImageIcon(img.getCaminho());
        materia();
        mundo = referencia2.getImage();
        personagemj = referencia.getImage();
        personagemj1 = referencia1.getImage();
        this.x = 800;
        this.y = 363;
        this.x1 = 10;
        this.y1 = 363;
        this.z = 0;
        this.xm = 0;
        this.ym = -30;

    }
    ActionListener action = new ActionListener() {
        public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
            if (y <= 650) {
                y++;
            }
        }
    };

    public void setAno(String ano) {
        t.setAno(materia, ano);

    }

    public void mexer() {
        if (x >= 220) {
            x -= dx;
        } else if (cl) {
            cl = false;
            personagemj = referencia3.getImage();

            t.setvisevel(true);
        }
    }

    public int getDX() {
        return dx;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getXm() {
        return xm;
    }

    public int getYm() {
        return ym;
    }

    public Image getImagem() {
        return personagemj;
    }

    public Image getImagem3() {
        return personagemj1;
    }

    public Image getImagem2() {
        return mundo;
    }

    private void materia() {
        String caminhoarquivo = "Res/Tutoria/Materia.txt", linha = "";
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
                Logger.getLogger(Personagem.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Personagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        materia = linha;
    }
}
