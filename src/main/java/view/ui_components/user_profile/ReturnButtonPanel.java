package view.ui_components.user_profile;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;

import interface_adapter.user_profile.UserProfileState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * A panel containing the return button, styled with a specific layout and design.
 */
public class ReturnButtonPanel extends AbstractViewDecorator<UserProfileState> {
    private static final int BACKGROUND_COLOR_RED = 211;
    private static final int BACKGROUND_COLOR_GREEN = 211;
    private static final int BACKGROUND_COLOR_BLUE = 211;

    private static final int BUTTON_COLOR_RED = 105;
    private static final int BUTTON_COLOR_GREEN = 105;
    private static final int BUTTON_COLOR_BLUE = 105;

    private static final int FONT_SIZE = 14;

    /**
     * Creates a panel containing the return button with customized styles.
     * @param view the page view to be connected into the decorator.
     * @param returnButton the return button to add to the panel, must not be null.
     */
    public ReturnButtonPanel(JButton returnButton, PageView<UserProfileState> view) {
        super(view);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(BACKGROUND_COLOR_RED, BACKGROUND_COLOR_GREEN, BACKGROUND_COLOR_BLUE));

        returnButton.setBackground(new Color(BUTTON_COLOR_RED, BUTTON_COLOR_GREEN, BUTTON_COLOR_BLUE));
        returnButton.setForeground(Color.WHITE);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        add(returnButton);
    }

    @Override
    public void update(UserProfileState state) {
        super.getTempPage().update(state);
    }
}
