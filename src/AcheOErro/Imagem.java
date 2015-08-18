/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AcheOErro;

import java.util.Random;

/**
 *
 * @author DANIEL
 */
public class Imagem {

    int numeroTmp = 0, z;
    String s, s2, dif;

    public Imagem(String dif, String nivel) {
        this.dif = dif;
        if (nivel.equals("facil")) {
            Random radom = new Random();
            while (numeroTmp == 0) {
                numeroTmp = radom.nextInt(9);
            }
        } else if (nivel.equals("medio")) {
            Random radom = new Random();
            while (numeroTmp == 0) {
                numeroTmp = radom.nextInt(16);
            }
        } else {
            Random radom = new Random();
            while (numeroTmp == 0) {
                numeroTmp = radom.nextInt(25);
            }
        }


        s = "Res/JogosExtras/AcheOErro/" + dif + ".png";
        s2 = "Res/JogosExtras/AcheOErro/" + dif + "-Errada.png";

    }

    public int getNumeroTmp() {
        return numeroTmp;
    }

    public String getS2() {
        return s2;
    }

    public String getS() {
        return s;
    }
}
