package use_case.user_profile;

import java.util.List;

import entities.recipe.Recipe;

/**
 * Output data for the user profile usecase.
 */
public class UserProfileOutputData {
    private final String username;
    private final List<Recipe> createdRecipes;

    public UserProfileOutputData(String username, List<Recipe> createdRecipes) {
        this.username = username;
        this.createdRecipes = createdRecipes;
    }

    public String getUsername() {
        return username;
    }

    public List<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }
}
