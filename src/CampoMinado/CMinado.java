package CampoMinado;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class CMinado extends JFrame {

    private JLabel[] quadrados = new JLabel[81];
    private int numerodosquadrados[] = new int[81];
    private JPanel panel = new JPanel();
    private JPanel panelrelogio = new JPanel();
    private JLabel relogio = new JLabel();
    private JLabel tempo = new JLabel("00:00");
    Timer timertempo;
    ActionListener action;
    int seg = 0, min = 0;
    RodarMusica r;

    public CMinado() {
        Inicializar();
        setTitle("Campo Minado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(745, 460);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        panel.setVisible(true);
        panel.setBackground(Color.white);
        add(panel);
        panel.setBounds(15, 15, 500, 395);
        panel.setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 5));
        tempo.setBounds(40, 41, 100, 100);
        tempo.setFont(new java.awt.Font("LCDMono2", 1, 32));
        tempo.setBorder(new javax.swing.border.MatteBorder(null));
        panelrelogio.add(tempo);
        relogio.setIcon(new ImageIcon("Res/system2.png"));
        relogio.setBounds(5, 0, 180, 180);
        panelrelogio.add(relogio);
        panelrelogio.setVisible(true);
        panelrelogio.setBackground(Color.white);
        add(panelrelogio);
        panelrelogio.setBounds(530, 30, 180, 180);
        panelrelogio.setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 3));
        panelrelogio.setLayout(null);
        action = new ActionListener() {
            public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                seg++;
                if (seg < 10) {
                    tempo.setText(tempo.getText().substring(0, 4) + seg);
                } else {
                    tempo.setText(tempo.getText().substring(0, 3) + seg);
                }
                if (seg == 60) {
                    tempo.setText(tempo.getText().substring(0, 3) + "00");
                    seg = 0;
                    min++;
                }
                if (min < 10) {
                    tempo.setText("0" + min + tempo.getText().substring(2, 5));
                } else if (min == 15) {
                } else {
                    tempo.setText(min + tempo.getText().substring(2, 5));
                }

            }
        };
        timertempo = new Timer(1000, action);
        timertempo.start();

    }

    private void Inicializar() {
        panel.setLayout(new GridLayout(9, 9));
        for (int i = 0; i < quadrados.length; i++) {
            quadrados[i] = new JLabel("", JLabel.CENTER);
            quadrados[i].setBackground(Color.green);
            quadrados[i].setOpaque(true);
            quadrados[i].setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 2));
            quadrados[i].setFont(new Font("Arial", 1, 47));
            panel.add(quadrados[i]);
            final int u = i;
            quadrados[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        if (numerodosquadrados[u] == 1) {
                            r = new RodarMusica("Res\\explosão.mp3");
                            perdeu();
                        } else {
                            if (quadrados[u].getBackground() == Color.green) {
                                r = new RodarMusica("Res\\abrir.mp3");
                                quadrados[u].setBackground(Color.white);
                                BombasAoRedor(u);
                            }
                        }
                    } else {
                        if (quadrados[u].getBackground() != Color.white) {
                            if (quadrados[u].getBackground() == Color.green) {
                                quadrados[u].setBackground(Color.red);

                            } else {
                                quadrados[u].setBackground(Color.green);
                            }
                        }
                    }
                    ganhou();
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
            });
        }
        for (int i = 0; i < 81; i++) {
            numerodosquadrados[i] = 0;
        }
        for (int i = 0; i < 10; i++) {
            boolean sorteou = false;
            while (!sorteou) {
                Random r = new Random();
                int j;
                j = r.nextInt(81);
                if (numerodosquadrados[j] != 1) {
                    sorteou = true;
                    numerodosquadrados[j] = 1;
                }
            }
        }
        for (int i = 0; i < 81; i += 9) {
            System.out.println(numerodosquadrados[i] + " " + numerodosquadrados[i + 1] + " " + numerodosquadrados[i + 2] + " " + numerodosquadrados[i + 3] + " " + numerodosquadrados[i + 4] + " " + numerodosquadrados[i + 5] + " " + numerodosquadrados[i + 6] + " " + numerodosquadrados[i + 7] + " " + numerodosquadrados[i + 8]);
        }
    }

    public void ganhou() {
        int cont = 0;
        for (int i = 0; i < 81; i++) {
            if (quadrados[i].getBackground() == Color.green || quadrados[i].getBackground() == Color.red) {
                cont++;
            }
        }
        if (cont == 10) {
            timertempo.stop();
            JOptionPane.showMessageDialog(null, "Você ganhou!");
            TelaInicial t = new TelaInicial();
            t.setVisible(true);
            dispose();
        }
    }

    public void perdeu() {
        for (int i = 0; i < 81; i++) {
            if (numerodosquadrados[i] == 1) {
                quadrados[i].setIcon(new ImageIcon("Res\\bomba.png"));
            }
        }
        timertempo.stop();
        JOptionPane.showMessageDialog(null, "Você perdeu!");
        TelaInicial t = new TelaInicial();
        t.setVisible(true);
        dispose();
    }

    public void BombasAoRedor(int i) {
        boolean n = false;
        int l = 0, c;
        while (!n) {
            if ((l * 9 <= i)) {
                l += 1;
            } else {
                n = true;
            }
        }
        c = i - ((l - 1) * 9);
        int cont = 0;
        if (i - 10 >= 0 && c != 0) {
            if (numerodosquadrados[i - 10] == 1) {
                cont++;
            }
        }
        if (i - 9 >= 0) {
            if (numerodosquadrados[i - 9] == 1) {
                cont++;
            }
        }
        if (i - 8 >= 0 && c != 8) {
            if (numerodosquadrados[i - 8] == 1) {
                cont++;
            }
        }
        if (i - 1 >= 0 && c != 0) {
            if (numerodosquadrados[i - 1] == 1) {
                cont++;
            }
        }
        if (i + 1 < 81 && c != 8) {
            if (numerodosquadrados[i + 1] == 1) {
                cont++;
            }
        }
        if (i + 8 < 81 && c != 0) {
            if (numerodosquadrados[i + 8] == 1) {
                cont++;
            }
        }
        if (i + 9 < 81) {
            if (numerodosquadrados[i + 9] == 1) {
                cont++;
            }
        }
        if (i + 10 < 81 && c != 8) {
            if (numerodosquadrados[i + 10] == 1) {
                cont++;
            }
        }
        if (cont != 0) {
            Random r = new Random();
            quadrados[i].setForeground(new Color(r.nextInt(185), r.nextInt(255), r.nextInt(255)));
            quadrados[i].setText("" + cont);
        }
    }

    public static void main(String[] args) {
        new CMinado();
    }
}
