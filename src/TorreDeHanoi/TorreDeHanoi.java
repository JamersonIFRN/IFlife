package TorreDeHanoi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

public class TorreDeHanoi extends JFrame implements ActionListener {

    private static Fase fase;
    private static Fase2 fase2;
    private static Fase3 fase3;
    private static Fase4 fase4;
    private static Fase5 fase5;
    private Timer tempo;
    int i, seg = 0, min = 0;
    JMenuBar barraMenu = new JMenuBar();
    JMenu itemMenu = new JMenu("Jogo");
    JMenuItem subItemMenuReiniciar = new JMenuItem("Reiniciar");
    JMenuItem subItemMenuSair = new JMenuItem("Sair");

    public TorreDeHanoi(int i) {

        this.i = i;
        if (i == 1) {
            System.out.println("entrou");
            fase = new Fase();
            fase.setBounds(0, 0, 750, 700);
        } else if (i == 2) {
            fase2 = new Fase2();
            fase2.setBounds(0, 0, 750, 700);
        } else if (i == 3) {
            fase3 = new Fase3();
            fase3.setBounds(0, 0, 750, 700);
        } else if (i == 4) {
            fase4 = new Fase4();
            fase4.setBounds(0, 0, 750, 700);
        } else if (i == 5) {
            fase5 = new Fase5();
            fase5.setBounds(0, 0, 750, 700);
        }
        setLayout(null);
        setTitle("Torre de Hanoi");
        setSize(750, 605);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        itemMenu.add(subItemMenuReiniciar);
        itemMenu.add(subItemMenuSair);
        subItemMenuReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciar();
            }
        });
        subItemMenuSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });
        barraMenu.add(itemMenu);
        setJMenuBar(barraMenu);
        if (i == 1) {
            add(fase);
        } else if (i == 2) {
            add(fase2);
        } else if (i == 3) {
            add(fase3);
        } else if (i == 4) {
            add(fase4);
        } else if (i == 5) {
            add(fase5);
        }
        tempo = new Timer(1000, this);
        tempo.start();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new TorreDeHanoi(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String temp = "";
        if (i == 1) {
            temp = fase.getTempo();
            if (!fase.isEmJogo()) {
                fase = null;
                sair();
            }
        } else if (i == 2) {
            temp = fase2.getTempo();
            if (!fase2.isEmJogo()) {
                fase2 = null;
                sair();
            }
        } else if (i == 3) {
            temp = fase3.getTempo();
            if (!fase3.isEmJogo()) {
                fase3 = null;
                sair();
            }
        } else if (i == 4) {
            temp = fase4.getTempo();
            if (!fase4.isEmJogo()) {
                fase4 = null;
                sair();
            }
        } else if (i == 5) {
            temp = fase5.getTempo();
            if (!fase5.isEmJogo()) {
                fase5 = null;
                sair();
            }
        }
        seg++;
        if (seg < 10) {
            temp = temp.substring(0, 4) + seg;
        } else {
            temp = temp.substring(0, 3) + seg;
        }
        if (seg == 60) {
            temp = temp.substring(0, 3) + "00";
            seg = 0;
            min++;
        }
        if (min < 10) {
            temp = "0" + min + temp.substring(2, 5);
        } else if (min == 15) {
        } else {
            temp = min + temp.substring(2, 5);
        }
        if (i == 1) {
            if (fase != null) {
                fase.setTempo(temp);
            }
        } else if (i == 2) {
            if (fase != null) {
                fase.setTempo(temp);
            }
        } else if (i == 3) {
            if (fase != null) {
                fase3.setTempo(temp);
            }
        } else if (i == 4) {
            if (fase != null) {
                fase4.setTempo(temp);
            }
        } else if (i == 5) {
            if (fase != null) {
                fase5.setTempo(temp);
            }
        }
    }

    private void reiniciar() {
        if (i == 1) {
            fase = null;
            fase = new Fase();
            add(fase);
        } else if (i == 2) {
            fase2 = null;
            fase2 = new Fase2();
        } else if (i == 3) {
            fase3 = null;
            fase3 = new Fase3();
        } else if (i == 4) {
            fase4 = null;
            fase4 = new Fase4();
        } else if (i == 5) {
            fase5 = null;
            fase5 = new Fase5();
        }
    }

    private void sair() {
        tempo.stop();
        tempo = null;
        TelaInicial t = new TelaInicial();
        t.setVisible(true);
        this.dispose();
    }
}
