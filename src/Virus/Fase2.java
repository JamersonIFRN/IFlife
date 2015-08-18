package Virus;

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

public class Fase2 extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private ImageIcon Imagem = new ImageIcon("Res/JogosExtras/EliminarVirus/AntiVirus.png");
    private ImageIcon Imagem2 = new ImageIcon("Res/JogosExtras/EliminarVirus/AntiVirus.gif");
    private ImageIcon Fundo = new ImageIcon("Res/JogosExtras/EliminarVirus/Fundo.jpg");
    private Image fundo;
    private FiguraMouse FiguraMouse;
    JProgressBar BarraVida = new JProgressBar(0, 100);
    private boolean emJogo;
    private Timer TimerJogo;
    public Timer TimerTempo;
    public Timer Timer1;
    private Virus Viru = new Virus(0, 0);
    private List<Virus> Virus;
    private Random gerador = new Random();
    private int[][] coordenadas = new int[10][2];
    private int Contador = 100;
    private int ContTimer = 0;
    private boolean Clicou = false;
    public boolean terminou = false;
    private ActionListener Action1 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!Virus.isEmpty()) {
                Virus.remove(0);
            }
        }
    };
    private ActionListener Action2 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ContTimer++;
        }
    };

    public void setContador(int Contador) {
        this.Contador = Contador;
    }

    public int getContador() {
        return Contador;
    }

    public void setContTimer(int ContTimer) {
        this.ContTimer = ContTimer;
    }

    public Fase2() {

        addMouseListener(this);
        addMouseMotionListener(this);
        BarraVida.setBackground(Color.red);
        BarraVida.setForeground(Color.yellow);
        BarraVida.setName("Vida");
        BarraVida.setBounds(0, 0, 750, 25);
        add(BarraVida);
        setFocusable(true);
        setDoubleBuffered(true);
        // addKeyListener(new TecladoAdapter());
        fundo = Fundo.getImage();

        FiguraMouse = new FiguraMouse();
        emJogo = true;

        TimerJogo = new Timer(1, this);
        TimerTempo = new Timer(1000, Action2);
        TimerJogo.start();
        TimerTempo.start();

        InicializaVirus();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("Sem Mouse");
        Point hotspot = new Point(10, 10);
        Cursor CursorTest = tk.createCustomCursor(img, hotspot, "Sem cursor");
        setCursor(CursorTest);
    }

    public void CriarCoordenadas() {
        for (int i = 0; i < 10; i++) {
            int numero = gerador.nextInt(695);
            int numero2 = gerador.nextInt(595) + 15;
            coordenadas[i][0] = numero;
            coordenadas[i][1] = numero2;
        }
    }

    public void InicializaVirus() {
        CriarCoordenadas();
        Virus = new ArrayList<Virus>();
        for (int i = 0; i < 10; i++) {
            Virus.add(new Virus(coordenadas[i][0], coordenadas[i][1]));
        }
        Timer1 = new Timer(500, Action1);
        Timer1.start();
    }

    public void checarColisoes() {
        Rectangle FormaMouse = FiguraMouse.getBounds();
        Rectangle FormaVirus;

        for (int i = 0; i < Virus.size(); i++) {
            Virus TempVirus = Virus.get(i);
            FormaVirus = TempVirus.getBounds();

            if (FormaVirus.intersects(FormaMouse) && Clicou) {

                Contador -= 2;

                TempVirus.setIsVisivel(false);
                if (Contador <= 0) {
                    emJogo = false;
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
                Viru = Virus.get(i);
                graficos.drawImage(Viru.getImagem(), Viru.getX(), Viru.getY(), this);
            }

            graficos.drawImage(FiguraMouse.getImagem(), FiguraMouse.getX(), FiguraMouse.getY(), this);

            if (Virus.isEmpty()) {
                CriarCoordenadas();
                for (int i = 0; i < 10; i++) {
                    Virus.add(new Virus(coordenadas[i][0], coordenadas[i][1]));
                }
                Timer1.stop();
                Timer1 = new Timer(500, Action1);
                Timer1.start();
            }

            graficos.setColor(Color.WHITE);
            graficos.setFont(new Font("Arial Narrow", 1, 16));
            graficos.drawString("Elimine 50 Virus, Eles São Bem Rapidos", 5, 15);
            graficos.drawString("Número de Virus Para Capturar: " + Contador / 2, 280, 15);
            graficos.drawString("Tempo Decorrido: " + ContTimer + " Segundos", 530, 15);

        } else {
            if (Contador <= 0) {
                ImageIcon fim = new ImageIcon("Res/JogosExtras/EliminarVirus/Fim2.jpg");
                graficos.drawImage(fim.getImage(), 0, 0, null);

            } else {
                ImageIcon fim = new ImageIcon("Res/JogosExtras/EliminarVirus/Fim.jpg");
                graficos.drawImage(fim.getImage(), 0, 0, null);
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

    public boolean isEmTerminou() {
        return terminou;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BarraVida.setValue(Contador);
        BarraVida.repaint();
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
        emJogo = true;
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
}
