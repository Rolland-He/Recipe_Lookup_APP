package view.ui_components.main_page;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;

/**
 * The panel that contains the contents to be shown in the main page.
 * This includes but not limited to:
 *  Recommended Recipes
 *  BookmarkedRecipes
 */
public class ContentPanel extends JPanel {
    public static final int CONTENT_PANEL_HGAP = 10;
    public static final int CONTENT_PANEL_VGAP = 10;
    private final RecommendedPanel recommendedPanel;
    private final BookmarkedPanel bookmarkedPanel;

    private final JScrollPane scrollPane;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    public ContentPanel(HomePageViewModel homePageViewModel,
                        HomePageController homePageController,
                        ServiceManager serviceManager) {
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        setLayout(new GridLayout(0, 1, CONTENT_PANEL_HGAP, CONTENT_PANEL_VGAP));

        scrollPane = new JScrollPane(this,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        recommendedPanel = new RecommendedPanel(homePageViewModel,
                homePageController,
                serviceManager);

        bookmarkedPanel = new BookmarkedPanel(homePageViewModel,
                homePageController,
                serviceManager);

        add(recommendedPanel);
        add(bookmarkedPanel);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Updates the panel.
     * @param state the home page state.
     */
    public void updatePanel(HomePageState state) {
        recommendedPanel.updatePanel(state.getRandomRecipe());
        bookmarkedPanel.updatePanel(state.getBookmarkedRecipes());
    }

}
