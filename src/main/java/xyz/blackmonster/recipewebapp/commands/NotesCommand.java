package xyz.blackmonster.recipewebapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {

	private long id;
	private String recipeNotes;
}
