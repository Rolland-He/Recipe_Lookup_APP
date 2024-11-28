package view.ui_components.recipe_detail;

import javax.swing.JLabel;

import entities.recipe.Recipe;
import interface_adapter.recipe_detail.RecipeDetailState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * The panel showing if a recipe is alcoholic.
 */
public class IsAlcoholicPanel extends AbstractViewDecorator<RecipeDetailState> {
    private final JLabel messageLabel;

    /**
     * Constructs a new IsAlcoholicPanel.
     *
     * @param view The PageView to decorate
     */
    public IsAlcoholicPanel(PageView<RecipeDetailState> view) {
        super(view);
        messageLabel = new JLabel();
        add(messageLabel);
    }

    @Override
    public void update(RecipeDetailState state) {
        super.getTempPage().update(state);

        final Recipe recipe = state.getRecipe();

        messageLabel.setText(recipe.getIsAlcoholic());
    }
}
