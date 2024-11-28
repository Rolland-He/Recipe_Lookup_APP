package view.ui_components.main_page;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Header Panel of the main page view.
 */
public class HeaderPanel extends JPanel {
    private static final int HGAP = 5;
    private static final int VGAP = 5;

    public HeaderPanel(JButton searchButton, JButton exploreIngredientButton,
                       JButton customRecipeButton, JButton userButton) {
        setLayout(new FlowLayout(FlowLayout.RIGHT, HGAP, VGAP));

        add(exploreIngredientButton);
        add(searchButton);
        add(customRecipeButton);
        add(userButton);
    }
}
