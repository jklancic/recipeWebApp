package xyz.blackmonster.recipewebapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.blackmonster.recipewebapp.models.Category;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;
import xyz.blackmonster.recipewebapp.repositories.CategoryRepository;
import xyz.blackmonster.recipewebapp.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {
	
	private static final String INDEX = "index";
	
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	@Autowired
	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model model) {
		Optional<Category> category = categoryRepository.findByDescription("Slovenian");
		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUom("Teaspoon");
		
		System.out.println("Category ID is:" + category.get().getId());
		System.out.println("Unit of measure ID is:" + unitOfMeasure.get().getId());
		
		model.addAttribute("value", 3);
		return INDEX;
	}
}
