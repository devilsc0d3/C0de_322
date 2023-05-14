package main.java.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JPanel {
    private final JPanel canPanel;
    private final JPanel bottlePanel;
    private final LeftPanel leftPanel;

    Game() {

        setBackground(Color.black);
        setLayout(new BorderLayout());

        initMouseListeners();

        JPanel indicatorPanel = new JPanel(new GridLayout(2,0));
        indicatorPanel.setOpaque(false);
        indicatorPanel.setPreferredSize(new Dimension(0, 150));

        leftPanel = new LeftPanel();
        add(leftPanel,BorderLayout.WEST);

        bottlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        canPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottlePanel.setPreferredSize(new Dimension(50, 400));
        canPanel.setPreferredSize(new Dimension(50, 400));
        bottlePanel.setBackground(new Color(0, 0, 0, 0));
        canPanel.setBackground(new Color(0, 0, 0, 0));

        indicatorPanel.add(bottlePanel);
        indicatorPanel.add(canPanel);
        add(indicatorPanel, BorderLayout.NORTH);
    }

    private void initMouseListeners(){
        ClickableCompsPanel clickableCompsPanel = new ClickableCompsPanel();
        add(clickableCompsPanel, BorderLayout.CENTER);
        clickableCompsPanel.dad.addMouseListener(new MouseAdapter() {

        });
        clickableCompsPanel.mom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                leftPanel.swapPrompt();

            }
        });
        clickableCompsPanel.openJournalPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                leftPanel.swapPrompt();
            }
        });
    }

    private void addWaterBottle() {
        ImageIcon waterImg = new ImageIcon("source/img/water-bottle.png");
        Image scaledImage = waterImg.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel waterIndicator = new JLabel(scaledIcon, JLabel.LEFT);

        bottlePanel.add(waterIndicator);

        bottlePanel.revalidate();
    }

    private void addFoodCan() {
        ImageIcon waterImg = new ImageIcon("source/img/soup-can.png");
        Image scaledImage = waterImg.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel foodIndicator = new JLabel(scaledIcon, JLabel.LEFT);
        canPanel.add(foodIndicator);

        canPanel.revalidate();
    }

    public void updateGameStat(int water, int food) {
        for (int i = 0; i < water; i++) {
            addWaterBottle();
        }
        for (int i = 0; i < food; i++) {
            addFoodCan();
        }
    }
}
