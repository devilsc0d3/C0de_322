package main.java.start;

import javax.swing.*;
import java.awt.*;

public class PromptPanel extends JPanel {
    public boolean textDisplayed = false;
    private Font font = new Font(Font.DIALOG, Font.BOLD, 40);
    private Font font2 = new Font(Font.DIALOG, Font.BOLD, 25);

    public PromptPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 200));
        setPreferredSize(new Dimension(600, 900));
        addDaySelectors();
        addSouthPanel();
    }

    private void addDaySelectors() {
        JPanel dayButtonsPanel = new JPanel();
        dayButtonsPanel.setOpaque(false);


        JButton nextDayButton = new JButton();
        JButton prevDayButton = new JButton();

        nextDayButton.setText(">");
        prevDayButton.setText("<");
        prevDayButton.setFont(font);
        nextDayButton.setFont(font);
        nextDayButton.setUI(new StyledButtonUI());
        prevDayButton.setUI(new StyledButtonUI());

        dayButtonsPanel.add(prevDayButton);
        dayButtonsPanel.add(nextDayButton);
        add(dayButtonsPanel, BorderLayout.NORTH);
    }

    Timer timer;

    public void addPromptLabel() {
        String currentText = "";
        String textToDisplay = "This is a long text that will wrap to the next line when it overflows the label's width.This is a long text that will wrap to the next line when it overflows the label's width.This is a long text that will wrap to the next line when it overflows the label's width.";
        if (!textDisplayed) {
            final int[] x = {6};

            JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JTextArea textArea = new JTextArea();
            textPanel.setBackground(Color.black);
            textPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,3));
            textArea.setPreferredSize(new Dimension(600, 300));
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setFont(font2);
            textArea.setForeground(Color.white);
            textArea.setOpaque(false);
            textPanel.add(textArea);
            add(textPanel, BorderLayout.CENTER);

            timer = new Timer(60, e -> {
                if (x[0] < textToDisplay.length()) {
                    String partialText = textToDisplay.substring(0, x[0]) + "|";
                    textArea.setText(partialText);
                    x[0]++;
                } else if (x[0] == textToDisplay.length()) {
                    textArea.setText(textToDisplay);
                    textArea.setOpaque(false);
                    x[0]++;
                } else {
                    timer.stop();
                }
            });
            timer.start();
            textDisplayed = true;

        }
    }
    private static ImageIcon resize(ImageIcon baseImage){
        Image scaledImage = baseImage.getImage().getScaledInstance(75, 75, Image.SCALE_AREA_AVERAGING);
        return new ImageIcon(scaledImage);
    }

    private void addSouthPanel(){
        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel headsPanel = new JPanel(new FlowLayout());
        headsPanel.setPreferredSize(new Dimension(600,150));

        JPanel foodChoicePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        foodChoicePanel.setOpaque(false);

        JLabel momHead = new JLabel( new ImageIcon("C0de_322/source/img/mom-head.png"));
        JLabel dadHead = new JLabel(new ImageIcon("C0de_322/source/img/dad-head.png"));
        JLabel canLab = new JLabel(resize(new ImageIcon("C0de_322/source/img/soup-can.png")));
        JLabel bottleLab = new JLabel(resize(new ImageIcon("C0de_322/source/img/water-bottle.png")));
        southPanel.setOpaque(false);
        headsPanel.setOpaque(false);

        AddButton addButton = new AddButton();
        RemoveButton removeButton = new RemoveButton();
        AddButton addButton2 = new AddButton();
        RemoveButton removeButton2 = new RemoveButton();
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        emptyPanel.setPreferredSize(new Dimension(100,100));

        foodChoicePanel.add(removeButton);
        foodChoicePanel.add(canLab);
        foodChoicePanel.add(addButton);

        foodChoicePanel.add(emptyPanel);

        foodChoicePanel.add(removeButton2);
        foodChoicePanel.add(bottleLab);
        foodChoicePanel.add(addButton2);

        headsPanel.add(momHead);
        headsPanel.add(dadHead);

        southPanel.add(headsPanel,BorderLayout.NORTH);
        southPanel.add(foodChoicePanel,BorderLayout.SOUTH);
        add(southPanel,BorderLayout.SOUTH);
    }
}
