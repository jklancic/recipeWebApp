package xyz.blackmonster.recipewebapp.services;

import xyz.blackmonster.recipewebapp.commands.RecipeCommand;

public interface IngredientService {
	
	RecipeCommand listIngredients(long recipeId);
}
