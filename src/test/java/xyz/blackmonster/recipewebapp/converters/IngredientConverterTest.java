package xyz.blackmonster.recipewebapp.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import xyz.blackmonster.recipewebapp.commands.IngredientCommand;
import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.models.Ingredient;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;

public class IngredientConverterTest {

	private UnitOfMeasureConverter unitOfMeasureConverter;
	private IngredientConverter ingredientConverter;

	private static final long TEST_ID = 1L;
	private static final float TEST_AMOUNT = 1.0f;
	private static final String TEST_UNIT = "Spoon";
	private static final String TEST_DESCRIPTION = "Oil";

	@Before
	public void setUp() {
		unitOfMeasureConverter = new UnitOfMeasureConverter();
		ingredientConverter = new IngredientConverter(unitOfMeasureConverter);
	}

	@Test
	public void testNullParameter() {
		assertNull(ingredientConverter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(ingredientConverter.convert(new IngredientCommand()));
	}

	@Test
	public void convert() {
		UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
		unitOfMeasureCommand.setId(TEST_ID);
		unitOfMeasureCommand.setUom(TEST_UNIT);
		IngredientCommand command = new IngredientCommand();
		command.setId(TEST_ID);
		command.setDescription(TEST_DESCRIPTION);
		command.setAmount(TEST_AMOUNT);
		command.setUnitOfMeasureCommand(unitOfMeasureCommand);

		Ingredient model = ingredientConverter.convert(command);
		assertNotNull(model);
		assertEquals(model.getId(), TEST_ID);
		assertEquals(model.getDescription(), TEST_DESCRIPTION);
		assertEquals(model.getAmount(), TEST_AMOUNT, 0);
		UnitOfMeasure unitOfMeasureModel = model.getUnitOfMeasure();
		assertNotNull(unitOfMeasureModel);
		assertEquals(unitOfMeasureModel.getUom(), TEST_UNIT);
	}
}
