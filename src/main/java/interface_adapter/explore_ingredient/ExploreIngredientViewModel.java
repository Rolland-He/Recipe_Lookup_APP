package interface_adapter.explore_ingredient;

import interface_adapter.ViewModel;

/**
 * View model for the explore ingredients usecase.
 */
public class ExploreIngredientViewModel extends ViewModel<ExploreIngredientState> {
    public ExploreIngredientViewModel() {
        super("explore ingredient");
        setState(new ExploreIngredientState());
    }
}
