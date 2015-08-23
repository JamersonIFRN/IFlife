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
    private Buraco buraco;
    Mundo mundo;
    Professores professores;
    PortaDaTutoria porta;
    public Timer timer, esperarsalvar;
    private boolean mexe = true, caiu = false, passouTutoria = false, chegouNaPorta = false, passouVirus = false, chegouvirus = false;
    private boolean cont = true, emjogo = true, podecarregar = true;
    TelaDePergunta11 tl = new TelaDePergunta11();
    boolean m1 = true, m2 = true, m3 = true, minimizar = false;
    boolean pprofs[];
    String[] perguntas;
    String[] materias;
    boolean passouNasMaterias[];
    String endereço;
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
        buraco = new Buraco();
        mundo = new Mundo();
        professores = new Professores();
        porta = new PortaDaTutoria();
        pprofs = new boolean[professores.getNumProfs()];
        passouNasMaterias = new boolean[professores.getNumProfs()];
        perguntas = new String[professores.getNumProfs()];
        String arquivo = "Res/Cursos/" + endereço + "/perguntas.txt", linha, f = "";
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
        arquivo = "Res/Cursos/" + endereço + "/materias.txt";
        f = "";
        materias = new String[professores.getNumProfs()];

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
        professores.setmaterias(materias);
        if (carregar) {
            carregarJ(true);
        } else {
            for (int i = 0; i < professores.getNumProfs(); i++) {
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
        graficos.drawImage(mundo.getImagem2(), mundo.getXm(), mundo.getYm(), this);
        graficos.setFont(new Font("Arial Narrow", 1, 20));

        int u = 0;
        for (int i = 0; i <= 10; i++) {
            graficos.drawImage(buraco.getImagem4(), buraco.getXb() + u, buraco.getYb(), this);
            u += 2500;
        }
        u = 0;
        for (int i = 0; i <= 10; i++) {
            graficos.drawImage(buraco.getImagem6(), buraco.getXb() - 32 + u, buraco.getYb() - 739, this);
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
        graficos.drawString("Atenção, precisa ser aprovado em 7 materias", 5, 85);
        u = 0;
        Image profs[] = professores.getProfs();
        for (int i = 0; i < professores.getNumProfs(); i++) {
            graficos.drawImage(profs[i], professores.getXp() + u, professores.getYp(), this);
            u += 2500;
        }
        graficos.drawImage(porta.getImagemTutoria(), porta.getXt(), porta.getYt(), this);
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
                porta.setXt(Integer.parseInt(xms[8]));
                mario.setXms(Integer.parseInt(xms[7]));
                buraco.setXb(Integer.parseInt(xms[6]));
                professores.setXp(Integer.parseInt(xms[5]));
                mundo.setXm(Integer.parseInt(xms[4]));

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
        mundo.setDxm(0);
        mario.setDxm(0);
        professores.setDxp(0);
        buraco.setDxb(0);
        mario.setDxc(0);
        porta.setDxt(0);
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
            bw.write("" + mundo.getXm());
            bw.newLine();
            bw.write("" + professores.getXp());
            bw.newLine();
            bw.write("" + buraco.getXb());
            bw.newLine();
            bw.write("" + mario.getXc());
            bw.newLine();
            bw.write("" + porta.getXt() + "");
            bw.newLine();
            bw.write("" + vidas);
            bw.write("@");
            for (int i = 0; i < professores.getNumProfs(); i++) {
                bw.write("" + pprofs[i]);
                if (i != professores.getNumProfs() - 1) {
                    bw.newLine();
                }
            }
            bw.write("@");
            for (int i = 0; i < professores.getNumProfs(); i++) {
                bw.write("" + passouNasMaterias[i]);
                if (i != professores.getNumProfs() - 1) {
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
        System.out.println("xm" + mario.getXm() + " x " + mario.getX());
        if (mundo.getXm() == -28920 && mario.getX() == 750 && mexe) {
            mexe = false;
            emjogo = false;
            SePassou sp = new SePassou();
            sp.setNMaterias(professores.getNumProfs());
            sp.setMaterias(materias);
            sp.setPassou(passouNasMaterias);
            sp.setVisible(m1);
            timer.stop();
            timer = null;
            mario.setPersonagemj(mario.getReferencia());
        }
        if (mexe) {
            mario.mexer();
            mundo.mexer();
            buraco.mexer();
            professores.mexer();
            porta.mexer();
        }
        int u = 0;
        for (int i = 0; i <= 10; i++) {
            if (mario.getBounds2().intersects(buraco.getBoundsburaco(buraco.getXb() + u))) {
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
        for (int i = 0; i < professores.getNumProfs(); i++) {

            if (mario.getBoundsmario().intersects(professores.getBoundsprofessor(professores.getXp() + u))) {
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
            if (mario.getBoundsmario().intersects(porta.getBoundstutoria(porta.getXt() - 785))) {
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
            if (mario.getBoundsmario().intersects(porta.getBoundstutoria(porta.getXt()))) {
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
                endereço = "informatica/1";
            }
            if (mda[4].equals("2")) {
                endereço = "informatica/2";
            }
            if (mda[4].equals("3")) {
                endereço = "informatica/3";
            }
            if (mda[4].equals("4")) {
                endereço = "informatica/4";
            }
        }
        if (mda[2].equals("agroecologia")) {
            if (mda[4].equals("1")) {
                endereço = "agroecologia/1";
            }
            if (mda[4].equals("2")) {
                endereço = "agroecologia/2";
            }
            if (mda[4].equals("3")) {
                endereço = "agroecologia/3";
            }
            if (mda[4].equals("4")) {
                endereço = "agroecologia/4";
            }
        }
        if (mda[2].equals("meio ambiente")) {
            if (mda[4].equals("1")) {
                endereço = "meio ambiente/1";
            }
            if (mda[4].equals("2")) {
                endereço = "meio ambiente/2";
            }
            if (mda[4].equals("3")) {
                endereço = "meio ambiente/3";
            }
            if (mda[4].equals("4")) {
                endereço = "meio ambiente/4";
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
                mundo.KeyPressed(e, m1, m2, m3);
                buraco.KeyPressed(e, m1, m2, m3);
                professores.KeyPressed(e, m1, m2, m3);
                porta.KeyPressed(e, m1, m2, m3);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (mexe) {
                mario.KeyReleased(e);
                mundo.KeyReleased(e);
                buraco.KeyReleased(e);
                professores.KeyReleased(e);
                porta.KeyReleased(e);
            }
        }
    }
}
