/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DANIEL
 */
public class PanelSudoku extends JPanel {

    JPanel paneisQuadrados[][] = new JPanel[3][3];
    JLabel quadrados[][] = new JLabel[3][3];
    int numeroPainel = 0, poscoluna, poslinha, px, py;
    boolean seFoiClicado = false;
    boolean podeClicar = true;

    public PanelSudoku() {
        Inicializar();
        setLayout(null);
        setSize(150, 150);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                quadrados[i][j].setBounds(0, 0, 50, 50);
                paneisQuadrados[i][j].add(quadrados[i][j]);
            }
        }
    }

    public void setNNum(int num) {
        this.numeroPainel = num;
    }

    public int getNNum() {
        return numeroPainel;
    }

    public int getPosC() {
        return poscoluna;
    }

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
    }

    public int getPosl() {
        return poslinha;
    }

    public void setCl(boolean cliq) {
        this.seFoiClicado = cliq;
    }

    public void setPCl(boolean podeClicar) {
        this.podeClicar = podeClicar;
    }

    public boolean getCl() {
        return seFoiClicado;
    }

    private void Inicializar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                paneisQuadrados[i][j] = new JPanel();
                paneisQuadrados[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                paneisQuadrados[i][j].setBounds((j * 50) + 3, (i * 50) + 3, 50, 50);
                paneisQuadrados[i][j].setLayout(null);
                add(paneisQuadrados[i][j]);
                Font f = new Font("Tahoma", Font.BOLD, 22);
                quadrados[i][j] = new JLabel("", JLabel.CENTER);
                quadrados[i][j].setFont(f);
                final int y = i, x = j;
                quadrados[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (podeClicar) {
                            seFoiClicado = true;
                            poscoluna = y;
                            poslinha = x;
                            int s = e.getX();
                            int ss = e.getY();
                            px = s / 10;
                            py = ss / 10;
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
                //jl[i / 50][j / 50] = new JLabel("a");
                //pn[i / 50][j / 50].add(jl[i / 50][j / 50]);
            }
        }
    }

    public static void main(String[] args) {
        new PanelSudoku();
    }
}
