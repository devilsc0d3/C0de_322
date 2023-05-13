package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OpenJournalPanel extends JPanel {

    public OpenJournalPanel(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(0,0,0,75));
        setBounds(585,300,250,350);


        JLabel journalLabel = new JLabel("Journal");
        JLabel journalLabel2 = new JLabel("de bord");
        journalLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
        journalLabel2.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
        journalLabel2.setForeground(Color.yellow.darker());
        journalLabel.setForeground(Color.yellow.darker());
        add(journalLabel);
        add(journalLabel2);

    }
}
