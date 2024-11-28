package view.ui_components.explore_ingredient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import entities.recipe.SimpleRecipe;
import interface_adapter.services.ServiceManager;

/**
 * A scrollable panel to display a list of simple recipes.
 */
public class SimpleRecipeScrollPanel extends JPanel {
    private static final int GRID_COLS = 2;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;
    private static final int NO_RESULTS_FONT_SIZE = 18;
    private static final Color NO_RESULTS_FONT_COLOR = new Color(108, 117, 125);

    private final JPanel recipePanel;
    private final ServiceManager serviceManager;

    /**
     * Creates a scrollable panel to display recipes.
     *
     * @param serviceManager the service manager used in the application, must not be null.
     */
    public SimpleRecipeScrollPanel(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        recipePanel = new JPanel(new GridLayout(0, GRID_COLS, H_GAP, V_GAP));
        recipePanel.setBackground(Color.WHITE);

        final JScrollPane scrollPane = new JScrollPane(recipePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Displays a list of recipes in the scrollable panel.
     *
     * @param recipes the list of recipes to display, can be null or empty.
     */
    public void displayRecipes(List<SimpleRecipe> recipes) {
        recipePanel.removeAll();

        if (recipes == null || recipes.isEmpty()) {
            showNoResultsMessage();
        } else {
            for (SimpleRecipe recipe : recipes) {
                final SimpleRecipePanel panel = new SimpleRecipePanel(serviceManager);
                panel.addRecipe(recipe);
                recipePanel.add(panel);
            }
        }

        recipePanel.revalidate();
        recipePanel.repaint();
    }

    private void showNoResultsMessage() {
        recipePanel.setLayout(new BorderLayout());
        final JLabel messageLabel = new JLabel("No cocktails found with this ingredient");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, NO_RESULTS_FONT_SIZE));
        messageLabel.setForeground(NO_RESULTS_FONT_COLOR);
        recipePanel.add(messageLabel, BorderLayout.CENTER);
    }
}
