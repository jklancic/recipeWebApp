package xyz.blackmonster.recipewebapp.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;

public class UnitOfMeasureCommandConverterTest {

	private UnitOfMeasureCommandConverter unitOfMeasureCommandConverter;

	private static long TEST_ID = 1L;
	private static String TEST_UNIT = "Spoon";

	@Before
	public void setUp() {
		unitOfMeasureCommandConverter = new UnitOfMeasureCommandConverter();
	}

	@Test
	public void testNullParameter() {
		assertNull(unitOfMeasureCommandConverter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(unitOfMeasureCommandConverter.convert(new UnitOfMeasure()));
	}

	@Test
	public void convert() {
		UnitOfMeasure model = new UnitOfMeasure();
		model.setId(TEST_ID);
		model.setUom(TEST_UNIT);

		UnitOfMeasureCommand command = unitOfMeasureCommandConverter.convert(model);
		assertNotNull(command);
		assertEquals(command.getId(), TEST_ID);
		assertEquals(command.getUom(), TEST_UNIT);
	}
}
