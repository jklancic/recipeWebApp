package xyz.blackmonster.recipewebapp.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.models.Recipe;

public interface RecipeService {
	
	List<Recipe> getAllRecipes();
	
	Recipe getRecipeById(long id) throws EntityNotFoundException;
	
	RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
