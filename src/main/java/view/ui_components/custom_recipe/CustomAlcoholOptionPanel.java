package view.ui_components.custom_recipe;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CustomAlcoholOptionPanel extends JPanel {
    public static final int COLOR_R = 255;
    public static final int COLOR_G = 165;
    public static final int COLOR_B = 0;
    public static final int FONT_SIZE = 14;
    private final ButtonGroup alcoholGroup = new ButtonGroup();

    public CustomAlcoholOptionPanel() {
        setLayout(new FlowLayout());
        setBackground(new Color(COLOR_R, COLOR_G, COLOR_B));

        final JCheckBox isAlcoholic = createStyledCheckbox("Is Alcoholic");
        final JCheckBox nonAlcoholic = createStyledCheckbox("Non Alcoholic");
        final JCheckBox optionalAlcoholic = createStyledCheckbox("Optional Alcoholic");

        alcoholGroup.add(isAlcoholic);
        alcoholGroup.add(nonAlcoholic);
        alcoholGroup.add(optionalAlcoholic);

        add(isAlcoholic);
        add(nonAlcoholic);
        add(optionalAlcoholic);
    }

    private JCheckBox createStyledCheckbox(String label) {
        final JCheckBox checkBox = new JCheckBox(label);
        checkBox.setBackground(new Color(COLOR_R, COLOR_G, COLOR_B));
        checkBox.setForeground(Color.WHITE);
        checkBox.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        return checkBox;
    }
}
