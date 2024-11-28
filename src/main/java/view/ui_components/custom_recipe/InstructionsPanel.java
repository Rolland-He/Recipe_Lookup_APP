package view.ui_components.custom_recipe;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A panel for inputting and displaying recipe instructions.
 */
public class InstructionsPanel extends JPanel {
    private static final int TEXTAREA_ROWS = 5;
    private static final int TEXTAREA_COLUMNS = 30;
    private final JTextArea instructionsArea;

    /**
     * Constructs a new InstructionsPanel.
     */
    public InstructionsPanel() {
        setLayout(new BorderLayout());
        final JLabel label = new JLabel("Instructions:");
        instructionsArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        final JScrollPane scrollPane = new JScrollPane(instructionsArea);

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
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
