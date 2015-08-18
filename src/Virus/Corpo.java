package Virus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Corpo extends JFrame implements ActionListener, MouseListener {

    private int Contador = 0;
    public Fase F = new Fase();
    private static JProgressBar BarraVida = new JProgressBar(0, 111);
    private Timer TimerBarra;
    JMenuBar barraMenu = new JMenuBar();
    JMenu itemMenu = new JMenu("Jogo");
    JMenuItem subItemMenuSair = new JMenuItem("Sair");
    JMenuItem subItemMenuReiniciar = new JMenuItem("Reiniciar");

    public Corpo() {
        F.Iniciar();
        addMouseListener(this);
        barraMenu.add(itemMenu);
        itemMenu.add(subItemMenuReiniciar);
        subItemMenuReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contador = 0;
                F.setEmJogo(true);
                F.setContador(20);
            }
        });
        itemMenu.add(subItemMenuSair);
        subItemMenuSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                F.TimerJogo.stop();
                TelaInicial t = new TelaInicial();
                t.setVisible(true);
                dispose();
            }
        });
        setJMenuBar(barraMenu);
        BarraVida.setBackground(Color.red);
        BarraVida.setForeground(Color.yellow);
        BarraVida.setName("Vida");
        BarraVida.setBounds(0, 0, 750, 25);
        F.setBounds(0, 25, 750, 700);

        setLayout(null);
        setTitle("Elimine os virus");
        setSize(750, 725);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        add(F);
        add(BarraVida);
        TimerBarra = new Timer(1000, this);
        TimerBarra.start();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Corpo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Contador >= 111) {
            //F.setEmJogo(false);
        }
        if (F.isEmJogo()) {
            Contador += 3;
            BarraVida.setValue(Contador);
            BarraVida.repaint();
        }
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }
}
