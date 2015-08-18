/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CampoMinado;

import java.util.Scanner;

/**
 *
 * @author DANIEL
 */
public class RodarMusica {

    Music MU;

    public RodarMusica(String caminho) {
        String g[] = new String[1];
        g[0] = caminho;
        String filePath = getPathFromArgs(g);
        MU = new Music();
        if (filePath == null) {
            loadMP3(MU);
        } else {
            if (!MU.loadFile(filePath)) {
                System.err.println("Invalid path!");
                loadMP3(MU);
            }
        }
        MU.play();
    }

    public void Parar() {
        MU.stop();
    }

    public void Restart() {
        MU.restart();
    }

    private static void loadMP3(Music MU) {
        boolean validPath;
        String filePath;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Path to .mp3: ");
            filePath = in.nextLine();
            validPath = MU.loadFile(filePath);

            if (!validPath) {
            }
        } while (!validPath);
    }

    private static String getPathFromArgs(String[] args) {
        String s = null;
        try {
            s = args[0];
        } catch (Exception e) {
//no arguements
        }
        return s;
    }
}
