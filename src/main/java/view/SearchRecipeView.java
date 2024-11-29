package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipeState;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import view.concrete_page.SearchRecipeConcrete;
import view.ui_components.search_recipe.SearchHeaderPanel;
import view.ui_components.search_recipe.ThumbnailsContainerPanel;

/**
 * The view when the user searches for a recipe through some text field.
 */
public class SearchRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int SEARCH_TEXT_FIELD_COLUMNS = 15;

    private final String viewName = "search recipe";

    private final JTextField searchTextField = new JTextField(SEARCH_TEXT_FIELD_COLUMNS);

    private final JButton searchButton;
    private final JButton backButton;

    private final ThumbnailsContainerPanel thumbnailContainerPanel;
    private final PageView<SearchRecipeState> viewHandler;

    private final SearchRecipeViewModel searchRecipeViewModel;
    private final ServiceManager serviceManager;
    private final SearchRecipeController searchRecipeController;

    public SearchRecipeView(SearchRecipeViewModel searchRecipeViewModel,
                            SearchRecipeController searchRecipeController,
                            ServiceManager serviceManager) {
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.searchRecipeController = searchRecipeController;
        this.serviceManager = serviceManager;

        this.searchRecipeViewModel.addPropertyChangeListener(this);

        searchButton = new JButton("Search");
        backButton = new JButton("<");

        final SearchRecipeConcrete searchRecipeConcrete = new SearchRecipeConcrete();
        thumbnailContainerPanel = new ThumbnailsContainerPanel(
                searchRecipeViewModel, searchRecipeController,
                serviceManager, searchRecipeConcrete);
        final SearchHeaderPanel searchBar = new SearchHeaderPanel(
                backButton, searchTextField, searchButton, thumbnailContainerPanel
        );
        viewHandler = searchBar;

        final ActionListener switchToHomeViewListener = event -> {
            if (event.getSource().equals(backButton)) {
                final SearchRecipeState state = searchRecipeViewModel.getState();
                searchRecipeController.switchToHomeView(state.getQuery());
            }
        };

        final ActionListener searchRecipeListener = event -> {
            if (event.getSource().equals(searchButton) || event.getSource().equals(searchTextField)) {
                final SearchRecipeState currentState = searchRecipeViewModel.getState();
                searchRecipeController.execute(
                        currentState.getQuery(), null);
            }
        };

        backButton.addActionListener(switchToHomeViewListener);
        searchButton.addActionListener(searchRecipeListener);

        setLayout(new BorderLayout());

        searchTextField.addActionListener(searchRecipeListener);
        addSearchTextFieldListener();

        this.add(searchBar, BorderLayout.NORTH);
        this.add(thumbnailContainerPanel, BorderLayout.CENTER);
    }

    private void addSearchTextFieldListener() {
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // We pop up a JOptionPane to the user.
        if (evt.getPropertyName().equals("state")) {
            final SearchRecipeState state = (SearchRecipeState) evt.getNewValue();
            viewHandler.update(state);
        }
        else if (evt.getPropertyName().equals("usecaseFailed")) {
            JOptionPane.showMessageDialog(this,
                    String.format("No recipes found with recipe for: %s",
                            searchRecipeViewModel.getState().getQuery()));
        }
    }

    private void documentListenerHelper() {
        final SearchRecipeState currentState = searchRecipeViewModel.getState();
        currentState.setQuery(searchTextField.getText());
        searchRecipeViewModel.setState(currentState);
    }

    public String getViewName() {
        return viewName;
    }
}
