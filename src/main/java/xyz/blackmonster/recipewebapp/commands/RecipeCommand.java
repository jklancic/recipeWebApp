package xyz.blackmonster.recipewebapp.commands;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import xyz.blackmonster.recipewebapp.models.Difficulty;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

	private long id;
	private String name;
	private String description;
	private int prepTime;
	private int cookTime;
	private int servings;
	private String source;
	private String url;
	private String directions;
	private byte[] image;
	private NotesCommand notes;
	private Set<IngredientCommand> ingredients = new HashSet<>();
	private Difficulty difficulty;
	private Set<CategoryCommand> categories = new HashSet<>();
}
