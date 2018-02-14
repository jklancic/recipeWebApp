package xyz.blackmonster.recipewebapp.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.models.Recipe;

@Component
public class RecipeConverter implements Converter<RecipeCommand, Recipe> {

	private final CategoryConverter categoryConverter;
	private final IngredientConverter ingredientConverter;
	private final NotesConverter notesConverter;

	@Autowired
	public RecipeConverter(CategoryConverter categoryConverter, IngredientConverter ingredientConverter, NotesConverter notesConverter) {
		this.categoryConverter = categoryConverter;
		this.ingredientConverter = ingredientConverter;
		this.notesConverter = notesConverter;
	}

	@Override
	public Recipe convert(RecipeCommand recipeCommand) {
		if(recipeCommand == null) {
			return null;
		}
		
		Recipe recipe = new Recipe();
		recipe.setId(recipeCommand.getId());
		recipe.setCookTime(recipeCommand.getCookTime());
		recipe.setPrepTime(recipeCommand.getPrepTime());
		recipe.setDescription(recipeCommand.getDescription());
		recipe.setDifficulty(recipeCommand.getDifficulty());
		recipe.setDirections(recipeCommand.getDirections());
		recipe.setServings(recipeCommand.getServings());
		recipe.setSource(recipeCommand.getSource());
		recipe.setUrl(recipeCommand.getUrl());
		recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));
		recipe.setImage(recipeCommand.getImage());
		recipe.setName(recipeCommand.getName());

		if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
			recipeCommand.getCategories()
				.forEach( category -> recipe.getCategories().add(categoryConverter.convert(category)));
		}

		if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
			recipeCommand.getIngredients()
				.forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
		}

		return recipe;
	}
}
