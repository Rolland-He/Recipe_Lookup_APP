package view.views_placeholder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import view.PageView;

/**
 * Login view with background and cocktail images.
 */
public class LoginView extends JPanel implements PageView<LoginState>, ActionListener, PropertyChangeListener {
    private static final int TEXT_FIELD_SIZE = 20;
    private static final int TITLE_FONT_SIZE = 24;
    private static final int COCKTAIL1_WIDTH = 200;
    private static final int COCKTAIL1_HEIGHT = 300;
    private static final int COCKTAIL2_WIDTH = 100;
    private static final int COCKTAIL2_HEIGHT = 150;
    private static final int COCKTAIL1_X = 50;
    private static final int COCKTAIL1_Y = 50;
    private static final int COCKTAIL2_X = 550;
    private static final int COCKTAIL2_Y = 50;
    private static final Color BACKGROUND_COLOR = new Color(255, 165, 0);
    private static final Color LOGIN_BUTTON_COLOR = new Color(255, 87, 34);
    private static final Color LOGIN_BUTTON_HOVER_COLOR = new Color(255, 140, 0);
    private static final Color SIGNUP_BUTTON_COLOR = new Color(75, 0, 130);
    private static final int TEN = 10;

    private final String viewName = "log in";
    private final JTextField usernameTextField = new JTextField(TEXT_FIELD_SIZE);
    private final JPasswordField passwordTextField = new JPasswordField(TEXT_FIELD_SIZE);
    private final JButton loginButton = new JButton("Login");
    private final JButton switchToSignupButton = new JButton("Switch to sign up");

    private LoginViewModel loginViewModel;
    private LoginController loginController;

    /**
     * Constructs a LoginView.
     *
     * @param loginViewModel The view model for login.
     * @param loginController The controller for login.
     */
    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;

        loginViewModel.addPropertyChangeListener(this);

        initializeUi();
    }

    private void initializeUi() {
        setLayout(new GridBagLayout());
        setOpaque(false);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(TEN, TEN, TEN, TEN);

        addTitle(gbc);
        addUsernameField(gbc);
        addPasswordField(gbc);
        addButtons(gbc);

        styleButtons();
        addActionListeners();
        addTextFieldListeners();
    }

    private void addTitle(GridBagConstraints gbc) {
        final JLabel titleLabel = new JLabel("Cocktail Lab");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
    }

    private void addUsernameField(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        add(usernameTextField, gbc);
    }

    private void addPasswordField(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        add(passwordTextField, gbc);
    }

    private void addButtons(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 2 + 1;
        add(loginButton, gbc);

        gbc.gridx = 1;
        add(switchToSignupButton, gbc);
    }

    private void styleButtons() {
        loginButton.setBackground(LOGIN_BUTTON_COLOR);
        loginButton.setForeground(Color.WHITE);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                loginButton.setBackground(LOGIN_BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                loginButton.setBackground(LOGIN_BUTTON_COLOR);
            }
        });

        switchToSignupButton.setBackground(SIGNUP_BUTTON_COLOR);
        switchToSignupButton.setForeground(Color.WHITE);
    }

    private void addActionListeners() {
        loginButton.addActionListener(event -> {
            final LoginState currentState = loginViewModel.getState();
            loginController.execute(currentState.getUsername(), currentState.getPassword());
        });

        switchToSignupButton.addActionListener(event -> loginController.switchToSignupView());
    }

    private void addTextFieldListeners() {
        addUsernameListener();
        addPasswordListener();
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

    /**
     * Gets the view name.
     *
     * @return The view name.
     */
    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // This method is left empty as it's not used in the current implementation
    }

    @Override
    public void update(LoginState state) {
        // This method is left empty as it's not used in the current implementation
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = loginViewModel.getState();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameTextField.setText(state.getUsername());
        passwordTextField.setText(state.getPassword());
        if (state.getLoginError() != null) {
            JOptionPane.showMessageDialog(this, state.getLoginError());
        }
    }

    private void addUsernameListener() {
        usernameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateUsername();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateUsername();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateUsername();
            }

            private void updateUsername() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameTextField.getText());
                loginViewModel.setState(currentState);
            }
        });
    }

    private void addPasswordListener() {
        passwordTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePassword();
            }

            private void updatePassword() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordTextField.getPassword()));
                loginViewModel.setState(currentState);
            }
        });
    }
}
