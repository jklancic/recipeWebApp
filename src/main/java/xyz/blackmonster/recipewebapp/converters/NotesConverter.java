package xyz.blackmonster.recipewebapp.converters;

import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.commands.NotesCommand;
import xyz.blackmonster.recipewebapp.models.Notes;

@Component
public class NotesConverter implements Converter<NotesCommand, Notes> {
	
	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand notesCommand) {
		if(notesCommand == null) {
			return null;
		}
		
		Notes notes = new Notes();
		notes.setId(notesCommand.getId());
		notes.setRecipeNotes(notesCommand.getRecipeNotes());
		return notes;
	}
}
