package interface_adapter.custom_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.create_recipe.CustomRecipeOutputBoundary;
import use_case.user_profile.UserProfileOutputData;

/**
 * Output boundary for creating custom recipes view.
 */
public class CustomRecipePresenter implements CustomRecipeOutputBoundary {
    private final HomePageViewModel homePageViewModel;
    private final CustomRecipeViewModel customRecipeViewModel;
    private final UserProfileViewModel userProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    public CustomRecipePresenter(HomePageViewModel homePageViewModel,
                                 CustomRecipeViewModel customRecipeViewModel,
                                 UserProfileViewModel userProfileViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.homePageViewModel = homePageViewModel;
        this.customRecipeViewModel = customRecipeViewModel;
        this.userProfileViewModel = userProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToRecipeCreationView() {
        final CustomRecipeState state = customRecipeViewModel.getState();

        customRecipeViewModel.setState(state);
        customRecipeViewModel.firePropertyChanged();

        viewManagerModel.setState(customRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomePageView() {
        viewManagerModel.setState(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void updateCustomRecipeView(UserProfileOutputData outputData) {
        customRecipeViewModel.firePropertyChanged("successful creation");
        customRecipeViewModel.firePropertyChanged();

        final UserProfileState userProfileState = userProfileViewModel.getState();
        userProfileState.setCreatedRecipes(outputData.getCreatedRecipes());

        userProfileViewModel.setState(userProfileState);
        userProfileViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final CustomRecipeState state = customRecipeViewModel.getState();
        state.setError(errorMessage);

        customRecipeViewModel.setState(state);
        customRecipeViewModel.firePropertyChanged();
    }
}
