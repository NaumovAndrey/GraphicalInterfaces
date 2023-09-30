package ru.gb.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerWindow extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 550;

    JButton btnStart, btnStop;

    public ServerWindow() throws HeadlessException{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //остановитть при закрытии
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //отцентровать
        setTitle("Сервер"); //заголовок
        setResizable(false); //не изменяемое окно

        btnStart = new JButton("Старт");
        btnStop = new JButton("Стоп");

        JTextArea textArea = new JTextArea(); //мнгострочное текстовое поле

        btnStart.addActionListener(new ActionListener() { //добавить слушателя действия
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        add(textArea);
        textArea.setEditable(false);

        JPanel panBotton = new JPanel(new GridLayout(1, 2));
        panBotton.add(btnStart);
        panBotton.add(btnStop);
        add(panBotton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
