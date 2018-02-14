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

public class IngredientCommandConverterTest {

	private UnitOfMeasureCommandConverter unitOfMeasureCommandConverter;
	private IngredientCommandConverter ingredientCommandConverter;

	private static final long TEST_ID = 1L;
	private static final float TEST_AMOUNT = 1.0f;
	private static final String TEST_UNIT = "Spoon";
	private static final String TEST_DESCRIPTION = "Oil";

	@Before
	public void setUp() {
		unitOfMeasureCommandConverter = new UnitOfMeasureCommandConverter();
		ingredientCommandConverter = new IngredientCommandConverter(unitOfMeasureCommandConverter);
	}

	@Test
	public void testNullParameter() {
		assertNull(ingredientCommandConverter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(ingredientCommandConverter.convert(new Ingredient()));
	}

	@Test
	public void convert() {
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(TEST_ID);
		unitOfMeasure.setUom(TEST_UNIT);
		Ingredient model = new Ingredient();
		model.setId(TEST_ID);
		model.setDescription(TEST_DESCRIPTION);
		model.setAmount(TEST_AMOUNT);
		model.setUnitOfMeasure(unitOfMeasure);

		IngredientCommand command = ingredientCommandConverter.convert(model);
		assertNotNull(command);
		assertEquals(command.getId(), TEST_ID);
		assertEquals(command.getDescription(), TEST_DESCRIPTION);
		assertEquals(command.getAmount(), TEST_AMOUNT, 0);
		UnitOfMeasureCommand unitOfMeasureCommand = command.getUnitOfMeasureCommand();
		assertNotNull(unitOfMeasureCommand);
		assertEquals(unitOfMeasureCommand.getUom(), TEST_UNIT);
	}
}
