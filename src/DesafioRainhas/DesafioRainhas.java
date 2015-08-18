package DesafioRainhas;

import CampoMinado.*;
import TorreDeHanoi.Principal;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DesafioRainhas extends JFrame {

    private JLabel[][] quadrados = new JLabel[8][8];
    private JLabel[] rainhas = new JLabel[8];
    private JPanel panel = new JPanel();
    private JPanel panelrainhas = new JPanel();
    private JPanel panelrelogio = new JPanel();
    private JLabel relogio = new JLabel();
    private JLabel tempo = new JLabel("00:00");
    private JButton j = new JButton("Sair");
    Timer timertempo;
    ActionListener action;
    int seg = 0, min = 0, mouserainha;
    RodarMusica r;

    public DesafioRainhas() {
        Inicializar();
        setTitle("Desafio 8 Rainhas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(795, 460);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        j.setBounds(630, 245, 90, 30);
        j.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        j.setBackground(Color.white);
        add(j);
        j.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timertempo.stop();
                timertempo = null;
                Principal p = new Principal();
                p.setVisible(true);
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
                j.setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                j.setForeground(Color.black);
            }
        });
        panel.setVisible(true);
        panel.setBackground(Color.white);
        add(panel);
        panel.setBounds(85, 15, 500, 395);
        panel.setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 5));
        panelrainhas.setVisible(true);
        panelrainhas.setBackground(Color.white);
        add(panelrainhas);
        panelrainhas.setBounds(5, 15, 62, 395);
        panelrainhas.setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 2));
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
        panelrelogio.setBounds(600, 30, 180, 180);
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
        panelrainhas.setLayout(new GridLayout(8, 1));
        for (int i = 0; i < rainhas.length; i++) {
            rainhas[i] = new JLabel("", JLabel.CENTER);
            rainhas[i].setBackground(Color.white);
            rainhas[i].setIcon(new ImageIcon("Res\\rainha.gif"));
            rainhas[i].setOpaque(true);
            rainhas[i].setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 1));
            panelrainhas.add(rainhas[i]);
            final int u = i;
            rainhas[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (rainhas[u].getIcon() != null && "Cursor Default".equals(getCursor().getName().toString())) {
                        Image cursorImage = Toolkit.getDefaultToolkit().getImage("Res\\rainha.gif");
                        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "");
                        setCursor(blankCursor);
                        rainhas[u].setIcon(null);
                        mouserainha = 1;
                    } else if (rainhas[u].getIcon() == null && !"Cursor Default".equals(getCursor().getName().toString())) {
                        setCursor(Cursor.DEFAULT_CURSOR);
                        rainhas[u].setIcon(new ImageIcon("Res\\rainha.gif"));
                        mouserainha = 0;
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (mouserainha == 1) {
                        Image cursorImage = Toolkit.getDefaultToolkit().getImage("Res\\rainha.gif");
                        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "");
                        setCursor(blankCursor);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(Cursor.DEFAULT_CURSOR);
                }
            });
        }
        panel.setLayout(new GridLayout(8, 8));
        float k = 1;
        for (int i = 0; i < quadrados.length; i++) {
            for (int j = 0; j < quadrados.length; j++) {
                quadrados[i][j] = new JLabel("", JLabel.CENTER);
                if (k / 8 == 1.0) {
                    k = 1;
                } else {
                    if (i == 0 && j == 0) {
                    } else {
                        k++;
                    }
                }
                if (i == 0 && j == 0) {
                    quadrados[i][j].setBackground(Color.black);
                } else {
                    if (k == 1) {
                        if (j == 0) {
                            if (quadrados[i - 1][7].getBackground() == Color.white) {
                                quadrados[i][j].setBackground(Color.white);
                            } else {
                                quadrados[i][j].setBackground(Color.black);
                            }
                        } else {
                            if (quadrados[i][j - 1].getBackground() == Color.white) {
                                quadrados[i][j].setBackground(Color.white);
                            } else {
                                quadrados[i][j].setBackground(Color.black);
                            }
                        }
                    } else {
                        if (j == 0) {

                            if (quadrados[i - 1][7].getBackground() == Color.white) {
                                quadrados[i][j].setBackground(Color.black);
                            } else {
                                quadrados[i][j].setBackground(Color.white);
                            }
                        } else {
                            if (quadrados[i][j - 1].getBackground() == Color.white) {
                                quadrados[i][j].setBackground(Color.black);
                            } else {
                                quadrados[i][j].setBackground(Color.white);
                            }
                        }
                    }
                }
                quadrados[i][j].setOpaque(true);
                quadrados[i][j].setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 2));
                ;
                panel.add(quadrados[i][j]);
                final int u = i;
                final int c = j;
                quadrados[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (quadrados[u][c].getIcon() != null && "Cursor Default".equals(getCursor().getName().toString())) {
                            Image cursorImage = Toolkit.getDefaultToolkit().getImage("Res\\rainha.gif");
                            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "");
                            setCursor(blankCursor);
                            quadrados[u][c].setIcon(null);
                            mouserainha = 1;
                        } else if (quadrados[u][c].getIcon() == null && !"Cursor Default".equals(getCursor().getName().toString())) {
                            setCursor(Cursor.DEFAULT_CURSOR);
                            quadrados[u][c].setIcon(new ImageIcon("Res\\rainha.gif"));
                            mouserainha = 0;
                        }
                        TesteVitoria();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (mouserainha == 1) {
                            Image cursorImage = Toolkit.getDefaultToolkit().getImage("Res\\rainha.gif");
                            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "");
                            setCursor(blankCursor);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        setCursor(Cursor.DEFAULT_CURSOR);
                    }
                });
            }
        }
    }

    private void TesteVitoria() {
        int numerodeRainhas = 0;
        for (int i = 0; i < quadrados.length; i++) {
            for (int j = 0; j < quadrados.length; j++) {
                if (quadrados[i][j].getIcon() != null) {
                    numerodeRainhas++;
                }
            }
        }

        if (numerodeRainhas == 8) {
            boolean vitoria = true;
            for (int i = 0; i < quadrados.length; i++) {
                for (int j = 0; j < quadrados.length; j++) {
                    if (quadrados[i][j].getIcon() != null) {
                        if (diagonalPricipal(i, j) == 1 && diagonalSecundaria(i, j) == 1 && esquerdaPraDireita(i) == 1 && direiraPraEsquerda(j) == 1) {
                        } else {
                            vitoria = false;
                        }
                    }
                }
            }
            if (vitoria) {
                JOptionPane.showMessageDialog(null, "Parabéns, você venceu!");
            } else {
            }
        } else {
            System.out.println("nao tem suficiente");
        }

    }

    private int diagonalPricipal(int i, int j) {
        int u = i, c = j;
        while (u != 0 && c != 0) {
            u--;
            c--;
        }
        int cont = 0;
        while (c != 8 && u != 8) {
            if (quadrados[u][c].getIcon() != null) {
                cont++;
            }
            c++;
            u++;
        }
        return cont;
    }

    private int diagonalSecundaria(int i, int j) {
        int u = i, c = j;
        while (u != 0 && c != 7) {
            u--;
            c++;
        }
        int cont = 0;
        while (c != -1 && u != 8) {
            if (quadrados[u][c].getIcon() != null) {
                cont++;
            }
            c--;
            u++;
        }
        return cont;
    }

    private int esquerdaPraDireita(int i) {
        int u = i, c = 0;
        int cont = 0;
        while (c != 8) {
            if (quadrados[u][c].getIcon() != null) {
                cont++;
            }
            c++;
        }
        return cont;
    }

    private int direiraPraEsquerda(int j) {
        int u = 0, c = j;
        int cont = 0;
        while (u != 8) {
            if (quadrados[u][c].getIcon() != null) {
                cont++;
            }
            u++;
        }
        return cont;
    }

    public static void main(String[] args) {
        new DesafioRainhas();
    }
}
