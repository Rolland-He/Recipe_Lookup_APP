package interface_adapter.explore_ingredient;

import java.util.List;
import entities.recipe.SimpleRecipe;
import entities.recipe.Ingredient;

public class ExploreIngredientState {
    private List<SimpleRecipe> recipes;
    private List<Ingredient> ingredients;
    private String query;

    public ExploreIngredientState(ExploreIngredientState copy) {
        this.recipes = copy.recipes;
        this.query = copy.query;
        this.ingredients = copy.ingredients;
    }

    public ExploreIngredientState() {}

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setRecipes(List<SimpleRecipe> recipes) {
        this.recipes = recipes;
    }

    public List<SimpleRecipe> getRecipes() {
        return recipes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}