package GUI;

import javax.swing.*;
import java.awt.*;

public class AddButton extends JButton {
    public AddButton(){
        setText("+");
        setFont(new Font(Font.SERIF,Font.BOLD,35));
        setForeground(Color.white);
        setBackground(Color.black);
//        setUI(new StyledButtonUI());
    }
}
