package xyz.blackmonster.recipewebapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;

@Service
public class RecipeService {
	
	private RecipeRepository recipeRepository;

	@Autowired
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}
	
	public List<Recipe> getAllRecipies() {
		List<Recipe> recipes = new ArrayList<>();
		recipeRepository.findAll().forEach(recipe -> recipes.add(recipe));
		
		return recipes;
	}
}
