package xyz.blackmonster.recipewebapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.converters.RecipeCommandConverter;
import xyz.blackmonster.recipewebapp.converters.RecipeConverter;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	
	private final RecipeRepository recipeRepository;
	private final RecipeConverter recipeConverter;
	private final RecipeCommandConverter recipeCommandConverter;

	@Autowired
	public RecipeServiceImpl(RecipeRepository recipeRepository, 
													 RecipeConverter recipeConverter, RecipeCommandConverter recipeCommandConverter) {
		this.recipeRepository = recipeRepository;
		this.recipeConverter = recipeConverter;
		this.recipeCommandConverter = recipeCommandConverter;
	}
	
	@Override
	public List<Recipe> getAllRecipes() {
		log.info("Retrieving all recipes.");
		
		List<Recipe> recipes = new ArrayList<>();
		recipeRepository.findAll().forEach(recipes::add);
		
		return recipes;
	}
	
	@Override
	public Recipe getRecipeById(long id) throws EntityNotFoundException {
		log.info("Retrieving recipe by ID.");
		
		Optional<Recipe> optional = recipeRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new EntityNotFoundException("Recipe not found.");
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe detachedRecipe = recipeConverter.convert(recipeCommand);
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		log.debug("Saved recipe with ID={}", savedRecipe.getId());
		
		return recipeCommandConverter.convert(savedRecipe);
	}
}
