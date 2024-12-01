package view.concrete_page;

import interface_adapter.search_recipe.SearchRecipeState;
import view.PageView;

/**
 * Search recipe concrete class.
 */
public class SearchRecipeConcrete implements PageView<SearchRecipeState> {
    @Override
    public void update(SearchRecipeState state) {
        System.out.println("Search recipe view updated.");
    }
}
