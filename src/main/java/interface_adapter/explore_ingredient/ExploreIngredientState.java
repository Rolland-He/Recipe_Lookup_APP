package interface_adapter.explore_ingredient;

import java.util.List;

/**
 * Explor Ingredient State.
 */
public class ExploreIngredientState {
    private List<String> ingredients;

    public ExploreIngredientState(ExploreIngredientState copy) {
        this.ingredients = copy.ingredients;
    }

    public ExploreIngredientState() {
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
