package Virus;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class FiguraMouse {

    private int x, y;
    private int dx, dy;
    private Image imagem;
    private boolean isVisible;

    public FiguraMouse() {
        ImageIcon image = new ImageIcon("Res/JogosExtras/EliminarVirus/AntiVirus.png");
        imagem = image.getImage();
        this.x = 150;
        this.y = 555;
    }

    public void mexer() {
        x = dx;
        y = dy;

        if (x > 723) {
            x = 723;
        }
        if (y > 640) {
            y = 640;
        }

    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 2, 2);
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
