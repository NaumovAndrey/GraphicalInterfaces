package ru.gb.server;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;


public class ClientGUI extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 550;

    JButton btnToSend;
    JToggleButton btnLogin;
    JTextArea logTextArea;

    ServerWindow serverWindow;
    //IntermediateClass intermediate;
    Socket socket;

    public ClientGUI() throws HeadlessException, IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //остановитть при закрытии
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //отцентровать
        setTitle("Клиент"); //заголовок
        setResizable(false); //не изменяемое окно

        btnLogin = new JToggleButton("Подключить");
        btnToSend = new JButton("Отправить");
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);




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

        btnToSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textArea.getText();
                logTextArea.append(message + "\n");
                textArea.setText("");

                try {
                    OutputStream toSend = socket.getOutputStream();
                    toSend.write(message.getBytes());
                    toSend.flush();

                    socket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

//                intermediate = new IntermediateClass(message);
//                // у сервера надо вызвать метод чтения сообщения
//                ServerWindow.readingMessage();
            }
        });

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

        btnToSend.setEnabled(false);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selected = btnLogin.isSelected();
                btnToSend.setEnabled(selected);

                LocalDateTime currentDateTime = LocalDateTime.now();
                String message = currentDateTime.toString();
                if (btnLogin.isSelected()) {
                    try {
                        socket = new Socket("localhost", 8080);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    logTextArea.append("[" + message + "]" + " Соединение установлено" + "\n");


                } else {
                    logTextArea.append("[" + message + "]" + " Соединение разорвано" + "\n");
                }

            }
        });

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
