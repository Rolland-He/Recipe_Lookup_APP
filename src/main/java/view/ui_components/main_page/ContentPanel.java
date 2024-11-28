package view.ui_components.main_page;

import java.awt.GridLayout;

import javax.swing.JScrollPane;

import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * The panel that contains the contents to be shown in the main page.
 * This includes but not limited to:
 *  Recommended Recipes
 *  BookmarkedRecipes
 */
public class ContentPanel extends AbstractViewDecorator<HomePageState> {
    private static final int GRID_LAYOUT_ROWS = 0;
    private static final int GRID_LAYOUT_COLS = 1;
    private static final int GRID_LAYOUT_HGAP = 10;
    private static final int GRID_LAYOUT_VGAP = 10;

    private final JScrollPane scrollPane;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    public ContentPanel(HomePageViewModel homePageViewModel,
                        HomePageController homePageController,
                        ServiceManager serviceManager, PageView<HomePageState> pageView) {
        super(pageView);
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        setLayout(new GridLayout(GRID_LAYOUT_ROWS, GRID_LAYOUT_COLS, GRID_LAYOUT_HGAP, GRID_LAYOUT_VGAP));

        scrollPane = new JScrollPane(this,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    @Override
    public void update(HomePageState homePageState) {
        super.getTempPage().update(homePageState);
    }
}
