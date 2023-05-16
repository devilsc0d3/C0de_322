package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JPanel {
    private final JPanel canPanel;
    private final JPanel bottlePanel;
    private final LeftPanel leftPanel;
    private int food;
    private int water;
    private final JPanel indicatorPanel;

    public void setFood(int food) {
        this.food = food;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getFood() {
        return food;
    }

    public int getWater() {
        return water;
    }

    Game(Integer difficulty) {
        setBackground(Color.black);
        setLayout(new BorderLayout());


        indicatorPanel = new JPanel(new GridLayout(2, 0));
        indicatorPanel.setBackground(Color.black);
        indicatorPanel.setPreferredSize(new Dimension(0, 150));

        leftPanel = new LeftPanel();
        add(leftPanel, BorderLayout.WEST);

        initMouseListeners();

        bottlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        canPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottlePanel.setPreferredSize(new Dimension(50, 400));
        canPanel.setPreferredSize(new Dimension(50, 400));
        bottlePanel.setBackground(new Color(0, 0, 0, 0));
        canPanel.setBackground(new Color(0, 0, 0, 0));

        indicatorPanel.add(bottlePanel);
        indicatorPanel.add(canPanel);
        add(indicatorPanel, BorderLayout.NORTH);
        initFoodAndWater(difficulty);
    }

    private void initFoodAndWater(int difficulty){
        switch (difficulty){
            case 1:setFood(15);setWater(15);
            case 2:setFood(10);setWater(11);
            case 3:setFood(7);setWater(8);
        }
    }

    private void initMouseListeners() {
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


        int x = 0;

        for (AddButton addButton : leftPanel.getAddButtons()) {
            int finalX1 = x;
            addButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (finalX1 == 0) {
                        setFood(getFood() - 1);
                        updateGameStat();
                    } else {
                        setWater(getWater() - 1);
                        updateGameStat();
                    }
                }
            });
            x++;
        }

        x = 0;

        for (RemoveButton removeButton : leftPanel.getRemoveButtons()) {
            int finalX = x;
            removeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (finalX == 0) {
                        setFood(getFood() + 1);
                        updateGameStat();
                    } else {
                        setWater(getWater() + 1);
                        updateGameStat();
                    }
                }
            });
            x++;
        }
    }

    public void setPromptText(String text) {
        leftPanel.promptPanel.textToDisplay = text;
        leftPanel.promptPanel.textDisplayed = false;
    }
    ImageIcon waterImg = new ImageIcon("C0de_322/source/img/water-bottle.png");
    private void addWaterBottle() {
        Image scaledImage = waterImg.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel waterIndicator = new JLabel(scaledIcon, JLabel.LEFT);

        bottlePanel.add(waterIndicator);

        bottlePanel.revalidate();
    }
    ImageIcon canImg = new ImageIcon("C0de_322/source/img/soup-can.png");

    private void addFoodCan() {
        Image scaledImage = canImg.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel foodIndicator = new JLabel(scaledIcon, JLabel.LEFT);
        canPanel.add(foodIndicator);

        canPanel.revalidate();
    }

    private void resetIndicatorPanel() {
        Component[] cans =  canPanel.getComponents();
        Component[] bottles =  bottlePanel.getComponents();
        for (Component label : bottles) {
                bottlePanel.remove(label);
        }
        for (Component label : cans) {
                canPanel.remove(label);
        }
        canPanel.revalidate();
        canPanel.repaint();
        bottlePanel.revalidate();
        bottlePanel.repaint();
        indicatorPanel.revalidate();
        indicatorPanel.repaint();
    }

    public void updateGameStat() {
        resetIndicatorPanel();
        for (int i = 0; i < water; i++) {
            addWaterBottle();
        }
        for (int i = 0; i < food; i++) {
            addFoodCan();
        }
    }
}
