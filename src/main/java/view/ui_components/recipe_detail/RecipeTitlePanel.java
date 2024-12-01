package view.ui_components.recipe_detail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import entities.recipe.Recipe;
import interface_adapter.recipe_detail.RecipeDetailState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * Contains the title of the recipe.
 */
public class RecipeTitlePanel extends AbstractViewDecorator<RecipeDetailState> {
    private static final int BACKGROUND_COLOR_RED = 240;
    private static final int BACKGROUND_COLOR_GREEN = 248;
    private static final int BACKGROUND_COLOR_BLUE = 255;
    private static final int TITLE_FONT_SIZE = 24;

    private final JLabel titleLabel;

    /**
     * Creates a panel to display the title of the recipe.
     *
     * @param view the page view to decorate, must not be null.
     */
    public RecipeTitlePanel(PageView<RecipeDetailState> view) {
        super(view);
        setLayout(new BorderLayout());
        setBackground(new Color(BACKGROUND_COLOR_RED, BACKGROUND_COLOR_GREEN, BACKGROUND_COLOR_BLUE));

        titleLabel = new JLabel("Recipe Title", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        add(titleLabel, BorderLayout.WEST);
    }

    @Override
    public void update(RecipeDetailState state) {
        super.getTempPage().update(state);
        final Recipe recipe = state.getRecipe();
        titleLabel.setText(recipe.getName() + " (ID: " + recipe.getId() + ")");
    }
}
