package xyz.blackmonster.recipewebapp.converters;

import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.commands.NotesCommand;
import xyz.blackmonster.recipewebapp.models.Notes;

@Component
public class NotesCommandConverter implements Converter<Notes, NotesCommand> {
	
	@Synchronized
	@Nullable
	@Override
	public NotesCommand convert(Notes notes) {
		if(notes == null) {
			return null;
		}
		
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(notes.getId());
		notesCommand.setRecipeNotes(notes.getRecipeNotes());
		return notesCommand;
	}
}
