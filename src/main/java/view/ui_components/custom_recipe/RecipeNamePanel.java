package view.ui_components.custom_recipe;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * RecipeNamePanel.
 */
public class RecipeNamePanel extends JPanel {
    private static final int TEXT_FIELD_COLUMNS = 20;

    private final JTextField nameField;

    public RecipeNamePanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        final JLabel nameLabel = new JLabel("Recipe Name:");
        nameField = new JTextField(TEXT_FIELD_COLUMNS);

        add(nameLabel);
        add(nameField);
    }

    public String getRecipeName() {
        return nameField.getText();
    }
}
