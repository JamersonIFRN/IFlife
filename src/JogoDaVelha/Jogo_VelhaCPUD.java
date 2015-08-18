package JogoDaVelha;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.Timer;

/**
 *
 * @author DANIEL
 */
public class Jogo_VelhaCPUD extends JFrame {

    JPanel painel = new JPanel();
    JLabel quadrados[][] = new JLabel[3][3];
    JLabel Jogadordavez = new JLabel("Jogador");
    JLabel fundo = new JLabel("label");
    int ultimajogada = -1;
    Timer esperar;
    boolean venceu = false;
    JPanel fund = new JPanel();

    public Jogo_VelhaCPUD(String jog) {
        Jogadordavez.setText(jog);

        inicializar();
        if (jog.equals("Computador")) {
            JogadaCPU();
        }
        //Titulo da janela
        setTitle("Jogo da velha");
        //Operação de fechamento da janela
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //Colocar fundo brando na janela. Mudando os valores altera-se a cor.
        getContentPane().setBackground(new Color(255, 255, 255));
        //Tamanho da janela em pixels
        setSize(400, 400);
        setResizable(false);
        setUndecorated(true);
        fund.setBounds(0, 0, 400, 400);
        fund.setLayout(null);
        fund.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        fund.setBackground(new Color(40, 215, 188));
        add(fund);
        //Local da janela na tela, no caso, ficara no centro
        setLocationRelativeTo(null);
        //Ficar visivel na tela
        setVisible(true);
        //Tipo de layout, no nulo o programador ajusta a posição de cada componente na tela
        setLayout(null);
        painel.setVisible(true);
        painel.setLayout(null);
        painel.setBackground(Color.white);
        fund.add(painel);//Localizacao do painel na janela
        painel.setBounds(25, 55, 335, 300);
        painel.setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 1));
        fundo.setBounds(0, 0, 337, 301);
        fundo.setIcon(new ImageIcon("Res/tictac.jpg"));
        painel.add(fundo);
        fundo.setBackground(Color.red);
        //Imagem de fundo do painel. As linha sao uma imagem
        Jogadordavez.setBounds(120, 5, 200, 40);
        Jogadordavez.setFont(new Font("Arial Narrow", Font.BOLD, 32));
        fund.add(Jogadordavez);
    }

    private void inicializar() {
        for (int i = 0; i < quadrados.length; i++) {
            for (int j = 0; j < quadrados.length; j++) {
                quadrados[i][j] = new JLabel("", JLabel.CENTER);
                //Set para autorizar a mudanca de cor de fundo
                quadrados[i][j].setOpaque(true);
                quadrados[i][j].setForeground(Color.red);
                quadrados[i][j].setBackground(Color.white);
                quadrados[i][j].setFont(new Font("Arial Narrow", Font.BOLD, 45));
                //Localizacao dos quadrados clicaveis no painel
                quadrados[i][j].setBounds(69 + (70) * i, 51 + (70 * j), 60, 60);
                //adicionar quadrados no painel
                painel.add(quadrados[i][j]);
                final int x = i, y = j;
                //Evento de clique
                quadrados[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (Jogadordavez.getText().equals("Jogador") && quadrados[x][y].getText().equals("")) {

                            quadrados[x][y].setText("X");
                            UltimaJogada(x, y);
                            TesteVencedor();
                            Jogadordavez.setText("Computador");
                            ActionListener action;
                            action = new ActionListener() {
                                public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                    JogadaCPU();
                                    TesteVencedor();
                                    Jogadordavez.setText("Jogador");
                                    esperar.stop();
                                }
                            };
                            if (!venceu) {
                                esperar = new Timer(500, action);
                                esperar.start();
                            }
                        } else if (quadrados[x][y].getText().equals("")) {
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
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
            }
        }
    }

    private void JogadaCPU() {
        if (ultimajogada == -1) {
            quadrados[1][1].setText("O");
            quadrados[1][1].setForeground(Color.blue);
        } else if ((quadrados[0][0].getText().equals("O") && quadrados[0][1].getText().equals("O") && quadrados[0][2].getText().equals("")) || (quadrados[0][2].getText().equals("O") && quadrados[0][1].getText().equals("O") && quadrados[0][0].getText().equals("")) || (quadrados[0][2].getText().equals("O") && quadrados[0][0].getText().equals("O") && quadrados[0][1].getText().equals(""))) {
            if ((quadrados[0][0].getText().equals("O") && quadrados[0][1].getText().equals("O") && quadrados[0][2].getText().equals(""))) {
                quadrados[0][2].setText("O");
                quadrados[0][2].setForeground(Color.blue);
            } else if ((quadrados[0][0].getText().equals("O") && quadrados[0][2].getText().equals("O") && quadrados[0][1].getText().equals(""))) {
                quadrados[0][1].setText("O");
                quadrados[0][1].setForeground(Color.blue);
            } else if ((quadrados[0][1].getText().equals("O") && quadrados[0][2].getText().equals("O") && quadrados[0][0].getText().equals(""))) {
                quadrados[0][0].setText("O");
                quadrados[0][0].setForeground(Color.blue);
            }
        } else if ((quadrados[1][0].getText().equals("O") && quadrados[1][1].getText().equals("O") && quadrados[1][2].getText().equals("")) || (quadrados[1][2].getText().equals("O") && quadrados[1][1].getText().equals("O") && quadrados[1][0].getText().equals("")) || (quadrados[1][2].getText().equals("O") && quadrados[1][0].getText().equals("O") && quadrados[1][1].getText().equals(""))) {
            if ((quadrados[1][0].getText().equals("O") && quadrados[1][1].getText().equals("O") && quadrados[1][2].getText().equals(""))) {
                quadrados[1][2].setText("O");
                quadrados[1][2].setForeground(Color.blue);
            } else if ((quadrados[1][0].getText().equals("O") && quadrados[1][2].getText().equals("O") && quadrados[1][1].getText().equals(""))) {
                quadrados[1][1].setText("O");
                quadrados[1][1].setForeground(Color.blue);
            } else if ((quadrados[1][1].getText().equals("O") && quadrados[1][2].getText().equals("O") && quadrados[1][0].getText().equals(""))) {
                quadrados[1][0].setText("O");
                quadrados[1][0].setForeground(Color.blue);
            }
        } else if ((quadrados[2][0].getText().equals("O") && quadrados[2][1].getText().equals("O") && quadrados[2][2].getText().equals("")) || (quadrados[2][2].getText().equals("O") && quadrados[2][1].getText().equals("O") && quadrados[2][0].getText().equals("")) || (quadrados[2][2].getText().equals("O") && quadrados[2][0].getText().equals("O") && quadrados[2][1].getText().equals(""))) {
            if ((quadrados[2][0].getText().equals("O") && quadrados[2][1].getText().equals("O") && quadrados[2][2].getText().equals(""))) {
                quadrados[2][2].setText("O");
                quadrados[2][2].setForeground(Color.blue);
            } else if ((quadrados[2][0].getText().equals("O") && quadrados[2][2].getText().equals("O") && quadrados[2][1].getText().equals(""))) {
                quadrados[2][1].setText("O");
                quadrados[2][1].setForeground(Color.blue);
            } else if ((quadrados[2][1].getText().equals("O") && quadrados[2][2].getText().equals("O") && quadrados[2][0].getText().equals(""))) {
                quadrados[2][0].setText("O");
                quadrados[2][0].setForeground(Color.blue);
            }
        } else if ((quadrados[0][0].getText().equals("O") && quadrados[1][1].getText().equals("O") && quadrados[2][2].getText().equals("")) || (quadrados[2][2].getText().equals("O") && quadrados[1][1].getText().equals("O") && quadrados[0][0].getText().equals("")) || (quadrados[2][2].getText().equals("O") && quadrados[0][0].getText().equals("O") && quadrados[1][1].getText().equals(""))) {
            if ((quadrados[0][0].getText().equals("O") && quadrados[1][1].getText().equals("O") && quadrados[2][2].getText().equals(""))) {
                quadrados[2][2].setText("O");
                quadrados[2][2].setForeground(Color.blue);
            } else if ((quadrados[0][0].getText().equals("O") && quadrados[2][2].getText().equals("O") && quadrados[1][1].getText().equals(""))) {
                quadrados[1][1].setText("O");
                quadrados[1][1].setForeground(Color.blue);
            } else if ((quadrados[1][1].getText().equals("O") && quadrados[2][2].getText().equals("O") && quadrados[0][0].getText().equals(""))) {
                quadrados[0][0].setText("O");
                quadrados[0][0].setForeground(Color.blue);
            }
        } else if ((quadrados[2][0].getText().equals("O") && quadrados[1][1].getText().equals("O") && quadrados[0][2].getText().equals("")) || (quadrados[0][2].getText().equals("O") && quadrados[1][1].getText().equals("O") && quadrados[2][0].getText().equals("")) || (quadrados[2][0].getText().equals("O") && quadrados[0][2].getText().equals("O") && quadrados[1][1].getText().equals(""))) {
            if ((quadrados[0][2].getText().equals("O") && quadrados[1][1].getText().equals("O") && quadrados[2][0].getText().equals(""))) {
                quadrados[2][0].setText("O");
                quadrados[2][0].setForeground(Color.blue);
            } else if ((quadrados[0][2].getText().equals("O") && quadrados[2][0].getText().equals("O") && quadrados[1][1].getText().equals(""))) {
                quadrados[1][1].setText("O");
                quadrados[1][1].setForeground(Color.blue);
            } else if ((quadrados[1][1].getText().equals("O") && quadrados[2][0].getText().equals("O") && quadrados[0][2].getText().equals(""))) {
                quadrados[0][2].setText("O");
                quadrados[0][2].setForeground(Color.blue);
            }
        } else if (ultimajogada == 5) {
            if (!quadrados[0][0].getText().equals("") && !quadrados[0][2].getText().equals("") && !quadrados[2][0].getText().equals("") && !quadrados[2][2].getText().equals("")) {
                boolean f = true;
                while (f) {
                    Random r = new Random();
                    int i = r.nextInt(3);
                    if (i == 0 && quadrados[0][0].getText().equals("")) {
                        quadrados[0][0].setText("O");
                        quadrados[0][0].setForeground(Color.blue);
                        f = false;
                    }
                    if (i == 1 && quadrados[0][2].getText().equals("")) {
                        quadrados[0][2].setText("O");
                        quadrados[0][2].setForeground(Color.blue);
                        f = false;
                    }
                    if (i == 2 && quadrados[2][0].getText().equals("")) {
                        quadrados[2][0].setText("O");
                        quadrados[2][0].setForeground(Color.blue);
                        f = false;
                    }
                    if (i == 3 && quadrados[2][2].getText().equals("")) {
                        quadrados[2][2].setText("O");
                        quadrados[2][2].setForeground(Color.blue);
                        f = false;
                    }
                }
            } else {
                Aleatoria();
            }
        } else if ((ultimajogada == 1 || ultimajogada == 3 || ultimajogada == 7 || ultimajogada == 9)) {
            if (ultimajogada == 1) {
                if (quadrados[1][1].getText().equals("X") && quadrados[2][2].getText().equals("")) {
                    quadrados[2][2].setText("O");
                    quadrados[2][2].setForeground(Color.blue);
                } else if (quadrados[0][2].getText().equals("X") && quadrados[0][1].getText().equals("")) {
                    quadrados[0][1].setText("O");
                    quadrados[0][1].setForeground(Color.blue);
                } else if (quadrados[0][1].getText().equals("X") && quadrados[0][2].getText().equals("")) {
                    quadrados[0][2].setText("O");
                    quadrados[0][2].setForeground(Color.blue);
                } else if (quadrados[2][0].getText().equals("X") && quadrados[1][0].getText().equals("")) {
                    quadrados[1][0].setText("O");
                    quadrados[1][0].setForeground(Color.blue);
                } else if (quadrados[1][0].getText().equals("X") && quadrados[2][0].getText().equals("")) {
                    quadrados[2][0].setText("O");
                    quadrados[2][0].setForeground(Color.blue);
                } else if (quadrados[1][1].getText().equals("")) {
                    quadrados[1][1].setText("O");
                    quadrados[1][1].setForeground(Color.blue);
                } else {
                    Aleatoria();
                }

            }
            if (ultimajogada == 3) {
                if (quadrados[1][1].getText().equals("X") && quadrados[2][0].getText().equals("")) {
                    quadrados[2][0].setText("O");
                    quadrados[2][0].setForeground(Color.blue);
                } else if (quadrados[0][0].getText().equals("X") && quadrados[0][1].getText().equals("")) {
                    quadrados[0][1].setText("O");
                    quadrados[0][1].setForeground(Color.blue);
                } else if (quadrados[0][1].getText().equals("X") && quadrados[0][0].getText().equals("")) {
                    quadrados[0][0].setText("O");
                    quadrados[0][0].setForeground(Color.blue);
                } else if (quadrados[2][2].getText().equals("X") && quadrados[1][2].getText().equals("")) {
                    quadrados[1][2].setText("O");
                    quadrados[1][2].setForeground(Color.blue);
                } else if (quadrados[1][2].getText().equals("X") && quadrados[2][2].getText().equals("")) {
                    quadrados[2][2].setText("O");
                    quadrados[2][2].setForeground(Color.blue);
                } else if (quadrados[1][1].getText().equals("")) {
                    quadrados[1][1].setText("O");
                    quadrados[1][1].setForeground(Color.blue);
                } else {
                    Aleatoria();
                }
            }
            if (ultimajogada == 7) {
                if (quadrados[1][1].getText().equals("X") && quadrados[0][2].getText().equals("")) {
                    quadrados[0][2].setText("O");
                    quadrados[0][2].setForeground(Color.blue);
                } else if (quadrados[0][0].getText().equals("X") && quadrados[1][0].getText().equals("")) {
                    quadrados[1][0].setText("O");
                    quadrados[1][0].setForeground(Color.blue);
                } else if (quadrados[1][0].getText().equals("X") && quadrados[0][0].getText().equals("")) {
                    quadrados[0][0].setText("O");
                    quadrados[0][0].setForeground(Color.blue);
                } else if (quadrados[2][2].getText().equals("X") && quadrados[2][1].getText().equals("")) {
                    quadrados[2][1].setText("O");
                    quadrados[2][1].setForeground(Color.blue);
                } else if (quadrados[2][1].getText().equals("X") && quadrados[2][2].getText().equals("")) {
                    quadrados[2][2].setText("O");
                    quadrados[2][2].setForeground(Color.blue);
                } else if (quadrados[1][1].getText().equals("")) {
                    quadrados[1][1].setText("O");
                    quadrados[1][1].setForeground(Color.blue);
                } else {
                    Aleatoria();
                }
            }
            if (ultimajogada == 9) {
                if (quadrados[1][1].getText().equals("X") && quadrados[0][0].getText().equals("")) {
                    quadrados[0][0].setText("O");
                    quadrados[0][0].setForeground(Color.blue);
                } else if (quadrados[0][2].getText().equals("X") && quadrados[1][2].getText().equals("")) {
                    quadrados[1][2].setText("O");
                    quadrados[1][2].setForeground(Color.blue);
                } else if (quadrados[1][2].getText().equals("X") && quadrados[0][2].getText().equals("")) {
                    quadrados[0][2].setText("O");
                    quadrados[0][2].setForeground(Color.blue);
                } else if (quadrados[2][0].getText().equals("X") && quadrados[2][1].getText().equals("")) {
                    quadrados[2][1].setText("O");
                    quadrados[2][1].setForeground(Color.blue);
                } else if (quadrados[2][1].getText().equals("X") && quadrados[2][0].getText().equals("")) {
                    quadrados[2][0].setText("O");
                    quadrados[2][0].setForeground(Color.blue);
                } else if (quadrados[1][1].getText().equals("")) {
                    quadrados[1][1].setText("O");
                    quadrados[1][1].setForeground(Color.blue);
                } else {
                    Aleatoria();
                }
            }
        } else if (ultimajogada == 2) {
            if (quadrados[1][1].getText().equals("X") && quadrados[2][1].getText().equals("")) {
                quadrados[2][1].setText("O");
                quadrados[2][1].setForeground(Color.blue);
            } else if (quadrados[0][0].getText().equals("X") && quadrados[0][2].getText().equals("")) {
                quadrados[0][2].setText("O");
                quadrados[0][2].setForeground(Color.blue);
            } else if (quadrados[0][2].getText().equals("X") && quadrados[0][0].getText().equals("")) {
                quadrados[0][0].setText("O");
                quadrados[0][0].setForeground(Color.blue);
            } else if (quadrados[1][1].getText().equals("")) {
                quadrados[1][1].setText("O");
                quadrados[1][1].setForeground(Color.blue);
            } else {
                Aleatoria();
            }
        } else if (ultimajogada == 4) {
            if (quadrados[1][1].getText().equals("X") && quadrados[1][2].getText().equals("")) {
                quadrados[1][2].setText("O");
                quadrados[1][2].setForeground(Color.blue);
            } else if (quadrados[0][0].getText().equals("X") && quadrados[2][0].getText().equals("")) {
                quadrados[2][0].setText("O");
                quadrados[2][0].setForeground(Color.blue);
            } else if (quadrados[2][0].getText().equals("X") && quadrados[0][0].getText().equals("")) {
                quadrados[0][0].setText("O");
                quadrados[0][0].setForeground(Color.blue);
            } else if (quadrados[1][1].getText().equals("")) {
                quadrados[1][1].setText("O");
                quadrados[1][1].setForeground(Color.blue);
            } else {
                Aleatoria();
            }
        } else if (ultimajogada == 6) {
            if (quadrados[1][1].getText().equals("X") && quadrados[1][0].getText().equals("")) {
                quadrados[1][0].setText("O");
                quadrados[1][0].setForeground(Color.blue);
            } else if (quadrados[0][2].getText().equals("X") && quadrados[2][2].getText().equals("")) {
                quadrados[2][2].setText("O");
                quadrados[2][2].setForeground(Color.blue);
            } else if (quadrados[2][2].getText().equals("X") && quadrados[0][2].getText().equals("")) {
                quadrados[0][2].setText("O");
                quadrados[0][2].setForeground(Color.blue);
            } else if (quadrados[1][1].getText().equals("")) {
                quadrados[1][1].setText("O");
                quadrados[1][1].setForeground(Color.blue);
            } else {
                Aleatoria();
            }
        } else if (ultimajogada == 8) {
            if (quadrados[1][1].getText().equals("X") && quadrados[0][1].getText().equals("")) {
                quadrados[0][1].setText("O");
                quadrados[0][1].setForeground(Color.blue);
            } else if (quadrados[2][0].getText().equals("X") && quadrados[2][2].getText().equals("")) {
                quadrados[2][2].setText("O");
                quadrados[2][2].setForeground(Color.blue);
            } else if (quadrados[2][2].getText().equals("X") && quadrados[2][0].getText().equals("")) {
                quadrados[2][0].setText("O");
                quadrados[2][0].setForeground(Color.blue);
            } else if (quadrados[1][1].getText().equals("")) {
                quadrados[1][1].setText("O");
                quadrados[1][1].setForeground(Color.blue);
            } else {
                Aleatoria();
            }
        } else if (quadrados[0][0].getText().equals("")) {
            quadrados[0][0].setText("O");
            quadrados[0][0].setForeground(Color.blue);
        } else {
            Aleatoria();
        }
        if (ultimajogada == -1) {
            Jogadordavez.setText("Jogador");
        }
    }

    private void Aleatoria() {
        boolean f = true;
        while (f) {
            Random r = new Random();
            int i = r.nextInt(8);
            if (i == 0 && quadrados[0][0].getText().equals("")) {
                quadrados[0][0].setText("O");
                quadrados[0][0].setForeground(Color.blue);
                f = false;
            }
            if (i == 1 && quadrados[0][1].getText().equals("")) {
                quadrados[0][1].setText("O");
                quadrados[0][1].setForeground(Color.blue);
                f = false;
            }
            if (i == 2 && quadrados[0][2].getText().equals("")) {
                quadrados[0][2].setText("O");
                quadrados[0][2].setForeground(Color.blue);
                f = false;
            }
            if (i == 3 && quadrados[1][0].getText().equals("")) {
                quadrados[1][0].setText("O");
                quadrados[1][0].setForeground(Color.blue);
                f = false;
            }
            if (i == 4 && quadrados[1][1].getText().equals("")) {
                quadrados[1][1].setText("O");
                quadrados[1][1].setForeground(Color.blue);
                f = false;
            }
            if (i == 5 && quadrados[1][2].getText().equals("")) {
                quadrados[1][2].setText("O");
                quadrados[1][2].setForeground(Color.blue);
                f = false;
            }
            if (i == 6 && quadrados[2][0].getText().equals("")) {
                quadrados[2][0].setText("O");
                quadrados[2][0].setForeground(Color.blue);
                f = false;
            }
            if (i == 7 && quadrados[2][1].getText().equals("")) {
                quadrados[2][1].setText("O");
                quadrados[2][1].setForeground(Color.blue);
                f = false;
            }
            if (i == 8 && quadrados[2][2].getText().equals("")) {
                quadrados[2][2].setText("O");
                quadrados[2][2].setForeground(Color.blue);
                f = false;
            }
        }
    }

    private void UltimaJogada(int x, int y) {
        if (x == 0 & y == 0) {
            ultimajogada = 1;
        }
        if (x == 0 & y == 1) {
            ultimajogada = 2;
        }
        if (x == 0 & y == 2) {
            ultimajogada = 3;
        }
        if (x == 1 & y == 0) {
            ultimajogada = 4;
        }
        if (x == 1 & y == 1) {
            ultimajogada = 5;
        }
        if (x == 1 & y == 2) {
            ultimajogada = 6;
        }
        if (x == 2 & y == 0) {
            ultimajogada = 7;
        }
        if (x == 2 & y == 1) {
            ultimajogada = 8;
        }
        if (x == 2 & y == 2) {
            ultimajogada = 9;
        }

    }

    private void TesteVencedor() {
        //Testar se houve vencedor na vertical
        for (int i = 0; i < quadrados.length; i++) {
            if (quadrados[i][0].getText().equals(quadrados[i][1].getText()) && quadrados[i][0].getText().equals(quadrados[i][2].getText()) && !quadrados[i][0].getText().equals("")) {
                MensagemVencedor();
            }
        }
        //Testar se houve vencedor na horizontal
        for (int i = 0; i < quadrados.length; i++) {
            if (quadrados[0][i].getText().equals(quadrados[1][i].getText()) && quadrados[0][i].getText().equals(quadrados[2][i].getText()) && !quadrados[0][i].getText().equals("")) {
                MensagemVencedor();
            }
        }
        //Testar se houve vencedor na diagonal principal
        if (quadrados[0][0].getText().equals(quadrados[1][1].getText()) && quadrados[0][0].getText().equals(quadrados[2][2].getText()) && !quadrados[0][0].getText().equals("")) {
            MensagemVencedor();
        }
        //Testar se houve vencedor na diagonal secundaria
        if (quadrados[0][2].getText().equals(quadrados[1][1].getText()) && quadrados[0][2].getText().equals(quadrados[2][0].getText()) && !quadrados[0][2].getText().equals("")) {
            MensagemVencedor();
        }
        boolean empate = false;
        int cont = 0;
        for (int i = 0; i < quadrados.length; i++) {
            for (int j = 0; j < quadrados.length; j++) {
                if (!quadrados[i][j].getText().equals("") && !venceu) {
                    cont++;

                }
            }
        }
        if (cont == 9) {
            empate = true;
        }
        if (empate) {
            venceu = true;
            JOptionPane.showMessageDialog(null, "Deu empate!");
            FimJ f = new FimJ(Jogadordavez.getText(), "dificil");
            f.setVisible(true);
            dispose();
        }

    }

    private void MensagemVencedor() {
        venceu = true;
        JOptionPane.showMessageDialog(null, "O " + Jogadordavez.getText() + " Venceu!");
        FimJ f = new FimJ(Jogadordavez.getText(), "dificil");
        f.setVisible(true);
        dispose();


    }

    public static void main(String[] args) {
        new Jogo_VelhaCPUD("Jogador");

    }
}
