package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HoverMovingPanel extends JPanel {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public HoverMovingPanel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                movePanel();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetPanel();
            }
        });
    }

    private void movePanel() {
        int targetX = (int) (0.2 * (getWidth() - width));
        int targetY = (int) (0.2 * (getHeight() - height));
        setLocation(targetX, targetY);
    }

    private void resetPanel() {
        setLocation(x, y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
