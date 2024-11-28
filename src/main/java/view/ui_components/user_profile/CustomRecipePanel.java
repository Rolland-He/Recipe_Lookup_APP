package view.ui_components.user_profile;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileViewModel;

/**
 * CustomRecipePanel represents a panel that displays custom recipes created by the user.
 * This class extends JPanel and is responsible for rendering and managing custom recipe information.
 * This class is not expected to be null.
 */
public class CustomRecipePanel extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(211, 211, 211);
    private static final Color BORDER_COLOR = new Color(169, 169, 169);
    private static final int BORDER_THICKNESS = 2;
    private static final String FONT_NAME = "SansSerif";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 14;
    private static final String PANEL_TITLE = "Custom Recipe";

    private final JScrollPane scrollPane;
    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileController userProfileController;
    private final ServiceManager serviceManager;

    public CustomRecipePanel(UserProfileViewModel userProfileViewModel,
                             UserProfileController userProfileController,
                             ServiceManager serviceManager) {
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileController = userProfileController;
        this.serviceManager = serviceManager;

        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS),
                PANEL_TITLE,
                0,
                0,
                new Font(FONT_NAME, FONT_STYLE, FONT_SIZE),
                Color.DARK_GRAY
        ));

        scrollPane = new JScrollPane(this,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Updates the custom recipe panel which consists of a list of recipes the user has created.
     * @param createdRecipes the list of recipes created by user.
     */
    public void updateComponents(List<Recipe> createdRecipes) {
        removeAll();

        for (Recipe recipe : createdRecipes) {
            final UserProfileRecipeThumbnailPanel customRecipePanel = new UserProfileRecipeThumbnailPanel(
                    userProfileViewModel,
                    userProfileController,
                    serviceManager
            );

            customRecipePanel.addRecipe(recipe);
            add(customRecipePanel);
        }
    }
}
