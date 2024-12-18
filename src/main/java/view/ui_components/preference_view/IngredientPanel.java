package view.ui_components.preference_view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import interface_adapter.preference.PreferenceController;
import interface_adapter.preference.PreferenceState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * Ingredient panel that contains the list of ingredients.
 */
public class IngredientPanel extends AbstractViewDecorator<PreferenceState> {
    private final JList<String> ingredientList;
    private final DefaultListModel<String> listModel;

    public IngredientPanel(PageView<PreferenceState> tempPage,
                           PreferenceController preferenceController,
                           JButton updateButton) {
        super(tempPage);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        ingredientList = new JList<>(listModel);
        ingredientList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        final JScrollPane listScrollPane = new JScrollPane(ingredientList);

        updateButton.addActionListener(event -> preferenceController.changePreference(getPreference()));

        add(new JLabel("Select ingredients to avoid:"), BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void update(PreferenceState state) {
        if (listModel.isEmpty()) {
            final List<String> ingredients = state.getIngredients();
            for (String ingredient : ingredients) {
                listModel.addElement(ingredient);
            }
            super.getTempPage().update(state);
        }
    }

    private List<String> getPreference() {
        final List<String> avoidedIngredients = new ArrayList<>();
        avoidedIngredients.addAll(ingredientList.getSelectedValuesList());
        JOptionPane.showMessageDialog(this, "Preferences updated: " + avoidedIngredients);
        return avoidedIngredients;

    }

}
