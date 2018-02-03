package xyz.blackmonster.recipewebapp.services;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	
	private final RecipeRepository recipeRepository;

	@Autowired
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}
	
	public List<Recipe> getAllRecipes() {
		log.info("Retrieving all recipes.");
		
		List<Recipe> recipes = new ArrayList<>();
		recipeRepository.findAll().forEach(recipes::add);
		
		return recipes;
	}
}
