package Virus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Corpo2 extends JFrame implements ActionListener, MouseListener {

    public Fase2 F2 = new Fase2();
    JMenuBar barraMenu = new JMenuBar();
    JMenu itemMenu = new JMenu("Jogo");
    JMenuItem subItemMenuSair = new JMenuItem("Sair");
    JMenuItem subItemMenuReiniciar = new JMenuItem("Reiniciar");

    public Corpo2() {
        addMouseListener(this);
        barraMenu.add(itemMenu);
        itemMenu.add(subItemMenuReiniciar);
        subItemMenuReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                F2.setContador(100);
            }
        });
        itemMenu.add(subItemMenuSair);

        subItemMenuSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                F2.Timer1.stop();
                F2.TimerTempo.stop();
                TelaInicial t = new TelaInicial();
                t.setVisible(true);
                dispose();
            }
        });
        setJMenuBar(barraMenu);

        F2.setBounds(0, 25, 750, 700);

        setLayout(null);
        setTitle("Elimine os virus");
        setSize(750, 725);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        add(F2);
        add(F2.BarraVida);


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Corpo2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
