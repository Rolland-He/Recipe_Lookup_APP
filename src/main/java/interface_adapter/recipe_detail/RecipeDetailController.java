package interface_adapter.recipe_detail;

import use_case.bookmark_recipe.BookmarkRecipeInputBoundary;
import use_case.bookmark_recipe.BookmarkRecipeInputData;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInputData;

/**
 * The controller for the recipe detail use case.
 */
public class RecipeDetailController {
    private final ViewRecipeInputBoundary recipeDetailInteractor;
    private final BookmarkRecipeInputBoundary bookmarkRecipeInteractor;

    public RecipeDetailController(ViewRecipeInputBoundary recipeDetailInteractor,
                                  BookmarkRecipeInputBoundary bookmarkRecipeInteractor) {
        this.recipeDetailInteractor = recipeDetailInteractor;
        this.bookmarkRecipeInteractor = bookmarkRecipeInteractor;
    }

    /**
     * Executes the interactor for recipe detail use case.
     * Essentially, switches to the recipe view.
     * @param id recipe id.
     */
    public void execute(int id) {
        final ViewRecipeInputData recipeDetailInputData = new ViewRecipeInputData(id);
        recipeDetailInteractor.execute(recipeDetailInputData);
    }

    /**
     * Switches to the search view.
     */
    public void switchToSearchView() {
        recipeDetailInteractor.switchToSearchView();
    }

    /**
     * Attempts to bookmark the recipe.
     * @param id the recipe id.
     */
    public void bookmarkRecipe(int id) {
        final BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(
                id
        );
        bookmarkRecipeInteractor.bookmarkRecipe(inputData);
    }
}
