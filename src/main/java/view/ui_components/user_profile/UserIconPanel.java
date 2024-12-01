package view.ui_components.user_profile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import interface_adapter.user_profile.UserProfileState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * User Icon Panel that stores the username and photo of the user.
 */
public class UserIconPanel extends AbstractViewDecorator<UserProfileState> {
    private static final int GRAY_COLOR = 169;
    private static final int FONT_SIZE = 18;

    private final JLabel userLabel;
    private final JLabel photoLabel;
    private final JButton preferenceButton;

    public UserIconPanel(JButton preferenceButton, PageView<UserProfileState> view) {
        super(view);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(GRAY_COLOR, GRAY_COLOR, GRAY_COLOR));

        this.preferenceButton = preferenceButton;
        // Add user icon
        photoLabel = new JLabel();
        final ImageIcon userPhoto = new ImageIcon(getClass().getResource("/image/icon.jpg"));
        final Image scaledPhoto = userPhoto.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        final ImageIcon roundedPhoto = createRoundedImage(new ImageIcon(scaledPhoto));
        photoLabel.setIcon(roundedPhoto);

        // Add username
        userLabel = new JLabel();
        userLabel.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        userLabel.setForeground(Color.WHITE);

        add(photoLabel);
        add(userLabel);
        add(preferenceButton);
    }

    @Override
    public void update(UserProfileState state) {
        super.getTempPage().update(state);
        userLabel.setText(String.format("Username: %s", state.getUsername()));
    }

    private ImageIcon createRoundedImage(ImageIcon imageIcon) {
        final int diameter = Math.min(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        final BufferedImage mask = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D g2d = mask.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(0, 0, diameter, diameter);
        g2d.setComposite(AlphaComposite.SrcIn);
        g2d.drawImage(imageIcon.getImage(), 0, 0, diameter, diameter, null);
        g2d.dispose();

        return new ImageIcon(mask);
    }
}
