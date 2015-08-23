package TFasePrincipal;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Personagem {

    private int x, y, x1, y1, x2, y2, x3, y3;
    private int dx;
    private int xm;
    private int dxm;
    private int xc, yc;
    private int xt, yt;
    private int dxc, dyc;
    private int dxt, dyt;
    private int altura, largura;
    private int altural, largural;
    private int altural2, largural2;
    private int alturaf, larguraf;
    private int alturat, largurat;
    private boolean m1 = true, m2 = true, m3 = true, apertouespaço = true, teminoudescida = true;
    static Map<String, ImageIcon> sprites = new HashMap<>();
    static ImageIcon referencia = new ImageIcon("Res/personagemParado.png");
    static ImageIcon referencia3 = new ImageIcon("Res/personagem.gif");
    static ImageIcon referencia4 = new ImageIcon("Res/personagem2.gif");
    static ImageIcon referencia5 = new ImageIcon("Res/personagemCotrario.png");
    static ImageIcon referencia6 = new ImageIcon("Res/mariopular.gif");
    static ImageIcon referencia10 = new ImageIcon("Res/porta.png");
    ImageIcon image = new ImageIcon("Res/lado.png");
    ImageIcon image2 = new ImageIcon("Res/lado.png");
    ImageIcon image3 = new ImageIcon("Res/fund.png");
    private Image personagemj, mundo, ima1, ima2, ima3, porta;
    //private Map<String, ImageIcon> mapa = new HashMap<>();
    Timer t;
    Timer timer, queda;
    boolean apertouLadoDireiro = false, apertouLadoEsquerdo = false, caiunoburaco = false;
    String ultimoLadoApertado = "";
    int cont = 0;
    ImagemMasculinoOuFeminino img = new ImagemMasculinoOuFeminino();

    public Personagem() {
        IniciarImages();
        personagemj = referencia.getImage();
        porta = referencia10.getImage();
        ima1 = image.getImage();
        ima2 = image3.getImage();
        ima3 = image2.getImage();
        altura = personagemj.getHeight(null);
        largura = personagemj.getWidth(null);
        largural = ima1.getWidth(null);
        altural = ima1.getHeight(null);
        larguraf = ima2.getWidth(null);
        alturaf = ima2.getHeight(null);
        largurat = porta.getWidth(null);
        alturat = porta.getHeight(null);
        largural2 = ima3.getWidth(null);
        altural2 = ima3.getHeight(null);

        this.x = 0;
        this.y = 400;
        this.x1 = 0;
        this.y1 = 370;
        this.x2 = 0;
        this.y2 = 585;
        this.x3 = 70;
        this.y3 = 370;
        this.xm = 0;
        this.xc = 2666;
        this.yc = 410;
        this.xt = 28666;
        this.yt = 375;
    }

    public void caiuRestartSpace() {
        teminoudescida = true;
        apertouespaço = true;
    }

    public void mexer() {
        x += dx;
        x1 += dx;
        x2 += dx;
        x3 += dx;
        //("xm+ " + xm);
        if (!apertouespaço) {
            if (cont < 38) {
                y -= 5;
                y1 -= 5;
                y2 -= 5;
                y3 -= 5;
                cont++;
            } else {
                apertouespaço = true;
                cont /= 2;
            }
        } else {
            if (cont > 0 && y <= 395) {
                y += 10;
                y1 += 10;
                y2 += 10;
                y3 += 10;
                cont--;
            } else {
                if (cont == 0 && y == 400) {
                    teminoudescida = true;
                }
            }
        }
        xm += dxm;
        xc += dxc;
        yc += dyc;
        xt += dxt;
        yt += dyt;
        if (this.x < 0) {
            x = 0;
            x1 = 0;
            x2 = 0;
            x3 = 70;
        }

        if (this.xm > -28910) {

            if (this.x > 25) {
                x = 25;
                x1 = 25;
                x2 = 25;
                x3 = 95;
            }

        } else {
            if (this.x > 750) {
                x = 750;
            }
        }

        if (this.y < -80) {
            y = - 80;
            y1 = -80;
            y2 = -80;
            y3 = -80;

        }

        if (this.y > 400) {
            y = 400;
            y1 = 400;
            y2 = 615;
            y3 = 400;
        }
        if (this.xm < -28920) {
            xm = -28920;
        }
    }

    public void setCaiu(boolean caiuNoBuraco) {
        this.caiunoburaco = caiuNoBuraco;
    }

    public boolean getCaiu() {
        return caiunoburaco;
    }

    public void caiuNoBuraco() {
        ActionListener action;
        action = new ActionListener() {
            public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                if (y <= 560) {
                    y += 10;
                } else {
                    caiunoburaco = false;
                    y = 400;
                    queda.stop();
                }
            }
        };
        queda = new Timer(35, action);
        queda.start();

    }

    public void setXms(int xc) {
        this.xc = xc;
    }

    public void setXs(int x, int x1, int x2, int x3) {
        this.x = x;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setPersonagemj(Image personagemj) {
        this.personagemj = personagemj;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDxm(int dxm) {
        this.dxm = dxm;
    }

    public void setDxc(int dxc) {
        this.dxc = dxc;
    }

    public void setDxt(int dxt) {
        this.dxt = dxt;
    }

    public void setMs(boolean m1, boolean m2, boolean m3) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    public static Image getReferencia() {
        return referencia.getImage();
    }

    public int getLarg1() {
        return largural;
    }

    public int getLarg2() {
        return larguraf;
    }

    public int getLarg3() {
        return largural2;
    }

    public int getAlt1() {
        return altural;
    }

    public int getAlt2() {
        return alturaf;
    }

    public int getAlt3() {
        return altural2;
    }

    public int getX() {
        return x;
    }

    public int getXm() {
        return xm;
    }

    public int getY() {
        return y;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getX3() {
        return x3;
    }

    public int getY3() {
        return y3;
    }

    public int getXc() {
        return xc;
    }

    public int getXt() {
        return xt;
    }

    public int getYc() {
        return yc;
    }

    public int getYt() {
        return yt;
    }

    public Image getImagem() {
        return personagemj;
    }

    public Image getImagem2() {
        return mundo;
    }

    public Image getIma1() {
        return ima1;
    }

    public Image getIma2() {
        return ima2;
    }

    public Image getIma3() {
        return ima3;
    }

    public Image getImagemTutoria() {
        return porta;
    }

    public Rectangle getBounds1() {
        return new Rectangle(x1, y1, largural, altural);
    }

    public Rectangle getBounds2() {
        return new Rectangle(x2, y2, larguraf, alturaf);
    }

    public Rectangle getBounds3() {
        return new Rectangle(x3 + 50, y3, largural + 20, altural);
    }

    public Rectangle getBoundsmario() {
        return new Rectangle(x, y, largura, altura);
    }

    public Rectangle getBoundstutoria(int x) {
        return new Rectangle(x, yc, largurat, alturat);
    }

    public void KeyPressed(KeyEvent tecla, boolean m1, boolean m2, boolean m3) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE || codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W) {
            if (teminoudescida) {
                teminoudescida = false;
                apertouespaço = false;
            }

        }
        if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A) {
            dx = -10;
            personagemj = referencia4.getImage();
            apertouLadoEsquerdo = true;
            ultimoLadoApertado = "a";
        }
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dx = 10;
            personagemj = referencia3.getImage();
            apertouLadoDireiro = true;
            ultimoLadoApertado = "d";
            if (x >= 5) {
                dxm = -15;
                dxc = -15;
                dxt = -15;
            }
        }
    }

    public void KeyReleased(KeyEvent tecla) {

        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE || codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W) {

            if (!apertouLadoDireiro) {
                if (!apertouLadoEsquerdo) {
                    if (ultimoLadoApertado.equals("a")) {
                        personagemj = referencia5.getImage();
                    } else if (ultimoLadoApertado.equals("d")) {
                        personagemj = referencia.getImage();
                    }
                }
            }

        }

        if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A) {
            dx = 0;
            personagemj = referencia5.getImage();
            apertouLadoEsquerdo = false;
        }
        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            personagemj = referencia.getImage();
            apertouLadoDireiro = false;
            dx = 0;
            dxm = 0;
            dxc = 0;
            dxt = 0;
        }

    }

    private void IniciarImages() {
        img.setarJogo("faseandarfrente");
        referencia3 = new ImageIcon(img.getCaminho());
        img.setarJogo("faseandartraz");
        referencia4 = new ImageIcon(img.getCaminho());
        img.setarJogo("faseparadofrente");
        referencia = new ImageIcon(img.getCaminho());
        img.setarJogo("faseparadotraz");
        referencia5 = new ImageIcon(img.getCaminho());
    }
}
