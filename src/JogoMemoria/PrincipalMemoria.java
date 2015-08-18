/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JogoMemoria;

import java.util.Random;

/**
 *
 * @author DANIEL
 */
public class PrincipalMemoria {

    String numerosSorteados = "";
    String numeros[] = new String[8];

    public PrincipalMemoria() {
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = "Res/JogosExtras/Memoria";


            Random r = new Random();
            if (numerosSorteados.equals("")) {
                int var = 0;
                while (var == 0) {
                    var = r.nextInt(15);
                }
                numerosSorteados += "" + var;
                numeros[i] += "/" + var + ".png";
            } else {
                boolean f = true;
                while (f) {
                    int var = 0;
                    while (var == 0) {
                        var = r.nextInt(15);
                    }
                    String blocoC[] = numerosSorteados.split("@");
                    int j = 0;
                    for (int jj = 0; jj < blocoC.length; jj++) {
                        if (var == Integer.parseInt(blocoC[jj])) {
                            j++;
                        }
                    }
                    if (j == 0) {
                        f = false;
                        numerosSorteados += "@" + var;
                        numeros[i] += "/" + var + ".png";
                    }
                }
            }
        }
        JogoMemoria jm = new JogoMemoria(numeros[0], numeros[1], numeros[2], numeros[3], numeros[4], numeros[5], numeros[6], numeros[7]);
        jm.setVisible(true);
    }

    public static void main(String[] args) {
        new PrincipalMemoria();
    }
}
