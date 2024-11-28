package view.ui_components.explore_ingredient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.recipe.SimpleRecipe;
import interface_adapter.services.ServiceManager;

/**
 * A panel to display a simple recipe with an image and title.
 */
public class SimpleRecipePanel extends JPanel {
    private static final int PANEL_WIDTH = 280;
    private static final int PANEL_HEIGHT = 320;
    private static final int IMAGE_HEIGHT = 240;
    private static final int TITLE_FONT_SIZE = 16;
    private static final int BORDER_THICKNESS = 1;
    private static final int TITLE_PADDING = 10;

    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private static final Color BORDER_COLOR = new Color(222, 226, 230);
    private static final Color TITLE_PANEL_COLOR = Color.WHITE;

    /**
     * Creates a panel to display a simple recipe.
     *
     * @param serviceManager the service manager used in the application.
     */
    public SimpleRecipePanel(ServiceManager serviceManager) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS));
    }

    /**
     * Adds a recipe to the panel.
     *
     * @param recipe the simple recipe to display, must not be null.
     */
    public void addRecipe(SimpleRecipe recipe) {
        removeAll();

        // Image Panel
        final JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(BACKGROUND_COLOR);
        final ImageIcon imageIcon = new ImageIcon(recipe.getImageLink());
        final Image image = imageIcon.getImage().getScaledInstance(PANEL_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
        final JLabel imageLabel = new JLabel(new ImageIcon(image));
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Title Panel
        final JPanel titlePanel = new JPanel();
        titlePanel.setBackground(TITLE_PANEL_COLOR);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(BorderFactory
                .createEmptyBorder(TITLE_PADDING, TITLE_PADDING, TITLE_PADDING, TITLE_PADDING));

        final JLabel titleLabel = new JLabel(recipe.getName());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, TITLE_FONT_SIZE));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        add(imagePanel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}
