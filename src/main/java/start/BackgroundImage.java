package start;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class BackgroundImage extends JPanel {
    private final Image backgroundImage;
    private float alpha;

    public BackgroundImage() {
        setBackground(Color.black);
        ImageIcon img = new ImageIcon("src/main/java/start/background.jpg");
        backgroundImage = img.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        alpha = 1.0f; // Initial opacity
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g2d.dispose();
    }

    static Timer timer;

    public void fadeOut(Runnable callback) {
        timer = new Timer(1, e -> {
            alpha -= 0.01; // Set a lower opacity value (0.5 for 50% opacity)
            if (alpha <= 0) {
                alpha = 0;
                timer.stop();
                callback.run();
            }
            repaint();
        });
        timer.start();
    }
    public void fadeIn() {
        if (alpha>0){
            alpha=0;
            repaint();
        }
        timer = new Timer(1, e -> {
            alpha += 0.01;
            if (alpha >= 1) {
                alpha = 1;
                timer.stop();
            }
            repaint();
        });
        timer.start();
    }
}