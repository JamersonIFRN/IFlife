package Tutoria;


import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Personagem1 {

    int x, y, z;
    int dx, dy;
    int xm, ym;
    private int dxm, dym;
    private boolean cl = true;
    static ImageIcon referencia = new ImageIcon("Res/mariop.gif");
    static ImageIcon referencia2 = new ImageIcon("Res/terra do jogo.png");
    static ImageIcon referencia3 = new ImageIcon("Res/mario.gif");
    static ImageIcon referencia4 = new ImageIcon("Res/marioc.gif");
    static ImageIcon referencia5 = new ImageIcon("Res/mariocp.gif");
    static ImageIcon referencia6 = new ImageIcon("Res/mariopular.gif");
    private Image personagemj, mundo;
    private Timer timer;

    public Personagem1() {

        mundo = referencia2.getImage();
        personagemj = referencia.getImage();

        this.x = -100;
        this.y = 310;
        this.z = 0;
        this.xm = 0;
        this.ym = -30;
    }
    ActionListener action = new ActionListener() {
        public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
            if(y<=650){
            y++;
            }
        }
    };

    public void mexer() {
        if (cl) {
            x += dx;
            //("y : " + dy + " x " + dx);
            if (y <= 310) {
                y += dy;
            }
            xm += dxm;
            ym += dym;

            if (this.x < -100) {
                x = -100;
            }

            if (this.xm > -28910) {
                if (this.x > 330) {
                    x = 330;
                }

            } else {
                if (this.x > 750) {
                    x = 750;
                }
            }

            if (this.y < -80) {
                y = - 80;
            }

            if (this.y > 310) {
                y = 310;
            }

            if (this.xm < -28920) {
                xm = -28920;
            }
            if ((xm <= -1260)) {
                z += dx;
            }
            //("xm " + xm + " z " + z);
            if ((xm <= -1260 && y == 310) && (z <= 250 && y == 310)) {
                cl = false;
                timer = new Timer(1, action);
                timer.start();
                //("ihhh caiu, no????");
            } else if (z <= 270) {
                //("ahjjjj " + z);
            }
            //(xm + "  " + ym);
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

    public int getXm() {
        return xm;
    }

    public int getYm() {
        return ym;
    }

    public Image getImagem() {
        return personagemj;
    }

    public Image getImagem2() {
        return mundo;
    }

    public void KeyPressed(KeyEvent tecla) {

        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE && cl) {
            dy = -10;
            personagemj = referencia6.getImage();
        }
        //if(codigo == KeyEvent.VK_S){
        // dy = 10;
        //}
        if (codigo == KeyEvent.VK_A) {
            dx = -10;
            personagemj = referencia4.getImage();
        }
        if (codigo == KeyEvent.VK_D) {
            dx = 10;
            personagemj = referencia3.getImage();
            if (x >= 300) {
                dxm = -15;
            }
        }
        if (codigo == KeyEvent.VK_P) {
            if(cl==true){
            cl = false;
            }else{
                cl=true;
            }
        }
    }

    public void KeyReleased(KeyEvent tecla) {

        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE && cl) {
            dy = 20;
            personagemj = referencia.getImage();
        }
        //if(codigo == KeyEvent.VK_S){
        //   dy = 0;
        // }
        if (codigo == KeyEvent.VK_A) {
            dx = 0;
            personagemj = referencia5.getImage();
        }
        if (codigo == KeyEvent.VK_D && cl) {
            personagemj = referencia.getImage();
            dx = 0;
            dxm = 0;
        }

    }
}
