package TorreDeHanoi;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class Fase extends JPanel implements ActionListener {

    private ImageIcon Fundo = new ImageIcon("Res\\torre\\CozinhaFundo.jpg");
    private Image fundo;
    private boolean emJogo, pracima = false;
    private Timer TimerJogo;
    private List<Discos> VDiscos;
    private List<Torre> VTorre;
    private int torreEmFoco = 0, andarLado = 0, quantidadeDeMovimentos = 0;
    private String tempo = "00:00";

    public Fase() {

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());
        fundo = Fundo.getImage();
        emJogo = true;

        InicializaComidas();

        TimerJogo = new Timer(2, this);
        TimerJogo.start();

    }

    public void InicializaComidas() {

        VDiscos = new ArrayList<>();
        VDiscos.add(new Discos(6, 155, 437));
        VDiscos.add(new Discos(5, 149, 465));
        VDiscos.add(new Discos(4, 139, 500));
        VTorre = new ArrayList<>();
        VTorre.add(new Torre(150, 145));
        Torre comi = VTorre.get(0);
        comi.adicionarDisco(0);
        comi.adicionarDisco(1);
        comi.adicionarDisco(2);
        for (int i = 0; i < comi.discos.size(); i++) {
            System.out.println(" " + comi.discos.get(i));
        }
        VTorre.add(new Torre(350, 145));
        VTorre.add(new Torre(550, 145));
    }

    public boolean isEmJogo() {
        return emJogo;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        if (emJogo) {
            for (int i = 0; i < VTorre.size(); i++) {
                Torre comi = VTorre.get(i);
                graficos.drawImage(comi.getImagem(), comi.getX(), comi.getY(), this);
            }
            for (int i = 0; i < VDiscos.size(); i++) {
                Discos comi = VDiscos.get(i);
                graficos.drawImage(comi.getImagem(), comi.getX(), comi.getY(), this);
            }
            g.setFont(new Font("Arial", 1, 22));
            graficos.drawString("Quantidade de movimentos: " + quantidadeDeMovimentos, 5, 30);
            g.setFont(new Font("LcdMono", 1, 26));
            graficos.drawString(tempo, 55, 65);
        } else {
            ImageIcon fim = new ImageIcon("Res\\Fim.jpg");
            graficos.drawImage(fim.getImage(), 0, 0, null);

        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public void discoASerMovimentadoCima(int i) {
        Torre comi = VTorre.get(i);
        if (comi.quantidadeDeDiscos() > 0) {
            pracima = true;
            Discos comi2 = VDiscos.get(comi.discos.get(0));
            comi2.mexerCima();
        }
    }

    public void discoASerMovimentadoBaixo(int i) {
        Torre comi = VTorre.get(i);
        if (comi.quantidadeDeDiscos() > 0) {
            Discos comi2 = VDiscos.get(comi.discos.get(0));
            System.out.println("comi " + comi.discos.get(0));
            Torre comi3 = VTorre.get(andarLado);
            Discos comi4 = null;
            if (comi3.quantidadeDeDiscos() > 0) {
                comi4 = VDiscos.get(comi3.discos.get(0));
            }
            boolean ODeBaixoEhMenor = false;
            if (comi3.discos.size() > 0) {
                System.out.println("o de baixo " + comi3.discos.get(comi3.discos.size() - 1) + " " + comi2.getNumero());
                if (comi3.discos.get(0) < comi.discos.get(0)) {
                    ODeBaixoEhMenor = true;
                }
            }
            if (!ODeBaixoEhMenor) {
                pracima = false;
                System.out.println("alturac " + comi2.getAltura());
                if (comi3.discos.isEmpty() || (i == andarLado && comi.quantidadeDeDiscos() == 1)) {
                    comi2.mexerBaixo(460 + (79 - comi2.getAltura()));
                } else if (comi3.discos.isEmpty() || i == andarLado) {
                    Discos comi5 = VDiscos.get(comi.discos.get(1));
                    comi2.mexerBaixo(comi5.getY() - comi2.getAltura());
                } else if (comi3.discos.size() == 1) {
                    comi2.mexerBaixo(comi4.getY() - comi2.getAltura());
                } else {
                    comi2.mexerBaixo(comi4.getY() - comi4.getAltura());
                }

                if (andarLado != i) {
                    System.out.println("andar " + andarLado);
                    comi3.discos.add(0, comi.discos.get(0));
                    comi.discos.remove(comi.discos.get(0));
                    andarLado = i;
                }
                quantidadeDeMovimentos++;
            }
        }
        testeVitoria();
    }

    public void discoASerMovimentadoEsquerda(int i) {
        Torre comi = VTorre.get(i);
        if (comi.quantidadeDeDiscos() > 0) {
            andarLado--;
            Discos comi2 = VDiscos.get(comi.discos.get(0));
            comi2.mexerEsquerda();
        }
    }

    public void discoASerMovimentadoDireita(int i) {
        Torre comi = VTorre.get(i);
        if (comi.quantidadeDeDiscos() > 0) {
            andarLado++;
            Discos comi2 = VDiscos.get(comi.discos.get(0));
            comi2.mexerDireita();
        }
    }

    private void testeVitoria() {
        Torre comi = VTorre.get(1);
        if (comi.quantidadeDeDiscos() == 3) {
            JOptionPane.showMessageDialog(null, "Você venceu!");
            TimerJogo.stop();
            TimerJogo = null;
            emJogo = false;
        }
        comi = VTorre.get(2);
        if (comi.quantidadeDeDiscos() == 3) {
            JOptionPane.showMessageDialog(null, "Você venceu!");
            TimerJogo.stop();
            TimerJogo = null;
            emJogo = false;
        }

    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent evt) {
            for (int i = 0; i < VTorre.size(); i++) {
                Torre comi = VTorre.get(i);
                for (int j = 0; j < comi.discos.size(); j++) {
                    System.out.println("torre " + i + ": " + comi.discos.get(j));
                }
            }
            int codigo = evt.getKeyCode();
            if (codigo == KeyEvent.VK_UP && !pracima) {
                if (torreEmFoco == 0) {
                    discoASerMovimentadoCima(0);
                }
                if (torreEmFoco == 1) {
                    discoASerMovimentadoCima(1);
                }
                if (torreEmFoco == 2) {
                    discoASerMovimentadoCima(2);
                }
                //comi2.mexer();
            } else {
                System.out.println("eeeerrrooo" + pracima);
            }
            if (codigo == KeyEvent.VK_DOWN && pracima) {
                if (torreEmFoco == 0) {
                    discoASerMovimentadoBaixo(0);
                }
                if (torreEmFoco == 1) {
                    discoASerMovimentadoBaixo(1);
                }
                if (torreEmFoco == 2) {
                    discoASerMovimentadoBaixo(2);
                }
            }
            //esquerda
            if (codigo == KeyEvent.VK_LEFT && andarLado > 0 && pracima) {
                if (torreEmFoco == 0) {
                    discoASerMovimentadoEsquerda(0);
                }
                if (torreEmFoco == 1) {
                    discoASerMovimentadoEsquerda(1);
                }
                if (torreEmFoco == 2) {
                    discoASerMovimentadoEsquerda(2);
                }
            }
            if (codigo == KeyEvent.VK_RIGHT && andarLado < 2 && pracima) {
                if (torreEmFoco == 0) {
                    discoASerMovimentadoDireita(0);
                }
                if (torreEmFoco == 1) {
                    discoASerMovimentadoDireita(1);
                }
                if (torreEmFoco == 2) {
                    discoASerMovimentadoDireita(2);
                }
            }
            if (codigo == KeyEvent.VK_A && !pracima) {
                torreEmFoco = 0;
                andarLado = 0;
            }
            if (codigo == KeyEvent.VK_S && !pracima) {
                torreEmFoco = 1;
                andarLado = 1;
                System.out.println("foco1");
            }
            if (codigo == KeyEvent.VK_D && !pracima) {
                torreEmFoco = 2;
                andarLado = 2;
            }
        }

        @Override
        public void keyReleased(KeyEvent evt) {
            int codigo = evt.getKeyCode();
            if (codigo == KeyEvent.VK_SPACE) {
            } else {
            }
        }
    }
}
