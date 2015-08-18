package TorreDeHanoi;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Discos {

    private int x, y;
    private Image Imagem;
    private int largura, altura;
    private boolean isVisivel;
    //  private static final int LARGURA_TELA = -200;
    private static final int VELOCIDADE = 2;
    private ImageIcon disco1 = new ImageIcon("Res\\torre\\p7.png");
    private ImageIcon disco2 = new ImageIcon("Res\\torre\\p6.png");
    private ImageIcon disco3 = new ImageIcon("Res\\torre\\p5.png");
    private ImageIcon disco4 = new ImageIcon("Res\\torre\\p4.png");
    private ImageIcon disco5 = new ImageIcon("Res\\torre\\p3.png");
    private ImageIcon disco6 = new ImageIcon("Res\\torre\\p2.png");
    private ImageIcon disco7 = new ImageIcon("Res\\torre\\p1.png");
    private Random gerador = new Random();
    private int numero;

    public int getNumero() {
        return numero;
    }

    public Discos(int numero, int x, int y) {

        this.numero = numero;
        this.y = y;
        this.x = x;

        switch (numero) {
            case 0:
                Imagem = disco7.getImage();
                break;
            case 1:
                Imagem = disco6.getImage();
                break;
            case 2:
                Imagem = disco5.getImage();
                break;
            case 3:
                Imagem = disco4.getImage();
                break;
            case 4:
                Imagem = disco3.getImage();
                break;
            case 5:
                Imagem = disco2.getImage();
                break;
            case 6:
                Imagem = disco1.getImage();
                break;
        }

        this.largura = Imagem.getWidth(null);
        this.altura = Imagem.getHeight(null);
        isVisivel = true;

    }

    public Discos(int x, int y) {

        int numero2 = gerador.nextInt(3);
        numero = numero2;
        switch (numero2) {
            case 0:
                Imagem = disco5.getImage();
                break;
            case 1:
                Imagem = disco6.getImage();
                break;
            case 2:
                Imagem = disco7.getImage();
                break;
            case 3:
                Imagem = disco4.getImage();
                break;
            case 4:
                Imagem = disco3.getImage();
                break;
            case 5:
                Imagem = disco2.getImage();
                break;
            case 6:
                Imagem = disco1.getImage();
                break;
        }

        this.largura = Imagem.getWidth(null);
        this.altura = Imagem.getHeight(null);
        isVisivel = true;

        this.y = y;
        this.x = x;
    }

    public void mexerCima() {
        y = 100;
    }

    public void mexerBaixo(int y) {
        this.y = y;
    }

    public void mexerEsquerda() {
        x -= 200;
    }

    public void mexerDireita() {
        x += 200;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public Image getImagem() {
        return Imagem;
    }

    public boolean isIsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
}
