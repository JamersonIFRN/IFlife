package TorreDeHanoi;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Torre {

    private int x, y;
    private Image imagem;
    private int altura, largura;
    private boolean isVisible;
    ArrayList<Integer> discos = new ArrayList<Integer>();

    public Torre(int x, int y) {
        ImageIcon image = new ImageIcon("Res\\torre\\torre.png");
        imagem = image.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
        this.x = x;
        this.y = y;
    }

    public int quantidadeDeDiscos() {
        return discos.size();
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

    public void adicionarDisco(int disco) {
        discos.add(disco);
    }

    public int discoDeCima() {
        int i = discos.size();
        System.out.println("i " + i);
        if (i != 0) {
            return discos.get(i - 1);
        } else {
            return 0;
        }
    }
}
