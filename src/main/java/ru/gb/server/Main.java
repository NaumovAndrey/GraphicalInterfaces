package ru.gb.server;

import ru.gb.server.ServerWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        new ServerWindow();
        new ClientGUI();
        //ClientGUI client = new ClientGUI();


    }
}
