package start;

import javax.swing.*;
import java.awt.*;

public class StartMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("60 Seconds");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.black);
        mainPanel.setLayout(new CardLayout());

        Game gamePanel = new Game();
        BackgroundImage menuPanel = new BackgroundImage();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Use BoxLayout with vertical alignment

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Use BoxLayout with horizontal alignment

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font(Font.SERIF, Font.PLAIN,  30));
        startButton.setForeground(Color.black);
        startButton.setUI(new StyledButtonUI());


        // Add horizontal glue to center the button horizontally
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createHorizontalGlue());

        // Add vertical glue to center the button vertically
        menuPanel.add(Box.createVerticalGlue());
        menuPanel.add(buttonPanel);
        menuPanel.add(Box.createVerticalGlue());

        startButton.addActionListener(e -> {
            startButton.setVisible(false);
            menuPanel.fadeOut(() -> {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "game");
                gamePanel.updateGameStat(15,10);
                gamePanel.fadeIn(); // Not sure what this does, so commented out for now
            });
        });

        gamePanel.displayCharacters();

        // Add both panels to the main panel
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(gamePanel, "game");

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
}
