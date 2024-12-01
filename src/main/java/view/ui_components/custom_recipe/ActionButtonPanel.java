package view.ui_components.custom_recipe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import interface_adapter.custom_recipe.CustomRecipeController;
import interface_adapter.custom_recipe.CustomRecipeState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * Panel that contains buttons.
 */
public class ActionButtonPanel extends AbstractViewDecorator<CustomRecipeState> {
    public static final int FIVE = 5;
    private final CustomRecipeController customRecipeController;

    public ActionButtonPanel(RecipeNamePanel namePanel, IngredientsPanel ingredientsPanel,
                             InstructionsPanel instructionsPanel, AlcoholicPanel radioButtonPanel,
                             CustomRecipeController customRecipeController, PageView<CustomRecipeState> view) {
        super(view);
        setLayout(new GridLayout(2, 1, FIVE, FIVE));
        this.customRecipeController = customRecipeController;

        final JButton goHomeButton = new JButton("Go Home");
        goHomeButton.addActionListener(event -> customRecipeController.switchToHome());

        final JButton createRecipeButton = new JButton("Create Recipe");
        createRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                final List<String[]> ingredients = ingredientsPanel.getIngredients();
                final List<String> ingredientNames = new ArrayList<>();
                final List<String> ingredientMeasurements = new ArrayList<>();
                for (String[] ingredient : ingredients) {
                    ingredientNames.add(ingredient[0]);
                    ingredientMeasurements.add(ingredient[1]);
                }
                customRecipeController.createRecipe(namePanel.getRecipeName(), instructionsPanel.getInstructions(),
                        ingredientNames, ingredientMeasurements, radioButtonPanel.getSelectedOption());
            }
        });

        add(goHomeButton);
        add(createRecipeButton);
    }

    @Override
    public void update(CustomRecipeState customRecipeState) {
        super.getTempPage().update(customRecipeState);
    }
}
