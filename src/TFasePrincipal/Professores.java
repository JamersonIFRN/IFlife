/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TFasePrincipal;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author DANIEL
 */
public class Professores {

    private int xp, yp;
    private int dxp, dyp;
    private int alturap, largurap;
    static ImageIcon referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/Padrão.png");
    private Image professor;
    private Image[] profs = new Image[10];

    public Professores() {
        professor = referencia7.getImage();
        alturap = professor.getHeight(null);
        largurap = professor.getWidth(null);
        this.xp = 1000;
        this.yp = 270;
    }

    public void setmaterias(String materias[]) {
        for (int i = 0; i < materias.length; i++) {
            if (materias[i].equalsIgnoreCase("Matemática")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/matematica.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Física")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/fisica.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Português")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/portugues.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Ed. Fisica")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/Ed.Fisica.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Artes I") || materias[i].equalsIgnoreCase("Artes II")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/Artes.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Sooiologia")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/Sociologia.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Espanhol")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/Espanhol.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Inglês")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/ingles.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Geografia")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/Geografia.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("História")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/Historia.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Filosofia")) {
                System.out.println("ahhhh" + materias[i]);
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/Filosofia.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Química")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/quimica.png");
                profs[i] = referencia7.getImage();
            } else if (materias[i].equalsIgnoreCase("Biologia")) {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/biologia.png");
                profs[i] = referencia7.getImage();
            } else {
                referencia7 = new ImageIcon("Res/Criação dos professores e dos personagens/Padrão.png");
                profs[i] = referencia7.getImage();
            }
        }
    }

    public Image[] getProfs() {
        return profs;
    }

    public int getNumProfs() {
        return profs.length;
    }

    public int getLargp() {
        return largurap;
    }

    public int getAltp() {
        return alturap;
    }

    public void mexer() {
        xp += dxp;
        yp += dyp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setDxp(int dxp) {
        this.dxp = dxp;
    }

    public int getXp() {
        return xp;
    }

    public int getYp() {
        return yp;
    }

    public Image getImagem3() {
        return professor;
    }

    public Rectangle getBoundsprofessor(int x) {
        return new Rectangle(x + 370, yp, largurap, alturap);

    }

    public void KeyPressed(KeyEvent tecla, boolean m1, boolean m2, boolean m3) {
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dxp = -15;
        }
    }

    public void KeyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dxp = 0;
        }
    }
}
