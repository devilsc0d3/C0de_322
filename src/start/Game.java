package start;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game extends JPanel {
    private JPanel canPanel;
    private JPanel bottlePanel;

    Game() {

        setBackground(Color.black);
        setLayout(new BorderLayout());
        initChoicePanel();

        JPanel indicatorPanel = new JPanel(new GridLayout(2,0));
        indicatorPanel.setOpaque(false);
        indicatorPanel.setPreferredSize(new Dimension(0, 150));

        bottlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        canPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottlePanel.setPreferredSize(new Dimension(50, 400));
        canPanel.setPreferredSize(new Dimension(50, 400));
        bottlePanel.setBackground(new Color(0, 0, 0, 0));
        canPanel.setBackground(new Color(0, 0, 0, 0));

        updateGameStat(15,15);

        indicatorPanel.add(bottlePanel);
        indicatorPanel.add(canPanel);
        add(indicatorPanel, BorderLayout.NORTH);
    }
    private void initChoicePanel(){
        JPanel choicePanel = new JPanel();
        choicePanel.setBackground(Color.red);
        choicePanel.setPreferredSize(new Dimension(200,900));
        add(choicePanel,BorderLayout.EAST);
    }




    private void addWaterBottle() {
        ImageIcon waterImg = new ImageIcon(Objects.requireNonNull(Game.class.getResource("water-bottle.png")));
        Image scaledImage = waterImg.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel waterIndicator = new JLabel(scaledIcon, JLabel.LEFT);

        bottlePanel.add(waterIndicator);

        bottlePanel.revalidate();
    }

    private void addFoodCan() {
        ImageIcon waterImg = new ImageIcon(Objects.requireNonNull(Game.class.getResource("soup-can.png")));
        Image scaledImage = waterImg.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel foodIndicator = new JLabel(scaledIcon, JLabel.LEFT);
        canPanel.add(foodIndicator);

        canPanel.revalidate();
    }

    private static void addResizeListener(JFrame frame, JPanel leftPanel) {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Update the size of the left panel when the frame is resized
                int newWindowWidth = (int) frame.getContentPane().getSize().getWidth();
                int newPanelWidth = newWindowWidth / 2;
                leftPanel.setPreferredSize(new Dimension(newPanelWidth, leftPanel.getHeight()));
                leftPanel.revalidate();
            }
        });
    }

    public void updateGameStat(int water, int food) {
        for (int i = 0; i < water; i++) {
            addWaterBottle();
        }
        for (int i = 0; i < food; i++) {
            addFoodCan();
        }
    }

    public List<JPanel> getPanels() {
        List<JPanel> panelList = new ArrayList<JPanel>();
        panelList.add(bottlePanel);
        panelList.add(canPanel);
        return panelList;
    }
}
