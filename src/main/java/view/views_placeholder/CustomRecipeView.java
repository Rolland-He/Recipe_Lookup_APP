package view.views_placeholder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.custom_recipe.CustomRecipeController;
import interface_adapter.custom_recipe.CustomRecipeState;
import interface_adapter.custom_recipe.CustomRecipeViewModel;
import interface_adapter.services.ServiceManager;
import view.PageView;
import view.ui_components.custom_recipe.ActionButtonPanel;
import view.ui_components.custom_recipe.AlcoholicPanel;
import view.ui_components.custom_recipe.IngredientsPanel;
import view.ui_components.custom_recipe.InstructionsPanel;
import view.ui_components.custom_recipe.RecipeNamePanel;

/**
 * Custom recipe view.
 */
public class CustomRecipeView extends JPanel implements PageView<CustomRecipeState>,
        ActionListener, PropertyChangeListener {
    private static final int LAYOUT_GAP = 10;
    private static final int BORDER_PADDING = 10;

    private final String viewName = "create recipe";

    private final JButton goHomeButton = new JButton("Go Home");
    private final JButton createRecipeButton = new JButton("Create Recipe");

    public CustomRecipeView(CustomRecipeViewModel customRecipeViewModel,
                            CustomRecipeController customRecipeController,
                            ServiceManager serviceManager) {
        setLayout(new BorderLayout(LAYOUT_GAP, LAYOUT_GAP));
        setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));
        customRecipeViewModel.addPropertyChangeListener(this);

        // Panels
        final RecipeNamePanel recipeNamePanel = new RecipeNamePanel();
        final IngredientsPanel ingredientsPanel = new IngredientsPanel();
        final InstructionsPanel instructionsPanel = new InstructionsPanel();
        final AlcoholicPanel alcoholicPanel = new AlcoholicPanel();
        final ActionButtonPanel actionButtonPanel = new ActionButtonPanel(
                recipeNamePanel, ingredientsPanel, instructionsPanel, alcoholicPanel,
                customRecipeController);

        // Add components to layout
        add(recipeNamePanel, BorderLayout.NORTH);
        add(ingredientsPanel, BorderLayout.CENTER);
        add(instructionsPanel, BorderLayout.SOUTH);
        add(alcoholicPanel, BorderLayout.WEST);
        add(actionButtonPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implementation details
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final CustomRecipeState state = (CustomRecipeState) evt.getSource();
            setFields(state);
        }
        else if (evt.getPropertyName().equals("successful creation")) {
            JOptionPane.showMessageDialog(this, "Successfully created recipe");
        }
    }

    private void setFields(CustomRecipeState state) {
        // Implementation details
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void update(CustomRecipeState state) {
        // Implementation details
    }
}
