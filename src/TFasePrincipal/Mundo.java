/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TFasePrincipal;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author DANIEL
 */
public class Mundo {

    private int xm, ym;
    private int dxm, dym;
    static ImageIcon referencia2 = new ImageIcon("Res/FUNDOAmarelo.png");
    private Image mundo;
    ImagemMasculinoOuFeminino img = new ImagemMasculinoOuFeminino();

    public Mundo() {
        IniciarImages();
        mundo = referencia2.getImage();
        this.xm = 0;
        this.ym = -30;
    }

    public void mexer() {
        xm += dxm;
        ym += dym;
    }

    public void setXm(int xm) {
        this.xm = xm;
    }

    public void setDxm(int dxm) {
        this.dxm = dxm;
    }

    public int getXm() {
        return xm;
    }

    public int getYm() {
        return ym;
    }

    public Image getImagem2() {
        return mundo;
    }

    private void IniciarImages() {
        img.setarJogo("fundo");
        referencia2 = new ImageIcon(img.getCaminho());
    }

    public void KeyPressed(KeyEvent tecla, boolean m1, boolean m2, boolean m3) {
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dxm = -15;
        }
    }

    public void KeyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dxm = 0;
        }
    }
}
