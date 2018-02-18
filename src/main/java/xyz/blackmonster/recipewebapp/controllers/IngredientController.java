package xyz.blackmonster.recipewebapp.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.blackmonster.recipewebapp.commands.IngredientCommand;
import xyz.blackmonster.recipewebapp.services.IngredientService;
import xyz.blackmonster.recipewebapp.services.RecipeService;
import xyz.blackmonster.recipewebapp.services.UnitOfMeasureService;

@Slf4j
@Controller
public class IngredientController {
	
	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService unitOfMeasureService;

	@Autowired
	public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
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
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
	public String updateRecipeIngredient(@PathVariable long recipeId, @PathVariable long ingredientId, Model model) {
		log.debug("Getting ingredient and list of unit of measures by ingredient and recipe id.");

		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
		model.addAttribute("uomList", unitOfMeasureService.getAll());
		
		return "recipe/ingredient/ingredientform";
	}
	
	@PostMapping
	@RequestMapping("recipe/{recipeId}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
		
		return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
	}
}
