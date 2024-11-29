package view.ui_components.custom_recipe;

import interface_adapter.custom_recipe.CustomRecipeState;
import view.AbstractViewDecorator;
import view.PageView;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * RecipeNamePanel.
 */
public class RecipeNamePanel extends AbstractViewDecorator<CustomRecipeState> {
    private static final int TEXT_FIELD_COLUMNS = 20;

    private final JTextField nameField;

    public RecipeNamePanel(PageView<CustomRecipeState> view) {
        super(view);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        final JLabel nameLabel = new JLabel("Recipe Name:");
        nameField = new JTextField(TEXT_FIELD_COLUMNS);

        add(nameLabel);
        add(nameField);
    }

    public String getRecipeName() {
        return nameField.getText();
    }

    @Override
    public void update(CustomRecipeState state) {
        super.getTempPage().update(state);
        nameField.setText("");
    }
}
