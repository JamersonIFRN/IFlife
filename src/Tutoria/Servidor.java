package Tutoria;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Servidor {

    java.util.List<PrintWriter> escritores = new ArrayList<>();
    static ServerSocket server;

    private void encaminharparatodos(String texto) {
        for (PrintWriter w : escritores) {
            w.println(texto);
            w.flush();
        }
    }

    public Servidor() {
        try {
            int porta = Integer.parseInt(JOptionPane.showInputDialog("Digite a porta do servidor"));
            if (porta > 64000 || porta < 1) {
                while (porta > 64000 || porta < 1) {
                    porta = Integer.parseInt(JOptionPane.showInputDialog("Porta invalida\nDigite a porta do servidor"));
                }
            }

            server = new ServerSocket(porta);

            while (true) {
                Socket socket = server.accept();
                new Thread(new EscutaCliente(socket)).start();
                PrintWriter p = new PrintWriter(socket.getOutputStream());
                escritores.add(p);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Errooooooooooooooooo");
            System.exit(0);
        }
    }

    private class EscutaCliente implements Runnable {

        Scanner leitor;

        public EscutaCliente(Socket socket) {
            try {
                leitor = new Scanner(socket.getInputStream());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Errooooooooooooooooo");
                System.exit(0);
            }
        }

        @Override
        public void run() {
            String texto;
            while ((texto = leitor.nextLine()) != null) {
                encaminharparatodos(texto);
            }
        }
    }

    public static void main(String[] args) {
        //PararServidor pr = new PararServidor();
        //pr.setVisible(true);
        new Servidor();
    }
}
