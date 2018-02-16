package xyz.blackmonster.recipewebapp.services;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.converters.IngredientCommandConverter;
import xyz.blackmonster.recipewebapp.converters.IngredientConverter;
import xyz.blackmonster.recipewebapp.repositories.IngredientRepository;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
	
	private final IngredientRepository ingredientRepository;
	private final IngredientConverter ingredientConverter;
	private final IngredientCommandConverter ingredientCommandConverter;
	private final RecipeService recipeService;

	@Autowired
	public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientConverter ingredientConverter, IngredientCommandConverter ingredientCommandConverter, RecipeService recipeService) {
		this.ingredientRepository = ingredientRepository;
		this.ingredientConverter = ingredientConverter;
		this.ingredientCommandConverter = ingredientCommandConverter;
		this.recipeService = recipeService;
	}

	@Override
	public RecipeCommand listIngredients(long recipeId) {
		log.debug("Getting recipe by recipe id: " + recipeId);
		
		return recipeService.findRecipeCommandById(recipeId);
	}
}
