package view.ui_components.explore_ingredient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;

/**
 * A scrollable panel to display a list of recipes with the option to explore ingredients.
 */
public class RecipeScrollPanel extends JPanel {
    private static final int ROW = 0;
    private static final int COL = 2;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;
    private static final int WELCOME_FONT_SIZE = 24;
    private static final int NO_RESULT_FONT_SIZE = 18;
    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 400;
    private static final int RIGID_AREA_HEIGHT = 20;
    private static final Color WELCOME_FONT_COLOR = new Color(108, 117, 125);
    private static final String FONT_NAME = "SansSerif";

    private final JPanel recipePanel;
    private final JScrollPane scrollPane;
    private final ServiceManager serviceManager;
    private boolean isExploreMode;

    private List<Recipe> currentRecipes = new ArrayList<>();
    private int currentRecipeIndex = -1;

    /**
     * Creates the RecipeScrollPanel to display recipes in a scrollable panel.
     *
     * @param serviceManager the service manager for recipe services, must not be null.
     */
    public RecipeScrollPanel(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        recipePanel = new JPanel(new GridLayout(ROW, COL, H_GAP, V_GAP));
        recipePanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(recipePanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Add initial empty state
        showEmptyState();
    }

    /**
     * Displays a list of recipes in the panel.
     *
     * @param recipes the list of recipes to display, may be null or empty.
     */
    public void displayRecipes(List<Recipe> recipes) {
        this.currentRecipes = recipes;
        recipePanel.removeAll();
        recipePanel.setLayout(new GridLayout(0, COL, H_GAP, V_GAP));

        if (recipes == null || recipes.isEmpty()) {
            if (!isExploreMode) {
                showEmptyState();
            }
            else {
                showNoResultsMessage();
            }
        }
        else {
            final List<JPanel> recipePanels = parseToPanel(recipes);
            for (JPanel recipe : recipePanels) {
                recipePanel.add(recipe);
            }
        }

        recipePanel.revalidate();
        recipePanel.repaint();
    }

    private void showNoResultsMessage() {
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));

        final JPanel messagePanel = new JPanel(new GridBagLayout());
        messagePanel.setBackground(Color.WHITE);
        final JLabel messageLabel = new JLabel("No cocktails found with this ingredient");
        messageLabel.setFont(new Font(FONT_NAME, Font.BOLD, NO_RESULT_FONT_SIZE));
        messageLabel.setForeground(WELCOME_FONT_COLOR);
        messagePanel.add(messageLabel);

        recipePanel.add(Box.createVerticalGlue());
        recipePanel.add(messagePanel);
        recipePanel.add(Box.createVerticalGlue());
    }

    /**
     * Clears all recipes from the panel.
     */
    public void clearRecipes() {
        if (!isExploreMode) {
            recipePanel.removeAll();
            showEmptyState();
            recipePanel.revalidate();
            recipePanel.repaint();
        }
    }

    /**
     * Sets the mode of the panel to explore mode.
     *
     * @param exploreMode whether the panel is in explore mode.
     */
    public void setExploreMode(boolean exploreMode) {
        this.isExploreMode = exploreMode;
        if (exploreMode) {
            recipePanel.removeAll();
            recipePanel.setLayout(new GridLayout(0, COL, H_GAP, V_GAP));
        }
        else {
            clearRecipes();
        }
        recipePanel.revalidate();
        recipePanel.repaint();
    }

    private void showEmptyState() {
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));

        final JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(Color.WHITE);
        final JLabel welcomeLabel = new JLabel("Welcome to Recipe Search!");
        welcomeLabel.setFont(new Font(FONT_NAME, Font.BOLD, WELCOME_FONT_SIZE));
        welcomeLabel.setForeground(WELCOME_FONT_COLOR);
        welcomePanel.add(welcomeLabel);

        final JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(Color.WHITE);
        final JLabel searchLabel = new JLabel("Search for recipes!");
        searchLabel.setFont(new Font(FONT_NAME, Font.BOLD, WELCOME_FONT_SIZE));
        searchLabel.setForeground(WELCOME_FONT_COLOR);
        searchPanel.add(searchLabel);

        recipePanel.add(Box.createVerticalGlue());
        recipePanel.add(welcomePanel);
        recipePanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        recipePanel.add(searchPanel);
        recipePanel.add(Box.createVerticalGlue());
    }

    /**
     * Gets the next recipe in the list relative to the current recipe.
     *
     * @param currentRecipe the current recipe, must not be null.
     * @return the next recipe or null if none is available.
     */
    public Recipe getNextRecipe(Recipe currentRecipe) {
        if (currentRecipes == null || currentRecipes.isEmpty()) {
            return null;
        }

        currentRecipeIndex = -1;
        for (int i = 0; i < currentRecipes.size(); i++) {
            if (currentRecipes.get(i).getId() == currentRecipe.getId()) {
                currentRecipeIndex = i;
                break;
            }
        }

        if (currentRecipeIndex >= 0 && currentRecipeIndex < currentRecipes.size() - 1) {
            return currentRecipes.get(currentRecipeIndex + 1);
        }
        return null;
    }

    private List<JPanel> parseToPanel(List<Recipe> recipes) {
        return new ArrayList<>();
    }
}
