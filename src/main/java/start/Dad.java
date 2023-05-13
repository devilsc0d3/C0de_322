package start;

import javax.swing.*;
import java.awt.*;

public class Dad extends JLabel {
    public Dad(){
        ImageIcon imageIcon = new ImageIcon("source/img/dad.png");
        Image scaledImage = imageIcon.getImage().getScaledInstance(150, 415, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        setIcon(scaledIcon);
        setSize(200,200);
    }
}
