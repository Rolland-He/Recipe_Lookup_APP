package use_case.create_recipe;

import java.util.List;

import com.mongodb.MongoSocketException;
import entities.recipe.Recipe;
import use_case.user_profile.UserProfileOutputData;

/**
 * Interactor for the custom recipe usecase.
 */
public class CustomRecipeInteractor implements CustomRecipeInputBoundary {

    private final CustomRecipeDataAccessInterface customRecipeDataAccessObject;
    private final CustomRecipeOutputBoundary customRecipePresenter;

    public CustomRecipeInteractor(CustomRecipeDataAccessInterface customRecipeDataAccessObject,
                                  CustomRecipeOutputBoundary customRecipePresenter) {
        this.customRecipeDataAccessObject = customRecipeDataAccessObject;
        this.customRecipePresenter = customRecipePresenter;
    }

    @Override
    public void switchToHomePageView() {
        customRecipePresenter.switchToHomePageView();
    }

    @Override
    public void switchToCustomRecipeView() {
        customRecipePresenter.switchToRecipeCreationView();
    }

    @Override
    public void saveCustomRecipe(CustomRecipeInputData inputData) {
        try {
            customRecipeDataAccessObject.createCustomRecipe(
                    customRecipeDataAccessObject.getCurrentUser(),
                    inputData.getRecipeName(),
                    inputData.getRecipeInstruction(),
                    inputData.getIngredients(),
                    inputData.getMeasurements(),
                    inputData.getIsAlcoholic()
            );
        }
        catch (MongoSocketException exception) {
            customRecipePresenter.prepareFailView("Failed to save custom recipe.");
        }

        final String username = customRecipeDataAccessObject.getCurrentUser();
        final List<Recipe> customRecipes = customRecipeDataAccessObject.getCustomRecipes(username);
        final UserProfileOutputData outputData = new UserProfileOutputData(username, customRecipes);
        customRecipePresenter.updateCustomRecipeView(outputData);
    }
}
