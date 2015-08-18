package Cozinha;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Prato {

    private int x, y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private boolean isVisible;
    int velocidade = 1;

    public Prato() {
        ImageIcon image = new ImageIcon("Res/JogosExtras/Cozinha/Prato.png");
        imagem = image.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
        this.x = 150;
        this.y = 555;
        this.dy = 555;
    }

    public void mexer(int velocidade) {
        this.velocidade = velocidade;
        x += dx;
        y = dy;
        if (x < 1) {
            x = 0;
        }
        if (x > 628) {
            x = 628;
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(int codigo) {
        if (codigo == KeyEvent.VK_SPACE || codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W) {
            dy = 520;
        }
        if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A) {
            dx = -velocidade;
        }
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dx = velocidade;
        }

    }

    public void keyReleased(int codigo) {
        if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A) {
            dx = 0;
        }
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dx = 0;
        }
        if (codigo == KeyEvent.VK_SPACE || codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W) {
            dy = 555;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
