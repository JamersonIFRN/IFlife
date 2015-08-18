/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Prova;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author DANIEL
 */
public class Prova extends JFrame {

    JPanel painelDados = new JPanel();
    JLabel imagemDeFundo = new JLabel();
    JTextField nomeJogador = new JTextField();
    JLabel numeroDeInscricao = new JLabel();
    Font fonte = new Font("Arial Narrow", Font.BOLD, 15);
    JLabel avançar = new JLabel();
    JLabel voltar = new JLabel();
    JLabel entregar = new JLabel();
    JPanel painelPrincipal = new JPanel();
    int painelEmFoco = 0;
    JPanel[] paineisDasPerguntas = new JPanel[5];

    public Prova() {
        setUndecorated(true);
        setSize(605, 695);
        setLocation(5, 3);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        setVisible(true);
        add(painelDados);
        painelDados.setLayout(null);
        painelDados.setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 1));
        painelDados.setBounds(0, 0, 605, 695);
        painelDados.add(nomeJogador);
        nomeJogador.setBounds(175, 172, 470, 30);
        nomeJogador.requestFocus();
        nomeJogador.setBorder(null);
        nomeJogador.setFont(fonte);
        painelDados.add(numeroDeInscricao);
        numeroDeInscricao.setBounds(205, 202, 440, 36);
        numeroDeInscricao.setBorder(null);
        numeroDeInscricao.setFont(fonte);
        numeroDeInscricao.setText("198654-08");
        //jtx.setBackground(Color.red);
        painelDados.add(voltar);
        voltar.setBounds(235, 640, 50, 50);
        voltar.setIcon(new javax.swing.ImageIcon("Res/seta1.png"));
        voltar.setToolTipText("Voltar pagina");
        voltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VoltarMouseClicked(evt);
            }
        });
        painelDados.add(avançar);
        avançar.setBounds(300, 640, 50, 50);
        avançar.setIcon(new javax.swing.ImageIcon("Res/seta2.png"));
        avançar.setToolTipText("Avançar Pagina");
        avançar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AvançarMouseClicked(evt);
            }
        });

        painelDados.add(imagemDeFundo);
        imagemDeFundo.setBounds(2, 2, 600, 700);
        imagemDeFundo.setIcon(new javax.swing.ImageIcon("Res/ProvaTela2.png"));
        Inicializar();
    }

    public String getNome() {
        return nomeJogador.getText();
    }

    public void Inicializar() {
        String arquivo = "Res/Prova1.txt";
        String linha, texto = "";
        FileReader fr;
        try {
            fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                    if (!linha.equals("")) {
                        texto += linha + "\n";
                    }
                }
                br.close();
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Prova.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Prova.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] perguntasDaProva = texto.split("§");
        for (int ii = 0; ii < paineisDasPerguntas.length; ii++) {
            paineisDasPerguntas[ii] = new JPanel();
            add(paineisDasPerguntas[ii]);
            paineisDasPerguntas[ii].setLayout(null);
            paineisDasPerguntas[ii].setVisible(false);
            paineisDasPerguntas[ii].setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 1));
            paineisDasPerguntas[ii].setBounds(0, 0, 605, 695);
            paineisDasPerguntas[ii].setBackground(Color.white);

            String perguntasDoPainel[] = perguntasDaProva[ii].split("%");
            int posicaoVertical = 10;
            if (ii == 0) {
                JLabel materiaPortugues = new JLabel("Portugues", JLabel.CENTER);
                materiaPortugues.setFont(new Font("Arialn Narrow", 1, 22));
                materiaPortugues.setBounds(225, 3, 115, 25);
                materiaPortugues.setForeground(Color.red);
                paineisDasPerguntas[ii].add(materiaPortugues);
                posicaoVertical += 20;
            }
            for (int jj = 0; jj < perguntasDoPainel.length; jj++) {

                String dividirPartesDaPergunta[] = perguntasDoPainel[jj].split("\n");
                String enuciadoEAlternativas[] = dividirPartesDaPergunta[0].split("#");
                String textoEImagem[] = dividirPartesDaPergunta[1].split("#");
                String textoEnuciado[] = textoEImagem[0].split(":");
                if (textoEnuciado.length > 1) {
                    JScrollPane jscrollpane = new JScrollPane();
                    JTextArea caixaTextoEnuciado = new JTextArea();
                    jscrollpane.setLayout(null);
                    jscrollpane.add(caixaTextoEnuciado);
                    jscrollpane.setBorder(null);
                    caixaTextoEnuciado.setBorder(null);
                    jscrollpane.setBackground(Color.white);
                    caixaTextoEnuciado.setBackground(Color.white);
                    Font ft = new Font("Arial Narrow", Font.BOLD, 16);
                    caixaTextoEnuciado.setFont(ft);
                    paineisDasPerguntas[ii].add(caixaTextoEnuciado);
                    String entersNoEnuciado[] = textoEnuciado[1].split("@");
                    int kk = entersNoEnuciado.length * 19;
                    jscrollpane.setBounds(20, posicaoVertical, 600, kk);
                    caixaTextoEnuciado.setBounds(20, posicaoVertical, 600, kk);
                    posicaoVertical += kk;
                    for (int i = 0; i < entersNoEnuciado.length; i++) {
                        caixaTextoEnuciado.setText(caixaTextoEnuciado.getText() + entersNoEnuciado[i] + "\n");
                    }
                    caixaTextoEnuciado.setEditable(false);
                }
                String imagem[] = textoEImagem[1].split(":");
                int larguraImagem = 0;
                int alturaImagem = 0;
                if (imagem.length > 1) {
                    ImageIcon imagemIcon = new ImageIcon(imagem[1]);
                    larguraImagem = imagemIcon.getIconWidth();
                    alturaImagem = imagemIcon.getIconHeight();
                    JLabel labelDaImagem = new JLabel();
                    paineisDasPerguntas[ii].add(labelDaImagem);
                    posicaoVertical += 7;
                    labelDaImagem.setBounds(20, posicaoVertical, larguraImagem, alturaImagem);
                    posicaoVertical += alturaImagem;
                    labelDaImagem.setIcon(new javax.swing.ImageIcon(imagem[1]));
                }
                JLabel enuciado = new JLabel();
                Font ft2 = new Font("Arial Narrow", Font.BOLD, 20);
                enuciado.setFont(ft2);
                paineisDasPerguntas[ii].add(enuciado);
                posicaoVertical += 15;
                enuciado.setBounds(20, posicaoVertical, 550, 40);
                enuciado.setText(enuciadoEAlternativas[0]);
                Font ft3 = new Font("Arial Narrow", Font.BOLD, 17);
                JLabel alternativa1 = new JLabel(enuciadoEAlternativas[1]);
                paineisDasPerguntas[ii].add(alternativa1);
                posicaoVertical += 38;
                alternativa1.setBounds(40, posicaoVertical, 550, 28);
                alternativa1.setFont(ft3);
                JLabel alternativa2 = new JLabel(enuciadoEAlternativas[2]);
                paineisDasPerguntas[ii].add(alternativa2);
                posicaoVertical += 28;
                alternativa2.setBounds(40, posicaoVertical, 550, 28);
                alternativa2.setFont(ft3);
                JLabel alternativa3 = new JLabel(enuciadoEAlternativas[3]);
                paineisDasPerguntas[ii].add(alternativa3);
                posicaoVertical += 28;
                alternativa3.setBounds(40, posicaoVertical, 550, 28);
                alternativa3.setFont(ft3);
                JLabel alternativa4 = new JLabel(enuciadoEAlternativas[4]);
                paineisDasPerguntas[ii].add(alternativa4);
                posicaoVertical += 28;
                alternativa4.setBounds(40, posicaoVertical, 550, 28);
                alternativa4.setFont(ft3);
                posicaoVertical += 38;
                if (ii == 2 && jj == 0) {
                    JLabel materiaMatematica = new JLabel("Matematica", JLabel.CENTER);
                    materiaMatematica.setFont(new Font("Arialn Narrow", 1, 22));
                    materiaMatematica.setBounds(218, posicaoVertical, 120, 25);
                    materiaMatematica.setForeground(Color.red);
                    paineisDasPerguntas[ii].add(materiaMatematica);
                    posicaoVertical += 38;
                }
            }
            JLabel voltar1 = new JLabel();
            paineisDasPerguntas[ii].add(voltar1);
            voltar1.setBounds(235, 640, 50, 50);
            voltar1.setIcon(new javax.swing.ImageIcon("Res/seta1.png"));
            voltar1.setToolTipText("Voltar pagina");
            voltar1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    VoltarMouseClicked(evt);
                }
            });
            JLabel avançar1 = new JLabel();
            paineisDasPerguntas[ii].add(avançar1);
            avançar1.setBounds(300, 640, 50, 50);
            avançar1.setIcon(new javax.swing.ImageIcon("Res/seta2.png"));
            avançar1.setToolTipText("Avançar pagina");
            avançar1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    AvançarMouseClicked(evt);
                }
            });
        }
    }

    private void VoltarMouseClicked(java.awt.event.MouseEvent evt) {
        int h = 0;
        if (painelEmFoco == 0) {
            paineisDasPerguntas[4].setVisible(true);
            painelDados.setVisible(false);
            validate();
            painelEmFoco = 5;
            h = 1;
        }
        if (painelEmFoco == 1 && h == 0) {
            paineisDasPerguntas[0].setVisible(false);
            painelDados.setVisible(true);
            validate();
            painelEmFoco = 0;
            h = 1;
        }
        if (painelEmFoco == 2 && h == 0) {
            paineisDasPerguntas[0].setVisible(true);
            paineisDasPerguntas[1].setVisible(false);
            validate();
            painelEmFoco = 1;
            h = 1;
        }
        if (painelEmFoco == 3 && h == 0) {
            paineisDasPerguntas[1].setVisible(true);
            paineisDasPerguntas[2].setVisible(false);
            validate();
            painelEmFoco = 2;
            h = 1;
        }
        if (painelEmFoco == 4 && h == 0) {
            paineisDasPerguntas[2].setVisible(true);
            paineisDasPerguntas[3].setVisible(false);
            validate();
            painelEmFoco = 3;
        }
        if (painelEmFoco == 5 && h == 0) {
            paineisDasPerguntas[3].setVisible(true);
            paineisDasPerguntas[4].setVisible(false);
            validate();
            painelEmFoco = 4;
        }
    }

    private void AvançarMouseClicked(java.awt.event.MouseEvent evt) {
        int h = 0;
        if (painelEmFoco == 0) {
            paineisDasPerguntas[0].setVisible(true);
            painelDados.setVisible(false);
            validate();
            painelEmFoco = 1;
            h = 1;
        }
        if (painelEmFoco == 1 && h == 0) {
            paineisDasPerguntas[1].setVisible(true);
            paineisDasPerguntas[painelEmFoco - 1].setVisible(false);
            validate();
            painelEmFoco = 2;
            h = 1;
        }
        if (painelEmFoco == 2 && h == 0) {
            paineisDasPerguntas[2].setVisible(true);
            paineisDasPerguntas[1].setVisible(false);
            validate();
            painelEmFoco = 3;
            h = 1;
        }
        if (painelEmFoco == 3 && h == 0) {
            paineisDasPerguntas[3].setVisible(true);
            paineisDasPerguntas[2].setVisible(false);
            validate();
            painelEmFoco = 4;
            h = 1;
        }
        if (painelEmFoco == 4 && h == 0) {
            paineisDasPerguntas[4].setVisible(true);
            paineisDasPerguntas[3].setVisible(false);
            validate();
            painelEmFoco = 5;
            h = 1;
        }
        if (painelEmFoco == 5 && h == 0) {
            painelDados.setVisible(true);
            paineisDasPerguntas[4].setVisible(false);
            validate();
            painelEmFoco = 0;
        }
    }

    public static void main(String[] args) {
        new Prova();
    }
}
