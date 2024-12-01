package view.ui_components.main_page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import entities.recipe.Recipe;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * The panel where the bookmarked recipes will show.
 */
public class BookmarkedDecoratorPanel extends AbstractViewDecorator<HomePageState> {
    private static final int LAYOUT_GAP = 10;
    private static final int BORDER_SIZE = 10;
    private static final int GRID_COLUMNS = 3;
    private static final int HEADER_FONT_SIZE = 20;

    private final JPanel gridPanel;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    public BookmarkedDecoratorPanel(HomePageViewModel homePageViewModel,
                                    HomePageController homePageController,
                                    ServiceManager serviceManager, PageView<HomePageState> pageView) {
        super(pageView);
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        // Recommendations section
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        setLayout(new BorderLayout(LAYOUT_GAP, LAYOUT_GAP));
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        final JLabel headerLabel = new JLabel("Bookmarked Recipes", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, HEADER_FONT_SIZE));
        add(headerLabel, BorderLayout.NORTH);

        gridPanel = new JPanel(new GridLayout(0, GRID_COLUMNS, LAYOUT_GAP, LAYOUT_GAP));
        final JScrollPane scrollPane = new JScrollPane(gridPanel);

        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void update(HomePageState state) {
        super.getTempPage().update(state);
        final List<Recipe> bookmarkedRecipes = state.getBookmarkedRecipes();
        gridPanel.removeAll();
        for (Recipe recipe : bookmarkedRecipes) {
            final HomeRecipeThumbnailPanel homeRecipeThumbnailPanel = new HomeRecipeThumbnailPanel(
                    homePageViewModel, homePageController, serviceManager);
            homeRecipeThumbnailPanel.addRecipe(recipe);
            gridPanel.add(homeRecipeThumbnailPanel);
        }
    }
}
