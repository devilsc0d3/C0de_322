package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class LeftPanel extends JPanel {
    private final Map<JPanel, String> componentToConstraints = new HashMap<>();
    public  PromptPanel promptPanel;

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
            if (!promptPanel.textDisplayed){
                promptPanel.startTextDisplay();
            }
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

    public List<AddButton> getAddButtons(){
        List<AddButton> addButtons = new ArrayList<>();
        addButtons.add(promptPanel.addButton);
        addButtons.add(promptPanel.addButton2);
        return addButtons;
    }
    public List<RemoveButton> getRemoveButtons(){
        List<RemoveButton> removeButtons = new ArrayList<>();
        removeButtons.add(promptPanel.removeButton);
        removeButtons.add(promptPanel.removeButton2);
        return removeButtons;
    }

}
