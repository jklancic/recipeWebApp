package xyz.blackmonster.recipewebapp.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import xyz.blackmonster.recipewebapp.commands.CategoryCommand;
import xyz.blackmonster.recipewebapp.commands.IngredientCommand;
import xyz.blackmonster.recipewebapp.commands.NotesCommand;
import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.models.Category;
import xyz.blackmonster.recipewebapp.models.Difficulty;
import xyz.blackmonster.recipewebapp.models.Ingredient;
import xyz.blackmonster.recipewebapp.models.Notes;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;

public class RecipeCommandConverterTest {

	private UnitOfMeasureCommandConverter unitOfMeasureCommandConverter;
	private CategoryCommandConverter categoryCommandConverter;
	private IngredientCommandConverter ingredientCommandConverter;
	private NotesCommandConverter notesCommandConverter;
	private RecipeCommandConverter recipeCommandConverter;

	private static final long TEST_ID = 1L;
	private static final int TEST_COOK_TIME = 1;
	private static final int TEST_PREP_TIME = 1;
	private static final int TEST_SERVINGS = 1;
	private static final float TEST_AMOUNT = 1.0f;
	private static final String TEST_UNIT = "Spoon";
	private static final String TEST_INGREDIENT = "Oil";
	private static final String TEST_CATEGORY = "Soup";
	private static final String TEST_NOTES = "Some brief notes";
	private static final String TEST_DIRECTIONS = "Soup needs to boil.";
	private static final String TEST_DESCRIPTION = "Beef soup";
	private static final String TEST_NAME = "Homemade beef soup";
	private static final String TEST_SOURCE = "Recipes.com";
	private static final String TEST_URL = "www.recipes.com";

	@Before
	public void setUp() {
		unitOfMeasureCommandConverter = new UnitOfMeasureCommandConverter();
		categoryCommandConverter = new CategoryCommandConverter();
		ingredientCommandConverter = new IngredientCommandConverter(unitOfMeasureCommandConverter);
		notesCommandConverter = new NotesCommandConverter();
		recipeCommandConverter = new RecipeCommandConverter(
			categoryCommandConverter, ingredientCommandConverter, notesCommandConverter);
	}

	@Test
	public void testNullParameter() {
		assertNull(recipeCommandConverter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(recipeCommandConverter.convert(new Recipe()));
	}

	@Test
	public void convert() {
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(TEST_ID);
		unitOfMeasure.setUom(TEST_UNIT);
		Ingredient ingredient = new Ingredient();
		ingredient.setId(TEST_ID);
		ingredient.setDescription(TEST_INGREDIENT);
		ingredient.setAmount(TEST_AMOUNT);
		ingredient.setUnitOfMeasure(unitOfMeasure);
		Category category = new Category();
		category.setId(TEST_ID);
		category.setDescription(TEST_CATEGORY);
		Notes notes = new Notes();
		notes.setId(TEST_ID);
		notes.setRecipeNotes(TEST_NOTES);
		Recipe model = new Recipe();
		model.setId(TEST_ID);
		model.setCookTime(TEST_COOK_TIME);
		model.setDifficulty(Difficulty.EASY);
		model.setDescription(TEST_DESCRIPTION);
		model.setDirections(TEST_DIRECTIONS);
		model.setName(TEST_NAME);
		model.setPrepTime(TEST_PREP_TIME);
		model.setServings(TEST_SERVINGS);
		model.setSource(TEST_SOURCE);
		model.setNotes(notes);
		model.setUrl(TEST_URL);
		model.setCategories(Collections.singleton(category));
		model.setIngredients(Collections.singleton(ingredient));
		model.setImage(new byte[1]);

		RecipeCommand command = recipeCommandConverter.convert(model);
		assertNotNull(command);
		assertEquals(command.getId(), TEST_ID);
		assertEquals(command.getCookTime(), TEST_COOK_TIME);
		assertEquals(command.getDifficulty(), Difficulty.EASY);
		assertEquals(command.getDescription(), TEST_DESCRIPTION);
		assertEquals(command.getDirections(), TEST_DIRECTIONS);
		assertEquals(command.getName(), TEST_NAME);
		assertEquals(command.getPrepTime(), TEST_PREP_TIME);
		assertEquals(command.getServings(), TEST_SERVINGS);
		assertEquals(command.getSource(), TEST_SOURCE);
		NotesCommand notesCommand = command.getNotes();
		assertNotNull(notesCommand);
		assertEquals(notesCommand.getId(), TEST_ID);
		assertEquals(notesCommand.getRecipeNotes(), TEST_NOTES);
		assertEquals(command.getUrl(), TEST_URL);
		command.getCategories().forEach(categoryCommand -> {
			assertNotNull(categoryCommand);
			assertEquals(categoryCommand.getId(), TEST_ID);
			assertEquals(categoryCommand.getDescription(), TEST_CATEGORY);
		});
		command.getIngredients().forEach(ingredientCommand -> {
			assertNotNull(ingredientCommand);
			assertEquals(ingredientCommand.getId(), TEST_ID);
			assertEquals(ingredientCommand.getDescription(), TEST_INGREDIENT);
			assertEquals(ingredientCommand.getAmount(), TEST_AMOUNT, 0);
			UnitOfMeasureCommand unitOfMeasureCommand = ingredientCommand.getUnitOfMeasureCommand();
			assertNotNull(unitOfMeasureCommand);
			assertEquals(unitOfMeasureCommand.getUom(), TEST_UNIT);
		});
		assertNotNull(command.getImage());
	}
}
