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
public class PortaDaTutoria {

    private int xt, yt;
    private int dxt, dyt;
    private int alturat, largurat;
    static ImageIcon referencia10 = new ImageIcon("Res/porta.png");
    private Image porta;

    public PortaDaTutoria() {
        porta = referencia10.getImage();
        largurat = porta.getWidth(null);
        alturat = porta.getHeight(null);
        this.xt = 28666;
        this.yt = 375;
    }

    public void mexer() {
        xt += dxt;
        yt += dyt;
    }

    public void setXt(int xt) {
        this.xt = xt;
    }

    public void setDxt(int dxt) {
        this.dxt = dxt;
    }

    public int getXt() {
        return xt;
    }

    public int getYt() {
        return yt;
    }

    public Image getImagemTutoria() {
        return porta;
    }

    public Rectangle getBoundstutoria(int x) {
        return new Rectangle(x, yt, largurat, alturat);
    }

    public void KeyPressed(KeyEvent tecla, boolean m1, boolean m2, boolean m3) {
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dxt = -15;
        }
    }

    public void KeyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dxt = 0;
        }
    }
}
