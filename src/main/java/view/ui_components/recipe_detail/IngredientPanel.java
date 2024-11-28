package view.ui_components.recipe_detail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.recipe.Ingredient;
import entities.recipe.Recipe;
import interface_adapter.recipe_detail.RecipeDetailState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * Contains the ingredients of a recipe.
 */
public class IngredientPanel extends AbstractViewDecorator<RecipeDetailState> {
    public static final int HEADER_LABEL_FONT_SIZE = 18;
    public static final int INGREDIENT_FONT_SIZE = 14;
    private final JPanel ingredientsListPanel;

    public IngredientPanel(PageView<RecipeDetailState> view) {
        super(view);
        ingredientsListPanel = new JPanel();
        ingredientsListPanel.setLayout(new BoxLayout(ingredientsListPanel, BoxLayout.Y_AXIS));

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        final JLabel headerLabel = new JLabel("Ingredients:");
        headerLabel.setFont(new Font("Arial", Font.BOLD, HEADER_LABEL_FONT_SIZE));
        add(headerLabel, BorderLayout.NORTH);
    }

    @Override
    public void update(RecipeDetailState state) {
        super.update(state);
        updateComponents(state.getRecipe());
    }

    private void updateComponents(Recipe recipe) {
        ingredientsListPanel.removeAll();

        final List<Ingredient> ingredients = recipe.getIngredients();

        if (ingredients == null || ingredients.isEmpty()) {
            ingredientsListPanel.add(new JLabel("No ingredients available"));
        }
        else {
            for (Ingredient ingredient : ingredients) {
                if (ingredient != null && ingredient.getName() != null && !ingredient.getName().isEmpty()) {
                    final JCheckBox ingredientCheckBox = new JCheckBox(
                            ingredient.getName() + " - " + ingredient.getMeasure());
                    ingredientCheckBox.setFont(new Font("Arial", Font.PLAIN, INGREDIENT_FONT_SIZE));
                    ingredientsListPanel.add(ingredientCheckBox);
                }
            }
        }
        add(ingredientsListPanel, BorderLayout.CENTER);
    }
}
