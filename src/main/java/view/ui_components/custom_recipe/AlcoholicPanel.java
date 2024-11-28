package view.ui_components.custom_recipe;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Panel that contains radio buttons to choose for the alcohol state.
 */
public class AlcoholicPanel extends JPanel {
    private final ButtonGroup group;

    public AlcoholicPanel() {
        setLayout(new GridLayout(2 + 1, 1));
        group = new ButtonGroup();
        final JRadioButton option1 = new JRadioButton("Alcoholic");
        final JRadioButton option2 = new JRadioButton("Non alcoholic");
        final JRadioButton option3 = new JRadioButton("Optional alcohol");

        group.add(option1);
        group.add(option2);
        group.add(option3);

        add(option1);
        add(option2);
        add(option3);
    }

    public String getSelectedOption() {
        return group.getSelection() != null ? group.getSelection().getActionCommand() : null;
    }
}
