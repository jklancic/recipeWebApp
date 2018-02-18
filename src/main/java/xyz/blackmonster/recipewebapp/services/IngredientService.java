package xyz.blackmonster.recipewebapp.services;

import xyz.blackmonster.recipewebapp.commands.IngredientCommand;

public interface IngredientService {
	
	IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId);
	
	IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
