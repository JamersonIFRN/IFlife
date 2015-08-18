/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tutoria;

import java.net.*;

/**
 *
 * @author DANIEL
 */
public class Conexão {

    public Conexão() {
    }

    private String localhost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return new String("127.0.0.1");
        }
    }

    public static void main(String[] args) {
        new Conexão();
    }
}
