package view.views_placeholder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import view.PageView;
import view.ui_components.user_profile.CustomRecipePanel;
import view.ui_components.user_profile.ReturnButtonPanel;
import view.ui_components.user_profile.UserIconPanel;

/**
 * User profile view.
 */
public class UserProfileView extends JPanel implements PageView<UserProfileState>,
        ActionListener,
        PropertyChangeListener {
    private static final int BACKGROUND_GRAY = 211;
    private final String viewName = "account";

    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileController userProfileController;
    private final ServiceManager serviceManager;

    private final UserIconPanel userIconPanel;
    private final CustomRecipePanel customRecipePanel;
    private final ReturnButtonPanel returnButtonPanel;

    private final JButton changePreference = new JButton("Change Settings");
    private final JButton returnButton = new JButton("Return to home.");

    public UserProfileView(UserProfileViewModel userProfileViewModel,
                           UserProfileController userProfileController,
                           ServiceManager serviceManager) {
        this.userProfileController = userProfileController;
        this.serviceManager = serviceManager;
        this.userProfileViewModel = userProfileViewModel;

        setLayout(new BorderLayout());
        setBackground(new Color(BACKGROUND_GRAY, BACKGROUND_GRAY, BACKGROUND_GRAY));

        userProfileViewModel.addPropertyChangeListener(this);

        userIconPanel = new UserIconPanel(changePreference);
        customRecipePanel = new CustomRecipePanel(
                userProfileViewModel, userProfileController, serviceManager);
        returnButtonPanel = new ReturnButtonPanel(returnButton);

        changePreference.addActionListener(event -> {
            userProfileController.switchToPreferenceView();
        });
        returnButton.addActionListener(event -> userProfileController.switchToHomePage());

        add(userIconPanel, BorderLayout.NORTH);
        add(customRecipePanel.getScrollPane(), BorderLayout.CENTER);
        add(returnButtonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final UserProfileState state = (UserProfileState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(UserProfileState state) {
        final String username = state.getUsername();
        final List<Recipe> createdRecipes = state.getCreatedRecipes();
        userIconPanel.updateComponents(username);
        customRecipePanel.updateComponents(createdRecipes);
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void update(UserProfileState state) {

    }
}
