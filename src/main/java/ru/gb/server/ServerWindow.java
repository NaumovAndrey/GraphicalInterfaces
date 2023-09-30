package ru.gb.server;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerWindow extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 550;

    JButton btnStop;
    JToggleButton btnStart;

    public ServerWindow() throws HeadlessException{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //остановитть при закрытии
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //отцентровать
        setTitle("Сервер"); //заголовок
        setResizable(false); //не изменяемое окно

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

        btnStop = new JButton("Выход");

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
