package view.ui_components.explore_ingredient;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import entities.recipe.SimpleRecipe;
import interface_adapter.services.ServiceManager;

public class SimpleRecipeScrollPanel extends JPanel {
    private static final int GRID_COLS = 2;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;

    private final JPanel recipePanel;
    private final ServiceManager serviceManager;

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

    public void displayRecipes(List<SimpleRecipe> recipes) {
        recipePanel.removeAll();

        if (recipes == null || recipes.isEmpty()) {
            showNoResultsMessage();
        }
        else {
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
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        messageLabel.setForeground(new Color(108, 117, 125));
        recipePanel.add(messageLabel, BorderLayout.CENTER);
    }
}