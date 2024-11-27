package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import use_case.change_preference.ChangePreferenceDataAccessInterface;
import use_case.change_preference.ChangePreferenceInputData;
import use_case.change_preference.ChangePreferenceOutputBoundary;
import use_case.change_preference.ChangePreferenceInteractor;
import use_case.change_preference.ChangePreferenceOutputData;
import use_case.explore_ingredient.ExploreIngredientDataAccessInterface;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ChangePreferenceInteractorTest {

    private ChangePreferenceDataAccessInterface dataAccess;
    private ChangePreferenceOutputBoundary presenter;
    private ChangePreferenceInteractor interactor;
    private ExploreIngredientDataAccessInterface ingredientDataAccess;

    @BeforeEach
    void setUp() {
        dataAccess = mock(ChangePreferenceDataAccessInterface.class);
        presenter = mock(ChangePreferenceOutputBoundary.class);
        interactor = new ChangePreferenceInteractor(presenter, dataAccess, ingredientDataAccess);
    }

    @Test
    void testChangeIngredientsToAvoid_Success() {
        // Arrange
        String username = "testUser";
        List<String> ingredientsToAvoid = Arrays.asList("Peanuts", "Dairy");
        when(dataAccess.getCurrentUser()).thenReturn(username);

        ChangePreferenceInputData inputData = new ChangePreferenceInputData(ingredientsToAvoid);

        // Act
        interactor.changeIngredientsToAvoid(inputData);

        // Assert
        verify(dataAccess).getCurrentUser();
        verify(dataAccess).changeIngredientsToAvoid(username, ingredientsToAvoid);
        verifyNoInteractions(presenter);
    }

    @Test
    void testChangeIngredientsToAvoid_NoCurrentUser() {
        // Arrange
        when(dataAccess.getCurrentUser()).thenReturn(null);

        ChangePreferenceInputData inputData = new ChangePreferenceInputData(Arrays.asList("Peanuts"));

        // Act
        interactor.changeIngredientsToAvoid(inputData);

        // Assert
        verify(dataAccess).getCurrentUser();
        verify(dataAccess, never()).changeIngredientsToAvoid(anyString(), anyList());
    }

    @Test
    void testSwitchToHome() {
        // Act
        interactor.switchToHomePageView();

        // Assert
        verify(presenter).switchToHomePageView();
    }

    @Test
    void testChangeIngredientsToAvoid_EmptyList() {
        // Arrange
        String username = "testUser";
        when(dataAccess.getCurrentUser()).thenReturn(username);

        ChangePreferenceInputData inputData = new ChangePreferenceInputData(Collections.emptyList());

        // Act
        interactor.changeIngredientsToAvoid(inputData);

        // Assert
        verify(dataAccess).getCurrentUser();
        verify(dataAccess).changeIngredientsToAvoid(username, Collections.emptyList());
    }
}

class ChangePreferenceOutputDataTest {

    @Test
    void testConstructorAndGetter_Failure() {
        // Arrange
        boolean useCaseFailed = true;
        List<String> ingredientsList = Arrays.asList("Peanuts", "Dairy");

        // Act
        ChangePreferenceOutputData outputData = new ChangePreferenceOutputData(ingredientsList, useCaseFailed);

        // Assert
        assertTrue(outputData.isUseCaseFailed(), "Expected useCaseFailed to be true");
    }

    @Test
    void testConstructorAndGetter_Success() {
        // Arrange
        boolean useCaseFailed = false;
        List<String> ingredientsList = Arrays.asList("Peanuts", "Dairy");

        // Act
        ChangePreferenceOutputData outputData = new ChangePreferenceOutputData(ingredientsList, useCaseFailed);

        // Assert
        assertFalse(outputData.isUseCaseFailed(), "Expected useCaseFailed to be false");
    }
}