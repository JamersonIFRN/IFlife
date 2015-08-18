package JogoMemoria;

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

public class JogoMemoria extends JFrame {

    private JLabel[] quadrados = new JLabel[16];
    private int numerodosquadrados[] = new int[16];
    private int cartavirada[] = new int[16];
    private JPanel panel = new JPanel();
    ActionListener action1;
    Timer atualizar;
    private int numCliques = 0, ultimoQClicado = -1;
    private int QuadClicado1, QuadClicado2;
    private ImageIcon imagens[] = new ImageIcon[8];
    JButton parar = new JButton("Sair");
    JPanel painelTempo = new JPanel();
    JLabel labelminuto = new JLabel("00:");
    JLabel labelsegundo = new JLabel("00");
    ActionListener action;
    Timer relogio;
    int min = 0, seg = 0;

    public JogoMemoria(String image, String image2, String image3, String image4, String image5, String image6, String image7, String image8) {
        imagens[0] = new ImageIcon(image);
        imagens[1] = new ImageIcon(image2);
        imagens[2] = new ImageIcon(image3);
        imagens[3] = new ImageIcon(image4);
        imagens[4] = new ImageIcon(image5);
        imagens[5] = new ImageIcon(image6);
        imagens[6] = new ImageIcon(image7);
        imagens[7] = new ImageIcon(image8);
        Inicializar();
        setTitle("Jogo da memoria");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        setSize(820, 725);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(new Color(40, 215, 188));
        panel.setVisible(true);
        panel.setBackground(Color.white);
        add(panel);
        panel.setBounds(25, 55, 750, 645);
        panel.setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 8));
        painelTempo.setBounds(618, 10, 100, 35);
        painelTempo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        painelTempo.setBackground(Color.white);
        add(painelTempo);
        labelminuto.setBounds(0, 0, 45, 35);
        labelminuto.setFont(new Font("Arial Narrow", 1, 22));
        painelTempo.add(labelminuto);
        labelsegundo.setBounds(50, 0, 45, 35);
        labelsegundo.setFont(new Font("Arial Narrow", 1, 22));
        painelTempo.add(labelsegundo);
        parar.setBounds(500, 10, 100, 35);
        parar.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        parar.setBackground(Color.white);
        add(parar);
        parar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TelaMemoria pc = new TelaMemoria();
                pc.setVisible(true);
                atualizar.stop();
                relogio.stop();
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

        action = new ActionListener() {
            public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                seg++;
                if (seg < 10) {
                    labelsegundo.setText("0" + seg);
                } else {
                    labelsegundo.setText("" + seg);
                }
                if (seg == 60) {
                    labelsegundo.setText("00");
                    seg = 0;
                    min++;
                }
                if (min < 10) {
                    labelminuto.setText("0" + min + ":");
                } else if (min == 15) {
                } else {
                    labelminuto.setText("" + min + ":");
                }

            }
        };
        relogio = new Timer(1000, action);
        relogio.start();
    }

    private void Inicializar() {
        panel.setLayout(new GridLayout(4, 4));
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
                    if (ultimoQClicado != u && numCliques <= 2 && cartavirada[u] != 1) {
                        numCliques++;
                        ultimoQClicado = u;
                        if (numCliques <= 2) {
                            if (numerodosquadrados[u] == 1) {
                                quadrados[u].setIcon(imagens[0]);
                            }
                            if (numerodosquadrados[u] == 2) {
                                quadrados[u].setIcon(imagens[1]);
                            }
                            if (numerodosquadrados[u] == 3) {
                                quadrados[u].setIcon(imagens[2]);
                            }
                            if (numerodosquadrados[u] == 4) {
                                quadrados[u].setIcon(imagens[3]);
                            }
                            if (numerodosquadrados[u] == 5) {
                                quadrados[u].setIcon(imagens[4]);
                            }
                            if (numerodosquadrados[u] == 6) {
                                quadrados[u].setIcon(imagens[5]);
                            }
                            if (numerodosquadrados[u] == 7) {
                                quadrados[u].setIcon(imagens[6]);
                            }
                            if (numerodosquadrados[u] == 8) {
                                quadrados[u].setIcon(imagens[7]);
                            }
                            if (QuadClicado1 == 0) {
                                QuadClicado1 = u;
                            } else {
                                QuadClicado2 = u;
                            }
                            if (numCliques == 2) {
                                if (numerodosquadrados[QuadClicado1] == numerodosquadrados[QuadClicado2] && QuadClicado1 != QuadClicado2) {
                                    cartavirada[QuadClicado1] = 1;
                                    cartavirada[QuadClicado2] = 1;
                                    int l = 0;
                                    for (int i = 0; i < quadrados.length; i++) {
                                        if (cartavirada[i] == 1) {
                                        } else {
                                            l = 1;
                                            quadrados[i].setIcon(new javax.swing.ImageIcon("Res/N.png"));
                                        }
                                    }
                                    numCliques = 0;
                                    QuadClicado1 = 0;
                                    QuadClicado2 = 0;
                                    ultimoQClicado = -1;
                                    if (l == 0) {
                                        JOptionPane.showMessageDialog(null, "Parabéns, você possui uma memoria excelente!");
                                        TelaMemoria t = new TelaMemoria();
                                        t.setVisible(true);
                                        atualizar.stop();
                                        relogio.stop();
                                        dispose();
                                    }
                                } else {

                                    action1 = new ActionListener() {
                                        public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                            int l = 0;
                                            for (int i = 0; i < quadrados.length; i++) {
                                                if (cartavirada[i] == 1) {
                                                } else {
                                                    l = 1;
                                                    quadrados[i].setIcon(new javax.swing.ImageIcon("Res/N.png"));
                                                }
                                            }
                                            atualizar.stop();
                                            numCliques = 0;
                                            QuadClicado1 = 0;
                                            QuadClicado2 = 0;
                                            ultimoQClicado = -1;
                                            if (l == 0) {
                                                JOptionPane.showMessageDialog(null, "Parabéns, você possui uma memoria excelente!");
                                                TelaMemoria t = new TelaMemoria();
                                                t.setVisible(true);
                                                atualizar.stop();
                                                relogio.stop();
                                                dispose();
                                            }
                                        }
                                    };
                                    atualizar = new Timer(1500, action1);
                                    atualizar.start();
                                }
                            } else {
                            }
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
        int radom, v = 1, k = 0;
        Random aleatorio = new Random();
        for (int jj = 0; jj < 16; jj++) {
            radom = aleatorio.nextInt(16) + 1;
            if (numerodosquadrados[radom - 1] == 0) {
                numerodosquadrados[radom - 1] = v;
                if (k < 1) {
                    k++;
                } else {
                    v++;
                    k = 0;
                }
            } else {
                int x = 0;
                while (x == 0) {
                    radom = aleatorio.nextInt(16) + 1;
                    if (numerodosquadrados[radom - 1] == 0) {
                        numerodosquadrados[radom - 1] = v;
                        if (k < 1) {
                            k++;
                        } else {
                            v++;
                            k = 0;
                        }
                        x++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new JogoMemoria("Res/JogosExtras/Memoria/1.png", "Res/JogosExtras/Memoria/2.png", "Res/JogosExtras/Memoria/3.png", "Res/JogosExtras/Memoria/4.png", "Res/JogosExtras/Memoria/5.png", "Res/JogosExtras/Memoria/6.png", "Res/JogosExtras/Memoria/7.png", "Res/JogosExtras/Memoria/8.png");
    }
}
