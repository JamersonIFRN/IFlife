package TFasePrincipal;

import Cozinha.TelaCozinha;
import Prova.Prova;
import Tutoria.AnoTutoria;
import Virus.AnoVirus;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

    private Personagem mario;
    public Timer timer, esperarsalvar;
    private boolean mexe = true, caiu = false, passouTutoria = false, chegouNaPorta = false, passouVirus = false, chegouvirus = false;
    private boolean cont = true, emjogo = true, podecarregar = true;
    TelaDePergunta11 tl = new TelaDePergunta11();
    boolean m1 = true, m2 = true, m3 = true, minimizar = false;
    boolean pprofs[];
    String[] perguntas;
    String[] materias;
    boolean passouNasMaterias[];
    String endere�o;
    int vidas = 3;
    AnoTutoria ano;
    AnoVirus anov;
    TelaCozinha c;

    public Fase(boolean carregar) {
        SetarMatsEPerg();
        setFocusable(true);
        setDoubleBuffered(true);

        addKeyListener(new TecladoAdapter());

        mario = new Personagem();
        pprofs = new boolean[mario.getNumProfs()];
        passouNasMaterias = new boolean[mario.getNumProfs()];
        perguntas = new String[mario.getNumProfs()];
        String arquivo = "Res/Cursos/" + endere�o + "/perguntas.txt", linha, f = "";
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                    if (!linha.equals("")) {
                        f += linha + "\n";
                    }
                }
                f = f.substring(0, f.length() - 1);
            } catch (IOException ex) {
                Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
        }
        String perguntastxt[] = f.split("\n");
        for (int i = 0; i < perguntas.length; i++) {
            perguntas[i] = perguntastxt[i];
        }
        arquivo = "Res/Cursos/" + endere�o + "/materias.txt";
        f = "";
        materias = new String[mario.getNumProfs()];

        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                    if (!linha.equals("")) {
                        f += linha + "\n";
                    }
                }
                f = f.substring(0, f.length() - 1);
            } catch (IOException ex) {
                Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
        }
        String materiastxt[] = f.split("\n");
        for (int i = 0; i < materias.length; i++) {
            materias[i] = materiastxt[i];
        }
        mario.setmaterias(materias);
        if (carregar) {
            carregarJ(true);
        } else {
            for (int i = 0; i < mario.getNumProfs(); i++) {
                pprofs[i] = true;
                passouNasMaterias[i] = false;
            }
            salvarJogo();
        }
        timer = new Timer(30, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        setFocusable(true);
        setDoubleBuffered(true);
        graficos.drawImage(mario.getImagem2(), mario.getXm(), mario.getYm(), this);
        graficos.setFont(new Font("Arial Narrow", 1, 20));

        int u = 0;
        for (int i = 0; i <= 10; i++) {
            graficos.drawImage(mario.getImagem4(), mario.getXb() + u, mario.getYb(), this);
            u += 2500;
        }
        u = 0;
        for (int i = 0; i <= 10; i++) {
            graficos.drawImage(mario.getImagem6(), mario.getXb() - 32 + u, mario.getYb() - 739, this);
            u += 2500;
        }
        graficos.setColor(Color.DARK_GRAY);
        int p = 0;
        for (int i = 0; i < passouNasMaterias.length; i++) {
            if (passouNasMaterias[i] == true) {
                p++;
            }
        }
        graficos.drawString("Materias Aprovadas:" + p, 5, 60);
        graficos.setColor(Color.red);
        graficos.drawString("Aten��o, precisa ser aprovado em 7 materias", 5, 85);
        u = 0;
        Image profs[] = mario.getProfs();
        for (int i = 0; i < mario.getNumProfs(); i++) {
            graficos.drawImage(profs[i], mario.getXp() + u, mario.getYp(), this);
            u += 2500;
        }
        graficos.drawImage(mario.getImagemTutoria(), mario.getXt(), mario.getYt(), this);
        //graficos.drawImage(mario.getImagem3(), mario.getXp(), mario.getYp(), this);
        graficos.drawImage(mario.getImagem(), mario.getX(), mario.getY(), this);
        Image img;
        if (vidas == 3) {
            img = new ImageIcon("Res/vidas.png").getImage();
            graficos.drawImage(img, 0, 0, this);
        } else if (vidas == 2) {
            img = new ImageIcon("Res/vidas2.png").getImage();
            graficos.drawImage(img, 0, 0, this);
        } else if (vidas == 1) {
            img = new ImageIcon("Res/vidas1.png").getImage();
            graficos.drawImage(img, 0, 0, this);
        }
        //graficos.drawString("Java Source and Support", 75, 100);
        /*graficos.drawImage(mario.getIma1(), mario.getX1(), mario.getY1(), this);
         graficos.drawImage(mario.getIma2(), mario.getX2(), mario.getY2(), this);
         graficos.drawImage(mario.getIma3(), mario.getX3(), mario.getY3(), this);*/
        g.dispose();

    }

    public boolean getPodeCarregar() {
        return podecarregar;
    }

    public void carregarJ(boolean vidasc) {
        tl.Inicio();
        tl.setSize(0, 0);
        tl.setVisible(false);
        String arquivo = "Res/Save.txt", linha, f = "";
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                    if (!linha.equals("")) {
                        f += linha + "\n";
                    }
                }
                f = f.substring(0, f.length() - 1);
                String setvar[] = f.split("@");
                String xms[] = setvar[1].split("\n");
                mario.setXs(Integer.parseInt(xms[0]), Integer.parseInt(xms[1]), Integer.parseInt(xms[2]), Integer.parseInt(xms[3]));
                mario.setXms(Integer.parseInt(xms[4]), Integer.parseInt(xms[5]), Integer.parseInt(xms[6]), Integer.parseInt(xms[7]), Integer.parseInt(xms[8]));
                if (vidasc) {
                    vidas = Integer.parseInt(xms[9]);
                } else {
                    vidas--;
                }
                String ppfs[] = setvar[2].split("\n");
                for (int i = 0; i < ppfs.length; i++) {
                    if (ppfs[i].equals("true")) {
                        pprofs[i] = true;
                    } else {
                        pprofs[i] = false;
                    }
                }
                String psm[] = setvar[3].split("\n");
                for (int i = 0; i < psm.length; i++) {
                    if (psm[i].equals("true")) {
                        passouNasMaterias[i] = true;
                    } else {
                        passouNasMaterias[i] = false;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
        }
        zerarDxsPersonagem();
    }

    public void zerarDxsPersonagem() {
        mario.setPersonagemj(mario.getReferencia());
        mario.setDx(0);
        mario.setDxm(0);
        mario.setDxp(0);
        mario.setDxb(0);
        mario.setDxc(0);
        mario.setDxt(0);
    }

    public void salvarJogo() {
        String arquivo = "Res/Save.txt";
        FileWriter fw;
        try {
            fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("fase@");
            bw.write("" + mario.getX());
            bw.newLine();
            bw.write("" + mario.getX1());
            bw.newLine();
            bw.write("" + mario.getX2());
            bw.newLine();
            bw.write("" + mario.getX3());
            bw.newLine();
            bw.write("" + mario.getXm());
            bw.newLine();
            bw.write("" + mario.getXp());
            bw.newLine();
            bw.write("" + mario.getXb());
            bw.newLine();
            bw.write("" + mario.getXc());
            bw.newLine();
            bw.write("" + mario.getXt() + "");
            bw.newLine();
            bw.write("" + vidas);
            bw.write("@");
            for (int i = 0; i < mario.getNumProfs(); i++) {
                bw.write("" + pprofs[i]);
                if (i != mario.getNumProfs() - 1) {
                    bw.newLine();
                }
            }
            bw.write("@");
            for (int i = 0; i < mario.getNumProfs(); i++) {
                bw.write("" + passouNasMaterias[i]);
                if (i != mario.getNumProfs() - 1) {
                    bw.newLine();
                }
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Fase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //("xm" + mario.getXm() + " x " + mario.getX());
        if (mario.getXm() == -28920 && mario.getX() == 750 && mexe) {
            mexe = false;
            emjogo = false;
            SePassou sp = new SePassou();
            sp.setNMaterias(mario.getNumProfs());
            sp.setMaterias(materias);
            sp.setPassou(passouNasMaterias);
            sp.setVisible(m1);
            timer.stop();
            timer = null;
            mario.setPersonagemj(mario.getReferencia());
        }
        if (mexe) {
            mario.mexer();
        }
        int u = 0;
        for (int i = 0; i <= 10; i++) {
            if (mario.getBounds2().intersects(mario.getBoundsburaco(mario.getXb() + u))) {
                if (!caiu) {
                    mario.setCaiu(true);
                    mario.caiuNoBuraco();
                    mario.caiuRestartSpace();
                    mexe = false;
                    caiu = true;

                }
                if (mario.getCaiu() == false) {
                    carregarJ(false);
                    mario.caiuRestartSpace();
                    mexe = true;
                    caiu = false;
                }
            }
            u += 2500;
        }

        u = 0;
        for (int i = 0; i < mario.getNumProfs(); i++) {

            if (mario.getBoundsmario().intersects(mario.getBoundsprofessor(mario.getXp() + u))) {
                if (pprofs[i]) {
                    mexe = tl.isCont();
                    podecarregar = tl.isCont();
                }
                if (tl.isCont()) {
                    passouNasMaterias[i] = tl.isPassou();
                    tl.setVisible(false);
                    final SalvarJogo j = new SalvarJogo();
                    j.setVisible(true);
                    ActionListener action;
                    action = new ActionListener() {
                        public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                            esperarsalvar.stop();
                            j.dispose();
                        }
                    };
                    esperarsalvar = new Timer(3000, action);
                    esperarsalvar.start();
                    tl.Inicio();
                    tl.setCont(false);
                    cont = true;
                    pprofs[i] = false;
                    salvarJogo();
                }
                if (cont && pprofs[i]) {
                    zerarDxsPersonagem();
                    String div[] = perguntas[i].split("#");
                    tl.setPergunta(div[0]);
                    tl.setAlternativas(div[1]);
                    tl.setMateria(materias[i]);
                    tl.setResposta(Integer.parseInt(div[2]));
                    tl.setvisevel(true);
                    cont = false;
                }

            } else {
                if (mexe) {
                    // ("aadwd");
                    //cont = true;
                    tl.setSize(0, 0);
                }
            }
            u += 2500;
        }
        if (!passouVirus) {
            if (mario.getBoundsmario().intersects(mario.getBoundstutoria(mario.getXt() - 785))) {
                if (!chegouvirus) {
                    anov = new AnoVirus();
                    chegouvirus = true;
                    podecarregar = false;
                    zerarDxsPersonagem();
                }
                mexe = anov.getTerminou();

                if (anov.getTerminou() == true) {
                    passouVirus = anov.getTerminou();
                    if (anov.getDoente() == true) {
                        anov.pararTempo();
                    }
                    anov = null;
                    podecarregar = true;
                }
            }
        }
        if (!passouTutoria) {
            if (mario.getBoundsmario().intersects(mario.getBoundstutoria(mario.getXt()))) {
                if (!chegouNaPorta) {
                    ano = new AnoTutoria();
                    c = new TelaCozinha();
                    chegouNaPorta = true;
                    podecarregar = false;
                    zerarDxsPersonagem();
                }
                mexe = c.c.F.getTerminou();
                if (ano != null) {
                    if (ano.getTerminou() == true) {
                        ano.pararTempo();
                        c.setVisible(true);
                        minimizar = true;
                        c.requestFocusInWindow();
                        ano = null;
                    }
                }
                if (c.c.F.getTerminou()) {
                    mexe = c.c.F.getTerminou();
                    setFocusable(true);
                    minimizar = false;
                    passouTutoria = c.c.F.getTerminou();
                    c.c.dispose();
                    c.c = null;
                    c.dispose();
                    c = null;
                    c = null;
                    podecarregar = true;
                }
            }
        }
        u = 0;
        if (vidas == 0) {
        }
        repaint();
    }

    private void SetarMatsEPerg() {
        String caminhoarquivo = "Res/Dados.txt";
        String linha = "", textotxt = "";
        FileReader fr;
        try {
            fr = new FileReader(caminhoarquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    linha = "";
                    linha += br.readLine();
                    if (!linha.equals("")) {
                        textotxt += linha + "\n";
                    }
                }
                br.close();
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Prova.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Prova.class.getName()).log(Level.SEVERE, null, ex);
        }

        String mda[] = textotxt.split("\n");
        if (mda[2].equals("informatica")) {
            if (mda[4].equals("1")) {
                endere�o = "informatica/1";
            }
            if (mda[4].equals("2")) {
                endere�o = "informatica/2";
            }
            if (mda[4].equals("3")) {
                endere�o = "informatica/3";
            }
            if (mda[4].equals("4")) {
                endere�o = "informatica/4";
            }
        }
        if (mda[2].equals("agroecologia")) {
            if (mda[4].equals("1")) {
                endere�o = "agroecologia/1";
            }
            if (mda[4].equals("2")) {
                endere�o = "agroecologia/2";
            }
            if (mda[4].equals("3")) {
                endere�o = "agroecologia/3";
            }
            if (mda[4].equals("4")) {
                endere�o = "agroecologia/4";
            }
        }
        if (mda[2].equals("meio ambiente")) {
            if (mda[4].equals("1")) {
                endere�o = "meio ambiente/1";
            }
            if (mda[4].equals("2")) {
                endere�o = "meio ambiente/2";
            }
            if (mda[4].equals("3")) {
                endere�o = "meio ambiente/3";
            }
            if (mda[4].equals("4")) {
                endere�o = "meio ambiente/4";
            }
        }
    }

    public boolean getEmJogo() {
        return emjogo;
    }

    public boolean getMinimizar() {
        return minimizar;
    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (mexe) {
                if (m1 == false || m2 == false || m3 == false) {
                }
                mario.KeyPressed(e, m1, m2, m3);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (mexe) {
                mario.KeyReleased(e);
            }
        }
    }
}
