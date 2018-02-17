package xyz.blackmonster.recipewebapp.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import xyz.blackmonster.recipewebapp.commands.IngredientCommand;
import xyz.blackmonster.recipewebapp.converters.IngredientCommandConverter;
import xyz.blackmonster.recipewebapp.converters.IngredientConverter;
import xyz.blackmonster.recipewebapp.models.Ingredient;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.repositories.IngredientRepository;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;

public class IngredientServiceImplTest {
	
	@Mock
	private IngredientRepository ingredientRepository;
	@Mock
	private IngredientConverter ingredientConverter;
	@Mock
	private IngredientCommandConverter ingredientCommandConverter;
	@Mock
	private RecipeRepository recipeRepository;
	
	private IngredientService ingredientService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		ingredientService = new IngredientServiceImpl(
			ingredientRepository, ingredientConverter, ingredientCommandConverter, recipeRepository);
	}
	
	@Test
	public void testFindByRecipeIdAndIngredientId() {
		long id = 1L;
		Ingredient ingredient = new Ingredient();
		ingredient.setId(id);
		Recipe recipe = new Recipe();
		recipe.setId(id);
		recipe.setIngredients(Collections.singleton(ingredient));
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(id);
		
		when(recipeRepository.findById(eq(id))).thenReturn(Optional.of(recipe));
		when(ingredientCommandConverter.convert(any(Ingredient.class))).thenReturn(ingredientCommand);
		
		IngredientCommand returnCommand = ingredientService.findByRecipeIdAndIngredientId(id, id);
		assertEquals(id, returnCommand.getId());
		verify(recipeRepository, times(1)).findById(eq(id));
		verify(ingredientCommandConverter, times(1)).convert(any(Ingredient.class));
	}
}
