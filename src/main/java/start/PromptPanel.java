package start;

import javax.swing.*;
import java.awt.*;

public class PromptPanel extends JPanel {
    public PromptPanel(){
        setBackground(new Color(0,0,0,200));
        setPreferredSize(new Dimension(600,900));
        addSelectors();
    }
    private void addSelectors(){
        Font font = new Font(Font.DIALOG, Font.BOLD, 40);

        JButton nextDayButton = new JButton();
        JButton prevDayButton = new JButton();

        nextDayButton.setText(">");
        prevDayButton.setText("<");
        prevDayButton.setFont(font);
        nextDayButton.setFont(font);
        nextDayButton.setUI(new StyledButtonUI());
        prevDayButton.setUI(new StyledButtonUI());

        add(prevDayButton);
        add(nextDayButton);
    }
}
