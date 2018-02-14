package xyz.blackmonster.recipewebapp.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import xyz.blackmonster.recipewebapp.commands.CategoryCommand;
import xyz.blackmonster.recipewebapp.models.Category;

public class CategoryCommandConverterTest {
	
	private CategoryCommandConverter categoryCommandConverter;
	
	private static final long TEST_ID = 1L;
	private static final String TEST_DESCRIPTION = "Soup";
	
	@Before
	public void setUp() {
		categoryCommandConverter = new CategoryCommandConverter();
	}

	@Test
	public void testNullParameter() {
		assertNull(categoryCommandConverter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(categoryCommandConverter.convert(new Category()));
	}

	@Test
	public void convert() {
		Category model = new Category();
		model.setId(TEST_ID);
		model.setDescription(TEST_DESCRIPTION);

		CategoryCommand command = categoryCommandConverter.convert(model);
		assertNotNull(command);
		assertEquals(command.getId(), TEST_ID);
		assertEquals(command.getDescription(), TEST_DESCRIPTION);
	}
}
