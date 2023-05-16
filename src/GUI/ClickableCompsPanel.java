package GUI;

import javax.swing.*;
import java.awt.*;

public class ClickableCompsPanel extends JPanel {
    public Dad dad;
    public Mom mom;
    public OpenJournalPanel  openJournalPanel;
    public ClickableCompsPanel(){
        setOpaque(false);
        setLayout(null);

        displayCharacters();

        openJournalPanel = new OpenJournalPanel();
        add(openJournalPanel);
    }
    public void displayCharacters() {
        JPanel charactersPanel = new JPanel(new FlowLayout());
        dad = new Dad();
        mom = new Mom();
        charactersPanel.add(dad);
        charactersPanel.add(mom);
        charactersPanel.setBounds(0,210,400,450);
        charactersPanel.setOpaque(false);
        add(charactersPanel);
    }
}
