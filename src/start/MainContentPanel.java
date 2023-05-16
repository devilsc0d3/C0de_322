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
                mainGamePanel.backgroundPanel.fadeIn();
            });
        });

        add(menuPanel, "menu");
        add(mainGamePanel, "game");
    }
}
