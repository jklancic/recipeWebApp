package xyz.blackmonster.recipewebapp.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.blackmonster.recipewebapp.services.IngredientService;
import xyz.blackmonster.recipewebapp.services.RecipeService;

@Slf4j
@Controller
public class IngredientController {
	
	private final RecipeService recipeService;
	private final IngredientService ingredientService;

	@Autowired
	public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable long recipeId, Model model) {
		log.debug("Getting ingredient list for recipe id: " + recipeId);
		
		model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));
		
		return "recipe/ingredient/list";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
	public String showRecipeIngredient(@PathVariable long recipeId, @PathVariable long ingredientId, Model model) {
		log.debug("Getting ingredient by ingredient and recipe id.");
		
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
		
		return "recipe/ingredient/show";
	}
}
