/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sudoku;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author DANIEL
 */
public class Sudoku extends JFrame implements ActionListener {

    PanelSudoku paineis[][] = new PanelSudoku[3][3];
    Color cor1 = new Color(180, 180, 180),
            cor2 = new Color(215, 215, 215);
    int ControleCorPainel = 0,
            numeroDoPainelClicado = 1,
            posicaoColuna = 0,
            posicaoLinha = 0;
    Timer timer = new Timer(50, this);
    String[] quebrarSudokuLinha;
    String ConstrutorSudoku, nomen;
    int nivel;
    JMenuBar barraMenu = new JMenuBar();
    JMenu itemMenu = new JMenu("Jogo");
    JMenuItem subItemMenuSair = new JMenuItem("Sair");

    public Sudoku(String constS, int n, String nn) {
        this.nivel = n;
        nomen = nn;
        ConstrutorSudoku = constS;
        setResizable(false);
        inicializar();
        setTitle("Sudoku");
        setSize(480, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        setVisible(true);
        setLayout(new GridLayout(3, 3));
        setLocationRelativeTo(null);
        barraMenu.add(itemMenu);
        itemMenu.add(subItemMenuSair);
        subItemMenuSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaSudoku t = new TelaSudoku();
                t.setVisible(true);
                dispose();
            }
        });
        setJMenuBar(barraMenu);

        timer.start();
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int contador = 1;
                for (int linha = 0; linha < 3; linha++) {
                    for (int coluna = 0; coluna < 3; coluna++) {
                        if (numeroDoPainelClicado == contador) {
                            boolean b = testeNumeroPermitido(e.getKeyChar(), linha, coluna, posicaoColuna, posicaoLinha);
                            boolean f = testeClicouQuadInicial(e.getKeyChar(), linha, coluna, numeroDoPainelClicado, posicaoColuna, posicaoLinha);
                            if (f && b) {
                                paineis[linha][coluna].quadrados[posicaoColuna][posicaoLinha].setText("" + e.getKeyChar());
                            }
                        }
                        contador++;
                    }
                }
                if (testeVencedor()) {
                    JOptionPane.showMessageDialog(null, "Venceu!");
                    Fimjogo f = new Fimjogo(nomen, nivel);
                    f.setVisible(true);
                    dispose();
                } else {
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public boolean testeNumeroPermitido(char e, int i, int j, int posc, int posl) {
        String valorDoQuadrado = "" + e;

        if (testarDiferenteDe1a9(valorDoQuadrado)) {
            return false;
        }
        for (int c = 0; c < 3; c++) {
            for (int l = 0; l < 3; l++) {
                if (paineis[i][j].quadrados[c][l].getText().equals(valorDoQuadrado)) {
                    return false;
                }
            }
        }
        for (int c = 0; c < 3; c++) {
            for (int l = 0; l < 3; l++) {
                if (paineis[i][c].quadrados[posc][l].getText().equals(valorDoQuadrado)) {
                    return false;
                }
            }
        }
        for (int c = 0; c < 3; c++) {
            for (int l = 0; l < 3; l++) {
                if (paineis[c][j].quadrados[l][posl].getText().equals(valorDoQuadrado)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean testeClicouQuadInicial(char e, int i, int j, int num, int posc, int posl) {
        String[] CaracterQuadrado = quebrarSudokuLinha[numeroDoPainelClicado - 1].split(" ");
        int contador = 0;
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (posc == linha && posl == coluna) {
                    if (CaracterQuadrado[contador].equals("@")) {
                        if (paineis[i][j].quadrados[posc][posl].getText().equals("" + e)) {
                            paineis[i][j].quadrados[posc][posl].setText("");

                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                contador++;
            }
        }

        return true;
    }

    public boolean testeVencedor() {
        for (int linha = 0; linha < 3; linha++) {
            //Teste na horizontal
            for (int coluna = 0; coluna < 3; coluna++) {
                String vector[] = new String[9];
                int cont = 0;
                while (cont < 9) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            vector[cont] = paineis[linha][i].quadrados[coluna][j].getText();
                            cont++;
                        }
                    }
                }
                for (int i = 0; i < 9; i++) {
                    for (int j = i + 1; j < 9; j++) {
                        if (!vector[i].equals(vector[j]) && !vector[i].equals("") && !vector[j].equals("")) {
                        } else {

                            return false;
                        }
                    }
                }
                //Teste na vertical

                cont = 0;
                while (cont < 9) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {

                            vector[cont] = paineis[linha][i].quadrados[coluna][j].getText();
                            cont++;
                        }
                    }
                }

                for (int i = 0; i < 9; i++) {
                    for (int j = i + 1; j < 9; j++) {
                        if (!vector[i].equals(vector[j]) && !vector[i].equals("") && !vector[j].equals("")) {
                        } else {

                            return false;
                        }
                    }
                }
            }
        }
        String vector[] = new String[9];
        //Teste quadrados
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                int cont = 0;
                while (cont < 9) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {

                            vector[cont] = paineis[l][c].quadrados[i][j].getText();
                            cont++;
                        }
                    }
                }

                for (int i = 0; i < 9; i++) {
                    for (int j = i + 1; j < 9; j++) {
                        if (!vector[i].equals(vector[j]) && !vector[i].equals("") && !vector[j].equals("")) {
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void inicializar() {
        int NumeroDoPainel = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                paineis[i][j] = new PanelSudoku();
                add(paineis[i][j]);
                paineis[i][j].setNNum(NumeroDoPainel);
                NumeroDoPainel++;
                if (ControleCorPainel == 0) {
                    for (int l = 0; l < 3; l++) {
                        for (int c = 0; c < 3; c++) {
                            paineis[i][j].paneisQuadrados[l][c].setBackground(this.cor1);
                        }
                    }
                    ControleCorPainel = 1;
                } else {
                    for (int l = 0; l < 3; l++) {
                        for (int c = 0; c < 3; c++) {
                            paineis[i][j].paneisQuadrados[l][c].setBackground(this.cor2);
                        }
                    }
                    ControleCorPainel = 0;
                }
            }
        }

        quebrarSudokuLinha = ConstrutorSudoku.split("#");
        int ContadorQuebraS = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String[] QuebrarPorQuadrado = quebrarSudokuLinha[ContadorQuebraS].split(" ");
                int ContadorIF = 0;
                for (int l = 0; l < 3; l++) {
                    for (int c = 0; c < 3; c++) {

                        if (QuebrarPorQuadrado[ContadorIF].equals("@")) {
                        } else {
                            paineis[i][j].quadrados[l][c].setText(QuebrarPorQuadrado[ContadorIF]);
                            paineis[i][j].quadrados[l][c].setForeground(Color.red);
                        }
                        //p[i][j].pn[l][c].setBackground(this.c);
                        ContadorIF++;
                    }
                }
                ContadorQuebraS++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (paineis[i][j].getCl()) {
                    numeroDoPainelClicado = paineis[i][j].getNNum();
                    posicaoColuna = paineis[i][j].getPosC();
                    posicaoLinha = paineis[i][j].getPosl();
                    paineis[i][j].setCl(false);
                }

            }
        }
    }

    private boolean testarDiferenteDe1a9(String NumeroDigitado) {
        return !NumeroDigitado.equals("1") && !NumeroDigitado.equals("2") && !NumeroDigitado.equals("3") && !NumeroDigitado.equals("4") && !NumeroDigitado.equals("5") && !NumeroDigitado.equals("6") && !NumeroDigitado.equals("7") && !NumeroDigitado.equals("8") && !NumeroDigitado.equals("9");
    }
}
