package view.ui_components.custom_recipe;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import interface_adapter.custom_recipe.CustomRecipeController;
import interface_adapter.custom_recipe.CustomRecipeViewModel;

public class CustomIngredientsPanel extends JPanel {
    public static final int BACKGROUND_COLOR_R = 255;
    public static final int BACKGROUND_COLOR_G = 165;
    public static final int BACKGROUND_COLOR_B = 0;
    public static final int BORDER_COLOR_G = 87;
    public static final int BORDER_COLOR_B = 34;
    public static final int BORDER_COLOR_R = 255;
    public static final int ROW_PANEL_BACKGROUND_COLOR_G = 140;
    public static final int BUTTON_BACKGROUND_COLOR_R = 34;
    public static final int BUTTON_BACKGROUND_COLOR_G = 139;
    public static final int BUTTON_BACKGROUND_COLOR_B = 34;
    public static final int BUTTON_BACKGROUND_COLOR_G_69 = 69;
    public static final int TEN = 10;
    private final CustomRecipeController controller;
    private final CustomRecipeViewModel viewModel;

    public CustomIngredientsPanel(CustomRecipeController controller, CustomRecipeViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(BACKGROUND_COLOR_R, BACKGROUND_COLOR_G, BACKGROUND_COLOR_B));
        setBorder(createRoundedBorder("Ingredients and Measurements", new Color(
                BORDER_COLOR_R, BORDER_COLOR_G, BORDER_COLOR_B)));

        // Initialize the interface
        refreshIngredients();
    }

    /**
     * Refresh ingredients.
     */
    public void refreshIngredients() {
        removeAll();
        final List<String[]> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            final String[] ingredient = ingredients.get(i);
            add(createRow(ingredient[0], ingredient[1], i));
        }
        revalidate();
        repaint();
    }

    /**
     * Create an ingredient row.
     * @param ingredient the name of the ingredient
     * @param measurement the measurement of the ingredient
     * @param index the index of the row
     * @return the row panel
     */
    private JPanel createRow(String ingredient, String measurement, int index) {
        final JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.setBackground(new Color(BACKGROUND_COLOR_R, ROW_PANEL_BACKGROUND_COLOR_G, BACKGROUND_COLOR_B));

        // Ingredient name and measurement input fields
        final JTextField ingredientField = new JTextField(ingredient, TEN);
        final JTextField measurementField = new JTextField(measurement, TEN);

        // Add button
        final JButton addButton = new JButton("+");
        addButton.setBackground(new Color(
                BUTTON_BACKGROUND_COLOR_R, BUTTON_BACKGROUND_COLOR_G, BUTTON_BACKGROUND_COLOR_B));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(event -> {
        });

        // Remove button
        final JButton removeButton = new JButton("-");
        removeButton.setBackground(new Color(BACKGROUND_COLOR_R, BUTTON_BACKGROUND_COLOR_G_69, BACKGROUND_COLOR_B));
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(event -> {
            refreshIngredients();
        });

        // Add components to the row panel
        rowPanel.add(new JLabel("Ingredient:"));
        rowPanel.add(ingredientField);
        rowPanel.add(new JLabel("Measurement:"));
        rowPanel.add(measurementField);
        rowPanel.add(addButton);
        rowPanel.add(removeButton);

        return rowPanel;
    }

    private CompoundBorder createRoundedBorder(String title, Color color) {
        final TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(color, 2),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                Color.WHITE
        );
        return BorderFactory.createCompoundBorder(
                titledBorder,
                new EmptyBorder(TEN, TEN, TEN, TEN)
        );
    }
}
