package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.services.ServiceManager;
import view.concrete_page.RecipeDetailConcrete;
import view.ui_components.recipe_detail.IngredientPanel;
import view.ui_components.recipe_detail.InstructionPanel;
import view.ui_components.recipe_detail.IsAlcoholicPanel;
import view.ui_components.recipe_detail.NavigationActionPanel;
import view.ui_components.recipe_detail.RecipeTitlePanel;
import view.ui_components.recipe_detail.VideoPanel;

/**
 * Recipe Detail View that shows the information about a selected recipe.
 */
public class RecipeDetailView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "recipe detail";

    private final JButton backButton = new JButton("<");
    private final JButton bookmarkButton = new JButton("Bookmark!");

    private final PageView<RecipeDetailState> pageHandler;

    private final RecipeDetailViewModel recipeDetailViewModel;
    private final ServiceManager serviceManager;
    private final RecipeDetailController recipeDetailController;

    /**
     * Creates the Recipe Detail View, displaying detailed information about the recipe.
     *
     * @param recipeDetailViewModel  the view model managing state, must not be null
     * @param recipeDetailController the controller for user actions, must not be null
     * @param serviceManager         the service manager, must not be null
     */
    public RecipeDetailView(RecipeDetailViewModel recipeDetailViewModel,
                            RecipeDetailController recipeDetailController,
                            ServiceManager serviceManager) {
        this.recipeDetailViewModel = recipeDetailViewModel;
        this.recipeDetailController = recipeDetailController;
        this.serviceManager = serviceManager;

        this.recipeDetailViewModel.addPropertyChangeListener(this);

        final RecipeDetailConcrete recipeDetailConcrete = new RecipeDetailConcrete();
        final VideoPanel videoPanel = new VideoPanel(recipeDetailConcrete, serviceManager);
        final IsAlcoholicPanel alcoholicPanel = new IsAlcoholicPanel(videoPanel);
        final InstructionPanel instructionPanel = new InstructionPanel(alcoholicPanel);
        final IngredientPanel ingredientPanel = new IngredientPanel(instructionPanel);
        final RecipeTitlePanel recipeTitlePanel = new RecipeTitlePanel(ingredientPanel);
        final NavigationActionPanel navigationActionPanel = new NavigationActionPanel(
                recipeTitlePanel, backButton, bookmarkButton
        );

        pageHandler = navigationActionPanel;

        final ActionListener switchToSearchListener = event -> {
            if (event.getSource().equals(backButton)) {
                recipeDetailController.switchToSearchView();
            }
        };

        final ActionListener bookmarkListener = event -> {
            if (event.getSource().equals(bookmarkButton)) {
                final RecipeDetailState recipeDetailState = recipeDetailViewModel.getState();
                recipeDetailController.bookmarkRecipe(
                        recipeDetailState.getRecipe().getId());
            }
        };

        backButton.addActionListener(switchToSearchListener);
        bookmarkButton.addActionListener(bookmarkListener);

        // Set main layout
        setLayout(new BorderLayout());

        // Top section
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(navigationActionPanel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.NORTH);

        // Center section
        final JPanel centerPanel = new JPanel();
        final JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(recipeTitlePanel);
        centerPanel.add(videoPanel);
        centerPanel.add(ingredientPanel);
        centerPanel.add(instructionPanel);
        centerPanel.add(alcoholicPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // No additional actions required at this time.
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        final RecipeDetailState state = (RecipeDetailState) event.getNewValue();
        if ("state".equals(event.getPropertyName())) {
            pageHandler.update(state);
        } else if ("bookmark".equals(event.getPropertyName())) {
            String message = "bookmarked";
            if (!state.getIsBookmarked()) {
                message = "un-" + message;
            }
            JOptionPane.showMessageDialog(null,
                    String.format("Recipe: %s successfully %s",
                            state.getRecipe().getName(), message));
        }
    }

    public String getViewName() {
        return viewName;
    }
}
