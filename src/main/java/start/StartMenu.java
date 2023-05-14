package main.java.start;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends BackgroundImage {
    public JButton startButton;
    public JPanel titlePanel;

    public StartMenu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use vertical BoxLayout for the StartMenu panel

        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.X_AXIS));
        JLabel titleLabel = new JLabel("CODE_322");
        titlePanel.setOpaque(false);
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 140));
        titleLabel.setForeground(Color.GRAY);
        titlePanel.add(titleLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Use BoxLayout with horizontal alignment

        startButton = new JButton("Start Game");
        startButton.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        startButton.setForeground(Color.black);
        startButton.setUI(new StyledButtonUI());
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createHorizontalGlue());

        add(Box.createVerticalGlue());
        add(titlePanel);
        add(Box.createVerticalGlue());
        add(buttonPanel);
        add(Box.createVerticalGlue());
    }
}
