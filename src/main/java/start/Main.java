package main.java.start;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        MainContentPanel mainPanel = new MainContentPanel();
        frame.setTitle("CODE_322");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(new Dimension(1600,900));

        ImageIcon icon = new ImageIcon("source/img/logo.jpg");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().add(mainPanel);

        frame.setVisible(true);
    }
}
