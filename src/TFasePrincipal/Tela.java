package TFasePrincipal;

import Tela_principal.Principal;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

public class Tela extends JFrame implements MouseMotionListener, MouseListener {

    Fase fase;
    JMenuBar barraMenu = new JMenuBar();
    JMenu itemMenu = new JMenu("Jogo");
    JMenuItem subItemMenuCarregar = new JMenuItem("Carregar");
    JMenuItem subItemMenuSalvar = new JMenuItem("Sair");
    String d1 = "";
    static Image cs = Toolkit.getDefaultToolkit().getImage("");
    static Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cs, new Point(0, 0), "");
    RodarMusica r;
    ActionListener action;
    Timer t;

    public Tela(boolean carregar) {
        r = new RodarMusica("Res/Sons/FUNDO.mp3");
        if (carregar) {
            fase = new Fase(true);
        } else {
            fase = new Fase(false);
        }
        add(fase);
        setTitle("IF Life");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        //setUndecorated(true);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        itemMenu.add(subItemMenuCarregar);
        itemMenu.add(subItemMenuSalvar);
        subItemMenuCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarJogo();
            }
        });
        subItemMenuSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });

        addMouseMotionListener(this);
        addMouseListener(this);

        barraMenu.add(itemMenu);
        setJMenuBar(barraMenu);
        action = new ActionListener() {
            public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                if (fase.getPodeCarregar()) {
                    subItemMenuCarregar.setEnabled(true);
                } else {
                    subItemMenuCarregar.setEnabled(false);
                }
                if (fase.getMinimizar()) {
                    setState(JFrame.ICONIFIED);
                } else {
                    setState(JFrame.NORMAL);
                }
                if (!fase.getEmJogo()) {
                    r.Parar();
                    t.stop();
                    if (fase.timer != null) {
                        fase.timer.stop();
                        fase.timer = null;
                    }
                    t = null;
                    r = null;
                    fase.tl.dispose();
                    fase.tl = null;
                    fase = null;
                    dispose();
                }
                if (fase != null) {
                    if (fase.vidas == 0) {
                        r.Parar();
                        t.stop();
                        if (fase.timer != null) {
                            fase.timer.stop();
                            fase.timer = null;
                        }
                        t = null;
                        r = null;
                        fase.tl.dispose();
                        fase.tl = null;
                        fase = null;
                        AcabouAsVidas a = new AcabouAsVidas();
                        a.setVisible(true);
                        dispose();
                    }
                }
            }
        };
        t = new Timer(50, action);
        t.start();
    }

    public static void main(String[] args) {
        new Tela(false);
    }

    public void mouseMoved(MouseEvent e) {
        //String d2;
        //SimpleDateFormat sdf = new SimpleDateFormat("ss");
        //.out.println(sdf.format(new Date()));
        //d2 = sdf.format(new Date());
        // if(!d2.equalsIgnoreCase(d1)){
        //  setCursor(blankCursor);
        //}else{
        //   setCursor(null);
        // }
        //
        // d1 = sdf.format(new Date());
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //("ap");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //("sp");
    }

    private void carregarJogo() {
        fase.carregarJ(true);
        /* String xml = "";

         List<String> linhas = null;

         try {
         linhas = Files.readAllLines(Paths.get("Res/Salvar.txt"), StandardCharsets.UTF_8);
         } catch (IOException ex) {
         Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
         }

         if (linhas != null) {
         for (String s : linhas) {
         xml += s;
         }
         }

         XStream xs = new XStream(new DomDriver());
         this.fase = (Fase) xs.fromXML(xml);
         * */
    }

    private void sair() {
        r.Parar();
        fase.timer.stop();
        fase.timer = null;
        if (fase.ano != null) {
            fase.ano.te.te.x.tutor.t.dispose();
            fase.ano.te.te.dispose();
            fase.ano.te.dispose();
        }
        if (fase.c != null) {
            fase.c.c.dispose();
            fase.c.dispose();
        }
        if (fase.anov != null) {
            fase.anov.te.C.dispose();
            fase.anov.te.dispose();
        }
        r = null;
        fase.tl.dispose();
        fase.tl = null;
        t.stop();
        t = null;
        fase = null;
        Principal p = new Principal();
        p.setVisible(true);
        dispose();
    }
}
