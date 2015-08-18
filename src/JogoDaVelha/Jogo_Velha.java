package JogoDaVelha;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author DANIEL
 */
public class Jogo_Velha extends JFrame {

    JPanel painel = new JPanel();
    JLabel quadrados[][] = new JLabel[3][3];
    JLabel Jogadordavez = new JLabel("Jogador 1");
    JLabel fundo = new JLabel("label");
    JPanel fund = new JPanel();

    public Jogo_Velha(String jog) {

        Jogadordavez.setText(jog);
        inicializar();
        //Titulo da janela
        setTitle("Jogo da velha");
        //Operação de fechamento da janela
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
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
                        if (Jogadordavez.getText().equals("Jogador 1") && quadrados[x][y].getText().equals("")) {
                            quadrados[x][y].setText("X");
                            TesteVencedor();
                            Jogadordavez.setText("Jogador 2");
                        } else if (quadrados[x][y].getText().equals("")) {
                            quadrados[x][y].setText("O");
                            quadrados[x][y].setForeground(Color.blue);
                            TesteVencedor();
                            Jogadordavez.setText("Jogador 1");
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
        boolean empate = true;
        for (int i = 0; i < quadrados.length; i++) {
            for (int j = 0; j < quadrados.length; j++) {
                if (quadrados[i][j].getText().equals("")) {
                    empate = false;
                }
            }
        }
        if (empate) {
            JOptionPane.showMessageDialog(null, "Deu empate!");
            FimJ f = new FimJ(Jogadordavez.getText(), "medio");
            f.setVisible(true);
            dispose();
        }

    }

    private void MensagemVencedor() {
        JOptionPane.showMessageDialog(null, "O " + Jogadordavez.getText() + " Venceu!");
        FimJ f = new FimJ(Jogadordavez.getText(), null);
        f.setVisible(true);
        dispose();


    }

    public static void main(String[] args) {
        new Jogo_Velha("Jogador 1");

    }
}
