package use_case;


import entities.recipe.CocktailRecipe;
import entities.recipe.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import use_case.create_recipe.CustomRecipeDataAccessInterface;
import use_case.create_recipe.CustomRecipeInputData;
import use_case.create_recipe.CustomRecipeInteractor;
import use_case.create_recipe.CustomRecipeOutputBoundary;
import use_case.user_profile.*;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import com.mongodb.MongoSocketException;

class CustomRecipeInteractorTest {

    private CustomRecipeDataAccessInterface customRecipeDataAccessMock;
    private CustomRecipeOutputBoundary customRecipePresenterMock;
    private CustomRecipeInteractor interactor;
    public CustomRecipeDataAccessInterface mockDataAccess;
    public CustomRecipeOutputBoundary mockPresenter;

    @BeforeEach
    void setUp() {
        customRecipeDataAccessMock = mock(CustomRecipeDataAccessInterface.class);
        customRecipePresenterMock = mock(CustomRecipeOutputBoundary.class);
        mockDataAccess = mock(CustomRecipeDataAccessInterface.class);
        mockPresenter = mock(CustomRecipeOutputBoundary.class);
        interactor = new CustomRecipeInteractor(customRecipeDataAccessMock, customRecipePresenterMock);
    }

    @Test
    void testSwitchToHomePageView() {
        // Act
        interactor.switchToHomePageView();

        // Assert
        verify(customRecipePresenterMock).switchToHomePageView();
    }

    @Test
    void testSwitchToCustomRecipeView() {
        // Act
        interactor.switchToCustomRecipeView();

        // Assert
        verify(customRecipePresenterMock).switchToRecipeCreationView();
    }

    @Test
    void testSaveCustomRecipe() {
        // Arrange
        String username = "test_user";
        String recipeName = "Mojito";
        String recipeInstruction = "Mix all ingredients";
        List<String> ingredients = Arrays.asList("Rum", "Mint", "Sugar");
        List<String> measurements = Arrays.asList("60ml", "4 leaves", "2 tsp");
        String isAlcoholic = "Alcoholic";

        CustomRecipeInputData inputData = new CustomRecipeInputData(
                recipeName, recipeInstruction, ingredients, measurements, isAlcoholic);

        List<Recipe> customRecipes = Arrays.asList(
                new CocktailRecipe("Mojito", 101, "Mix ingredients", null, null, null, "Alcoholic"),
                new CocktailRecipe("Virgin Mojito", 102, "Mix ingredients", null, null, null, "Non-Alcoholic")
        );

        when(customRecipeDataAccessMock.getCustomRecipes(username)).thenReturn(customRecipes);

        // Act
        interactor.saveCustomRecipe(inputData);

        // Assert
        verify(customRecipeDataAccessMock).createCustomRecipe(
                username,
                recipeName,
                recipeInstruction,
                ingredients,
                measurements,
                isAlcoholic
        );
        verify(customRecipeDataAccessMock, times(2)).getCurrentUser(); // Called twice
        verify(customRecipeDataAccessMock).getCustomRecipes(username);
        verify(customRecipePresenterMock).updateCustomRecipeView(argThat(outputData ->
                outputData.getUsername().equals(username) &&
                        outputData.getCreatedRecipes().equals(customRecipes)
        ));
    }

    @Test
    void testSaveCustomRecipeFailureDueToMongoSocketException() {
        // Arrange
        CustomRecipeInputData inputData = mock(CustomRecipeInputData.class);
        when(inputData.getRecipeName()).thenReturn("Fail Recipe");
        when(inputData.getRecipeInstruction()).thenReturn("Fail Instructions");
        when(inputData.getIngredients()).thenReturn(List.of("FailIngredient1"));
        when(inputData.getMeasurements()).thenReturn(List.of("1 tbsp"));
        when(inputData.getIsAlcoholic()).thenReturn("true");

        String currentUser = "testUser";
        when(customRecipeDataAccessMock.getCurrentUser()).thenReturn(currentUser);

        doThrow(new MongoSocketException("Connection failed", null))
                .when(customRecipeDataAccessMock)
                .createCustomRecipe(
                        eq(currentUser),
                        eq("Fail Recipe"),
                        eq("Fail Instructions"),
                        eq(List.of("FailIngredient1")),
                        eq(List.of("1 tbsp")),
                        eq("true")
                );

        // Act
        interactor.saveCustomRecipe(inputData);

        // Assert
        verify(customRecipePresenterMock).prepareFailView("Failed to save custom recipe.");
        verify(customRecipePresenterMock, times(0)).updateCustomRecipeView(any());
    }
}
class CustomRecipeInputDataTest {

    private CustomRecipeDataAccessInterface customRecipeDataAccessMock;
    private CustomRecipeOutputBoundary customRecipePresenterMock;
    private CustomRecipeInteractor interactor;
    public CustomRecipeDataAccessInterface mockDataAccess;
    public CustomRecipeOutputBoundary mockPresenter;

    @BeforeEach
    void setUp() {
        customRecipeDataAccessMock = mock(CustomRecipeDataAccessInterface.class);
        customRecipePresenterMock = mock(CustomRecipeOutputBoundary.class);
        mockDataAccess = mock(CustomRecipeDataAccessInterface.class);
        mockPresenter = mock(CustomRecipeOutputBoundary.class);
        interactor = new CustomRecipeInteractor(customRecipeDataAccessMock, customRecipePresenterMock);
    }

    @Test
    void testConstructorAndGettersWithValidData() {
        // Arrange
        String recipeName = "Mojito";
        String recipeInstruction = "Mix all ingredients";
        List<String> ingredientNames = Arrays.asList("Rum", "Mint", "Sugar");
        List<String> ingredientMeasurements = Arrays.asList("60ml", "4 leaves", "2 tsp");
        String isAlcoholic = "Alcoholic";

        // Act
        CustomRecipeInputData inputData = new CustomRecipeInputData(
                recipeName, recipeInstruction, ingredientNames, ingredientMeasurements, isAlcoholic);

        // Assert
        assertEquals(recipeName, inputData.getRecipeName());
        assertEquals(recipeInstruction, inputData.getRecipeInstruction());
        assertEquals(ingredientNames, inputData.getIngredients());
        assertEquals(ingredientMeasurements, inputData.getMeasurements());
        assertEquals(isAlcoholic, inputData.getIsAlcoholic());
    }

    @Test
    void testConstructorWithEmptyLists() {
        // Arrange
        String recipeName = "Empty Recipe";
        String recipeInstruction = "No instructions";
        List<String> ingredientNames = Arrays.asList();
        List<String> ingredientMeasurements = Arrays.asList();
        String isAlcoholic = "Non-Alcoholic";

        // Act
        CustomRecipeInputData inputData = new CustomRecipeInputData(
                recipeName, recipeInstruction, ingredientNames, ingredientMeasurements, isAlcoholic);

        // Assert
        assertEquals(recipeName, inputData.getRecipeName());
        assertEquals(recipeInstruction, inputData.getRecipeInstruction());
        assertEquals(ingredientNames, inputData.getIngredients());
        assertEquals(ingredientMeasurements, inputData.getMeasurements());
        assertEquals(isAlcoholic, inputData.getIsAlcoholic());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String recipeName = null;
        String recipeInstruction = null;
        List<String> ingredientNames = null;
        List<String> ingredientMeasurements = null;
        String isAlcoholic = null;

        // Act
        CustomRecipeInputData inputData = new CustomRecipeInputData(
                recipeName, recipeInstruction, ingredientNames, ingredientMeasurements, isAlcoholic);

        // Assert
        assertNull(inputData.getRecipeName());
        assertNull(inputData.getRecipeInstruction());
        assertNull(inputData.getIngredients());
        assertNull(inputData.getMeasurements());
        assertNull(inputData.getIsAlcoholic());
    }

    @Test
    void testSaveCustomRecipeFailureDueToMongoSocketException() {
        // Arrange
        CustomRecipeInputData inputData = mock(CustomRecipeInputData.class);
        when(inputData.getRecipeName()).thenReturn("Fail Recipe");
        when(inputData.getRecipeInstruction()).thenReturn("Fail Instructions");
        when(inputData.getIngredients()).thenReturn(List.of("FailIngredient1"));
        when(inputData.getMeasurements()).thenReturn(List.of("1 tbsp"));
        when(inputData.getIsAlcoholic()).thenReturn("true");

        String currentUser = "testUser";
        when(mockDataAccess.getCurrentUser()).thenReturn(currentUser);

        // Simulate MongoSocketException being thrown
        doThrow(new MongoSocketException("Connection failed", null))
                .when(mockDataAccess)
                .createCustomRecipe(
                        eq(currentUser),
                        eq("Fail Recipe"),
                        eq("Fail Instructions"),
                        eq(List.of("FailIngredient1")),
                        eq(List.of("1 tbsp")),
                        eq("true")
                );

        // Act
        interactor.saveCustomRecipe(inputData);

        // Assert
        verify(mockPresenter, times(1)).prepareFailView("Failed to save custom recipe.");
        verify(mockPresenter, times(0)).updateCustomRecipeView(any(UserProfileOutputData.class));
    }
}
