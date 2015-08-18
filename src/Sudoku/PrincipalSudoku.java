/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sudoku;

import CacaPalavras.CaçaPalavras;
import CacaPalavras.PrincipalCaca;
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
public class PrincipalSudoku {

    public PrincipalSudoku(int num, String nivel) {
        String arquivo = "Res/JogosExtras/Sudoku/contador.txt", linha = "", f = "", line;
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
            } catch (IOException ex) {
                Logger.getLogger(PrincipalCaca.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalCaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        f = f.substring(0, f.length() - 1);
        String[] conts = f.split("@");
        linha = conts[num];
        if (nivel.equals("facil")) {
            arquivo = "Res/JogosExtras/Sudoku/SudokuFacil.txt";
        } else if (nivel.equals("medio")) {
            arquivo = "Res/JogosExtras/Sudoku/SudokuMedio.txt";
        } else if (nivel.equals("dificil")) {
            arquivo = "Res/JogosExtras/Sudoku/SudokuDificil.txt";
        } else {
            arquivo = "Res/JogosExtras/Sudoku/SudokuAvancado.txt";
        }
        f = "";
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                while (br.ready()) {
                    line = "";
                    line += br.readLine();
                    if (!line.equals("")) {
                        f += line + "\n";
                    }
                }
                f = f.substring(0, f.length() - 1);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalCaca.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalCaca.class.getName()).log(Level.SEVERE, null, ex);
        }

        String caça[] = f.split("\n");
        Sudoku s = new Sudoku(caça[Integer.parseInt(linha)], Integer.parseInt(linha), nivel);
        s.setVisible(true);

    }
}
