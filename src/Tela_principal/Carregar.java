package Tela_principal;

import java.awt.AlphaComposite;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Carregar extends JFrame {

    private BufferedImage img1;
    private BufferedImage img2;
    private volatile float alpha = 1.0f;
    private float add = -0.02f;

    private BufferedImage carregarImagem(String image) {
        try {
            //Carrega a imagem do disco
            BufferedImage img = ImageIO.read(new File(image));

            //Converte ela para o formato da tela. Isso aumenta muito as chances do seu desenho ser acelerado por hardware.
            //O processo de covers�o � simples. Simplesmente criamos uma imagem com a configura��o da tela, e desenhamos
            //a imagem carrega sobre esta.
            //Tamb�m j� farei aqui o redimensionamento para o tamanho da tela, evitando gastar tempo com isso na hora de
            //desenhar na tela.
            BufferedImage imagem = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration()
                    .createCompatibleImage(getWidth(), getHeight(), Transparency.TRANSLUCENT);

            Graphics2D g2d = imagem.createGraphics();
            g2d.drawImage(img, 0, 0, imagem.getWidth(), imagem.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
            g2d.dispose();

            //Retornamos a imagem redimensionada e otimizada
            return imagem;
        } catch (IOException e) {
            throw new RuntimeException("N�o foi poss�vel carregar a imagem");
        }
    }

    public Carregar() {
        super("Exemplo de alpha composite");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setIgnoreRepaint(true);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        img1 = carregarImagem("Res/LogoJogoIF3.png");
        img2 = carregarImagem("Res/Imagens/M4.png");
        setVisible(true);

        //Iniciamos a thread que far� a imagem se repintar v�rias vezes
        //Isso d� o efeito de anima��o.
        Thread t = new Thread(new DemoThread(), "Thread de demonstra��o");
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage buffer = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffer.createGraphics();
        //Desenhamos a imagem 1
        g2d.drawImage(img1, 0, 0, null);

        //Nesta linha, alteramos a transpar�ncia de tudo que ser� desenhado
        //sobre o graphics.
        g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
        //E ent�o desenhamos a imagem 2, sobre a imagem 1.
        g2d.drawImage(img2, 0, 0, null);

        //Variamos o alpha
        alpha += add;

        if (alpha < 0.02) {
            add = 0.02f;
        } else if (alpha > 0.98) {
            add = -0.02f;
        }

        g2d.dispose();
        g.drawImage(buffer, 0, 0, null);
    }

    public class DemoThread implements Runnable {

        @Override
        public void run() {
            try {
                //Para fazer a anima��o, simplesmente chamamos o repaint v�rias vezes
                //Essa � uma forma bem rudimentar de animar. O Ponto V! descreve mecanismos
                //melhores.
                int i = 1;
                repaint();
                Thread.sleep(500);
                while (i <= 49) {
                    Thread.sleep(1000 / 15); //15 fps
                    repaint();
                    i++;
                }
                i = 0;
                img2 = carregarImagem("Res/Imagens/M4.png");
                Thread.sleep(500);
                img2 = carregarImagem("Res/Imagens/M4.png");
                while (i <= 49) {
                    Thread.sleep(1000 / 15); //15 fps
                    repaint();
                    i++;
                }
                img1 = carregarImagem("Res/Imagens/M3.png");
                Thread.sleep(500);
                i = 0;
                while (i <= 50) {
                    Thread.sleep(1000 / 15); //15 fps
                    repaint();
                    i++;
                }
                Principal p = new Principal();
                p.setVisible(true);
                dispose();
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Carregar().setVisible(true);
            }
        });
    }
}
