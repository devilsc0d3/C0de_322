package start;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Mom extends JLabel {
    public Mom(){
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(Mom.class.getResource("mom.png")));
        Image scaledImage = imageIcon.getImage().getScaledInstance(125, 412, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        setIcon(scaledIcon);
        setSize(200,200);
    }
}
