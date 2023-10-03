package ru.gb.server;

import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;


public class ServerWindow extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 550;

    JButton btnExit;
    JToggleButton btnStart;
    JTextArea textArea;

    //IntermediateClass intermediate;


    public ServerWindow() throws HeadlessException, IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //остановитть при закрытии
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //отцентровать
        setTitle("Сервер"); //заголовок
        setResizable(false); //не изменяемое окно
        ServerSocket serverSocket = new ServerSocket(8080);


        btnStart = new JToggleButton("Влючить");
        btnStart.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (btnStart.isSelected()) {
                    btnStart.setText("Отключить");
                } else {
                    btnStart.setText("Включить");
                }
            }
        });
        btnExit = new JButton("Выход");
        textArea = new JTextArea(); //мнгострочное текстовое поле
        btnStart.addActionListener(new ActionListener() { //добавить слушателя действия
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                String message = currentDateTime.toString();
                if (btnStart.isSelected()) {
                    textArea.append("[" + message + "]" + " Сервер включен" + "\n");
                    readingMessage();
                } else {
                    textArea.append("[" + message + "]" + " Сервер выключен" + "\n");
                }
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(textArea);
        textArea.setEditable(false);

        JPanel panBotton = new JPanel(new GridLayout(1, 2));
        panBotton.add(btnStart);
        panBotton.add(btnExit);
        add(panBotton, BorderLayout.SOUTH);

        setVisible(true);
    }

    void readingMessage(){
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();

            BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(inputStream, StandardCharsets.UTF_8
                            )
                    );
            String readLine =
                    bufferedReader.readLine();

            textArea. append(readLine);

            accept.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
//        intermediate = new IntermediateClass();
//        textArea.append(intermediate.getMessage() + "\n");
    }
}
