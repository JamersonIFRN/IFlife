package Tutoria;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase1 extends JPanel implements ActionListener {

    public Personagem tutor = new Personagem();
    private Timer timer;
    private int x = 500, y = 407;

    public Fase1() {
    }

    public void iniciar() {
        setFocusable(true);
        setDoubleBuffered(true);
        timer = new Timer(35, this);
        timer.start();
    }

    public void setAno(String ano) {
        tutor.setAno(ano);
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        setFocusable(true);
        setDoubleBuffered(true);
        graficos.drawImage(tutor.getImagem2(), tutor.getXm(), tutor.getYm(), this);
        graficos.drawImage(tutor.getImagem(), tutor.getX(), tutor.getY(), this);
        graficos.drawImage(tutor.getImagem3(), tutor.getX1(), tutor.getY1(), this);
        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tutor.mexer();
        repaint();
    }
}
