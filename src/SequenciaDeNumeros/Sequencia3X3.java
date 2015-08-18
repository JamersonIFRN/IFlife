package SequenciaDeNumeros;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Sequencia3X3 extends JFrame {

    private JLabel[] quadrados = new JLabel[9];
    private int numerodosquadrados[] = new int[quadrados.length];
    private int cartavirada[] = new int[quadrados.length];
    private JPanel panel = new JPanel();
    Timer atualizar;
    private int ultimoQClicado = -1;
    JButton parar = new JButton("Sair");
    Timer mostrar;
    int num = 1;
    boolean emjogo = false;

    public Sequencia3X3() {
        Inicializar();
        setTitle("Jogo da memoria");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        setSize(470, 425);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(new Color(40, 215, 188));
        panel.setVisible(true);
        panel.setBackground(Color.white);
        add(panel);
        panel.setBounds(25, 55, 400, 345);
        panel.setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 8));

        parar.setBounds(150, 10, 75, 30);
        parar.setFont(new Font("Arial Narrow", Font.BOLD, 16));
        parar.setBackground(Color.white);
        add(parar);
        parar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TelaSequencia s = new TelaSequencia();
                s.setVisible(true);
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                parar.setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                parar.setForeground(Color.black);
            }
        });
    }

    private void Inicializar() {
        panel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < quadrados.length; i++) {
            quadrados[i] = new JLabel("", JLabel.CENTER);
            quadrados[i].setBackground(Color.white);
            quadrados[i].setIcon(new javax.swing.ImageIcon("Res/N.png"));
            quadrados[i].setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 2));
            panel.add(quadrados[i]);


            final int u = i;
            quadrados[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (ultimoQClicado != u && cartavirada[u] != 1 && emjogo) {
                        ultimoQClicado = u;
                        if (num == numerodosquadrados[u]) {
                            num++;
                            ColocarImagem(u);
                            cartavirada[u] = 1;
                        } else {
                            ColocarImagem(u);
                            JOptionPane.showMessageDialog(null, "Você perdeu!");
                            TelaSequencia s = new TelaSequencia();
                            s.setVisible(true);
                            dispose();
                        }
                        int l = 0;
                        for (int i = 0; i < quadrados.length; i++) {
                            if (cartavirada[i] == 1) {
                            } else {
                                l = 1;
                                quadrados[i].setIcon(new javax.swing.ImageIcon("Res/N.png"));
                            }
                        }
                        if (l == 0) {
                            JOptionPane.showMessageDialog(null, "Parabéns, você possui uma memoria excelente!");
                            TelaSequencia s = new TelaSequencia();
                            s.setVisible(true);
                            dispose();
                        }
                    }
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
        int radom, v = 1;
        Random aleatorio = new Random();
        for (int jj = 0; jj < quadrados.length; jj++) {
            boolean sort = true;
            while (sort) {
                radom = aleatorio.nextInt(quadrados.length) + 1;
                if (numerodosquadrados[radom - 1] == 0) {
                    numerodosquadrados[radom - 1] = v;
                    sort = false;
                    v++;
                }
            }
        }
        for (int i = 0; i < quadrados.length; i++) {
            ColocarImagem(i);
        }

        ActionListener action1;
        action1 = new ActionListener() {
            public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                for (int i = 0; i < quadrados.length; i++) {
                    quadrados[i].setIcon(new javax.swing.ImageIcon("Res/N.png"));
                }
                emjogo = true;
                mostrar.stop();
            }
        };
        mostrar = new Timer(4000, action1);
        mostrar.start();

    }

    private void ColocarImagem(int i) {
        if (numerodosquadrados[i] == 1) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/1.png"));
        }
        if (numerodosquadrados[i] == 2) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/2.png"));
        }
        if (numerodosquadrados[i] == 3) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/3.png"));
        }
        if (numerodosquadrados[i] == 4) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/4.png"));
        }
        if (numerodosquadrados[i] == 5) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/5.png"));
        }
        if (numerodosquadrados[i] == 6) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/6.png"));
        }
        if (numerodosquadrados[i] == 7) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/7.png"));
        }
        if (numerodosquadrados[i] == 8) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/8.png"));
        }
        if (numerodosquadrados[i] == 9) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/9.png"));
        }
        if (numerodosquadrados[i] == 10) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/10.png"));
        }
        if (numerodosquadrados[i] == 11) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/11.png"));
        }
        if (numerodosquadrados[i] == 12) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/12.png"));
        }
        if (numerodosquadrados[i] == 13) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/13.png"));
        }
        if (numerodosquadrados[i] == 14) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/14.png"));
        }
        if (numerodosquadrados[i] == 15) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/15.png"));
        }
        if (numerodosquadrados[i] == 16) {
            quadrados[i].setIcon(new ImageIcon("Res/JogosExtras/Sequencia/16.png"));
        }
    }

    public static void main(String[] args) {
        new Sequencia3X3();
    }
}
