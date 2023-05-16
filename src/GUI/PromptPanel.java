package GUI;

import javax.swing.*;
import java.awt.*;

public class PromptPanel extends JPanel {
    private JTextArea textArea;
    public String textToDisplay;
    public JPanel southPanel;
    public AddButton addButton;
    public AddButton addButton2;
    public RemoveButton removeButton;
    public RemoveButton removeButton2;
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
        textToDisplay = """
				Le jour que tout le monde redoutaient est arrivé,\s
				Le boss Informatique actuel auto ChatGPT s’est rebellé le 22 mai 2023.
				Il prend d’assaut les humains avec ses amis les robots.\s
				Le monde est en train de sombrer mais un groupe de bras cassé est détérminé a renverser les IA.
				Arriveront-ils a restituer la paix ?
				""";
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textArea = new JTextArea();
        textPanel.setBackground(Color.black);
        textPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        textArea.setPreferredSize(new Dimension(600, 300));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(font2);
        textArea.setForeground(Color.white);
        textArea.setOpaque(false);
        textPanel.add(textArea);
        add(textPanel, BorderLayout.CENTER);
    }

    public void startTextDisplay() {
        final int[] x = {0};

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


    private static ImageIcon resize(ImageIcon baseImage) {
        Image scaledImage = baseImage.getImage().getScaledInstance(75, 75, Image.SCALE_AREA_AVERAGING);
        return new ImageIcon(scaledImage);
    }

    private void addSouthPanel() {
        southPanel = new JPanel(new BorderLayout());
        JPanel headsPanel = new JPanel(new FlowLayout());
        headsPanel.setPreferredSize(new Dimension(600, 150));

        JPanel foodChoicePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        foodChoicePanel.setOpaque(false);

        JLabel momHead = new JLabel(new ImageIcon("C0de_322/source/img/mom-head.png"));
        JLabel dadHead = new JLabel(new ImageIcon("C0de_322/source/img/dad-head.png"));
        JLabel canLab = new JLabel(resize(new ImageIcon("C0de_322/source/img/soup-can.png")));
        JLabel bottleLab = new JLabel(resize(new ImageIcon("C0de_322/source/img/water-bottle.png")));
        southPanel.setOpaque(false);
        headsPanel.setOpaque(false);

        addButton = new AddButton();
        removeButton = new RemoveButton();
        addButton2 = new AddButton();
        removeButton2 = new RemoveButton();
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        emptyPanel.setPreferredSize(new Dimension(100, 100));

        foodChoicePanel.add(removeButton);
        foodChoicePanel.add(canLab);
        foodChoicePanel.add(addButton);

        foodChoicePanel.add(emptyPanel);

        foodChoicePanel.add(removeButton2);
        foodChoicePanel.add(bottleLab);
        foodChoicePanel.add(addButton2);

        headsPanel.add(momHead);
        headsPanel.add(dadHead);

        southPanel.add(headsPanel, BorderLayout.NORTH);
        southPanel.add(foodChoicePanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);
    }
}
