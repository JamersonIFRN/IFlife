/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TFasePrincipal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANIEL
 */
public class ImagemMasculinoOuFeminino {

    String caminhoImagem, ano, nome, sexo;

    public ImagemMasculinoOuFeminino() {
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
                textotxt = textotxt.substring(0, textotxt.length() - 1);
                br.close();
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(ImagemMasculinoOuFeminino.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImagemMasculinoOuFeminino.class.getName()).log(Level.SEVERE, null, ex);
        }
        String text[] = textotxt.split("\n");
        ano = text[4];
        nome = text[0];
        sexo = text[3];
    }

    public void setarJogo(String jogo) {
        if (jogo.equals("faseandarfrente")) {
            if (sexo.equals("masculino")) {
                caminhoImagem = "Res/personagem.gif";
            }
            if (sexo.equals("feminino")) {
                caminhoImagem = "Res/personagemF.gif";
            }
        }
        if (jogo.equals("faseandartraz")) {
            if (sexo.equals("masculino")) {
                caminhoImagem = "Res/personagem2.gif";
            }
            if (sexo.equals("feminino")) {
                caminhoImagem = "Res/personagem2f.gif";
            }
        }
        if (jogo.equals("faseparadofrente")) {
            if (sexo.equals("masculino")) {
                caminhoImagem = "Res/personagemParado.png";
            }
            if (sexo.equals("feminino")) {
                caminhoImagem = "Res/personagemParadof.png";
            }
        }
        if (jogo.equals("faseparadotraz")) {
            if (sexo.equals("masculino")) {
                caminhoImagem = "Res/personagemCotrario.png";
            }
            if (sexo.equals("feminino")) {
                caminhoImagem = "Res/personagemContrario.png";
            }
        }
        if (jogo.equals("lab1")) {
            if (sexo.equals("masculino")) {
                caminhoImagem = "Res/pers.png";
            }
            if (sexo.equals("feminino")) {
                caminhoImagem = "Res/persf.png";
            }
        }
        if (jogo.equals("lab2")) {
            if (sexo.equals("masculino")) {
                caminhoImagem = "Res/pers2.png";
            }
            if (sexo.equals("feminino")) {
                caminhoImagem = "Res/persf2.png";
            }
        }
        if (jogo.equals("formou")) {
            if (sexo.equals("masculino")) {
                caminhoImagem = "Res/formouhm.png";
            }
            if (sexo.equals("feminino")) {
                caminhoImagem = "Res/formoum.png";
            }
        }
        if (jogo.equals("3X4")) {
            if (sexo.equals("masculino")) {
                caminhoImagem = "Res/3X4.png";
            }
            if (sexo.equals("feminino")) {
                caminhoImagem = "Res/3X4m.png";
            }
        }
        if (jogo.equals("fundo")) {
            if (ano.equals("1") || ano.equals("2")) {
                caminhoImagem = "Res/FUNDO.png";
            }
            if (ano.equals("3") || ano.equals("4")) {
                caminhoImagem = "Res/FUNDOAmarelo.png";
            }
        }
        if (jogo.equals("tutoria")) {
            if (sexo.equals("masculino")) {
                caminhoImagem = "Res/PersonagemSentado.png";
            }
            if (sexo.equals("feminino")) {
                caminhoImagem = "Res/PersonageSentado2.png";
            }
        }
    }

    public String getCaminho() {
        return caminhoImagem;
    }

    public String getNome() {
        return nome;
    }

    public static void main(String[] args) {
        ImagemMasculinoOuFeminino imagemMasculinoOuFeminino = new ImagemMasculinoOuFeminino();
        imagemMasculinoOuFeminino.setarJogo("faseparadotraz");
    }
}
