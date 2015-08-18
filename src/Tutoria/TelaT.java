package Tutoria;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class TelaT extends JFrame {

    public Fase1 x = new Fase1();
    Timer timer;
    Timer t;

    public TelaT() {
    }

    public void iniciar() {
        x.iniciar();
        add(x);
        //this.setUndecorated(true);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Tutoria");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        setSize(805, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
    }
    int i = 0;

    public void setAno(String ano) {
        x.setAno(ano);
    }

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {
        setCursor(Cursor.DEFAULT_CURSOR);
        if (i == 0) {
            t = new Timer(3000, action);
            t.start();
            i++;
        }
    }
    ActionListener action = new ActionListener() {
        public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
            Image cursorImage = Toolkit.getDefaultToolkit().getImage("xparent.gif");
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "");
            setCursor(blankCursor);
            PararT();
        }
    };

    public void PararT() {
        i = 0;
        timer = null;
        t = null;
    }

    public static void main(String[] args) {
        new TelaT();
    }
}
