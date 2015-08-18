package Virus;

import TFasePrincipal.RodarMusica;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private ImageIcon Imagem = new ImageIcon("Res/JogosExtras/EliminarVirus/AntiVirus.png");
    private ImageIcon Imagem2 = new ImageIcon("Res/JogosExtras/EliminarVirus/AntiVirus.gif");
    private ImageIcon Fundo = new ImageIcon("Res/JogosExtras/EliminarVirus/Fundo.jpg");
    private Image fundo;
    private FiguraMouse FiguraMouse;
    private static JProgressBar BarraVida = new JProgressBar(0, 100);
    private boolean emJogo;
    public Timer TimerJogo;
    private List<Virus> Virus;
    private Random gerador = new Random();
    private int[][] coordenadas = new int[1][2];
    private int Contador = 20;
    private boolean Clicou = false, terminou = false;

    public int getContador() {
        return Contador;
    }

    public void setContador(int Contador) {
        this.Contador = Contador;
    }

    public Fase() {
    }

    public void Iniciar() {
        addMouseListener(this);
        addMouseMotionListener(this);

        setFocusable(true);
        setDoubleBuffered(true);
        // addKeyListener(new TecladoAdapter());
        fundo = Fundo.getImage();

        FiguraMouse = new FiguraMouse();
        emJogo = true;

        InicializaVirus();

        TimerJogo = new Timer(1, this);
        TimerJogo.start();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("Sem Mouse");
        Point hotspot = new Point(10, 10);
        Cursor CursorTest = tk.createCustomCursor(img, hotspot, "Sem cursor");
        setCursor(CursorTest);
    }

    public void CriarCoordenadas() {
        int numero = gerador.nextInt(695);
        int numero2 = gerador.nextInt(595) + 15;
        coordenadas[0][0] = numero;
        coordenadas[0][1] = numero2;

    }

    public void InicializaVirus() {
        CriarCoordenadas();
        Virus = new ArrayList<Virus>();
        Virus.add(new Virus(coordenadas[0][0], coordenadas[0][1]));
    }

    public void checarColisoes() {
        Rectangle FormaMouse = FiguraMouse.getBounds();
        Rectangle FormaVirus;

        for (int i = 0; i < Virus.size(); i++) {
            Virus TempVirus = Virus.get(i);
            FormaVirus = TempVirus.getBounds();

            if (FormaVirus.intersects(FormaMouse) && Clicou) {

                Contador -= 1;
                TempVirus.setIsVisivel(false);
                if (Contador <= 0) {
                    emJogo = false;
                } else {
                }
                //Comidas.remove(i);
            }
        }
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);

        if (emJogo) {
            for (int i = 0; i < Virus.size(); i++) {
                Virus comi = Virus.get(i);
                graficos.drawImage(comi.getImagem(), comi.getX(), comi.getY(), this);
            }

            graficos.drawImage(FiguraMouse.getImagem(), FiguraMouse.getX(), FiguraMouse.getY(), this);

            if (Virus.isEmpty()) {
                CriarCoordenadas();
                Virus.add(new Virus(coordenadas[0][0], coordenadas[0][1]));
            }

            graficos.setColor(Color.WHITE);
            graficos.setFont(new Font("Arial Narrow", 1, 18));
            graficos.drawString("Elimines Todos os Virus Antes do Tempo Acabar", 5, 15);
            graficos.drawString("Numero de Virus Para Eliminar: " + Contador, 375, 15);

        } else {
            if (Contador > 0) {
                ImageIcon fim = new ImageIcon("Res/JogosExtras/EliminarVirus/Fim.jpg");
                graficos.drawImage(fim.getImage(), 0, 0, null);
            } else {
                ImageIcon fim = new ImageIcon("Res/JogosExtras/EliminarVirus/Fim2.jpg");
                graficos.drawImage(fim.getImage(), 0, 0, null);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
            }
            terminou = true;
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < Virus.size(); i++) {
            Virus Virus2 = Virus.get(i);
            if (Virus2.isIsVisivel()) {

                Virus2.mexer();

            } else {
                Virus.remove(i);
            }

        }
        FiguraMouse.mexer();
        checarColisoes();
        repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        FiguraMouse.setDx(e.getX());
        FiguraMouse.setDy(e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        FiguraMouse.setImagem(Imagem2.getImage());
        Clicou = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        FiguraMouse.setImagem(Imagem.getImage());
        Clicou = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        FiguraMouse.setDx(e.getX());
        FiguraMouse.setDy(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        FiguraMouse.setDx(e.getX());
        FiguraMouse.setDy(e.getY());
    }

    public void setEmJogo(boolean emJogo) {
        this.emJogo = emJogo;
    }

    public boolean isEmJogo() {
        return emJogo;
    }

    public boolean isEmTerminou() {
        return terminou;
    }
}
