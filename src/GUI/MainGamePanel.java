package GUI;

import javax.swing.*;
import java.awt.*;

public class MainGamePanel extends JLayeredPane {
    public BackgroundImage backgroundPanel;
    public Game gamePanel;
    public MainGamePanel() {
        setLayout(null);
        addBackgroundPanel();
        addGamePanel();
    }
    public void addBackgroundPanel() {
        backgroundPanel = new BackgroundImage();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBounds(0, 0, 1600, 850);
        add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
    }

    public void addGamePanel() {
        gamePanel = new Game(2);
        gamePanel.setOpaque(false);
        gamePanel.updateGameStat();
        gamePanel.setBounds(0, 0, 1600, 850);
        add(gamePanel, JLayeredPane.PALETTE_LAYER);
    }

}
