package xyz.blackmonster.recipewebapp.services;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.blackmonster.recipewebapp.commands.IngredientCommand;
import xyz.blackmonster.recipewebapp.converters.IngredientCommandConverter;
import xyz.blackmonster.recipewebapp.converters.IngredientConverter;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.repositories.IngredientRepository;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
	
	private final IngredientRepository ingredientRepository;
	private final IngredientConverter ingredientConverter;
	private final IngredientCommandConverter ingredientCommandConverter;
	private final RecipeRepository recipeRepository;

	@Autowired
	public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientConverter ingredientConverter, IngredientCommandConverter ingredientCommandConverter, RecipeRepository recipeRepository) {
		this.ingredientRepository = ingredientRepository;
		this.ingredientConverter = ingredientConverter;
		this.ingredientCommandConverter = ingredientCommandConverter;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId) {
		log.debug("Getting ingredient by ingredient id {} and recipe id {}.", ingredientId, recipeId);

		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		if(!recipe.isPresent()) {
			log.error("Recipe with the id {} was not found.", recipeId);
		}
		
		Optional<IngredientCommand> commandOptional = 
			recipe.get().getIngredients().stream().filter(
				ingredient -> ingredient.getId() == ingredientId).map(ingredientCommandConverter::convert).findFirst();
		
		if(!commandOptional.isPresent()) {
			log.error("Ingredient with the id {} was not listed in recipe with id {}.", ingredientId, recipeId);
		}
		
		return commandOptional.get();
	}
}
