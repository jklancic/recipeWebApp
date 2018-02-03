package xyz.blackmonster.recipewebapp.bootstrap;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.models.Category;
import xyz.blackmonster.recipewebapp.models.Ingredient;
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

	private void initData() {

		UnitOfMeasure ounce = unitOfMeasureRepository.findByUom("Ounce").get();
		UnitOfMeasure dash = unitOfMeasureRepository.findByUom("Dash").get();
		UnitOfMeasure tablespoon = unitOfMeasureRepository.findByUom("Tablespoon").get();
		UnitOfMeasure pinch = unitOfMeasureRepository.findByUom("Pinch").get();
		UnitOfMeasure slice = unitOfMeasureRepository.findByUom("Slice").get();
		UnitOfMeasure cup = unitOfMeasureRepository.findByUom("Cup").get();

		Category slovenian = categoryRepository.findByDescription("Slovenian").get();
		Category italian = categoryRepository.findByDescription("Italian").get();
		Category american = categoryRepository.findByDescription("American").get();

		Set<Category> categoryList1 = new HashSet<>();
		categoryList1.add(slovenian);
		categoryList1.add(italian);
		
		Recipe recipe1 = new Recipe();
		recipe1.setName("Oven baked salmon");
		recipe1.setDescription("Slowly baked salmon in olive oil and garlic.");
		recipe1.setCookTime(45);
		recipe1.setPrepTime(5);
		recipe1.setServings(2);
		recipe1.setDirections("First glaze oil on the salmon, add the cut garlic and leave the salmon in the oven at 250 degrees.");
		recipe1.setCategories(categoryList1);

		Ingredient salmon = new Ingredient();
		salmon.setAmount(8);
		salmon.setUnitOfMeasure(ounce);
		salmon.setDescription("Whole salmon.");
		salmon.setRecipe(recipe1);
		
		Ingredient salt = new Ingredient();
		salt.setAmount(1);
		salt.setUnitOfMeasure(dash);
		salt.setDescription("Whole salmon.");
		salt.setRecipe(recipe1);
		
		Ingredient oil = new Ingredient();
		oil.setAmount(2);
		oil.setUnitOfMeasure(tablespoon);
		oil.setDescription("Glaze th salmon with the oil.");
		oil.setRecipe(recipe1);
		
		Ingredient garlic = new Ingredient();
		garlic.setAmount(2);
		garlic.setUnitOfMeasure(pinch);
		garlic.setDescription("Cut into little pieces.");
		garlic.setRecipe(recipe1);

		Set<Category> categoryList2 = new HashSet<>();
		categoryList2.add(american);
		categoryList2.add(italian);
		
		Recipe recipe2 = new Recipe();
		recipe2.setName("Pepperoni pizza");
		recipe2.setDescription("Basic pepperoni pizza.");
		recipe2.setCookTime(25);
		recipe2.setPrepTime(10);
		recipe2.setServings(1);
		recipe2.setDirections("Roll up the dough, add the sauce, add pepperoni and add the cheese. Leave in the oven at 300 degrees.");
		recipe2.setCategories(categoryList2);

		Ingredient pepperoni = new Ingredient();
		pepperoni.setAmount(8);
		pepperoni.setUnitOfMeasure(slice);
		pepperoni.setDescription("8 slices.");
		pepperoni.setRecipe(recipe2);

		Ingredient cheese = new Ingredient();
		cheese.setAmount(8);
		cheese.setUnitOfMeasure(slice);
		cheese.setDescription("8 slices.");
		cheese.setRecipe(recipe2);

		Ingredient dough = new Ingredient();
		dough.setAmount(8);
		dough.setUnitOfMeasure(dash);
		dough.setDescription("Flatten the dough.");
		dough.setRecipe(recipe2);

		Ingredient sauce = new Ingredient();
		sauce.setAmount(1);
		sauce.setUnitOfMeasure(cup);
		sauce.setDescription("Glaze the dough with sauce.");
		sauce.setRecipe(recipe2);
		
		recipeRepository.save(recipe1);
		recipeRepository.save(recipe2);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}
}
