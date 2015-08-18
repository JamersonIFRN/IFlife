package Virus;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Virus {

    private int x, y;
    private Image Imagem;
    private int largura, altura;
    private boolean isVisivel;
    private ImageIcon TiposVirus = new ImageIcon("Res/JogosExtras/EliminarVirus/Virus11.png");
    private ImageIcon TiposVirus1 = new ImageIcon("Res/JogosExtras/EliminarVirus/Virus22.png");
    private ImageIcon TiposVirus2 = new ImageIcon("Res/JogosExtras/EliminarVirus/Virus33.png");
    private ImageIcon TiposVirus3 = new ImageIcon("Res/JogosExtras/EliminarVirus/Virus44.png");
    private ImageIcon TiposVirus4 = new ImageIcon("Res/JogosExtras/EliminarVirus/Virus55.png");
    private Random gerador = new Random();
    private int numero;

    public int getNumero() {
        return numero;
    }

    public Virus(int numero, int x, int y) {

        this.numero = numero;
        this.y = y;
        this.x = x;

        switch (numero) {
            case 0:
                Imagem = TiposVirus.getImage();
                break;
            case 1:
                Imagem = TiposVirus1.getImage();
                break;
            case 2:
                Imagem = TiposVirus2.getImage();
                break;
            case 3:
                Imagem = TiposVirus3.getImage();
                break;
            case 4:
                Imagem = TiposVirus4.getImage();
                break;
        }

        this.largura = Imagem.getWidth(null);
        this.altura = Imagem.getHeight(null);
        isVisivel = true;

    }

    public Virus(int x, int y) {

        int numero2 = gerador.nextInt(5);
        numero = numero2;
        switch (numero2) {
            case 0:
                Imagem = TiposVirus.getImage();
                break;
            case 1:
                Imagem = TiposVirus1.getImage();
                break;
            case 2:
                Imagem = TiposVirus2.getImage();
                break;
            case 3:
                Imagem = TiposVirus3.getImage();
                break;
            case 4:
                Imagem = TiposVirus4.getImage();
                break;
        }

        this.largura = Imagem.getWidth(null);
        this.altura = Imagem.getHeight(null);
        isVisivel = true;

        this.y = y;
        this.x = x;
    }

    public void mexer() {
        this.y = y;
        this.x = x;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
