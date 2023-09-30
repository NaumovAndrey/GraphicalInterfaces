package ru.gb.server;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 550;

    JButton btnToSend;
    JToggleButton btnLogin;

    public ClientGUI() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //остановитть при закрытии
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //отцентровать
        setTitle("Клиент"); //заголовок
        //setResizable(false); //не изменяемое окно

        btnLogin = new JToggleButton("Подключить");
        btnLogin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (btnLogin.isSelected()) {
                    btnLogin.setText("Отключить");
                } else {
                    btnLogin.setText("Подключить");
                }
            }
        });

        btnToSend = new JButton("Отправить");

        JTextArea logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        add(createALoginPanel(), BorderLayout.NORTH);
        add(logTextArea);
        add(sendingMessages(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private Component sendingMessages() {
        JPanel entryPanel = new JPanel(new GridLayout(0, 2));
        JTextArea textArea = new JTextArea();
//        textArea.setLineWrap(true); //перенос строк
//        textArea.setWrapStyleWord(true); //перенос слов
        JScrollPane jScrollPane = new JScrollPane(textArea); //скролинг
//        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        entryPanel.add(jScrollPane, BorderLayout.CENTER);
        entryPanel.add(btnToSend, BorderLayout.EAST);

        textArea.setText("Введите cсообщение");
        textArea.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals("Введите cсообщение")) {
                    textArea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textArea.getText().isEmpty()) {
                    textArea.setText("Введите cсообщение");
                }
            }
        });

        btnToSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textArea.getText();
                //здесь хочу обратиться к  logTextArea (который инициализирован на 23 строке)  и присвоить, что то вроде этого:
                //logTextArea.append(message + "\n");
                textArea.setText("");

            }
        });

        return entryPanel;
    }

    private Component createALoginPanel() {
        JPanel entryPanel = new JPanel(new GridLayout(2, 1));
        entryPanel.add(createIpPanel());
        entryPanel.add(createLoginPanel());

        return entryPanel;
    }

    private Component createIpPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JTextField ipText = new JTextField();
        JTextField portText = new JTextField();
        panel.add(ipText);
        panel.add(portText);

        ipText.setText("Введите ip сервера");
        ipText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ipText.getText().equals("Введите ip сервера")) {
                    ipText.setText("127.0.0.1");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ipText.getText().isEmpty()) {
                    ipText.setText("Введите ip сервера");
                }
            }
        });

        portText.setText("Введите PORT сервера");
        portText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (portText.getText().equals("Введите PORT сервера")) {
                    portText.setText("8080");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (portText.getText().isEmpty()) {
                    portText.setText("Введите PORT сервера");
                }
            }
        });

        return panel;
    }

    private Component createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));

        JTextField loginText = new JTextField();
        JPasswordField passwordText = new JPasswordField();
        passwordText.setEchoChar('*');

        panel.add(loginText);
        panel.add(passwordText);
        panel.add(btnLogin);

        loginText.setText("Введите логин");
        loginText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (loginText.getText().equals("Введите логин")) {
                    loginText.setText("Admin");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (loginText.getText().isEmpty()) {
                    loginText.setText("Введите логин");
                }
            }
        });

        return panel;
    }
}
