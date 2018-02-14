package xyz.blackmonster.recipewebapp.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;

public class UnitOfMeasureConverterTest {
	
	private UnitOfMeasureConverter unitOfMeasureConverter;
	
	private static long TEST_ID = 1L;
	private static String TEST_UNIT = "Spoon";
	
	@Before
	public void setUp() {
		unitOfMeasureConverter = new UnitOfMeasureConverter();
	}
	
	@Test
	public void testNullParameter() {
		assertNull(unitOfMeasureConverter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(unitOfMeasureConverter.convert(new UnitOfMeasureCommand()));
	}
	
	@Test
	public void convert() {
		UnitOfMeasureCommand command = new UnitOfMeasureCommand();
		command.setId(TEST_ID);
		command.setUom(TEST_UNIT);

		UnitOfMeasure model = unitOfMeasureConverter.convert(command);
		assertNotNull(model);
		assertEquals(model.getId(), TEST_ID);
		assertEquals(model.getUom(), TEST_UNIT);
	}
}
