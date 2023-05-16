package GUI;

import javax.swing.*;
import java.awt.*;

public class RemoveButton extends JButton {
    public RemoveButton(){
        setText("-");
        setFont(new Font(Font.SERIF,Font.BOLD,35));
        setForeground(Color.white);
        setBackground(Color.black);
    }
}
