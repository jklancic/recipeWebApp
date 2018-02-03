package xyz.blackmonster.recipewebapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.blackmonster.recipewebapp.models.Category;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;
import xyz.blackmonster.recipewebapp.repositories.CategoryRepository;
import xyz.blackmonster.recipewebapp.repositories.UnitOfMeasureRepository;
import xyz.blackmonster.recipewebapp.services.RecipeService;

@Controller
public class IndexController {
	
	private static final String INDEX = "index";
	
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	private RecipeService recipeService;

	@Autowired
	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, 
												 RecipeService recipeService) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model model) {
		Optional<Category> category = categoryRepository.findByDescription("Slovenian");
		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUom("Teaspoon");
		
		System.out.println("Category ID is:" + category.get().getId());
		System.out.println("Unit of measure ID is:" + unitOfMeasure.get().getId());

		List<Recipe> recipes = recipeService.getAllRecipies();
		model.addAttribute("recipes", recipes);
		return INDEX;
	}
}
