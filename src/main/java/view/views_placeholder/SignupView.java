package view.views_placeholder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import view.PageView;

/**
 * View for sign up with enhanced design.
 */
public class SignupView extends JPanel implements PageView<SignupState>, ActionListener, PropertyChangeListener {
    private static final int FIELD_COLUMNS = 20;
    private static final int TITLE_FONT_SIZE = 24;
    private static final int BUTTON_FONT_SIZE = 18;
    private static final int INSET_SIZE = 10;
    private static final int THREE = 3;
    private static final int FOUR = 10;

    private static final int COCKTAIL1_X = 50;
    private static final int COCKTAIL1_Y = 50;
    private static final int COCKTAIL1_WIDTH = 200;
    private static final int COCKTAIL1_HEIGHT = 300;

    private static final int COCKTAIL2_X = 550;
    private static final int COCKTAIL2_Y = 50;
    private static final int COCKTAIL2_WIDTH = 100;
    private static final int COCKTAIL2_HEIGHT = 150;

    private static final Color SIGNUP_BUTTON_COLOR = new Color(0, 128, 0);
    private static final Color LOGIN_BUTTON_COLOR = new Color(75, 0, 130);
    private static final Color SIGNUP_HOVER_COLOR = new Color(34, 139, 34);
    private static final Color LOGIN_HOVER_COLOR = new Color(139, 0, 139);
    private static final Color BACKGROUND_COLOR = new Color(255, 165, 0);

    private final JButton signupButton = new JButton("Sign up");
    private final JButton loginButton = new JButton("Switch to Login");
    private final JTextField usernameField = new JTextField(FIELD_COLUMNS);
    private final JPasswordField passwordField = new JPasswordField(FIELD_COLUMNS);
    private final JPasswordField confirmPasswordField = new JPasswordField(FIELD_COLUMNS);

    private SignupController signupController;
    private SignupViewModel signupViewModel;

    public SignupView(SignupController signupController, SignupViewModel signupViewModel) {
        this.signupController = signupController;
        this.signupViewModel = signupViewModel;

        signupViewModel.addPropertyChangeListener(this);

        setLayout(new GridBagLayout());
        setOpaque(false);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);

        // Add title
        final JLabel titleLabel = new JLabel("Cocktail lab");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Add username input
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        // Add password input
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        // Add confirm password input
        gbc.gridx = 0;
        gbc.gridy = THREE;
        add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        add(confirmPasswordField, gbc);

        // Add buttons
        gbc.gridx = 0;
        gbc.gridy = FOUR;
        add(signupButton, gbc);

        gbc.gridx = 1;
        add(loginButton, gbc);

        configureButtons();

        signupButton.addActionListener(event -> {
            final SignupState currentState = signupViewModel.getState();
            signupController.execute(
                    currentState.getUsername(),
                    currentState.getPassword(),
                    currentState.getRepeatPassword()
            );
        });

        loginButton.addActionListener(event -> signupController.switchToLoginView());

        addUsernameListener();
        addPasswordListener();
        addConfirmPasswordListener();
    }

    private void configureButtons() {
        signupButton.setBackground(SIGNUP_BUTTON_COLOR);
        signupButton.setForeground(Color.WHITE);
        loginButton.setBackground(LOGIN_BUTTON_COLOR);
        loginButton.setForeground(Color.WHITE);

        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(SIGNUP_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(SIGNUP_BUTTON_COLOR);
            }
        });

        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(LOGIN_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(LOGIN_BUTTON_COLOR);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        final ImageIcon cocktail1 = new ImageIcon(getClass().getResource("/image/cocktail1.png"));
        g.drawImage(cocktail1.getImage(), COCKTAIL1_X, COCKTAIL1_Y, COCKTAIL1_WIDTH, COCKTAIL1_HEIGHT, this);

        final ImageIcon cocktail2 = new ImageIcon(getClass().getResource("/image/cocktail2.png"));
        g.drawImage(cocktail2.getImage(), COCKTAIL2_X, COCKTAIL2_Y, COCKTAIL2_WIDTH, COCKTAIL2_HEIGHT, this);
    }

    public String getViewName() {
        return "sign up";
    }

    @Override
    public void update(SignupState state) {
        // Update method implementation if needed
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action performed method if needed
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
        else if ("successful signup".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this,
                    String.format("Account successfully created, %s", state.getUsername()));
        }
    }

    private void addUsernameListener() {
        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                usernameDocumentListener();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                usernameDocumentListener();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                usernameDocumentListener();
            }
        });
    }

    private void usernameDocumentListener() {
        final SignupState currentState = signupViewModel.getState();
        currentState.setUsername(usernameField.getText());
        signupViewModel.setState(currentState);
    }

    private void addPasswordListener() {
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                passwordDocumentListener();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                passwordDocumentListener();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                passwordDocumentListener();
            }
        });
    }

    private void passwordDocumentListener() {
        final SignupState currentState = signupViewModel.getState();
        currentState.setPassword(new String(passwordField.getPassword()));
        signupViewModel.setState(currentState);
    }

    private void addConfirmPasswordListener() {
        confirmPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                confirmDocumentListener();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                confirmDocumentListener();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                confirmDocumentListener();
            }
        });
    }

    private void confirmDocumentListener() {
        final SignupState currentState = signupViewModel.getState();
        currentState.setRepeatPassword(new String(confirmPasswordField.getPassword()));
        signupViewModel.setState(currentState);
    }
}
