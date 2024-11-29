package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interface_adapter.explore_ingredient.ExploreIngredientController;
import interface_adapter.explore_ingredient.ExploreIngredientState;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.services.ServiceManager;

/**
 * Explore ingredient recipe view.
 */
public class ExploreIngredientRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private static final int GRID_COLUMNS = 3;
    private static final int HEADER_PADDING = 10;
    private static final int TITLE_FONT_SIZE = 24;
    private static final String TITLE_FONT_NAME = "SansSerif";
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 40;
    private static final int BUTTON_FONT_SIZE = 14;
    private static final Color BUTTON_BACKGROUND = Color.WHITE;
    private static final Color BUTTON_BORDER_COLOR = new Color(200, 200, 200);
    private static final int BUTTON_PADDING_TOP_BOTTOM = 5;
    private static final int BUTTON_PADDING_LEFT_RIGHT = 10;
    private static final int HGAP = 10;
    private static final int VGAP = 10;

    private final String viewName = "explore ingredient";
    private final ExploreIngredientViewModel exploreViewModel;
    private final ExploreIngredientController exploreController;
    private final ServiceManager serviceManager;

    // UI Components
    private final JButton backButton;
    private final JPanel gridPanel;
    private final JPanel contentPanel;

    public ExploreIngredientRecipeView(
            ExploreIngredientViewModel exploreViewModel,
            ExploreIngredientController exploreController,
            ServiceManager serviceManager) {

        this.exploreViewModel = exploreViewModel;
        this.exploreController = exploreController;
        this.serviceManager = serviceManager;
        this.exploreViewModel.addPropertyChangeListener(this);

        // Initialize components
        backButton = new JButton("<");

        // Content panel for recipes
        contentPanel = new JPanel(new BorderLayout());

        // Grid panel for ingredients
        gridPanel = new JPanel(new GridLayout(0, GRID_COLUMNS, HGAP, VGAP));
        gridPanel.setBackground(BACKGROUND_COLOR);

        setupLayout();
        setupActionListeners();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
    
        final JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(BorderFactory
                .createEmptyBorder(HEADER_PADDING, HEADER_PADDING, HEADER_PADDING, HEADER_PADDING));
    
        headerPanel.add(backButton, BorderLayout.WEST);
    
        final JLabel titleLabel = new JLabel("Explore by Ingredients");
        titleLabel.setFont(new Font(TITLE_FONT_NAME, Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
    
        final JScrollPane ingredientScroll = new JScrollPane(gridPanel);
        ingredientScroll.setBorder(null);
    
        add(headerPanel, BorderLayout.NORTH);
        add(ingredientScroll, BorderLayout.CENTER);
    }

    private void setupActionListeners() {
        backButton.addActionListener(event -> exploreController.switchToHome());
    }

    private JButton createIngredientButton(String ingredient) {
        final JButton button = new JButton(ingredient);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setFont(new Font(TITLE_FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE));
        button.setBackground(BUTTON_BACKGROUND);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BUTTON_BORDER_COLOR),
                BorderFactory.createEmptyBorder(BUTTON_PADDING_TOP_BOTTOM, BUTTON_PADDING_LEFT_RIGHT,
                                                BUTTON_PADDING_TOP_BOTTOM, BUTTON_PADDING_LEFT_RIGHT)
        ));
    
        button.addActionListener(event -> exploreController.switchToRecipes(ingredient));
    
        return button;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ExploreIngredientState state = (ExploreIngredientState) evt.getNewValue();

        // updates fields
        displayIngredients(state.getIngredients());
    }

    private void displayIngredients(List<String> ingredients) {
        gridPanel.removeAll();
        for (String ingredient : ingredients) {
            gridPanel.add(createIngredientButton(ingredient));
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Click " + event.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }
}
