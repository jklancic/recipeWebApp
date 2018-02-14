package xyz.blackmonster.recipewebapp.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import xyz.blackmonster.recipewebapp.commands.CategoryCommand;
import xyz.blackmonster.recipewebapp.models.Category;

public class CategoryConverterTest {

	private CategoryConverter categoryConverter;

	private static final long TEST_ID = 1L;
	private static final String TEST_DESCRIPTION = "Soup";

	@Before
	public void setUp() {
		categoryConverter = new CategoryConverter();
	}

	@Test
	public void testNullParameter() {
		assertNull(categoryConverter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(categoryConverter.convert(new CategoryCommand()));
	}

	@Test
	public void convert() {
		CategoryCommand command = new CategoryCommand();
		command.setId(TEST_ID);
		command.setDescription(TEST_DESCRIPTION);

		Category model = categoryConverter.convert(command);
		assertNotNull(model);
		assertEquals(model.getId(), TEST_ID);
		assertEquals(model.getDescription(), TEST_DESCRIPTION);
	}
}
