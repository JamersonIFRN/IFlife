/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forca;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author DANIEL
 */
public class ForcaJogo extends javax.swing.JFrame {

    Timer esperar;
    JButton botoesDasLetras[] = new JButton[26];
    JButton parar = new JButton("Parar");
    JPanel painel = new JPanel();
    JLabel forca = new JLabel("");
    String palavra = "ALBERT EINSTEIN";
    String caracteres = "@@@@@@-@@@@@@@@";
    JLabel codificacao = new JLabel();
    JLabel pontuacao = new JLabel("Pontos: 0");
    JLabel dica = new JLabel("Dica: Teoria da relatividade ");
    JLabel letrasErradas = new JLabel("Letras Erradas: ");
    String stringParaCodificacao;
    int estadoDoPersonagem = 0;
    String palavrasedicasG[];
    int cont = 1, pontos = 0;
    String palavrasPassadas = "";

    public ForcaJogo(String forcatxt) {
        palavrasedicasG = forcatxt.split("@");
        MudarPalavra();
        CriarCod();
        setUndecorated(true);
        setSize(830, 425);
        setTitle("Forca");
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        repaint();
        Iniciar();
        painel.setBounds(0, 0, 830, 425);
        painel.setLayout(null);
        add(painel);
        painel.setBackground(new Color(40, 215, 188));
        painel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        Palavra();
    }

    private void Iniciar() {
        int espVertical = 260, linhaB = 9, contadorPraEsp = 0, contador = 0, espHrizontal = 0, espHorizont = 0;
        Font fonte1 = new Font("Tahoma", Font.BOLD, 11);
        Font fonte2 = new Font("Showcard Gothic", Font.BOLD, 14);
        Font fonte3 = new Font("Showcard Gothic", Font.BOLD, 20);
        while (contador < 26) {
            botoesDasLetras[contador] = new JButton("a");
            botoesDasLetras[contador].setBounds(espHrizontal + 190, espVertical, 45, 35);
            botoesDasLetras[contador].setFont(fonte1);
            botoesDasLetras[contador].setBackground(Color.white);
            espHrizontal += 11;
            painel.add(botoesDasLetras[contador]);
            if (contadorPraEsp == linhaB) {
                linhaB--;
                espVertical += 35;
                contadorPraEsp = 0;
                espHrizontal = 0;
                if (espHorizont == 0) {
                    espHrizontal -= 10;
                } else {
                    espHrizontal += 40;
                }
                espHorizont++;
            } else {
                contadorPraEsp++;
            }
            espHrizontal += 35;
            final int numeroBotao = contador;
            botoesDasLetras[contador].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Teste();

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

                private void Teste() {
                    boolean letraCorreta = false;
                    for (int i = 0; i < caracteres.length(); i++) {
                        String a = caracteres;
                        if (palavra.substring(i, i + 1).equalsIgnoreCase(botoesDasLetras[numeroBotao].getText())) {
                            letraCorreta = true;
                            caracteres = caracteres.substring(0, i);
                            caracteres += botoesDasLetras[numeroBotao].getText();
                            caracteres += a.substring(i + 1, a.length());
                        }
                    }
                    String mudarTextCodif = "";

                    for (int j = 0; j < caracteres.length(); j++) {
                        if (caracteres.substring(j, j + 1).equals("@")) {
                            if (j == 0) {
                                mudarTextCodif += "___";
                            } else {
                                mudarTextCodif += "  ___";
                            }
                        } else if (caracteres.substring(j, j + 1).equals("-")) {
                            mudarTextCodif += "    ";
                        } else {
                            if (j == 0) {
                                mudarTextCodif += caracteres.substring(j, j + 1);
                            } else {
                                mudarTextCodif += "  " + caracteres.substring(j, j + 1);
                            }
                        }
                    }

                    if (!botoesDasLetras[numeroBotao].getText().substring(0, 1).equals("<")) {
                        if (letraCorreta) {
                        } else {
                            botoesDasLetras[numeroBotao].setVisible(false);
                            estadoDoPersonagem++;
                        }
                        if (estadoDoPersonagem == 1 && !letraCorreta) {
                            forca.setIcon(new javax.swing.ImageIcon("Res/forca.jpg"));
                            letrasErradas.setText(letrasErradas.getText() + botoesDasLetras[numeroBotao].getText() + " ");
                        }
                        if (estadoDoPersonagem == 2 && !letraCorreta) {
                            forca.setIcon(new javax.swing.ImageIcon("Res/forca1.jpg"));
                            letrasErradas.setText(letrasErradas.getText() + botoesDasLetras[numeroBotao].getText() + " ");
                        }
                        if (estadoDoPersonagem == 3 && !letraCorreta) {
                            forca.setIcon(new javax.swing.ImageIcon("Res/forca3.jpg"));
                            letrasErradas.setText(letrasErradas.getText() + botoesDasLetras[numeroBotao].getText() + " ");
                        }
                        if (estadoDoPersonagem == 4 && !letraCorreta) {
                            forca.setIcon(new javax.swing.ImageIcon("Res/forca4.jpg"));
                            letrasErradas.setText(letrasErradas.getText() + botoesDasLetras[numeroBotao].getText() + " ");
                        }
                        if (estadoDoPersonagem == 5 && !letraCorreta) {
                            forca.setIcon(new javax.swing.ImageIcon("Res/forca5.jpg"));
                            letrasErradas.setText(letrasErradas.getText() + botoesDasLetras[numeroBotao].getText() + " ");
                        }
                        if (estadoDoPersonagem == 6 && !letraCorreta) {
                            forca.setIcon(new javax.swing.ImageIcon("Res/forca6.jpg"));
                            letrasErradas.setText(letrasErradas.getText() + botoesDasLetras[numeroBotao].getText() + " ");
                            JOptionPane.showMessageDialog(null, "Fim de Jogo!\nA resposta certa era: " + palavra);
                            VerSeFezRecordes();
                            Categorias c = new Categorias();
                            c.setVisible(true);
                            dispose();
                        }
                    }
                    codificacao.setText(mudarTextCodif);
                    botoesDasLetras[numeroBotao].setText("<html><s>" + botoesDasLetras[numeroBotao].getText() + "</s></html>");

                    botoesDasLetras[numeroBotao].setForeground(Color.red);
                    if (TesteVitoria(mudarTextCodif)) {
                        ActionListener action;
                        action = new ActionListener() {
                            public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                cont++;
                                MudarPalavra();
                                Reiniciar();
                                CriarCod();
                                Palavra();
                                pontos += 150;
                                pontuacao.setText("Pontos: " + pontos);
                                esperar.stop();
                            }
                        };
                        esperar = new Timer(1500, action);
                        esperar.start();
                    }
                }
            });
            contador++;
        }
        forca.setBounds(10, 100, 140, 190);
        codificacao.setBounds(200, 80, 680, 200);
        pontuacao.setBounds(20, 0, 500, 100);
        dica.setBounds(200, 0, 850, 100);
        letrasErradas.setBounds(200, 145, 480, 180);
        forca.setIcon(new javax.swing.ImageIcon("Res/forc.jpg"));
        painel.add(forca);
        codificacao.setFont(fonte2);
        painel.add(codificacao);
        pontuacao.setFont(fonte3);
        painel.add(pontuacao);
        dica.setFont(fonte3);
        painel.add(dica);
        letrasErradas.setFont(fonte3);
        painel.add(letrasErradas);
        parar.setBounds(20, 300, 100, 45);
        parar.setFont(new Font("Showcard Gothic", Font.BOLD, 17));
        parar.setBackground(Color.white);
        painel.add(parar);
        parar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                VerSeFezRecordes();
                Categorias c = new Categorias();
                c.setVisible(true);
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
        TextosLetras();
    }

    private void VerSeFezRecordes() {
        String arquivo = "Res/JogosExtras/Forca/Recordes.txt", linha, f = "";
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                    if (!linha.equals("")) {
                        f += linha + "\n";
                    }
                }
                boolean b = true;
                f = f.substring(0, f.length() - 1);
                String recordestxt[] = f.split("\n");
                String RecordesNomes[] = recordestxt[0].split("-");
                if (pontos > Integer.parseInt(RecordesNomes[0])) {
                    String nome = JOptionPane.showInputDialog(null, "Parabéns, você fez o 1º recorde!\nDigite seu nome");
                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(pontos + "-" + nome + "\n" + recordestxt[1] + "\n" + recordestxt[2] + "\n" + recordestxt[3] + "\n" + recordestxt[4]);
                        bw.close();
                        fw.close();
                        b = false;
                    } catch (IOException ex) {
                        Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RecordesNomes = recordestxt[1].split("-");
                if (pontos > Integer.parseInt(RecordesNomes[0]) && b) {
                    String nome = JOptionPane.showInputDialog(null, "Parabéns, você fez o 2º recorde!\nDigite seu nome");
                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(recordestxt[0] + "\n" + pontos + "-" + nome + "\n" + recordestxt[2] + "\n" + recordestxt[3] + "\n" + recordestxt[4]);
                        bw.close();
                        fw.close();
                        b = false;
                    } catch (IOException ex) {
                        Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RecordesNomes = recordestxt[2].split("-");
                if (pontos > Integer.parseInt(RecordesNomes[0]) && b) {
                    String nome = JOptionPane.showInputDialog(null, "Parabéns, você fez o 3º recorde!\nDigite seu nome");
                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(recordestxt[0] + "\n" + recordestxt[1] + "\n" + pontos + "-" + nome + "\n" + recordestxt[3] + "\n" + recordestxt[4]);
                        bw.close();
                        fw.close();
                        b = false;
                    } catch (IOException ex) {
                        Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RecordesNomes = recordestxt[3].split("-");
                if (pontos > Integer.parseInt(RecordesNomes[0]) && b) {
                    String nome = JOptionPane.showInputDialog(null, "Parabéns, você fez o 4º recorde!\nDigite seu nome");
                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(recordestxt[0] + "\n" + recordestxt[1] + "\n" + recordestxt[2] + "\n" + pontos + "-" + nome + "\n" + recordestxt[4]);
                        bw.close();
                        fw.close();
                        b = false;
                    } catch (IOException ex) {
                        Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RecordesNomes = recordestxt[4].split("-");
                if (pontos > Integer.parseInt(RecordesNomes[0]) && b) {
                    String nome = JOptionPane.showInputDialog(null, "Parabéns, você fez o 5º recorde!\nDigite seu nome");
                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(recordestxt[0] + "\n" + recordestxt[1] + "\n" + recordestxt[2] + "\n" + recordestxt[3] + "\n" + pontos + "-" + nome);
                        bw.close();
                        fw.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void MudarPalavra() {
        Random r = new Random();
        String b[] = palavrasPassadas.split("@");
        if (b.length == palavrasedicasG.length) {
            palavrasPassadas = "";
        }
        if (palavrasPassadas.equals("")) {
            int cont = r.nextInt(palavrasedicasG.length);
            String bloco[] = palavrasedicasG[cont].split("-");
            palavrasPassadas += "" + cont;
            dica.setText("Dica: " + bloco[0]);
            palavra = bloco[1];
        } else {
            boolean f = true;
            while (f) {
                int cont = r.nextInt(palavrasedicasG.length);
                String blocoC[] = palavrasPassadas.split("@");
                int j = 0;
                for (int i = 0; i < blocoC.length; i++) {
                    if (cont == Integer.parseInt(blocoC[i])) {
                        j++;
                    }
                }
                if (j == 0) {
                    f = false;
                    palavrasPassadas += "@" + cont;
                    String bloco[] = palavrasedicasG[cont].split("-");
                    dica.setText("Dica: " + bloco[0]);
                    palavra = bloco[1];
                }
            }
        }
    }

    private void Reiniciar() {
        int contador = 0;
        while (contador < 26) {
            botoesDasLetras[contador].setForeground(Color.black);
            botoesDasLetras[contador].setVisible(true);
            contador++;
        }
        forca.setIcon(new javax.swing.ImageIcon("Res/forc.jpg"));
        estadoDoPersonagem = 0;
        letrasErradas.setText("Letras Erradas: ");
        TextosLetras();
    }

    private void TextosLetras() {
        botoesDasLetras[0].setText("A");
        botoesDasLetras[1].setText("B");
        botoesDasLetras[2].setText("C");
        botoesDasLetras[3].setText("D");
        botoesDasLetras[4].setText("E");
        botoesDasLetras[5].setText("F");
        botoesDasLetras[6].setText("G");
        botoesDasLetras[7].setText("H");
        botoesDasLetras[8].setText("I");
        botoesDasLetras[9].setText("J");
        botoesDasLetras[10].setText("K");
        botoesDasLetras[11].setText("L");
        botoesDasLetras[12].setText("M");
        botoesDasLetras[13].setText("N");
        botoesDasLetras[14].setText("O");
        botoesDasLetras[15].setText("P");
        botoesDasLetras[16].setText("Q");
        botoesDasLetras[17].setText("R");
        botoesDasLetras[18].setText("S");
        botoesDasLetras[19].setText("T");
        botoesDasLetras[20].setText("U");
        botoesDasLetras[21].setText("V");
        botoesDasLetras[22].setText("W");
        botoesDasLetras[23].setText("X");
        botoesDasLetras[24].setText("Y");
        botoesDasLetras[25].setText("Z");
    }

    private void CriarCod() {
        caracteres = "";
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.substring(i, i + 1).equals(" ")) {
                caracteres += "-";
            } else {
                caracteres += "@";
            }
        }
    }

    private boolean TesteVitoria(String testevenceu) {
        for (int i = 0; i < testevenceu.length(); i++) {
            if (testevenceu.substring(i, i + 1).equals("_")) {
                return false;

            }
        }
        return true;
    }

    private void Palavra() {
        int numLetras = palavra.length();
        stringParaCodificacao = "";
        for (int i = 0; i < numLetras; i++) {
            String caracter = palavra.substring(i, i + 1);
            if (caracter.equals(" ")) {
                stringParaCodificacao += "    ";
            } else {
                if (i == 0) {
                    stringParaCodificacao += "___";
                } else {
                    stringParaCodificacao += "  ___";
                }
            }
        }
        codificacao.setText(stringParaCodificacao);

    }
}
