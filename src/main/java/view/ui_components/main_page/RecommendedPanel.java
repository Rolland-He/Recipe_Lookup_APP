package view.ui_components.main_page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entities.recipe.Recipe;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * Panel that contains all the recommended recipes to the user.
 */
public class RecommendedPanel extends AbstractViewDecorator<HomePageState> {
    private static final int BORDER_SIZE = 10;
    private static final int HEADER_FONT_SIZE = 20;
    private static final int GRID_COLUMNS = 3;
    private static final int GRID_HORIZONTAL_GAP = 10;
    private static final int GRID_VERTICAL_GAP = 10;

    private final JPanel gridPanel;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    /**
     * Creates the RecommendedPanel with its associated components.
     *
     * @param homePageViewModel  the view model for the home page, must not be null
     * @param homePageController the controller for the home page, must not be null
     * @param serviceManager     the service manager, must not be null
     * @param pageView           the page view to decorate, must not be null
     */
    public RecommendedPanel(HomePageViewModel homePageViewModel,
                            HomePageController homePageController,
                            ServiceManager serviceManager, PageView<HomePageState> pageView) {
        super(pageView);
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        setLayout(new BorderLayout(BORDER_SIZE, BORDER_SIZE));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        final JLabel headerLabel = new JLabel("Recommended Recipes", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, HEADER_FONT_SIZE));
        add(headerLabel, BorderLayout.NORTH);

        gridPanel = new JPanel(new GridLayout(0, GRID_COLUMNS, GRID_HORIZONTAL_GAP, GRID_VERTICAL_GAP));
        final JScrollPane scrollPane = new JScrollPane(gridPanel);

        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void update(HomePageState state) {
        super.getTempPage().update(state);
        gridPanel.removeAll();
        final List<Recipe> recommendedRecipes = state.getRandomRecipe();

        for (Recipe recipe : recommendedRecipes) {
            final HomeRecipeThumbnailPanel homeRecipeThumbnailPanel = new HomeRecipeThumbnailPanel(
                    homePageViewModel, homePageController, serviceManager);
            homeRecipeThumbnailPanel.addRecipe(recipe);
            gridPanel.add(homeRecipeThumbnailPanel);
        }
    }
}
