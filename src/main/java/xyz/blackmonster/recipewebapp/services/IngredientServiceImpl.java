package xyz.blackmonster.recipewebapp.services;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.blackmonster.recipewebapp.commands.IngredientCommand;
import xyz.blackmonster.recipewebapp.converters.IngredientCommandConverter;
import xyz.blackmonster.recipewebapp.converters.IngredientConverter;
import xyz.blackmonster.recipewebapp.models.Ingredient;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.repositories.IngredientRepository;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;
import xyz.blackmonster.recipewebapp.repositories.UnitOfMeasureRepository;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
	
	private final IngredientRepository ingredientRepository;
	private final IngredientConverter ingredientConverter;
	private final IngredientCommandConverter ingredientCommandConverter;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	@Autowired
	public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientConverter ingredientConverter, 
															 IngredientCommandConverter ingredientCommandConverter, RecipeRepository recipeRepository, 
															 UnitOfMeasureRepository unitOfMeasureRepository) {
		this.ingredientRepository = ingredientRepository;
		this.ingredientConverter = ingredientConverter;
		this.ingredientCommandConverter = ingredientCommandConverter;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent()){
			//todo impl error handling
			log.error("recipe id not found. Id: " + recipeId);
		}

		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
			.filter(ingredient -> ingredient.getId() == ingredientId)
			.map( ingredient -> ingredientCommandConverter.convert(ingredient)).findFirst();

		if(!ingredientCommandOptional.isPresent()){
			//todo impl error handling
			log.error("Ingredient id not found: " + ingredientId);
		}

		return ingredientCommandOptional.get();
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

		if(!recipeOptional.isPresent()){

			//todo toss error if not found!
			log.error("Recipe not found for id: " + command.getRecipeId());
			return new IngredientCommand();
		} else {
			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe
				.getIngredients()
				.stream()
				.filter(ingredient -> ingredient.getId() == command.getId())
				.findFirst();

			if(ingredientOptional.isPresent()){
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
					.findById(command.getUnitOfMeasureCommand().getId())
					.orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
			} else {
				//add new Ingredient
				recipe.addIngredient(ingredientConverter.convert(command));
			}

			Recipe savedRecipe = recipeRepository.save(recipe);

			//to do check for fail
			return ingredientCommandConverter.convert(savedRecipe.getIngredients().stream()
				.filter(recipeIngredients -> recipeIngredients.getId() == command.getId())
				.findFirst()
				.get());
		}
	}
}
