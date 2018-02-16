package xyz.blackmonster.recipewebapp.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.blackmonster.recipewebapp.services.IngredientService;

@Slf4j
@Controller
public class IngredientController {
	
	private final IngredientService ingredientService;

	@Autowired
	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable long recipeId, Model model) {
		log.debug("Getting ingredient list for recipe id: " + recipeId);
		
		model.addAttribute("recipe", ingredientService.listIngredients(recipeId));
		
		return "recipe/ingredient/list";
	}
}
