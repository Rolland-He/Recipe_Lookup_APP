package view.ui_components.search_recipe;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import interface_adapter.search_recipe.SearchRecipeState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * A panel that contains a back button, search field, and search button.
 */
public class SearchHeaderPanel extends AbstractViewDecorator<SearchRecipeState> {
    private final JButton backButton;
    private final JTextField searchField;
    private final JButton searchButton;

    public SearchHeaderPanel(JButton backButton, JTextField searchField,
                             JButton searchButton, PageView<SearchRecipeState> pageView) {
        super(pageView);
        this.backButton = backButton;
        this.searchField = searchField;
        this.searchButton = searchButton;

        setLayout(new BorderLayout());

        // Add components
        add(backButton, BorderLayout.WEST);
        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);
    }

    @Override
    public void update(SearchRecipeState state) {
        super.getTempPage().update(state);
    }
}
