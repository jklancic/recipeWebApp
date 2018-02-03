package xyz.blackmonster.recipewebapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	private final RecipeRepository recipeRepository;

	@Autowired
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}
	
	public List<Recipe> getAllRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		recipeRepository.findAll().forEach(recipes::add);
		
		return recipes;
	}
}
