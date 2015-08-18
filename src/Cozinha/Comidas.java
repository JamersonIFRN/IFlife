package Cozinha;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Comidas {

    private int x, y;
    private Image Imagem;
    private int largura, altura;
    private boolean isVisivel;
    //  private static final int LARGURA_TELA = -200;
    private static final int VELOCIDADE = 3;
    private ImageIcon TiposComidas = new ImageIcon("Res/JogosExtras/Cozinha/Maca.png");
    private ImageIcon TiposComidas1 = new ImageIcon("Res/JogosExtras/Cozinha/Pera.png");
    private ImageIcon TiposComidas2 = new ImageIcon("Res/JogosExtras/Cozinha/Banana.png");
    private ImageIcon TiposComidas3 = new ImageIcon("Res/JogosExtras/Cozinha/Cenoura.png");
    private ImageIcon TiposComidas4 = new ImageIcon("Res/JogosExtras/Cozinha/Beterraba.png");
    private ImageIcon TiposComidas5 = new ImageIcon("Res/JogosExtras/Cozinha/Melancia.png");
    private ImageIcon TiposComidas6 = new ImageIcon("Res/JogosExtras/Cozinha/Abacaxi.png");
    private ImageIcon TiposComidas7 = new ImageIcon("Res/JogosExtras/Cozinha/Refri.png");
    private ImageIcon TiposComidas8 = new ImageIcon("Res/JogosExtras/Cozinha/Coxinha.png");
    private ImageIcon TiposComidas9 = new ImageIcon("Res/JogosExtras/Cozinha/BChocolate.png");
    private ImageIcon TiposComidas10 = new ImageIcon("Res/JogosExtras/Cozinha/Refri.png");
    private ImageIcon TiposComidas11 = new ImageIcon("Res/JogosExtras/Cozinha/Coxinha.png");
    private ImageIcon TiposComidas12 = new ImageIcon("Res/JogosExtras/Cozinha/BChocolate.png");
    private ImageIcon TiposComidas13 = new ImageIcon("Res/JogosExtras/Cozinha/Bolo.png");
    private ImageIcon TiposComidas14 = new ImageIcon("Res/JogosExtras/Cozinha/Bolo.png");
    private Random gerador = new Random();
    private int numero;

    public int getNumero() {
        return numero;
    }

    public Comidas(int numero, int x, int y) {

        this.numero = numero;
        this.y = y;
        this.x = x;

        switch (numero) {
            case 0:
                Imagem = TiposComidas.getImage();
                break;
            case 1:
                Imagem = TiposComidas1.getImage();
                break;
            case 2:
                Imagem = TiposComidas2.getImage();
                break;
            case 3:
                Imagem = TiposComidas3.getImage();
                break;
            case 4:
                Imagem = TiposComidas4.getImage();
                break;
            case 5:
                Imagem = TiposComidas5.getImage();
                break;
            case 6:
                Imagem = TiposComidas6.getImage();
                break;
            case 7:
                Imagem = TiposComidas7.getImage();
                break;
            case 8:
                Imagem = TiposComidas8.getImage();
                break;
            case 9:
                Imagem = TiposComidas9.getImage();
                break;
            case 10:
                Imagem = TiposComidas10.getImage();
                break;
            case 11:
                Imagem = TiposComidas11.getImage();
                break;
            case 12:
                Imagem = TiposComidas12.getImage();
                break;
            case 13:
                Imagem = TiposComidas13.getImage();
                break;
            case 14:
                Imagem = TiposComidas14.getImage();
                break;
        }

        this.largura = Imagem.getWidth(null);
        this.altura = Imagem.getHeight(null);
        isVisivel = true;

    }

    public Comidas(int x, int y) {

        int numero2 = gerador.nextInt(15);
        numero = numero2;
        switch (numero2) {
            case 0:
                Imagem = TiposComidas.getImage();
                break;
            case 1:
                Imagem = TiposComidas1.getImage();
                break;
            case 2:
                Imagem = TiposComidas2.getImage();
                break;
            case 3:
                Imagem = TiposComidas3.getImage();
                break;
            case 4:
                Imagem = TiposComidas4.getImage();
                break;
            case 5:
                Imagem = TiposComidas5.getImage();
                break;
            case 6:
                Imagem = TiposComidas6.getImage();
                break;
            case 7:
                Imagem = TiposComidas7.getImage();
                break;
            case 8:
                Imagem = TiposComidas8.getImage();
                break;
            case 9:
                Imagem = TiposComidas9.getImage();
                break;
            case 10:
                Imagem = TiposComidas10.getImage();
                break;
            case 11:
                Imagem = TiposComidas11.getImage();
                break;
            case 12:
                Imagem = TiposComidas12.getImage();
                break;
            case 13:
                Imagem = TiposComidas13.getImage();
                break;
            case 14:
                Imagem = TiposComidas14.getImage();
                break;
        }

        this.largura = Imagem.getWidth(null);
        this.altura = Imagem.getHeight(null);
        isVisivel = true;

        this.y = y;
        this.x = x;
    }

    public void mexer(int VELOCIDADE) {
        if (numero >= 0 && numero <= 6) {
            if (this.y > 480) {
                // this.y = LARGURA_TELA;
                isVisivel = false;
            } else {
                this.y += VELOCIDADE;
            }
        } else {
            if (this.y > 700) {
                // this.y = LARGURA_TELA;
                isVisivel = false;
            } else {
                this.y += VELOCIDADE;
            }
        }
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
