package view.ui_components.explore_ingredient;

import java.awt.*;

import javax.swing.*;

import entities.recipe.SimpleRecipe;
import interface_adapter.services.ServiceManager;

public class SimpleRecipePanel extends JPanel {
    private static final int PANEL_WIDTH = 280;
    private static final int PANEL_HEIGHT = 320;
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);

    public SimpleRecipePanel(ServiceManager serviceManager) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createLineBorder(new Color(222, 226, 230), 1));
    }

    public void addRecipe(SimpleRecipe recipe) {
        removeAll();

        // Image Panel
        final JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(BACKGROUND_COLOR);
        final ImageIcon imageIcon = new ImageIcon(recipe.getImageLink());
        final Image image = imageIcon.getImage().getScaledInstance(PANEL_WIDTH, 240, Image.SCALE_SMOOTH);
        final JLabel imageLabel = new JLabel(new ImageIcon(image));
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Title Panel
        final JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        final JLabel titleLabel = new JLabel(recipe.getName());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        add(imagePanel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}
