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
public class Buraco {

    private int xb, yb;
    private int dxb, dyb;
    static ImageIcon referencia8 = new ImageIcon("Res/buraco.png");
    static ImageIcon referencia11 = new ImageIcon("Res/betoneira.png");
    private Image buraco, buracob;
    private int alturab, largurab;

    public Buraco() {
        buraco = referencia8.getImage();
        buracob = referencia11.getImage();
        alturab = buraco.getHeight(null);
        largurab = buraco.getWidth(null);
        this.xb = 2253;
        this.yb = 575;
    }

    public void mexer() {
        xb += dxb;
        yb += dyb;
    }

    public Image getImagem4() {
        return buraco;
    }

    public Image getImagem6() {
        return buracob;
    }

    public Rectangle getBoundsburaco(int x) {
        return new Rectangle(x + 80, yb, largurab - 135, alturab);
    }

    public int getYb() {
        return yb;
    }

    public int getXb() {
        return xb;
    }

    public void setDxb(int dxb) {
        this.dxb = dxb;
    }

    public void setXb(int xb) {
        this.xb = xb;
    }

    public void KeyPressed(KeyEvent tecla, boolean m1, boolean m2, boolean m3) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dxb = -15;
        }
    }

    public void KeyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dxb = 0;
        }

    }
}
