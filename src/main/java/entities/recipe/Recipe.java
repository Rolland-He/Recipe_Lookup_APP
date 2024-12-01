package entities.recipe;

import java.util.List;

/**
 * Recipe entity that both meal and cocktail must follow.
 */
public interface Recipe {

    /**
     * Returns the name of the recipe.
     * @return name of recipe.
     */
    String getName();

    /**
     * Returns the id of the recipe.
     * @return id of recipe.
     */
    int getId();

    /**
     * Returns the instruction of recipe.
     * @return instruction of recipe.
     */
    String getInstruction();

    /**
     * Returns a list of ingredient.
     * @return list of ingredient.
     */
    List<Ingredient> getIngredients();

    /**
     * String representation of recipe.
     * @return string repr of recipe.
     */
    String toString();

    /**
     * Image link of the recipe.
     * @return link of recipe image.
     */
    String getImageLink();

    /**
     * Video link of the recipe.
     * @return link of recipe video.
     */
    String getVideoLink();

    /**
     * IsAlcoholic of the recipe.
     * @return IsAlcoholic recipe video.
     */
    String getIsAlcoholic();
}
