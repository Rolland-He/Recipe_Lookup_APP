package view.views_placeholder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import view.PageView;
import view.concrete_page.UserProfileConcrete;
import view.ui_components.user_profile.CustomRecipePanel;
import view.ui_components.user_profile.ReturnButtonPanel;
import view.ui_components.user_profile.UserIconPanel;

/**
 * User profile view.
 */
public class UserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final int BACKGROUND_GRAY = 211;
    private final String viewName = "account";

    private final PageView<UserProfileState> viewHandler;

    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileController userProfileController;
    private final ServiceManager serviceManager;

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

        final UserProfileConcrete userProfileConcrete = new UserProfileConcrete();
        final UserIconPanel userIconPanel = new UserIconPanel(changePreference, userProfileConcrete);
        final CustomRecipePanel customRecipePanel = new CustomRecipePanel(
                userProfileViewModel, userProfileController, serviceManager, userIconPanel);
        final ReturnButtonPanel returnButtonPanel = new ReturnButtonPanel(returnButton, customRecipePanel);
        viewHandler = returnButtonPanel;

        changePreference.addActionListener(event -> {
            userProfileController.switchToPreferenceView();
        });
        returnButton.addActionListener(event -> userProfileController.switchToHomePage());

        add(userIconPanel, BorderLayout.NORTH);
        add(customRecipePanel, BorderLayout.CENTER);
        add(returnButtonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final UserProfileState state = (UserProfileState) evt.getNewValue();
        viewHandler.update(state);
    }

    public String getViewName() {
        return viewName;
    }
}
