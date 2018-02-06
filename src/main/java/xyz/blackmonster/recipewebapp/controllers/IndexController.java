package xyz.blackmonster.recipewebapp.controllers;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.services.RecipeService;

@Slf4j
@Controller
public class IndexController {
	
	public static final String INDEX = "index";
	
	private final RecipeService recipeService;

	@Autowired
	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model model) {
		log.debug("Retrieving index page.");
		
		List<Recipe> recipes = recipeService.getAllRecipes();
		model.addAttribute("recipes", recipes);
		
		return INDEX;
	}
}
