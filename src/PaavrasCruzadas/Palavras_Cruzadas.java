package PaavrasCruzadas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DANIEL
 */
public class Palavras_Cruzadas extends JFrame {

    String[] linha;
    String[] Palavra;
    static JFormattedTextField caixasdetexto[][] = new JFormattedTextField[15][15];
    JLabel dicas[];
    static String CoresdeFundoCaixas[][] = new String[15][15];
    static String titulos[][] = new String[15][15];
    JPanel painelDicas = new JPanel();
    JPanel PainelQuadrados = new JPanel();
    JPanel paineisComTitulo[][] = new JPanel[15][15];
    int acertos;
    String pintou = "";
    JButton parar = new JButton("Sair");

    public Palavras_Cruzadas(String palavrac) {
        linha = palavrac.split("@");
        setUndecorated(true);
        inicializar();
        setTitle("Palavras cruzadas");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        setSize(1190, 695);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(new Color(40, 215, 188));
        parar.setBounds(25, 655, 100, 35);
        parar.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        parar.setBackground(Color.white);
        add(parar);
        parar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TelaPalavrasC t = new TelaPalavrasC();
                t.setVisible(true);
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

        painelDicas.setSize(100, 100);
        painelDicas.setVisible(true);
        painelDicas.setBackground(Color.white);
        painelDicas.setBounds(790, 15, 370, 630);
        painelDicas.setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 5));
        add(painelDicas);
        PainelQuadrados.setSize(1200, 700);
        PainelQuadrados.setVisible(true);
        PainelQuadrados.setBackground(Color.white);
        this.add(PainelQuadrados);
        PainelQuadrados.setBounds(20, 5, 750, 645);
        PainelQuadrados.setBorder(BorderFactory.createLineBorder(new Color(15, 35, 75), 5));
        PainelQuadrados.setLayout(new GridLayout(15, 21));

        caixasdetexto[0][0].requestFocus();

    }

    private void inicializar() {

        String[] coluna = linha[1].split("#");
        String[] coluna2 = linha[0].split("#");
        Palavra = linha[2].split(" ");
        String[] P = linha[3].split("#");
        dicas = new JLabel[P.length];
        painelDicas.setLayout(new GridLayout(dicas.length, 0));
        for (int i = 0; i < P.length; i++) {
            dicas[i] = new JLabel(P[i], JLabel.CENTER);
            dicas[i].setBounds(0, i * 40, 400, 80);
            dicas[i].setFont(new Font("Arial Narrow", Font.BOLD, 18));
            painelDicas.add(dicas[i]);
        }
        for (int i = 0; i < 15; i++) {
            String[] col = coluna[i].split(" ");
            for (int u = 0; u < 15; u++) {
                CoresdeFundoCaixas[i][u] = col[u];
            }
        }
        for (int i = 0; i < 15; i++) {
            String[] col = coluna2[i].split(" ");
            for (int iu = 0; iu < 15; iu++) {
                titulos[i][iu] = col[iu];
            }
        }

        for (int k = 0; k < 15; k++) {
            for (int l = 0; l < 15; l++) {
                caixasdetexto[k][l] = new JFormattedTextField();
                caixasdetexto[k][l].setHorizontalAlignment(javax.swing.JTextField.CENTER);
                caixasdetexto[k][l].setBorder(BorderFactory.createLineBorder(Color.white, 1));
                caixasdetexto[k][l].setFont(new Font("Arial Narrow", 1, 20));
                final int c = k, v = l;
                caixasdetexto[k][l].addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        caixasdetexto[c][v].requestFocus();

                        caixasdetexto[c][v].setText(caixasdetexto[c][v].getText());

                        caixasdetexto[c][v].selectAll();
                    }
                });
                paineisComTitulo[k][l] = new JPanel();
                paineisComTitulo[k][l].setVisible(true);
                paineisComTitulo[k][l].setBackground(Color.white);

                PainelQuadrados.add(paineisComTitulo[k][l]);
                paineisComTitulo[k][l].setLayout(null);
                caixasdetexto[k][l].setBounds(2, 14, 40, 30);
                caixasdetexto[k][l].setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                caixasdetexto[k][l].setBackground(Color.white);
                paineisComTitulo[k][l].add(caixasdetexto[k][l]);
                try {
                    caixasdetexto[k][l].setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*")));
                } catch (ParseException ex) {
                    Logger.getLogger(Palavras_Cruzadas.class.getName()).log(Level.SEVERE, null, ex);
                }
                caixasdetexto[k][l].setBorder(BorderFactory.createLineBorder(Color.white, 1));
                if (CoresdeFundoCaixas[k][l].equals("1")) {
                    if (titulos[k][l].equals("$")) {
                        paineisComTitulo[k][l].setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(15, 35, 75), 1), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), " ")));
                    } else {
                        if (titulos[k][l].length() == 2) {
                            paineisComTitulo[k][l].setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(15, 35, 75), 1), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), titulos[k][l].substring(0, 1))));
                        } else {
                            paineisComTitulo[k][l].setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(15, 35, 75), 1), javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), titulos[k][l].substring(0, 2))));
                        }
                    }
                } else {
                    paineisComTitulo[k][l].setBackground(Color.black);
                    caixasdetexto[k][l].setBackground(Color.black);
                    caixasdetexto[k][l].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    caixasdetexto[k][l].setEditable(false);
                }


                final int y = k;
                final int x = l;

                caixasdetexto[k][l].addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            if (x == 14) {
                                int p = 0;
                                if (!CoresdeFundoCaixas[y][p].equals("1")) {
                                    while (!CoresdeFundoCaixas[y][p].equals("1")) {
                                        p++;
                                    }
                                    caixasdetexto[y][p].requestFocus();
                                    caixasdetexto[y][p].selectAll();
                                } else {
                                    caixasdetexto[y][0].requestFocus();
                                    caixasdetexto[y][0].selectAll();
                                }

                            } else if (x == 13 && !CoresdeFundoCaixas[y][14].equals("1")) {
                                int p = 0;
                                while (!CoresdeFundoCaixas[y][p].equals("1")) {
                                    p++;
                                }
                                caixasdetexto[y][p].requestFocus();
                                caixasdetexto[y][p].selectAll();
                            } else {
                                int p = x;
                                if (!CoresdeFundoCaixas[y][x + 1].equals("1")) {
                                    while (!CoresdeFundoCaixas[y][p + 1].equals("1")) {
                                        if (p == 13) {
                                            p = -1;
                                        } else {
                                            p++;
                                        }
                                    }
                                    caixasdetexto[y][p + 1].requestFocus();
                                    caixasdetexto[y][p + 1].selectAll();
                                } else {
                                    caixasdetexto[y][x + 1].requestFocus();
                                    caixasdetexto[y][x + 1].selectAll();
                                }
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            if (x == 0) {
                                int p = 14;
                                if (!CoresdeFundoCaixas[y][p].equals("1")) {
                                    while (!CoresdeFundoCaixas[y][p].equals("1")) {
                                        p--;
                                    }
                                    caixasdetexto[y][p].requestFocus();
                                    caixasdetexto[y][p].selectAll();
                                } else {
                                    caixasdetexto[y][14].requestFocus();
                                    caixasdetexto[y][14].selectAll();
                                }

                            } else if (x == 1 && !CoresdeFundoCaixas[y][0].equals("1")) {
                                int p = 14;
                                while (!CoresdeFundoCaixas[y][p].equals("1")) {
                                    p--;
                                }
                                caixasdetexto[y][p].requestFocus();
                                caixasdetexto[y][p].selectAll();
                            } else {
                                int p = x;
                                if (!CoresdeFundoCaixas[y][x - 1].equals("1")) {
                                    while (!CoresdeFundoCaixas[y][p - 1].equals("1")) {
                                        if (p == 1) {
                                            p = 14;
                                        } else {
                                            p--;
                                        }
                                    }
                                    caixasdetexto[y][p - 1].requestFocus();
                                    caixasdetexto[y][p - 1].selectAll();
                                } else {
                                    caixasdetexto[y][x - 1].requestFocus();
                                    caixasdetexto[y][x - 1].selectAll();
                                }
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            if (y == 0) {
                                int p = 14;
                                if (!CoresdeFundoCaixas[p][x].equals("1")) {
                                    while (!CoresdeFundoCaixas[p - 1][x].equals("1")) {
                                        p--;
                                    }
                                    caixasdetexto[p - 1][x].requestFocus();
                                    caixasdetexto[p - 1][x].selectAll();
                                } else {
                                    caixasdetexto[14][x].requestFocus();
                                    caixasdetexto[14][x].selectAll();
                                }

                            } else if (y == 1 && !CoresdeFundoCaixas[0][x].equals("1")) {
                                int p = 14;
                                while (!CoresdeFundoCaixas[y][p].equals("1")) {
                                    p--;
                                }
                                caixasdetexto[y][p].requestFocus();
                                caixasdetexto[y][p].selectAll();
                            } else {
                                int p = y;
                                if (!CoresdeFundoCaixas[y - 1][x].equals("1")) {
                                    while (!CoresdeFundoCaixas[p - 1][x].equals("1")) {
                                        if (p == 1) {
                                            p = 14;
                                        } else {
                                            p--;
                                        }
                                    }
                                    caixasdetexto[p - 1][x].requestFocus();
                                    caixasdetexto[p - 1][x].selectAll();
                                } else {
                                    caixasdetexto[y - 1][x].requestFocus();
                                    caixasdetexto[y - 1][x].selectAll();
                                }
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            if (y == 14) {
                                int p = 0;
                                if (!CoresdeFundoCaixas[p][x].equals("1")) {
                                    while (!CoresdeFundoCaixas[p + 1][x].equals("1")) {
                                        p++;
                                    }
                                    caixasdetexto[p + 1][x].requestFocus();
                                    caixasdetexto[p + 1][x].selectAll();
                                } else {
                                    caixasdetexto[0][x].requestFocus();
                                    caixasdetexto[0][x].selectAll();
                                }

                            } else if (y == 13 && !CoresdeFundoCaixas[14][x].equals("1")) {
                                int p = 0;
                                while (!CoresdeFundoCaixas[p + 1][x].equals("1")) {
                                    p++;
                                }
                                caixasdetexto[p + 1][x].requestFocus();
                                caixasdetexto[p + 1][x].selectAll();
                            } else {
                                int p = y;
                                if (!CoresdeFundoCaixas[y + 1][x].equals("1")) {
                                    while (!CoresdeFundoCaixas[p + 1][x].equals("1")) {
                                        if (p == 13) {
                                            p = 0;
                                        } else {
                                            p++;
                                        }
                                    }
                                    caixasdetexto[p + 1][x].requestFocus();
                                    caixasdetexto[p + 1][x].selectAll();
                                } else {
                                    caixasdetexto[y + 1][x].requestFocus();
                                    caixasdetexto[y + 1][x].selectAll();
                                }
                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        for (int k = 0; k < 15; k++) {
                            for (int l = 0; l < 15; l++) {
                                if (titulos[k][l].equals("$")) {
                                } else {
                                    if (titulos[k][l].length() > 2) {
                                        if (titulos[k][l].substring(2, 3).equals("h")) {
                                            TestarHorizontal(k, l);
                                        }
                                        if (titulos[k][l].substring(2, 3).equals("v")) {
                                            TestarVertical(k, l);
                                        }
                                        if (titulos[k][l].substring(2, 3).equals("%")) {
                                            TestarVerticalEHorizontal(k, l);
                                        }
                                    } else {
                                        if (titulos[k][l].substring(1, 2).equals("h")) {
                                            TestarHorizontal(k, l);
                                        }
                                        if (titulos[k][l].substring(1, 2).equals("v")) {
                                            TestarVertical(k, l);
                                        }
                                        if (titulos[k][l].substring(1, 2).equals("%")) {
                                            TestarVerticalEHorizontal(k, l);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private void TestarVitoria() {
        if (acertos == dicas.length) {
            FimJogoP fj = new FimJogoP();
            fj.setVisible(true);
            JOptionPane.showMessageDialog(null, "Você Venceu");
        }
    }

    private boolean TestarsePintou(String titulo) {
        String numerospintados[] = pintou.split("-");
        for (int i = 0; i < numerospintados.length; i++) {
            if (numerospintados[i].equalsIgnoreCase(titulo)) {
                return false;
            }
        }
        return true;
    }

    private void TestarHorizontal(int k, int l) {
        String[] resultado = TesteHor(k, l).split("-");
        String palavra = resultado[0];
        int o;
        if (!resultado[1].equals("")) {
            o = Integer.parseInt(resultado[1]);
        } else {
            o = -1;
        }
        if (o != -1) {
            for (int i = 0; i < Palavra.length; i++) {
                if (titulos[k][l].length() > 2) {
                    String[] separarPalavra = Palavra[i].split("-");
                    if (palavra.equalsIgnoreCase(separarPalavra[1]) && titulos[k][l].substring(0, 2).equalsIgnoreCase(separarPalavra[0]) && TestarsePintou(titulos[k][l].substring(0, 2))) {
                        if (pintou.equalsIgnoreCase("")) {
                            pintou += titulos[k][l].substring(0, 2);
                        } else {
                            pintou += "-" + titulos[k][l].substring(0, 2);
                        }
                        acertos++;
                        for (int j = l; j <= o; j++) {
                            caixasdetexto[k][j].setBackground(new Color(100, 255, 100));
                            paineisComTitulo[k][j].setBackground(new Color(100, 255, 100));
                            caixasdetexto[k][j].setBorder(BorderFactory.createLineBorder(new Color(100, 255, 100), 1));
                        }
                        TestarVitoria();
                    }
                } else {

                    String[] separarPalavra = Palavra[i].split("-");
                    if (palavra.equalsIgnoreCase(separarPalavra[1]) && titulos[k][l].substring(0, 1).equalsIgnoreCase(separarPalavra[0]) && TestarsePintou(titulos[k][l].substring(0, 1))) {
                        if (pintou.equals("")) {
                            pintou += titulos[k][l].substring(0, 1);
                        } else {
                            pintou += "-" + titulos[k][l].substring(0, 1);
                        }
                        acertos++;
                        for (int j = l; j <= o; j++) {
                            caixasdetexto[k][j].setBackground(new Color(100, 255, 100));
                            paineisComTitulo[k][j].setBackground(new Color(100, 255, 100));
                            caixasdetexto[k][j].setBorder(BorderFactory.createLineBorder(new Color(100, 255, 100), 1));
                        }
                        TestarVitoria();
                    }
                }
            }
        }
    }

    public void TestarVertical(int k, int l) {
        String[] resultado = TesteVert(k, l).split("-");
        String palavra = resultado[0];
        int o = Integer.parseInt(resultado[1]);
        for (int i = 0; i < Palavra.length; i++) {
            if (titulos[k][l].length() > 2) {
                String[] separarPalavra = Palavra[i].split("-");
                if (palavra.equalsIgnoreCase(separarPalavra[1]) && titulos[k][l].substring(0, 2).equalsIgnoreCase(separarPalavra[0]) && TestarsePintou(titulos[k][l].substring(0, 2))) {
                    if (pintou.equals("")) {
                        pintou += titulos[k][l].substring(0, 2);
                    } else {
                        pintou += "-" + titulos[k][l].substring(0, 2);
                    }
                    acertos++;
                    for (int j = k; j <= o; j++) {
                        caixasdetexto[j][l].setBackground(new Color(100, 255, 100));
                        paineisComTitulo[j][l].setBackground(new Color(100, 255, 100));
                        caixasdetexto[j][l].setBorder(BorderFactory.createLineBorder(new Color(100, 255, 100), 1));
                    }
                    TestarVitoria();
                }
            } else {
                String[] separarPalavra = Palavra[i].split("-");
                if (palavra.equalsIgnoreCase(separarPalavra[1]) && titulos[k][l].substring(0, 1).equalsIgnoreCase(separarPalavra[0]) && TestarsePintou(titulos[k][l].substring(0, 1))) {
                    if (pintou.equals("")) {
                        pintou += titulos[k][l].substring(0, 1);
                    } else {
                        pintou += "-" + titulos[k][l].substring(0, 1);
                    }
                    acertos++;
                    for (int j = k; j <= o; j++) {
                        caixasdetexto[j][l].setBackground(new Color(100, 255, 100));
                        paineisComTitulo[j][l].setBackground(new Color(100, 255, 100));
                        caixasdetexto[j][l].setBorder(BorderFactory.createLineBorder(new Color(100, 255, 100), 1));
                    }
                    TestarVitoria();
                }
            }
        }
    }

    public void TestarVerticalEHorizontal(int k, int l) {
        for (int i = 0; i < Palavra.length; i++) {
            if (Palavra[i].substring(0, 1).equals("%")) {

                String[] separarPalavra = Palavra[i].substring(1, Palavra[i].length()).split("-");
                if (titulos[k][l].length() > 2) {
                    if (titulos[k][l].substring(0, 2).equalsIgnoreCase(separarPalavra[0])) {
                        String[] linhaH = separarPalavra[1].split("#");
                        String[] resultado = TesteHor(k, l).split("-");
                        String palavra = resultado[0];
                        int o = Integer.parseInt(resultado[1]);
                        if (palavra.equalsIgnoreCase(linhaH[0]) && TestarsePintou(titulos[k][l].substring(0, 2) + "h")) {
                            if (pintou.equalsIgnoreCase("")) {
                                pintou += titulos[k][l].substring(0, 2) + "h";
                            } else {
                                pintou += "-" + titulos[k][l].substring(0, 2) + "h";
                            }
                            acertos++;
                            for (int j = l; j <= o; j++) {
                                caixasdetexto[k][j].setBackground(new Color(100, 255, 100));
                                paineisComTitulo[k][j].setBackground(new Color(100, 255, 100));
                                caixasdetexto[k][j].setBorder(BorderFactory.createLineBorder(new Color(100, 255, 100), 1));
                            }
                            TestarVitoria();
                        }
                        resultado = TesteVert(k, l).split("-");
                        palavra = resultado[0];
                        o = Integer.parseInt(resultado[1]);
                        if (palavra.equalsIgnoreCase(linhaH[1]) && TestarsePintou(titulos[k][l].substring(0, 2) + "v")) {
                            if (pintou.equals("")) {
                                pintou += titulos[k][l].substring(0, 2) + "v";
                            } else {
                                pintou += "-" + titulos[k][l].substring(0, 2) + "v";
                            }
                            acertos++;
                            for (int j = k; j <= o; j++) {
                                caixasdetexto[j][l].setBackground(new Color(100, 255, 100));
                                paineisComTitulo[j][l].setBackground(new Color(100, 255, 100));
                                caixasdetexto[j][l].setBorder(BorderFactory.createLineBorder(new Color(100, 255, 100), 1));
                            }
                            TestarVitoria();
                        }
                    }
                } else {
                    if (titulos[k][l].substring(0, 1).equalsIgnoreCase(separarPalavra[0])) {
                        String[] linhaH = separarPalavra[1].split("#");
                        String[] resultado = TesteHor(k, l).split("-");
                        String palavra = resultado[0];
                        int o = Integer.parseInt(resultado[1]);
                        if (palavra.equalsIgnoreCase(linhaH[0]) && TestarsePintou(titulos[k][l].substring(0, 1) + "h")) {
                            if (pintou.equals("")) {
                                pintou += titulos[k][l].substring(0, 1) + "h";
                            } else {
                                pintou += "-" + titulos[k][l].substring(0, 1) + "h";
                            }
                            acertos++;
                            for (int j = l; j <= o; j++) {
                                caixasdetexto[k][j].setBackground(new Color(100, 255, 100));
                                paineisComTitulo[k][j].setBackground(new Color(100, 255, 100));
                                caixasdetexto[k][j].setBorder(BorderFactory.createLineBorder(new Color(100, 255, 100), 1));
                            }
                            TestarVitoria();
                        }
                        resultado = TesteVert(k, l).split("-");
                        palavra = resultado[0];
                        o = Integer.parseInt(resultado[1]);
                        if (palavra.equalsIgnoreCase(linhaH[1]) && TestarsePintou(titulos[k][l].substring(0, 1) + "v")) {
                            if (pintou.equals("")) {
                                pintou += titulos[k][l].substring(0, 1) + "v";
                            } else {
                                pintou += "-" + titulos[k][l].substring(0, 1) + "v";
                            }
                            acertos++;
                            for (int j = k; j <= o; j++) {
                                caixasdetexto[j][l].setBackground(new Color(100, 255, 100));
                                paineisComTitulo[j][l].setBackground(new Color(100, 255, 100));
                                caixasdetexto[j][l].setBorder(BorderFactory.createLineBorder(new Color(100, 255, 100), 1));
                            }
                            TestarVitoria();
                        }
                    }
                }
            }
        }
    }

    public String TesteHor(int k, int l) {
        int o = l, z = 0;
        String palavra = "";
        if (!caixasdetexto[k][l].getText().equals("-")) {
            palavra = caixasdetexto[k][l].getText();
        }
        while (CoresdeFundoCaixas[k][o + 1].equals("1") && o + 1 < 14) {
            if (!caixasdetexto[k][o + 1].getText().equals("") && !caixasdetexto[k][o + 1].getText().equals("-")) {
                palavra += caixasdetexto[k][o + 1].getText();
            }
            if (o == 12 && CoresdeFundoCaixas[k][o + 2].equals("1") && !caixasdetexto[k][o + 2].getText().equals("-")) {
                palavra += caixasdetexto[k][o + 2].getText();
                z++;
            }
            o++;
        }
        if (z != 0) {
            o++;
        }
        return palavra + "-" + o;
    }

    public String TesteVert(int k, int l) {
        int o = k, z = 0;
        String palavra = caixasdetexto[k][l].getText();
        while ((CoresdeFundoCaixas[o + 1][l].equals("1")) && o + 1 < 14) {
            if (!caixasdetexto[o + 1][l].getText().equals("") && !caixasdetexto[o + 1][l].getText().equals("-")) {
                palavra += caixasdetexto[o + 1][l].getText();
            }
            if (o == 12 && CoresdeFundoCaixas[o + 2][l].equals("1") && !caixasdetexto[o + 2][l].getText().equals("-")) {
                z++;
            }
            o++;
        }
        if (z != 0) {
            o++;
            palavra += caixasdetexto[o][l].getText();
        }
        return palavra + "-" + o;
    }
}
