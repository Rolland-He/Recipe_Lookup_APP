package view.ui_components.custom_recipe;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import interface_adapter.custom_recipe.CustomRecipeState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * A panel for inputting and displaying recipe instructions.
 */
public class InstructionsPanel extends AbstractViewDecorator<CustomRecipeState> {
    private static final int TEXTAREA_ROWS = 5;
    private static final int TEXTAREA_COLUMNS = 30;
    private final JTextArea instructionsArea;

    public InstructionsPanel(PageView<CustomRecipeState> view) {
        super(view);
        setLayout(new BorderLayout());
        final JLabel label = new JLabel("Instructions:");
        instructionsArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        final JScrollPane scrollPane = new JScrollPane(instructionsArea);

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void update(CustomRecipeState state) {
        super.getTempPage().update(state);
        instructionsArea.setText("");
    }

    /**
     * Retrieves the instructions entered in the text area.
     *
     * @return The instructions as a String.
     */
    public String getInstructions() {
        return instructionsArea.getText();
    }
}
