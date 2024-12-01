package view.ui_components.user_profile;

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
import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * CustomRecipePanel represents a panel that displays custom recipes created by the user.
 * This class extends JPanel and is responsible for rendering and managing custom recipe information.
 * This class is not expected to be null.
 */
public class CustomRecipePanel extends AbstractViewDecorator<UserProfileState> {
    private static final int LAYOUT_GAP = 10;
    private static final int BORDER_SIZE = 10;
    private static final int GRID_COLUMNS = 3;
    private static final int HEADER_FONT_SIZE = 20;
    private static final String PANEL_TITLE = "Custom Recipe";

    private final JPanel gridPanel;
    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileController userProfileController;
    private final ServiceManager serviceManager;

    public CustomRecipePanel(UserProfileViewModel userProfileViewModel,
                             UserProfileController userProfileController,
                             ServiceManager serviceManager,
                             PageView<UserProfileState> view) {
        super(view);
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileController = userProfileController;
        this.serviceManager = serviceManager;

        // Recommendations section
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        setLayout(new BorderLayout(LAYOUT_GAP, LAYOUT_GAP));
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        final JLabel headerLabel = new JLabel(PANEL_TITLE, SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, HEADER_FONT_SIZE));
        add(headerLabel, BorderLayout.NORTH);

        gridPanel = new JPanel(new GridLayout(0, GRID_COLUMNS, LAYOUT_GAP, LAYOUT_GAP));
        final JScrollPane scrollPane = new JScrollPane(gridPanel);

        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void update(UserProfileState state) {
        super.getTempPage().update(state);
        gridPanel.removeAll();
        final List<Recipe> createdRecipes = state.getCreatedRecipes();
        for (Recipe recipe : createdRecipes) {
            final UserProfileRecipeThumbnailPanel customRecipePanel = new UserProfileRecipeThumbnailPanel(
                    userProfileViewModel,
                    userProfileController,
                    serviceManager
            );

            customRecipePanel.addRecipe(recipe);
            gridPanel.add(customRecipePanel);
        }
    }
}
