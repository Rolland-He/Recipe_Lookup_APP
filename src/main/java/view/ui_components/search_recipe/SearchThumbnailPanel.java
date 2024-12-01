package view.ui_components.search_recipe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import entities.recipe.Recipe;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;

/**
 * Recipe Panel that shows when searching for recipes.
 */
public class SearchThumbnailPanel extends JPanel {
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;
    private static final int TOP = 5;
    private static final int BOTTOM = 5;
    private static final int LEFT = 10;
    private static final int RIGHT = 10;
    private static final int FONT_SIZE = 14;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 250;
    private static final int BORDER_THICKNESS = 1;

    // Colors for modern button styling
    private static final Color BUTTON_BACKGROUND = new Color(51, 122, 183);
    private static final Color BUTTON_HOVER = new Color(40, 96, 144);
    private static final Color BUTTON_BORDER = new Color(46, 109, 164);
    private static final Color TEXT_COLOR = Color.WHITE;

    private JLabel imageLabel;
    private JButton nameButton;

    private final SearchRecipeController searchRecipeController;
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final ServiceManager serviceManager;

    public SearchThumbnailPanel(SearchRecipeViewModel searchRecipeViewModel,
                                SearchRecipeController searchRecipeController,
                                ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        this.searchRecipeController = searchRecipeController;
        this.searchRecipeViewModel = searchRecipeViewModel;
        // Sets Layout
        setLayout(new BorderLayout(H_GAP, V_GAP));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, BORDER_THICKNESS));

        // Initializes JComponents
        imageLabel = new JLabel();
        nameButton = createStyledButton();

        // Adjust the panel size
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private JButton createStyledButton() {
        final JButton button = new JButton();

        // Basic button setup
        // Remove focus border
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        button.setForeground(TEXT_COLOR);
        button.setBackground(BUTTON_BACKGROUND);

        // Create rounded border with padding
        button.setBorder(new CompoundBorder(
                new LineBorder(BUTTON_BORDER, BORDER_THICKNESS, true),
                new EmptyBorder(TOP, LEFT, BOTTOM, RIGHT)
        ));

        // Make sure the background is painted
        button.setContentAreaFilled(true);
        button.setOpaque(true);

        // Add hover effect for decor
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_BACKGROUND);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }

    /**
     * Shows the given recipe thumbnail in the panel.
     * @param recipe Recipe entity that holds the recipe information.
     */
    public void addRecipe(Recipe recipe) {
        final String recipeName = recipe.getName();
        final String imageLink = recipe.getImageLink();

        final ImageServiceInterface imageService = serviceManager.getWebImageService();
        final ImageIcon recipeImage = imageService.fetchImage(imageLink);

        final ActionListener recipeDetailListener = event -> {
            if (event.getSource().equals(nameButton)) {
                searchRecipeController.switchToRecipeView(recipe.getId());
            }
        };

        // Image label at the top
        imageLabel.setIcon(recipeImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Recipe name button at the bottom
        nameButton.setText(recipeName);
        nameButton.addActionListener(recipeDetailListener);
        add(nameButton, BorderLayout.SOUTH);
    }
}
