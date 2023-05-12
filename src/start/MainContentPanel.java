package start;

import javax.swing.*;
import java.awt.*;

public class MainContentPanel extends JPanel {
    public MainContentPanel(){
        setBackground(Color.black);
        setLayout(new CardLayout());

        MainGamePanel mainGamePanel = new MainGamePanel();
        StartMenu menuPanel = new StartMenu();
        menuPanel.startButton.addActionListener(e -> {
            menuPanel.startButton.setVisible(false);
            menuPanel.titlePanel.setVisible(false);
            menuPanel.fadeOut(() -> {
                CardLayout cardLayout = (CardLayout) this.getLayout();
                cardLayout.show(this, "game");
                mainGamePanel.gamePanel.updateGameStat(15,10);
                mainGamePanel.backgroundPanel.fadeIn(); // Not sure what this does, so commented out for now
                mainGamePanel.addGamePanel();
            });
        });


        // Add both panels to the main panel
        add(menuPanel, "menu");
        add(mainGamePanel, "game");

    }

}
