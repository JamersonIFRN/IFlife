package JogosEFasesExtras;

import Tela_principal.TelaInstrução;
import TFasePrincipal.ImagemMasculinoOuFeminino;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.Timer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DANIEL
 */
public class Lab extends JFrame implements ActionListener {

    Timer tempo;
    JPanel painel = new JPanel();
    static JLabel quadrados[][] = new JLabel[13][23];
    String txtConstroiLab[];
    int posicaoVert = 6, posicaoHoriz = 0, mudarImagem = 0, lado = 0;
    int controleCaminho[][] = new int[13][23];
    JLabel onibus = new JLabel(new ImageIcon("Res/Onibus.gif"));
    String textolab, ano;
    ImageIcon pers;
    ImageIcon pers2;
    boolean emjogo = true;

    public Lab(String textolab, String ano) {
        ImagemMasculinoOuFeminino img = new ImagemMasculinoOuFeminino();
        img.setarJogo("lab1");
        pers = new ImageIcon(img.getCaminho());
        img.setarJogo("lab2");
        pers2 = new ImageIcon(img.getCaminho());
        this.textolab = textolab;
        this.ano = ano;
        onibus.setBounds(0, 0, 950, 355);
        onibus.setVisible(false);
        add(onibus);
        Iniciar();
        setTitle("Labirinto");
        setSize(929, 535);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        setLayout(null);
        painel.setBounds(0, 0, 925, 511);
        painel.setVisible(true);
        painel.setLayout(new GridLayout(13, 23));
        add(painel);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

    }

    private void venceu() {
        mudarImagem++;
        onibus.setVisible(true);
        deixarInvisivel();
        setSize(929, 380);
        quadrados[posicaoVert][posicaoHoriz].setIcon(new javax.swing.ImageIcon("Res/chao.png"));
        tempo = new Timer(4340, this);
        tempo.start();
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        if ((evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_D) && emjogo) {
            lado = 0;
            quadrados[posicaoVert][posicaoHoriz].setIcon(pers);
            if (posicaoHoriz != 22) {
                if (controleCaminho[posicaoVert][posicaoHoriz + 1] != 1) {
                    quadrados[posicaoVert][posicaoHoriz].setIcon(new javax.swing.ImageIcon("Res/chao.png"));
                    posicaoHoriz++;
                    quadrados[posicaoVert][posicaoHoriz].setIcon(pers);
                }
            } else {
                emjogo = false;
                venceu();
            }
        }
        if ((evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_A) && emjogo) {
            lado = 1;
            quadrados[posicaoVert][posicaoHoriz].setIcon(pers2);
            if (posicaoHoriz != 0) {
                if (controleCaminho[posicaoVert][posicaoHoriz - 1] != 1) {
                    quadrados[posicaoVert][posicaoHoriz].setIcon(new javax.swing.ImageIcon("Res/chao.png"));
                    posicaoHoriz--;
                    quadrados[posicaoVert][posicaoHoriz].setIcon(pers2);
                }
            }
        }
        if ((evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_W) && emjogo) {
            quadrados[posicaoVert][posicaoHoriz].setIcon(pers);
            if (posicaoVert != 0) {
                if (controleCaminho[posicaoVert - 1][posicaoHoriz] != 1) {
                    quadrados[posicaoVert][posicaoHoriz].setIcon(new javax.swing.ImageIcon("Res/chao.png"));
                    posicaoVert--;
                    if (lado == 0) {
                        quadrados[posicaoVert][posicaoHoriz].setIcon(pers);
                    } else {
                        quadrados[posicaoVert][posicaoHoriz].setIcon(pers2);
                    }
                }
            } else {
                emjogo = false;
                venceu();
            }
        }
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_S) && emjogo) {
            quadrados[posicaoVert][posicaoHoriz].setIcon(pers);
            if (posicaoHoriz != 21 || posicaoVert != 12) {
                if (controleCaminho[posicaoVert + 1][posicaoHoriz] != 1) {
                    quadrados[posicaoVert][posicaoHoriz].setIcon(new javax.swing.ImageIcon("Res/chao.png"));
                    posicaoVert++;
                    if (lado == 0) {
                        quadrados[posicaoVert][posicaoHoriz].setIcon(pers);
                    } else {
                        quadrados[posicaoVert][posicaoHoriz].setIcon(pers2);
                    }
                }
            } else {
                emjogo = false;
                venceu();

            }
        }
    }

    private void Iniciar() {
        for (int k = 0; k < 13; k++) {
            for (int l = 0; l < 23; l++) {
                quadrados[k][l] = new JLabel("", JLabel.CENTER);
                quadrados[k][l].setBorder(BorderFactory.createLineBorder(Color.black, 1));
            }
        }
        for (int i = 0; i < 455; i += 35) {
            for (int j = 0; j < 805; j += 35) {
                painel.add(quadrados[i / 35][j / 35]);
            }
        }
        String[] posini = textolab.split("#");
        posicaoVert = Integer.parseInt(posini[1]);
        txtConstroiLab = posini[0].split("@");
        for (int i = 0; i < 13; i++) {
            String[] coluna = txtConstroiLab[i].split(",");
            for (int j = 0; j < 23; j++) {
                if (coluna[j].equals("1")) {
                    quadrados[i][j].setIcon(new javax.swing.ImageIcon("Res/matto.jpg"));
                    controleCaminho[i][j] = 1;
                }
                if (coluna[j].equals("2")) {
                    quadrados[i][j].setIcon(new javax.swing.ImageIcon("Res/chao.png"));
                    controleCaminho[i][j] = 2;
                }
            }
        }
        quadrados[posicaoVert][posicaoHoriz].setIcon(pers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tempo.stop();

        TelaInstrução telaInstrução = new TelaInstrução();
        String caminhoarquivo = "Res/Arquivos instrução/instrucoes iniciais.txt";
        String linha, textotxt = "";
        FileReader fr;
        try {
            fr = new FileReader(caminhoarquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                    if (!linha.equals("")) {
                        textotxt += linha + "\n";
                    }
                }
                br.close();
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] textotelainstrução = textotxt.split("@");
        if (ano.equals("1")) {
            String textoEIcone[] = textotelainstrução[2].split("-");
            telaInstrução.setTexto(textoEIcone[0], textoEIcone[1], textoEIcone[2]);
        }
        if (ano.equals("2")) {
            String textoEIcone[] = textotelainstrução[4].split("-");
            telaInstrução.setTexto(textoEIcone[0], textoEIcone[1], textoEIcone[2]);
        }
        if (ano.equals("3")) {
            String textoEIcone[] = textotelainstrução[5].split("-");
            telaInstrução.setTexto(textoEIcone[0], textoEIcone[1], textoEIcone[2]);
        }
        if (ano.equals("4")) {
            String textoEIcone[] = textotelainstrução[6].split("-");
            telaInstrução.setTexto(textoEIcone[0], textoEIcone[1], textoEIcone[2]);
        }
        telaInstrução.setProximaTela("instruçãotela");
        telaInstrução.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
    }

    private void deixarInvisivel() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 23; j++) {
                quadrados[i][j].setVisible(false);
            }
        }
    }
}
