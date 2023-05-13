package start;

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
        addCharactersHeads();
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
            textPanel.setOpaque(false);
            JTextArea textArea = new JTextArea();
            textArea.setPreferredSize(new Dimension(600, 300));
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setFont(font2);
            textArea.setForeground(Color.white);
            textArea.setOpaque(false);
            textPanel.add(textArea);
            add(textPanel, BorderLayout.CENTER);

            timer = new Timer(6, e -> {
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
    private void addCharactersHeads(){
        JPanel headsPanel = new JPanel();

    }
}
