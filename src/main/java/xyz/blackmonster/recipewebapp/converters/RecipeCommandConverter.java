package xyz.blackmonster.recipewebapp.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.models.Category;
import xyz.blackmonster.recipewebapp.models.Recipe;

@Component
public class RecipeCommandConverter implements Converter<Recipe, RecipeCommand> {

	private final CategoryCommandConverter categoryCommandConverter;
	private final IngredientCommandConverter ingredientCommandConverter;
	private final NotesCommandConverter notesCommandConverter;

	@Autowired
	public RecipeCommandConverter(CategoryCommandConverter categoryCommandConverter, IngredientCommandConverter ingredientCommandConverter, NotesCommandConverter notesCommandConverter) {
		this.categoryCommandConverter = categoryCommandConverter;
		this.ingredientCommandConverter = ingredientCommandConverter;
		this.notesCommandConverter = notesCommandConverter;
	}

	@Override
	public RecipeCommand convert(Recipe recipe) {
		if(recipe == null) {
			return null;
		}
		
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(recipe.getId());
		recipeCommand.setCookTime(recipe.getCookTime());
		recipeCommand.setPrepTime(recipe.getPrepTime());
		recipeCommand.setDescription(recipe.getDescription());
		recipeCommand.setDifficulty(recipe.getDifficulty());
		recipeCommand.setDirections(recipe.getDirections());
		recipeCommand.setServings(recipe.getServings());
		recipeCommand.setSource(recipe.getSource());
		recipeCommand.setUrl(recipe.getUrl());
		recipeCommand.setNotes(notesCommandConverter.convert(recipe.getNotes()));
		recipeCommand.setImage(recipe.getImage());
		recipeCommand.setName(recipe.getName());

		if (recipe.getCategories() != null && recipe.getCategories().size() > 0){
			recipe.getCategories()
				.forEach((Category category) -> recipeCommand.getCategories().add(categoryCommandConverter.convert(category)));
		}

		if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
			recipe.getIngredients()
				.forEach(ingredient -> recipeCommand.getIngredients().add(ingredientCommandConverter.convert(ingredient)));
		}
		
		return recipeCommand;
	}
}
