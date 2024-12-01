package view.views_placeholder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.custom_recipe.CustomRecipeController;
import interface_adapter.custom_recipe.CustomRecipeState;
import interface_adapter.custom_recipe.CustomRecipeViewModel;
import interface_adapter.services.ServiceManager;
import view.PageView;
import view.concrete_page.CustomRecipeConcrete;
import view.ui_components.custom_recipe.ActionButtonPanel;
import view.ui_components.custom_recipe.AlcoholicPanel;
import view.ui_components.custom_recipe.IngredientsPanel;
import view.ui_components.custom_recipe.InstructionsPanel;
import view.ui_components.custom_recipe.RecipeNamePanel;

/**
 * Custom recipe view.
 */
public class CustomRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int LAYOUT_GAP = 10;
    private static final int BORDER_PADDING = 10;

    private final String viewName = "create recipe";
    private final PageView<CustomRecipeState> viewHandler;
    private CustomRecipeViewModel customRecipeViewModel;

    public CustomRecipeView(CustomRecipeViewModel customRecipeViewModel,
                            CustomRecipeController customRecipeController,
                            ServiceManager serviceManager) {
        this.customRecipeViewModel = customRecipeViewModel;
        setLayout(new BorderLayout(LAYOUT_GAP, LAYOUT_GAP));
        setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));
        customRecipeViewModel.addPropertyChangeListener(this);

        // Panels
        final CustomRecipeConcrete customRecipeConcrete = new CustomRecipeConcrete();
        final RecipeNamePanel recipeNamePanel = new RecipeNamePanel(customRecipeConcrete);
        final IngredientsPanel ingredientsPanel = new IngredientsPanel(recipeNamePanel);
        final InstructionsPanel instructionsPanel = new InstructionsPanel(ingredientsPanel);
        final AlcoholicPanel alcoholicPanel = new AlcoholicPanel(instructionsPanel);
        final ActionButtonPanel actionButtonPanel = new ActionButtonPanel(
                recipeNamePanel, ingredientsPanel, instructionsPanel, alcoholicPanel,
                customRecipeController, alcoholicPanel);
        viewHandler = actionButtonPanel;

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
            final CustomRecipeState state = customRecipeViewModel.getState();
            viewHandler.update(state);
            if (!state.getError().isEmpty()) {
                JOptionPane.showMessageDialog(this, state.getError());
                state.setError("");
            }
        }
        else if (evt.getPropertyName().equals("successful creation")) {
            JOptionPane.showMessageDialog(this, "Successfully created recipe");
        }
    }

    public String getViewName() {
        return viewName;
    }
}
