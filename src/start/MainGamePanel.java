package start;

import javax.swing.*;
import java.awt.*;

public class MainGamePanel extends JLayeredPane {
    public BackgroundImage backgroundPanel;
    public Game gamePanel;

    public MainGamePanel() {
        setLayout(null);
        addBackgroundPanel();
        displayCharacters();
    }

    public void addBackgroundPanel() {
        backgroundPanel = new BackgroundImage();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBounds(0, 0, 1600, 850);
        add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
    }

    public void addGamePanel() {
        gamePanel = new Game();
        gamePanel.setBounds(0, 0, 1600, 850);
        add(gamePanel, JLayeredPane.PALETTE_LAYER);
    }


    public void displayCharacters() {
        JPanel charactersPanel = new JPanel(new FlowLayout());
        Dad dad = new Dad();
        Mom mom = new Mom();
        charactersPanel.add(dad);
        charactersPanel.add(mom);
        charactersPanel.setPreferredSize(new Dimension(getWidth(), 500));
        charactersPanel.setOpaque(false);
        backgroundPanel.add(charactersPanel, BorderLayout.SOUTH);
    }
}
