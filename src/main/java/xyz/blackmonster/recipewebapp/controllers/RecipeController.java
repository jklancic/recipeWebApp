package xyz.blackmonster.recipewebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.services.RecipeService;

@Controller
public class RecipeController {
	
	private final RecipeService recipeService;

	@Autowired
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {

		Recipe recipe = recipeService.getRecipeById(Long.valueOf(id));
		model.addAttribute("recipe", recipe);
		
		return "recipe/show";
	}
	
	@GetMapping
	@RequestMapping("recipe/new")
	public String newRecipe(Model model) {
		
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}
	
	@GetMapping
	@RequestMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable long id, Model model) {
		model.addAttribute("recipe", recipeService.findRecipeCommandById(id));
		return "recipe/recipeform";
	}
	
	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
		
		RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
		return "redirect:/recipe/show/" + savedRecipe.getId();
	}
	
	@GetMapping
	@RequestMapping("recipe/{id}/delete")
	public String delete(@PathVariable long id) {
		
		recipeService.deleteById(id);
		return "redirect:/";
	}
}
