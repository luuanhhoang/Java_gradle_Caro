/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hoangdz.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author hoangisdev
 */
public class PlayerService {

    private Socket socket; // kh?i t?o socket
    private String name;
    private char Sybom;
    private BufferedReader in;
    private PrintWriter out;
    private boolean myTurn = false;


    public PlayerService(Socket socket, char Sybom) { // dùng ð? kh?i t?o 1 ngý?i chõi m?i
        try {
            System.out.println("1 2 3 4 ");
            this.socket = socket; // truy?n socket
            this.Sybom = Sybom; // truy?n kí t?
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // ð? x? l? t? client
            this.out = new PrintWriter(socket.getOutputStream(), true); // ð? x? l? t? server
            this.name = in.readLine();
            if (this.name == null || this.name.trim().isEmpty()) {
                this.name = "Player" + Sybom;
            }
        } catch (Exception e) {
        }
    }

    public String getName() {
        return name;
    }

    public char getSybom() {
        return Sybom;
    }

    public BufferedReader getInput() {
        return in;
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }
    public void setTurn(boolean turn) {
        this.myTurn = turn;
    }

    public void close() {
        try {
            socket.close();
            System.out.println("Disconnect to player : " + name);
        } catch (Exception e) {
        }
    }

    
}
