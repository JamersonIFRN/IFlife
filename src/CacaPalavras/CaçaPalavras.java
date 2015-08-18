package CacaPalavras;

import Forca.ForcaJogo;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DANIEL
 */
public class CaçaPalavras extends JFrame {

    Timer tempo, esperar, relogio;
    String[] linha;
    String[] Palavra;
    static JLabel quadrados[][] = new JLabel[15][21];
    JLabel PalavarsdoCaca[];
    JLabel labelminuto = new JLabel("00:");
    JLabel labelsegundo = new JLabel("00");
    static String letraDosQuadrados[][] = new String[15][21];
    static int numCliques = 0, colunaLabel1, colunaLabel2, linhaLabel1, linhaLabel2;
    static String ultimoLbClicado = "";
    JPanel paneldosQuadrados = new JPanel();
    JPanel painelDasPalavras = new JPanel();
    JPanel painelTempo = new JPanel();
    boolean podeJogar = true;
    String caçatxt;
    int numeroPalavras = 0, min = 0, seg = 0, numcaça;
    JButton desistir = new JButton("Desistir");
    ActionListener action;
    JPanel fundo = new JPanel();

    public CaçaPalavras(String caça, int numcaça) {
        setUndecorated(true);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        caçatxt = caça;
        this.numcaça = numcaça;
        inicializar();
        setTitle("Caca Palavras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        fundo.setBounds(0, 0, 1200, 700);
        fundo.setLayout(null);
        fundo.setBackground(new Color(40, 215, 188));
        fundo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(fundo);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        getContentPane().setBackground(new Color(40, 215, 188));
        paneldosQuadrados.setSize(100, 100);
        painelDasPalavras.setSize(1200, 700);
        paneldosQuadrados.setVisible(true);
        painelDasPalavras.setVisible(true);
        paneldosQuadrados.setBackground(Color.white);
        painelDasPalavras.setBackground(Color.white);
        fundo.add(paneldosQuadrados);
        fundo.add(painelDasPalavras);
        paneldosQuadrados.setBounds(855, 80, 250, 550);
        painelDasPalavras.setBounds(15, 30, 815, 600);
        paneldosQuadrados.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        painelDasPalavras.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        painelDasPalavras.setLayout(new GridLayout(15, 21));
        setSize(1190, 695);
        for (int i = 0; i < 525; i += 35) {
            for (int j = 0; j < 735; j += 35) {
                quadrados[i / 35][j / 35].setBounds(j, i, 35, 35);
                painelDasPalavras.add(quadrados[i / 35][j / 35]);
            }
        }
        desistir.setBounds(995, 30, 100, 35);
        desistir.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        desistir.setBackground(Color.white);
        fundo.add(desistir);
        desistir.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PrincipalCaca pc = new PrincipalCaca();
                pc.setVisible(true);
                tempo.stop();
                esperar.stop();
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
                desistir.setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                desistir.setForeground(Color.black);
            }
        });
        painelTempo.setBounds(855, 30, 100, 35);
        painelTempo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        painelTempo.setBackground(Color.white);
        fundo.add(painelTempo);
        labelminuto.setBounds(0, 0, 45, 35);
        labelminuto.setFont(new Font("Arial Narrow", 1, 22));
        painelTempo.add(labelminuto);
        labelsegundo.setBounds(50, 0, 45, 35);
        labelsegundo.setFont(new Font("Arial Narrow", 1, 22));
        painelTempo.add(labelsegundo);
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
                    Fim();
                } else {
                    labelminuto.setText("" + min + ":");
                }

            }
        };
        relogio = new Timer(1000, action);
        relogio.start();
    }

    private void Fim() {
        FimJogo fj = new FimJogo("Acabou o tempo, você perdeu!", numcaça);
        fj.setVisible(true);
        tempo.stop();
        esperar.stop();
        relogio.stop();
        dispose();
    }

    private boolean VerSeFezRecordes() {
        String arquivo = "Res/JogosExtras/CacaPalavras/Recordes.txt", linha, f = "";
        int pontos = 150000 / ((min * 60) + seg);
        boolean naofezrecorde = true;
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
                f = f.substring(0, f.length() - 1);
                String recordestxt[] = f.split("\n");
                String RecordesNomes[] = recordestxt[0].split("-");
                if (pontos > Integer.parseInt(RecordesNomes[0]) && naofezrecorde) {
                    String nome = JOptionPane.showInputDialog(null, "Parabéns, você fez o 1º recorde!\nDigite seu nome");
                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(pontos + "-" + nome + "\n" + recordestxt[1] + "\n" + recordestxt[2] + "\n" + recordestxt[3] + "\n" + recordestxt[4]);
                        naofezrecorde = false;
                        bw.close();
                        fw.close();
                        return true;
                    } catch (IOException ex) {
                        Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RecordesNomes = recordestxt[1].split("-");
                if (pontos > Integer.parseInt(RecordesNomes[0]) && naofezrecorde) {
                    String nome = JOptionPane.showInputDialog(null, "Parabéns, você fez o 2º recorde!\nDigite seu nome");
                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(recordestxt[0] + "\n" + pontos + "-" + nome + "\n" + recordestxt[2] + "\n" + recordestxt[3] + "\n" + recordestxt[4]);
                        naofezrecorde = false;
                        bw.close();
                        fw.close();
                        return true;
                    } catch (IOException ex) {
                        Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RecordesNomes = recordestxt[2].split("-");
                if (pontos > Integer.parseInt(RecordesNomes[0]) && naofezrecorde) {
                    String nome = JOptionPane.showInputDialog(null, "Parabéns, você fez o 3º recorde!\nDigite seu nome");
                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(recordestxt[0] + "\n" + recordestxt[1] + "\n" + pontos + "-" + nome + "\n" + recordestxt[3] + "\n" + recordestxt[4]);
                        naofezrecorde = false;
                        bw.close();
                        fw.close();
                        return true;
                    } catch (IOException ex) {
                        Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RecordesNomes = recordestxt[3].split("-");
                if (pontos > Integer.parseInt(RecordesNomes[0]) && naofezrecorde) {
                    String nome = JOptionPane.showInputDialog(null, "Parabéns, você fez o 4º recorde!\nDigite seu nome");
                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(recordestxt[0] + "\n" + recordestxt[1] + "\n" + recordestxt[2] + "\n" + pontos + "-" + nome + "\n" + recordestxt[4]);
                        naofezrecorde = false;
                        bw.close();
                        fw.close();
                        return true;
                    } catch (IOException ex) {
                        Logger.getLogger(ForcaJogo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RecordesNomes = recordestxt[4].split("-");
                if (pontos > Integer.parseInt(RecordesNomes[0]) && naofezrecorde) {
                    String nome = JOptionPane.showInputDialog(null, "Parabéns, você fez o 5º recorde!\nDigite seu nome");
                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(recordestxt[0] + "\n" + recordestxt[1] + "\n" + recordestxt[2] + "\n" + recordestxt[3] + "\n" + pontos + "-" + nome);
                        bw.close();
                        fw.close();
                        return true;
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
        return false;
    }

    private void TesteVitoria() {
        if (numeroPalavras == Palavra.length) {
            ActionListener action;
            action = new ActionListener() {
                public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                    esperar.stop();
                    relogio.stop();
                    if (VerSeFezRecordes()) {
                        Recordes r = new Recordes("fim", numcaça);
                        r.setVisible(true);
                    } else {
                        FimJogo fj = new FimJogo("Parabéns, você venceu!", numcaça);
                        fj.setVisible(true);
                    }
                    tempo.stop();
                    esperar.stop();
                    relogio.stop();
                    dispose();
                }
            };
            esperar = new Timer(1500, action);
            esperar.start();
        }
    }

    private void inicializar() {
        linha = caçatxt.split("@");
        String[] coluna = linha[1].split("#");
        Palavra = linha[2].split("-");

        paneldosQuadrados.setLayout(new GridLayout(Palavra.length, 0));
        PalavarsdoCaca = new JLabel[Palavra.length];
        for (int i = 0; i < Palavra.length; i++) {
            PalavarsdoCaca[i] = new JLabel(Palavra[i], JLabel.CENTER);
            PalavarsdoCaca[i].setFont(new Font("Arial Narrow", 1, 17));
            paneldosQuadrados.add(PalavarsdoCaca[i]);
        }
        for (int i = 0; i < 15; i++) {
            String[] col = coluna[i].split(" ");
            for (int u = 0; u < 21; u++) {
                letraDosQuadrados[i][u] = col[u];
            }
        }


        for (int linhaq = 0; linhaq < 15; linhaq++) {
            for (int colunaq = 0; colunaq < 21; colunaq++) {
                quadrados[linhaq][colunaq] = new JLabel(letraDosQuadrados[linhaq][colunaq], JLabel.CENTER);
                quadrados[linhaq][colunaq].setOpaque(true);
                quadrados[linhaq][colunaq].setBackground(Color.white);
                quadrados[linhaq][colunaq].setForeground(Color.black);
                quadrados[linhaq][colunaq].setFont(new Font("Arial Narrow", 1, 16));
                quadrados[linhaq][colunaq].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                final int vert = linhaq;
                final int horiz = colunaq;
                quadrados[linhaq][colunaq].addMouseListener(new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        if (podeJogar) {
                            quadrados[vert][horiz].setOpaque(true);
                            if (quadrados[vert][horiz].getBackground() != Color.GREEN) {
                                quadrados[vert][horiz].setBackground(Color.black);
                                quadrados[vert][horiz].setForeground(Color.white);
                                quadrados[vert][horiz].setBorder(BorderFactory.createLineBorder(Color.white, 1));
                            }
                            if (!quadrados[vert][horiz].equals(ultimoLbClicado)) {
                                if (numCliques == 0) {
                                    colunaLabel1 = horiz;
                                    linhaLabel1 = vert;
                                }
                                numCliques++;
                                ultimoLbClicado = quadrados[vert][horiz].getText();
                            } else {
                            }

                            if (numCliques == 2) {
                                podeJogar = false;
                                if (colunaLabel1 == horiz) {
                                    if (linhaLabel1 < vert) {
                                        String palavrafeita = "";
                                        for (int i = linhaLabel1; i <= vert; i++) {
                                            palavrafeita += quadrados[i][colunaLabel1].getText();
                                        }
                                        Palavras(1, palavrafeita);
                                    } else {
                                        String palavrafeita = "";
                                        for (int i = linhaLabel1; i >= vert; i--) {
                                            palavrafeita += quadrados[i][colunaLabel1].getText();
                                        }
                                        Palavras(2, palavrafeita);
                                    }
                                } else if (linhaLabel1 == vert) {
                                    if (colunaLabel1 < horiz) {
                                        String palavrafeita = "";
                                        for (int i = colunaLabel1; i <= horiz; i++) {
                                            palavrafeita += quadrados[linhaLabel1][i].getText();
                                        }
                                        Palavras(3, palavrafeita);
                                    } else {
                                        String palavrafeita = "";
                                        for (int i = colunaLabel1; i >= horiz; i--) {
                                            palavrafeita += quadrados[linhaLabel1][i].getText();
                                        }
                                        Palavras(4, palavrafeita);
                                    }
                                } else if (colunaLabel1 - horiz == linhaLabel1 - vert) {
                                    if (colunaLabel1 < horiz) {
                                        int l = linhaLabel1;
                                        while (l <= 21) {
                                            if (linhaLabel1 == l) {
                                                int ly = linhaLabel1;
                                                int lx = colunaLabel1;
                                                String palavrafeita = "";
                                                while (ly <= vert && lx <= horiz) {
                                                    palavrafeita += quadrados[ly][lx].getText();
                                                    ly++;
                                                    lx++;
                                                }
                                                Palavras(5, palavrafeita);
                                            }
                                            l++;
                                        }
                                    } else {
                                        boolean o = true;
                                        int l = colunaLabel1;
                                        while (l >= 0 && o) {
                                            int ly = linhaLabel1;
                                            int lx = colunaLabel1;
                                            String palavrafeita = "";
                                            while (ly >= vert && lx >= horiz) {
                                                palavrafeita += quadrados[ly][lx].getText();
                                                ly--;
                                                lx--;
                                                o = false;
                                            }
                                            Palavras(6, palavrafeita);
                                            l--;
                                        }
                                    }
                                } else if ((horiz - colunaLabel1 == (vert - linhaLabel1) * -1)) {
                                    boolean cv = true;
                                    if (colunaLabel1 > horiz) {
                                        int l = linhaLabel1;
                                        while (l <= 21 && cv) {
                                            if (linhaLabel1 == l) {
                                                int ly = linhaLabel1;
                                                int lx = colunaLabel1;
                                                String palavrafeita = "";
                                                while (ly != vert + 1 && lx != horiz - 1) {
                                                    palavrafeita += quadrados[ly][lx].getText();
                                                    ly++;
                                                    lx--;
                                                    cv = false;
                                                }
                                                Palavras(7, palavrafeita);
                                            }
                                            l++;
                                        }
                                    } else {
                                        int l = linhaLabel1;
                                        while (l <= 21 && cv) {
                                            if (linhaLabel1 == l) {
                                                int ly = linhaLabel1;
                                                int lx = colunaLabel1;
                                                String palavrafeita = "";
                                                while (ly != vert - 1 && lx != horiz + 1) {
                                                    palavrafeita += quadrados[ly][lx].getText();
                                                    ly--;
                                                    lx++;
                                                    cv = false;
                                                }
                                                Palavras(8, palavrafeita);
                                            }
                                            l++;
                                        }
                                    }
                                } else {
                                    podeJogar = true;
                                    if (quadrados[linhaLabel1][colunaLabel1].getBackground() != Color.GREEN) {
                                        quadrados[linhaLabel1][colunaLabel1].setOpaque(true);
                                        quadrados[linhaLabel1][colunaLabel1].setBackground(Color.white);
                                        quadrados[linhaLabel1][colunaLabel1].setForeground(Color.black);
                                        quadrados[linhaLabel1][colunaLabel1].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                    }
                                    if (quadrados[vert][horiz].getBackground() != Color.GREEN) {
                                        quadrados[vert][horiz].setOpaque(true);
                                        quadrados[vert][horiz].setBackground(Color.white);
                                        quadrados[vert][horiz].setForeground(Color.black);
                                        quadrados[vert][horiz].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                    }
                                    numCliques = 0;
                                }
                                numCliques = 0;
                                ultimoLbClicado = "";
                            }
                        }
                    }

                    public void mousePressed(MouseEvent e) {
                    }

                    public void mouseReleased(MouseEvent e) {
                    }

                    public void mouseEntered(MouseEvent e) {
                    }

                    public void mouseExited(MouseEvent e) {
                    }

                    private void Palavras(int p, String pala) {
                        boolean palavracorreta = true;
                        if (p == 1) {
                            Palavra = linha[2].split("-");
                            for (int j = 0; j < Palavra.length; j++) {
                                if (pala.equals(Palavra[j])) {
                                    PalavarsdoCaca[j].setText("<html><s>" + PalavarsdoCaca[j].getText() + "</s></html>");
                                    PalavarsdoCaca[j].setForeground(Color.red);
                                    for (int i = linhaLabel1; i <= vert; i++) {
                                        quadrados[i][colunaLabel1].setOpaque(true);
                                        quadrados[i][colunaLabel1].setBackground(Color.GREEN);
                                        quadrados[i][colunaLabel1].setForeground(Color.black);
                                        quadrados[i][colunaLabel1].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                        palavracorreta = false;
                                        podeJogar = true;
                                    }
                                }
                            }
                            if (palavracorreta) {
                                for (int i = linhaLabel1; i <= vert; i++) {
                                    if (quadrados[i][colunaLabel1].getBackground() != Color.GREEN) {
                                        quadrados[i][colunaLabel1].setOpaque(true);
                                        quadrados[i][colunaLabel1].setBackground(Color.RED);
                                        quadrados[i][colunaLabel1].setForeground(Color.black);
                                        quadrados[i][colunaLabel1].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                    }
                                }
                                final int o = linhaLabel1;
                                final int a = vert;
                                ActionListener action = new ActionListener() {
                                    public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                        for (int i = o; i <= a; i++) {
                                            if (quadrados[i][colunaLabel1].getBackground() != Color.GREEN) {
                                                quadrados[i][colunaLabel1].setOpaque(true);
                                                quadrados[i][colunaLabel1].setBackground(Color.white);
                                                quadrados[i][colunaLabel1].setForeground(Color.black);
                                                quadrados[i][colunaLabel1].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                            }
                                            podeJogar = true;
                                            tempo.stop();
                                        }
                                    }
                                };
                                tempo = new Timer(1500, action);
                                tempo.start();
                            } else {
                                numeroPalavras++;
                                TesteVitoria();
                            }

                        }
                        if (p == 2) {
                            Palavra = linha[2].split("-");
                            for (int j = 0; j < Palavra.length; j++) {
                                int tamanhopalav = Palavra.length;
                                if (j == Palavra.length - 1) {
                                    if (pala.equals(Palavra[j].substring(0, Palavra[j].length() - 1))) {
                                        for (int i = vert; i <= linhaLabel1; i++) {
                                            quadrados[i][colunaLabel1].setOpaque(true);
                                            quadrados[i][colunaLabel1].setBackground(Color.GREEN);
                                            quadrados[i][colunaLabel1].setForeground(Color.black);
                                            quadrados[i][colunaLabel1].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                            palavracorreta = false;
                                            podeJogar = true;
                                        }
                                    }
                                } else {
                                    if (pala.equals(Palavra[j])) {
                                        PalavarsdoCaca[j].setText("<html><s>" + PalavarsdoCaca[j].getText() + "</s></html>");
                                        PalavarsdoCaca[j].setForeground(Color.red);
                                        for (int i = vert; i <= linhaLabel1; i++) {
                                            quadrados[i][colunaLabel1].setOpaque(true);
                                            quadrados[i][colunaLabel1].setBackground(Color.GREEN);
                                            quadrados[i][colunaLabel1].setForeground(Color.black);
                                            quadrados[i][colunaLabel1].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                            palavracorreta = false;
                                            podeJogar = true;
                                        }
                                    }
                                }
                            }
                            if (palavracorreta) {
                                for (int i = vert; i <= linhaLabel1; i++) {
                                    if (quadrados[i][colunaLabel1].getBackground() != Color.GREEN) {
                                        quadrados[i][colunaLabel1].setOpaque(true);
                                        quadrados[i][colunaLabel1].setBackground(Color.RED);
                                        quadrados[i][colunaLabel1].setForeground(Color.black);
                                        quadrados[i][colunaLabel1].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                    }
                                }
                                final int o = linhaLabel1;
                                final int a = vert;
                                ActionListener action = new ActionListener() {
                                    public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                        for (int i = a; i <= o; i++) {
                                            if (quadrados[i][colunaLabel1].getBackground() != Color.GREEN) {
                                                quadrados[i][colunaLabel1].setOpaque(true);
                                                quadrados[i][colunaLabel1].setBackground(Color.white);
                                                quadrados[i][colunaLabel1].setForeground(Color.black);
                                                quadrados[i][colunaLabel1].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                            }
                                            podeJogar = true;
                                            tempo.stop();
                                        }
                                    }
                                };
                                tempo = new Timer(1500, action);
                                tempo.start();
                            } else {
                                numeroPalavras++;
                                TesteVitoria();
                            }
                        }
                        if (p == 3) {
                            Palavra = linha[2].split("-");
                            Palavra[Palavra.length - 1] = Palavra[Palavra.length - 1].substring(0, Palavra[Palavra.length - 1].length() - 1);
                            for (int j = 0; j < Palavra.length; j++) {
                                if (pala.equals(Palavra[j])) {
                                    PalavarsdoCaca[j].setText("<html><s>" + PalavarsdoCaca[j].getText() + "</s></html>");
                                    PalavarsdoCaca[j].setForeground(Color.red);
                                    for (int i = colunaLabel1; i <= horiz; i++) {
                                        quadrados[linhaLabel1][i].setOpaque(true);
                                        quadrados[linhaLabel1][i].setBackground(Color.GREEN);
                                        quadrados[linhaLabel1][i].setForeground(Color.black);
                                        quadrados[linhaLabel1][i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                        palavracorreta = false;
                                        podeJogar = true;
                                    }
                                }
                            }
                            if (palavracorreta) {
                                for (int i = colunaLabel1; i <= horiz; i++) {
                                    if (quadrados[linhaLabel1][i].getBackground() != Color.GREEN) {
                                        quadrados[linhaLabel1][i].setOpaque(true);
                                        quadrados[linhaLabel1][i].setBackground(Color.RED);
                                        quadrados[linhaLabel1][i].setForeground(Color.black);
                                        quadrados[linhaLabel1][i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                    }
                                }
                                final int o = colunaLabel1;
                                final int a = horiz;
                                ActionListener action = new ActionListener() {
                                    public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                        for (int i = o; i <= a; i++) {
                                            if (quadrados[linhaLabel1][i].getBackground() != Color.GREEN) {
                                                quadrados[linhaLabel1][i].setOpaque(true);
                                                quadrados[linhaLabel1][i].setBackground(Color.white);
                                                quadrados[linhaLabel1][i].setForeground(Color.black);
                                                quadrados[linhaLabel1][i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                            }
                                            podeJogar = true;
                                            tempo.stop();
                                        }
                                    }
                                };
                                tempo = new Timer(1500, action);
                                tempo.start();
                            } else {
                                numeroPalavras++;
                                TesteVitoria();
                            }
                        }
                        if (p == 4) {
                            Palavra = linha[2].split("-");
                            Palavra[Palavra.length - 1] = Palavra[Palavra.length - 1].substring(0, Palavra[Palavra.length - 1].length() - 1);
                            for (int j = 0; j < Palavra.length; j++) {
                                if (pala.equalsIgnoreCase(Palavra[j])) {
                                    PalavarsdoCaca[j].setText("<html><s>" + PalavarsdoCaca[j].getText() + "</s></html>");
                                    PalavarsdoCaca[j].setForeground(Color.red);
                                    for (int i = horiz; i <= colunaLabel1; i++) {
                                        quadrados[linhaLabel1][i].setOpaque(true);
                                        quadrados[linhaLabel1][i].setBackground(Color.GREEN);
                                        quadrados[linhaLabel1][i].setForeground(Color.black);
                                        quadrados[linhaLabel1][i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                        palavracorreta = false;
                                        podeJogar = true;
                                    }
                                }
                            }
                            if (palavracorreta) {
                                for (int i = horiz; i <= colunaLabel1; i++) {
                                    if (quadrados[linhaLabel1][i].getBackground() != Color.GREEN) {
                                        quadrados[linhaLabel1][i].setOpaque(true);
                                        quadrados[linhaLabel1][i].setBackground(Color.RED);
                                        quadrados[linhaLabel1][i].setForeground(Color.black);
                                        quadrados[linhaLabel1][i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                    }
                                }
                                final int o = horiz;
                                final int a = colunaLabel1;
                                ActionListener action = new ActionListener() {
                                    public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                        for (int i = o; i <= a; i++) {
                                            if (quadrados[linhaLabel1][i].getBackground() != Color.GREEN) {
                                                quadrados[linhaLabel1][i].setOpaque(true);
                                                quadrados[linhaLabel1][i].setBackground(Color.white);
                                                quadrados[linhaLabel1][i].setForeground(Color.black);
                                                quadrados[linhaLabel1][i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                            }
                                            podeJogar = true;
                                            tempo.stop();
                                        }
                                    }
                                };
                                tempo = new Timer(1500, action);
                                tempo.start();
                            } else {
                                numeroPalavras++;
                                TesteVitoria();
                            }
                        }
                        if (p == 5) {
                            Palavra = linha[2].split("-");
                            Palavra[Palavra.length - 1] = Palavra[Palavra.length - 1].substring(0, Palavra[Palavra.length - 1].length() - 1);
                            for (int j = 0; j < Palavra.length; j++) {
                                if (pala.equalsIgnoreCase(Palavra[j])) {
                                    PalavarsdoCaca[j].setText("<html><s>" + PalavarsdoCaca[j].getText() + "</s></html>");
                                    PalavarsdoCaca[j].setForeground(Color.red);
                                    int ly = linhaLabel1;
                                    int lx = colunaLabel1;
                                    while (ly <= vert && lx <= horiz) {
                                        quadrados[ly][lx].setOpaque(true);
                                        quadrados[ly][lx].setBackground(Color.GREEN);
                                        quadrados[ly][lx].setForeground(Color.BLACK);
                                        quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                                        ly++;
                                        podeJogar = true;
                                        lx++;
                                    }
                                    palavracorreta = false;
                                }
                            }
                            if (palavracorreta) {
                                int ly = linhaLabel1;
                                int lx = colunaLabel1;
                                while (ly <= vert && lx <= horiz) {
                                    if (quadrados[ly][lx].getBackground() != Color.GREEN) {
                                        quadrados[ly][lx].setOpaque(true);
                                        quadrados[ly][lx].setBackground(Color.RED);
                                        quadrados[ly][lx].setForeground(Color.BLACK);
                                        quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                    }
                                    ly++;
                                    lx++;
                                }
                                ActionListener action = new ActionListener() {
                                    public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                        int ly = linhaLabel1;
                                        int lx = colunaLabel1;
                                        while (ly <= vert && lx <= horiz) {
                                            if (quadrados[ly][lx].getBackground() != Color.GREEN) {
                                                quadrados[ly][lx].setOpaque(true);
                                                quadrados[ly][lx].setBackground(Color.white);
                                                quadrados[ly][lx].setForeground(Color.black);
                                                quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                            }
                                            ly++;
                                            lx++;
                                        }
                                        podeJogar = true;
                                        tempo.stop();
                                    }
                                };
                                tempo = new Timer(1500, action);
                                tempo.start();
                            } else {
                                numeroPalavras++;
                                TesteVitoria();
                            }
                        }
                        if (p == 6) {
                            Palavra = linha[2].split("-");
                            Palavra[Palavra.length - 1] = Palavra[Palavra.length - 1].substring(0, Palavra[Palavra.length - 1].length() - 1);
                            for (int j = 0; j < Palavra.length; j++) {
                                if (pala.equalsIgnoreCase(Palavra[j])) {
                                    PalavarsdoCaca[j].setText("<html><s>" + PalavarsdoCaca[j].getText() + "</s></html>");
                                    PalavarsdoCaca[j].setForeground(Color.red);
                                    int ly = linhaLabel1;
                                    int lx = colunaLabel1;
                                    while (ly >= vert && lx >= horiz) {
                                        quadrados[ly][lx].setOpaque(true);
                                        quadrados[ly][lx].setBackground(Color.GREEN);
                                        quadrados[ly][lx].setForeground(Color.black);
                                        quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                        ly--;
                                        lx--;
                                        podeJogar = true;
                                    }
                                    palavracorreta = false;
                                }
                            }
                            if (palavracorreta) {
                                int ly = linhaLabel1;
                                int lx = colunaLabel1;
                                while (ly >= vert && lx >= horiz) {
                                    if (quadrados[ly][lx].getBackground() != Color.GREEN) {
                                        quadrados[ly][lx].setOpaque(true);
                                        quadrados[ly][lx].setBackground(Color.RED);
                                        quadrados[ly][lx].setForeground(Color.black);
                                        quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                    }
                                    ly--;
                                    lx--;
                                }
                                ActionListener action = new ActionListener() {
                                    public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                        int ly = linhaLabel1;
                                        int lx = colunaLabel1;
                                        while (ly >= vert && lx >= horiz) {
                                            if (quadrados[ly][lx].getBackground() != Color.GREEN) {
                                                quadrados[ly][lx].setOpaque(true);
                                                quadrados[ly][lx].setBackground(Color.white);
                                                quadrados[ly][lx].setForeground(Color.black);
                                                quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                            }
                                            ly--;
                                            lx--;
                                        }
                                        podeJogar = true;
                                        tempo.stop();
                                    }
                                };
                                tempo = new Timer(1500, action);
                                tempo.start();
                            } else {
                                numeroPalavras++;
                                TesteVitoria();
                            }
                        }
                        if (p == 7) {
                            Palavra = linha[2].split("-");
                            Palavra[Palavra.length - 1] = Palavra[Palavra.length - 1].substring(0, Palavra[Palavra.length - 1].length() - 1);
                            for (int j = 0; j < Palavra.length; j++) {
                                if (pala.equalsIgnoreCase(Palavra[j])) {
                                    PalavarsdoCaca[j].setText("<html><s>" + PalavarsdoCaca[j].getText() + "</s></html>");
                                    PalavarsdoCaca[j].setForeground(Color.red);
                                    int ly = linhaLabel1;
                                    int lx = colunaLabel1;
                                    while (ly != vert + 1 && lx != horiz - 1) {
                                        quadrados[ly][lx].setOpaque(true);
                                        quadrados[ly][lx].setBackground(Color.GREEN);
                                        quadrados[ly][lx].setForeground(Color.black);
                                        quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                        ly++;
                                        lx--;
                                        podeJogar = true;
                                    }
                                    palavracorreta = false;
                                }
                            }
                            if (palavracorreta) {
                                int ly = linhaLabel1;
                                int lx = colunaLabel1;
                                while (ly != vert + 1 && lx != horiz - 1) {
                                    if (quadrados[ly][lx].getBackground() != Color.GREEN) {
                                        quadrados[ly][lx].setOpaque(true);
                                        quadrados[ly][lx].setBackground(Color.RED);
                                        quadrados[ly][lx].setForeground(Color.black);
                                        quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                    }
                                    ly++;
                                    lx--;
                                }
                                ActionListener action = new ActionListener() {
                                    public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                        int ly = linhaLabel1;
                                        int lx = colunaLabel1;
                                        while (ly != vert + 1 && lx != horiz - 1) {
                                            if (quadrados[ly][lx].getBackground() != Color.GREEN) {
                                                quadrados[ly][lx].setOpaque(true);
                                                quadrados[ly][lx].setBackground(Color.white);
                                                quadrados[ly][lx].setForeground(Color.black);
                                                quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                            }
                                            ly++;
                                            lx--;
                                        }
                                        podeJogar = true;
                                        tempo.stop();
                                    }
                                };
                                tempo = new Timer(1500, action);
                                tempo.start();
                            } else {
                                numeroPalavras++;
                                TesteVitoria();
                            }
                        }
                        if (p == 8) {
                            Palavra = linha[2].split("-");
                            Palavra[Palavra.length - 1] = Palavra[Palavra.length - 1].substring(0, Palavra[Palavra.length - 1].length() - 1);
                            for (int j = 0; j < Palavra.length; j++) {
                                if (pala.equalsIgnoreCase(Palavra[j])) {
                                    PalavarsdoCaca[j].setText("<html><s>" + PalavarsdoCaca[j].getText() + "</s></html>");
                                    PalavarsdoCaca[j].setForeground(Color.red);
                                    int ly = linhaLabel1;
                                    int lx = colunaLabel1;
                                    while (ly != vert - 1 && lx != horiz + 1) {
                                        quadrados[ly][lx].setOpaque(true);
                                        quadrados[ly][lx].setBackground(Color.GREEN);
                                        quadrados[ly][lx].setForeground(Color.black);
                                        quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                        ly--;
                                        lx++;
                                        podeJogar = true;
                                    }
                                    palavracorreta = false;
                                }
                            }
                            if (palavracorreta) {
                                int ly = linhaLabel1;
                                int lx = colunaLabel1;
                                while (ly != vert - 1 && lx != horiz + 1) {
                                    if (quadrados[ly][lx].getBackground() != Color.GREEN) {
                                        quadrados[ly][lx].setOpaque(true);
                                        quadrados[ly][lx].setBackground(Color.RED);
                                        quadrados[ly][lx].setForeground(Color.black);
                                        quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                    }
                                    ly--;
                                    lx++;
                                }
                                ActionListener action = new ActionListener() {
                                    public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                                        int ly = linhaLabel1;
                                        int lx = colunaLabel1;
                                        while (ly != vert - 1 && lx != horiz + 1) {
                                            if (quadrados[ly][lx].getBackground() != Color.GREEN) {
                                                quadrados[ly][lx].setOpaque(true);
                                                quadrados[ly][lx].setBackground(Color.white);
                                                quadrados[ly][lx].setForeground(Color.black);
                                                quadrados[ly][lx].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                            }
                                            ly--;
                                            lx++;
                                        }
                                        podeJogar = true;
                                        tempo.stop();
                                    }
                                };
                                tempo = new Timer(1500, action);
                                tempo.start();
                            } else {
                                numeroPalavras++;
                                TesteVitoria();
                            }
                        }

                    }
                });

            }
        }

    }
}
