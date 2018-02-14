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
import xyz.blackmonster.recipewebapp.models.Difficulty;
import xyz.blackmonster.recipewebapp.models.Notes;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;

public class RecipeConverterTest {

	private UnitOfMeasureConverter unitOfMeasureConverter;
	private CategoryConverter categoryConverter;
	private IngredientConverter ingredientConverter;
	private NotesConverter notesConverter;
	private RecipeConverter recipeConverter;

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
		unitOfMeasureConverter = new UnitOfMeasureConverter();
		categoryConverter = new CategoryConverter();
		ingredientConverter = new IngredientConverter(unitOfMeasureConverter);
		notesConverter = new NotesConverter();
		recipeConverter = new RecipeConverter(categoryConverter, ingredientConverter, notesConverter);
	}

	@Test
	public void testNullParameter() {
		assertNull(recipeConverter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(recipeConverter.convert(new RecipeCommand()));
	}

	@Test
	public void convert() {
		UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
		unitOfMeasureCommand.setId(TEST_ID);
		unitOfMeasureCommand.setUom(TEST_UNIT);
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(TEST_ID);
		ingredientCommand.setDescription(TEST_INGREDIENT);
		ingredientCommand.setAmount(TEST_AMOUNT);
		ingredientCommand.setUnitOfMeasureCommand(unitOfMeasureCommand);
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(TEST_ID);
		categoryCommand.setDescription(TEST_CATEGORY);
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(TEST_ID);
		notesCommand.setRecipeNotes(TEST_NOTES);
		RecipeCommand command = new RecipeCommand();
		command.setId(TEST_ID);
		command.setCookTime(TEST_COOK_TIME);
		command.setDifficulty(Difficulty.EASY);
		command.setDescription(TEST_DESCRIPTION);
		command.setDirections(TEST_DIRECTIONS);
		command.setName(TEST_NAME);
		command.setPrepTime(TEST_PREP_TIME);
		command.setServings(TEST_SERVINGS);
		command.setSource(TEST_SOURCE);
		command.setNotes(notesCommand);
		command.setUrl(TEST_URL);
		command.setCategories(Collections.singleton(categoryCommand));
		command.setIngredients(Collections.singleton(ingredientCommand));
		command.setImage(new byte[1]);

		Recipe model = recipeConverter.convert(command);
		assertNotNull(model);
		assertEquals(model.getId(), TEST_ID);
		assertEquals(model.getCookTime(), TEST_COOK_TIME);
		assertEquals(model.getDifficulty(), Difficulty.EASY);
		assertEquals(model.getDescription(), TEST_DESCRIPTION);
		assertEquals(model.getDirections(), TEST_DIRECTIONS);
		assertEquals(model.getName(), TEST_NAME);
		assertEquals(model.getPrepTime(), TEST_PREP_TIME);
		assertEquals(model.getServings(), TEST_SERVINGS);
		assertEquals(model.getSource(), TEST_SOURCE);
		Notes notesModel = model.getNotes();
		assertNotNull(notesModel);
		assertEquals(notesModel.getId(), TEST_ID);
		assertEquals(notesModel.getRecipeNotes(), TEST_NOTES);
		assertEquals(model.getUrl(), TEST_URL);
		model.getCategories().forEach(categoryModel -> {
			assertNotNull(categoryModel);
			assertEquals(categoryModel.getId(), TEST_ID);
			assertEquals(categoryModel.getDescription(), TEST_CATEGORY);
		});
		model.getIngredients().forEach(ingredientModel -> {
			assertNotNull(ingredientModel);
			assertEquals(ingredientModel.getId(), TEST_ID);
			assertEquals(ingredientModel.getDescription(), TEST_INGREDIENT);
			assertEquals(ingredientModel.getAmount(), TEST_AMOUNT, 0);
			UnitOfMeasure unitOfMeasureModel = ingredientModel.getUnitOfMeasure();
			assertNotNull(unitOfMeasureModel);
			assertEquals(unitOfMeasureModel.getUom(), TEST_UNIT);
		});
		assertNotNull(model.getImage());
	}
}
