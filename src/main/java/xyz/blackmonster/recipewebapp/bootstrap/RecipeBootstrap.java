package xyz.blackmonster.recipewebapp.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.models.Category;
import xyz.blackmonster.recipewebapp.models.Ingredient;
import xyz.blackmonster.recipewebapp.models.Notes;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;
import xyz.blackmonster.recipewebapp.repositories.CategoryRepository;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;
import xyz.blackmonster.recipewebapp.repositories.UnitOfMeasureRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private RecipeRepository recipeRepository;
	
	private CategoryRepository categoryRepository;
	
	private UnitOfMeasureRepository unitOfMeasureRepository;

	@Autowired
	public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	private List<Recipe> initData() {

		UnitOfMeasure ounce = unitOfMeasureRepository.findByUom("Ounce").get();
		UnitOfMeasure dash = unitOfMeasureRepository.findByUom("Dash").get();
		UnitOfMeasure tablespoon = unitOfMeasureRepository.findByUom("Tablespoon").get();
		UnitOfMeasure slice = unitOfMeasureRepository.findByUom("Slice").get();
		UnitOfMeasure cup = unitOfMeasureRepository.findByUom("Cup").get();

		Category slovenian = categoryRepository.findByDescription("Slovenian").get();
		Category italian = categoryRepository.findByDescription("Italian").get();
		Category american = categoryRepository.findByDescription("American").get();
		
		Recipe recipe1 = new Recipe();
		recipe1.setName("Oven baked salmon");
		recipe1.setDescription("Slowly baked salmon in olive oil and garlic.");
		recipe1.setCookTime(45);
		recipe1.setPrepTime(5);
		recipe1.setServings(2);
		recipe1.setDirections("First glaze oil on the salmon, add the cut garlic and leave the salmon in the oven at 250 degrees.");
		recipe1.getCategories().add(slovenian);
		recipe1.getCategories().add(italian);
		recipe1.getIngredients().add(new Ingredient("Salmon", 8, ounce, recipe1));
		recipe1.getIngredients().add(new Ingredient("Salt", 1, dash, recipe1));
		recipe1.getIngredients().add(new Ingredient("Oil", 1, tablespoon, recipe1));
		recipe1.getIngredients().add(new Ingredient("Onions", 1, ounce, recipe1));
		recipe1.setNotes(new Notes("Make sure to not overcook the salmon, while staying in the oven.\nAlso make sure to cook it evenly.", recipe1));
		
		Recipe recipe2 = new Recipe();
		recipe2.setName("Pepperoni pizza");
		recipe2.setDescription("Basic pepperoni pizza.");
		recipe2.setCookTime(25);
		recipe2.setPrepTime(10);
		recipe2.setServings(1);
		recipe2.setDirections("Roll up the dough, add the sauce, add pepperoni and add the cheese. Leave in the oven at 300 degrees.");
		recipe2.getCategories().add(italian);
		recipe2.getCategories().add(american);
		recipe2.getIngredients().add(new Ingredient("Pepperoni", 8, slice, recipe2));
		recipe2.getIngredients().add(new Ingredient("Cheese", 8, slice, recipe2));
		recipe2.getIngredients().add(new Ingredient("Dough", 8, ounce, recipe2));
		recipe2.getIngredients().add(new Ingredient("Pizza sauce", 1, cup, recipe2));
		recipe2.setNotes(new Notes("Make sure to properly flatten the dough.\nAlso make sure to check on the pizza every 5 min to not burn it. You want a crispy golden yellow on the top, when the cheese melts.", recipe2));
		
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe1);
		recipes.add(recipe2);
		
		return recipes;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		recipeRepository.saveAll(initData());
	}
}
