package Server;

import Request.Request;
import Utils.Utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server extends Utils {

    private final ServerSocket ss;

    public Server() throws IOException {
        ss = new ServerSocket(port);
    }

    public void acceptClient() throws IOException {

            Socket socket = ss.accept();
            new Request(socket).start();
            acceptClient();

    }

    public static void main(String[] args) throws IOException {

        // riempio la matrice
        for (char[] chars : SEATS) Arrays.fill(chars, 'L');

        new Server().acceptClient();
    }

}
