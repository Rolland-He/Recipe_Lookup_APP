package view.ui_components.custom_recipe;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomImagePanel extends JPanel {

    public static final int COLOR_R = 255;
    public static final int COLOR_G = 165;
    public static final int COLOR_B = 0;

    public CustomImagePanel() {
        setBackground(new Color(COLOR_R, COLOR_G, COLOR_B));
        final JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        final ImageIcon cocktailImage = new ImageIcon(getClass().getResource("/image/cocktail1.png"));
        final Image scaledImage = cocktailImage.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        add(imageLabel);
    }
}
