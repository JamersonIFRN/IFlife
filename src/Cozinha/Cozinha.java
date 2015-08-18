package Cozinha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Cozinha extends JFrame {

    public Fase F = new Fase();
    boolean terminou = false, inicio;
    JMenuBar barraMenu = new JMenuBar();
    JMenu itemMenu = new JMenu("Jogo");
    JMenuItem subItemMenuSair = new JMenuItem("Sair");
    JMenuItem subItemMenuReiniciar = new JMenuItem("Reiniciar");

    public Cozinha(boolean inicio) {
        this.inicio = inicio;
        F.setIinicio(inicio);
        if (inicio) {
            iniciar();
            setVisible(true);
            barraMenu.add(itemMenu);
            itemMenu.add(subItemMenuReiniciar);
            subItemMenuReiniciar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    F.reiniciar();
                }
            });

            itemMenu.add(subItemMenuSair);
            subItemMenuSair.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    F.fim();
                    TelaInicial t = new TelaInicial();
                    t.setVisible(true);
                    dispose();
                }
            });
            setJMenuBar(barraMenu);
        }
    }

    public void iniciar() {
        F.setBounds(0, 25, 750, 700);

        setLayout(null);
        setTitle("Cozinha Do Seu Francisco");
        setSize(750, 725);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());

        add(F);
        add(F.BarraVida);
    }

    public boolean getTerminou() {
        return terminou;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Cozinha(true);
    }
}
