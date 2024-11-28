package view.ui_components.custom_recipe;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * RecipeNamePanel.
 */
public class RecipeNamePanel extends JPanel {
    private final JTextField nameField;

    public RecipeNamePanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        final JLabel nameLabel = new JLabel("Recipe Name:");
        nameField = new JTextField(20);

        add(nameLabel);
        add(nameField);
    }

    public String getRecipeName() {
        return nameField.getText();
    }
}
