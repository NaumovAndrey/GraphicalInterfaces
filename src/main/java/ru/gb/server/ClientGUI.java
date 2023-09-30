package ru.gb.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 550;

    JButton btnLogin, btnToSend;

    public ClientGUI() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //остановитть при закрытии
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //отцентровать
        setTitle("Клиент"); //заголовок
        //setResizable(false); //не изменяемое окно

        btnLogin = new JButton("Логин");
        btnToSend = new JButton("Отправить");

        JTextField ipText = new JTextField();
        JTextField portText = new JTextField();
        JTextField loginText = new JTextField();
        JTextField passwordText = new JTextField();
        JTextField mesageText = new JTextField();

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnToSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JPanel entryPanel = new JPanel(new GridLayout(2,3));
        entryPanel.add(ipText);
        ipText.setForeground(Color.GRAY);
        ipText.setText("Введите ip сервера");
        ipText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ipText.getText().equals("Введите ip сервера")) {
                    ipText.setText("");
                    ipText.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ipText.getText().isEmpty()) {
                    ipText.setForeground(Color.GRAY);
                    ipText.setText("Введите ip сервера");
                }
            }
        });

        entryPanel.add(portText);
        entryPanel.add(loginText);
        entryPanel.add(passwordText);
        entryPanel.add(btnLogin);

        add(entryPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private Component createEntryPanel(){

    }
}
