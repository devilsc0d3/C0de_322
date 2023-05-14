package main.java.start;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LeftPanel extends JPanel {
    private final Map<JPanel, String> componentToConstraints = new HashMap<>();
    private static PromptPanel promptPanel;

    public LeftPanel(){
        setLayout(new CardLayout());
        setOpaque(false);

        promptPanel = new PromptPanel();
        JPanel inviPanel = new JPanel();
        inviPanel.setOpaque(false);

        add(inviPanel,"invi");
        add(promptPanel,"prompt");

        componentToConstraints.put(promptPanel, "PROMPT");
        componentToConstraints.put(inviPanel, "INVI");

    }

    public void swapPrompt(){
        CardLayout cardLayout = (CardLayout) this.getLayout();
        if (!Objects.equals(getDisplayedCard(), "PROMPT")){
            cardLayout.show(this,"prompt");
            promptPanel.addPromptLabel();
        }else{
            cardLayout.show(this,"invi");
        }
    }

    public String getDisplayedCard() {
        Component[] components = this.getComponents();

        for (Component component : components) {
            if (component.isVisible()) {
                return this.componentToConstraints.get(component);
            }
        }

        return null;
    }

}
