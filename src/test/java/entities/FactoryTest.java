package entities;

import entities.recipe.*;
import entities.recipe.factory.RecipeFactory;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CocktailRecipeTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String name = "Mojito";
        int id = 11000;
        String instruction = "Mix all ingredients";
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Light rum", "2-3 oz "),
                new Ingredient("Lime", "Juice of 1 "),
                new Ingredient("Sugar", "2 tsp "),
                new Ingredient("Mint", "2-4 "),
                new Ingredient("Soda Water", "")
        );
        String imageLink = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg";
        String videoLink = "";
        String isAlcoholic = "Alcoholic";

        // Act
        CocktailRecipe recipe = new CocktailRecipe(name, id, instruction, ingredients,
                imageLink, videoLink, isAlcoholic);

        // Assert
        assertEquals(name, recipe.getName());
        assertEquals(id, recipe.getId());
        assertEquals(instruction, recipe.getInstruction());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(imageLink, recipe.getImageLink());
        assertEquals(videoLink, recipe.getVideoLink());
        assertEquals(isAlcoholic, recipe.getIsAlcoholic());
        assertEquals("Name: Mojito", recipe.toString());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String name = null;
        int id = 0;
        String instruction = null;
        List<Ingredient> ingredients = null;
        String imageLink = null;
        String videoLink = null;
        String isAlcoholic = null;

        // Act
        CocktailRecipe recipe = new CocktailRecipe(name, id, instruction, ingredients,
                imageLink, videoLink, isAlcoholic);

        // Assert
        assertNull(recipe.getName());
        assertEquals(0, recipe.getId());
        assertNull(recipe.getInstruction());
        assertNull(recipe.getIngredients());
        assertNull(recipe.getImageLink());
        assertNull(recipe.getVideoLink());
        assertNull(recipe.getIsAlcoholic());
        assertEquals("Name: null", recipe.toString());
    }
}

class IngredientTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String name = "Sugar";
        String measure = "2 tsp";

        // Act
        Ingredient ingredient = new Ingredient(name, measure);

        // Assert
        assertEquals(name, ingredient.getName());
        assertEquals(measure, ingredient.getMeasure());
        assertEquals("Ingredient{name='Sugar', measure='2 tsp'}", ingredient.toString());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String name = null;
        String measure = null;

        // Act
        Ingredient ingredient = new Ingredient(name, measure);

        // Assert
        assertNull(ingredient.getName());
        assertNull(ingredient.getMeasure());
        assertEquals("Ingredient{name='null', measure='null'}", ingredient.toString());
    }
}

class CocktailFactoryTest {
    @Test
    void testCreate() {
        // Arrange
        RecipeFactory factory = new RecipeFactory();
        String name = "Margarita";
        int id = 123;
        String instruction = "Mix tequila";
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Tequila", "60ml"),
                new Ingredient("Lime", "1 piece")
        );
        String imageLink = "margarita.jpg";
        String videoLink = "margarita.mp4";
        String isAlcoholic = "Alcoholic";

        // Act
        Recipe recipe = factory.create(name, id, instruction, ingredients,
                imageLink, videoLink, isAlcoholic, "cocktail");

        // Assert
        assertInstanceOf(CocktailRecipe.class, recipe);
        assertEquals(name, recipe.getName());
        assertEquals(id, recipe.getId());
        assertEquals(instruction, recipe.getInstruction());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(imageLink, recipe.getImageLink());
        assertEquals(videoLink, recipe.getVideoLink());
        assertEquals(isAlcoholic, recipe.getIsAlcoholic());
    }

    @Test
    void testCreateInvalidType() {
        // Arrange
        RecipeFactory factory = new RecipeFactory();
        String name = "Margarita";
        int id = 123;
        String instruction = "Mix tequila";
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Tequila", "60ml"),
                new Ingredient("Lime", "1 piece")
        );
        String imageLink = "margarita.jpg";
        String videoLink = "margarita.mp4";
        String isAlcoholic = "Alcoholic";
        String invalidType = "unknown";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                factory.create(name, id, instruction, ingredients, imageLink, videoLink, isAlcoholic, invalidType)
        );

        assertEquals("Invalid type of recipe", exception.getMessage());
    }
}

