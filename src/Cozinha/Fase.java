package Cozinha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

    private ImageIcon Fundo = new ImageIcon("Res/JogosExtras/Cozinha/CozinhaFundo.jpg");
    private ImageIcon Mesa = new ImageIcon("Res/JogosExtras/Cozinha/Mesa.png");
    private Image fundo;
    private Image mesa;
    private Prato prato;
    private boolean TesteEspaço = true;
    public JProgressBar BarraVida = new JProgressBar(0, 100);
    private boolean emJogo;
    private Timer TimerJogo;
    private List<Comidas> VComidas;
    private List<Comidas> ComidasPrato;
    private Random gerador = new Random();
    private int[][] coordenadas = new int[1][2];
    private int Contador = 0;
    boolean terminou = false, inicio, pause = false;

    public int getContador() {
        return Contador;
    }

    public void reiniciar() {
        Contador = 0;
        terminou = false;
        coordenadas[0][0] = 100;
        coordenadas[0][1] = -100;
        ComidasPrato = null;
        VComidas = null;
        if (pause == true) {
            pause = false;
            TimerJogo.start();
        }
        InicializaComidas();
    }

    public void fim() {
        if (TimerJogo != null) {
            TimerJogo.stop();
            TimerJogo = null;
        }
    }

    public void setIinicio(boolean ini) {
        inicio = ini;
    }

    public Fase() {

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());
        fundo = Fundo.getImage();
        mesa = Mesa.getImage();

        prato = new Prato();
        emJogo = true;

        coordenadas[0][0] = 100;
        coordenadas[0][1] = -100;
        InicializaComidas();

        TimerJogo = new Timer(2, this);
        TimerJogo.start();
        BarraVida.setBackground(Color.red);
        BarraVida.setForeground(Color.yellow);
        BarraVida.setName("Vida");
        BarraVida.setBounds(0, 0, 750, 25);

    }

    public boolean getTerminou() {
        return terminou;
    }

    public void CriarCoordenadas() {

        int numero = gerador.nextInt(8) * 95;
        int numero2 = gerador.nextInt(3) * -100;
        coordenadas[0][0] = numero;
        coordenadas[0][1] = numero2;

    }

    public void InicializaComidas() {

        VComidas = new ArrayList<Comidas>();
        ComidasPrato = new ArrayList<Comidas>();
        VComidas.add(new Comidas(coordenadas[0][0], coordenadas[0][1]));

    }

    public void EncherPrato() {
        for (int i = 0; i < VComidas.size(); i++) {

            Comidas ParaPrato = VComidas.get(i);
            int LFrutax = gerador.nextInt(30) + 5;
            int LFrutay = gerador.nextInt(5) + 30;
            ComidasPrato.add(new Comidas(ParaPrato.getNumero(), LFrutax, LFrutay));

        }
    }

    public void checarColisoes() {
        Rectangle FormaPrato = prato.getBounds();
        Rectangle formaComida;

        for (int i = 0; i < VComidas.size(); i++) {
            Comidas TempComidas = VComidas.get(i);
            formaComida = TempComidas.getBounds();

            if (formaComida.intersects(FormaPrato)) {

                // TempComidas.setIsVisivel(false);
                if (TempComidas.getNumero() >= 0 && TempComidas.getNumero() <= 6) {
                    EncherPrato();
                    Contador += 20;
                } else {
                    if (Contador != 0 && Contador >= 35) {
                        Contador -= 35;
                        ComidasPrato.clear();
                    } else {
                        ComidasPrato.clear();
                        Contador = 0;
                    }
                }
                TempComidas.setIsVisivel(false);
                if (Contador >= 100) {
                    emJogo = false;
                }
                //Comidas.remove(i);
            }
        }
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(mesa, -30, 430, null);

        if (emJogo) {
            graficos.drawImage(prato.getImagem(), prato.getX(), prato.getY(), this);

            for (int i = 0; i < VComidas.size(); i++) {

                Comidas comi = VComidas.get(i);
                graficos.drawImage(comi.getImagem(), comi.getX(), comi.getY(), this);

                for (int j = 0; j < ComidasPrato.size(); j++) {

                    Comidas comi2 = ComidasPrato.get(j);
                    graficos.drawImage(comi2.getImagem(), prato.getX() + comi2.getX(), prato.getY() - comi2.getY(), this);

                }

            }

            if (VComidas.isEmpty()) {
                CriarCoordenadas();
                VComidas.add(new Comidas(coordenadas[0][0], coordenadas[0][1]));
            }
            graficos.setFont(new Font("Arial Narrow", 1, 25));
            graficos.setColor(Color.DARK_GRAY);
            graficos.drawString(Contador + "% Completo", 10, 30);
            graficos.setFont(new Font("Arial Narrow", 1, 20));
            graficos.setColor(Color.red);
            graficos.drawString("Para pausar o jogo aperte P", 175, 28);
            if (pause) {
                graficos.setFont(new Font("Arial Narrow", 1, 50));
                graficos.setColor(Color.RED);
                graficos.drawString("Jogo pausado", 225, 280);
            }
        } else {
            ImageIcon fim = new ImageIcon("Res/Fim.jpg");
            graficos.drawImage(fim.getImage(), 0, 0, null);
            terminou = true;
            if (TimerJogo != null) {
                TimerJogo.stop();
                TimerJogo = null;
            }
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BarraVida.setValue(Contador);
        BarraVida.repaint();
        for (int i = 0; i < VComidas.size(); i++) {
            Comidas comi2 = VComidas.get(i);
            if (comi2.isIsVisivel()) {
                if (inicio) {
                    comi2.mexer(2);
                } else {
                    comi2.mexer(3);
                }

            } else {
                VComidas.remove(i);
            }

        }
        if (inicio) {
            prato.mexer(1);
        } else {
            prato.mexer(2);
        }
        checarColisoes();
        repaint();

    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent evt) {
            int codigo = evt.getKeyCode();
            if (codigo == KeyEvent.VK_P) {
                if (!pause) {
                    pause = true;
                    repaint();
                    TimerJogo.stop();
                } else {
                    pause = false;
                    TimerJogo.start();
                }
            }
            if (codigo == KeyEvent.VK_SPACE || codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W) {
                prato.keyPressed(codigo);
                prato.keyReleased(37);
                prato.keyReleased(39);
                TesteEspaço = false;

            } else {
                if (TesteEspaço) {
                    prato.keyPressed(codigo);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent evt) {
            int codigo = evt.getKeyCode();
            if (codigo == KeyEvent.VK_SPACE || codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W) {
                prato.keyReleased(codigo);
                TesteEspaço = true;
            } else {
                if (TesteEspaço) {
                    prato.keyReleased(codigo);
                }
            }
        }
    }
}
