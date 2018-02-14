package xyz.blackmonster.recipewebapp.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import xyz.blackmonster.recipewebapp.commands.NotesCommand;
import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.models.Notes;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;

public class NotesConverterTest {

	private NotesConverter notesConverter;

	private static long TEST_ID = 1L;
	private static String TEST_NOTES = "Brief recipe notes";

	@Before
	public void setUp() {
		notesConverter = new NotesConverter();
	}

	@Test
	public void testNullParameter() {
		assertNull(notesConverter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(notesConverter.convert(new NotesCommand()));
	}

	@Test
	public void convert() {
		NotesCommand command = new NotesCommand();
		command.setId(TEST_ID);
		command.setRecipeNotes(TEST_NOTES);

		Notes model = notesConverter.convert(command);
		assertNotNull(model);
		assertEquals(model.getId(), TEST_ID);
		assertEquals(model.getRecipeNotes(), TEST_NOTES);
	}
}
