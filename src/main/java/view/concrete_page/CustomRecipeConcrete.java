package view.concrete_page;

import interface_adapter.custom_recipe.CustomRecipeState;
import view.PageView;

/**
 * Custom recipe concrete ui class.
 */
public class CustomRecipeConcrete implements PageView<CustomRecipeState> {
    @Override
    public void update(CustomRecipeState state) {
        System.out.println("Custom recipe view updated.");
    }
}
