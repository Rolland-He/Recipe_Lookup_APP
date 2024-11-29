package view.ui_components.custom_recipe;

import interface_adapter.custom_recipe.CustomRecipeState;
import view.AbstractViewDecorator;
import view.PageView;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Panel that contains radio buttons to choose for the alcohol state.
 */
public class AlcoholicPanel extends AbstractViewDecorator<CustomRecipeState> {
    private final ButtonGroup group;

    public AlcoholicPanel(PageView<CustomRecipeState> view) {
        super(view);
        setLayout(new GridLayout(2 + 1, 1));
        group = new ButtonGroup();
        final JRadioButton option1 = new JRadioButton("Alcoholic");
        final JRadioButton option2 = new JRadioButton("Non alcoholic");
        final JRadioButton option3 = new JRadioButton("Optional alcohol");

        group.add(option1);
        group.add(option2);
        group.add(option3);

        add(option1);
        add(option2);
        add(option3);
    }

    @Override
    public void update(CustomRecipeState state) {
        super.getTempPage().update(state);
        group.clearSelection();
    }

    /**
     * Gets the selected alcoholic option. Otherwise, unspecified.
     * @return the alcoholic state.
     */
    public String getSelectedOption() {
        String result = "Unspecified";
        if (group.getSelection() != null) {
            result = group.getSelection().getActionCommand();
        }
        return result;
    }
}
