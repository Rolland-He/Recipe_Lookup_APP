package view.ui_components.custom_recipe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Panel for managing ingredients in a custom recipe.
 */
public class IngredientsPanel extends JPanel {
    private static final int SCROLL_PANE_WIDTH = 400;
    private static final int SCROLL_PANE_HEIGHT = 150;
    private static final int GRID_ROWS = 1;
    private static final int GRID_COLS = 3;
    private static final int GRID_HGAP = 5;
    private static final int GRID_VGAP = 5;

    private final JPanel ingredientListPanel;

    public IngredientsPanel() {
        setLayout(new BorderLayout());
        ingredientListPanel = new JPanel();
        ingredientListPanel.setLayout(new BoxLayout(ingredientListPanel, BoxLayout.Y_AXIS));

        final JScrollPane scrollPane = new JScrollPane(ingredientListPanel);
        scrollPane.setPreferredSize(new Dimension(SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT));
        add(scrollPane, BorderLayout.CENTER);

        final JButton addIngredientButton = new JButton("Add Ingredient");
        addIngredientButton.addActionListener(event -> addIngredientRow());
        add(addIngredientButton, BorderLayout.SOUTH);
    }

    private void addIngredientRow() {
        final JPanel ingredientRow = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS, GRID_HGAP, GRID_VGAP));
        final JTextField nameField = new JTextField();
        final JTextField measurementField = new JTextField();
        final JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(event -> {
            ingredientListPanel.remove(ingredientRow);
            ingredientListPanel.revalidate();
            ingredientListPanel.repaint();
        });

        ingredientRow.add(nameField);
        ingredientRow.add(measurementField);
        ingredientRow.add(removeButton);
        ingredientListPanel.add(ingredientRow);
        ingredientListPanel.revalidate();
        ingredientListPanel.repaint();
    }

    /**
     * Retrieves the list of ingredients entered in the panel.
     * This method iterates through all the ingredient rows in the panel,
     * extracting the name and measurement of each ingredient from the
     * respective text fields.
     *
     * @return An ArrayList of String arrays, where each array contains two elements:
     *         the ingredient name at index 0 and the measurement at index 1.
     *         Returns an empty list if no ingredients are present.
     */
    public ArrayList<String[]> getIngredients() {
        final ArrayList<String[]> ingredients = new ArrayList<>();
        for (java.awt.Component component : ingredientListPanel.getComponents()) {
            final JPanel row = (JPanel) component;
            final JTextField nameField = (JTextField) row.getComponent(0);
            final JTextField measurementField = (JTextField) row.getComponent(1);
            ingredients.add(new String[]{nameField.getText(), measurementField.getText()});
        }
        return ingredients;
    }
}
