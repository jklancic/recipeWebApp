package xyz.blackmonster.recipewebapp.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import xyz.blackmonster.recipewebapp.commands.NotesCommand;
import xyz.blackmonster.recipewebapp.models.Notes;

public class NotesCommandConverterTest {

	private NotesCommandConverter notesCommandConverter;

	private static long TEST_ID = 1L;
	private static String TEST_NOTES = "Brief recipe notes";

	@Before
	public void setUp() {
		notesCommandConverter = new NotesCommandConverter();
	}

	@Test
	public void testNullParameter() {
		assertNull(notesCommandConverter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(notesCommandConverter.convert(new Notes()));
	}

	@Test
	public void convert() {
		Notes model = new Notes();
		model.setId(TEST_ID);
		model.setRecipeNotes(TEST_NOTES);

		NotesCommand command = notesCommandConverter.convert(model);
		assertNotNull(command);
		assertEquals(command.getId(), TEST_ID);
		assertEquals(command.getRecipeNotes(), TEST_NOTES);
	}
}
