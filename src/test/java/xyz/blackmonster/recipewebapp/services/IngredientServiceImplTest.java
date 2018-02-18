package xyz.blackmonster.recipewebapp.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import xyz.blackmonster.recipewebapp.converters.UnitOfMeasureCommandConverter;
import xyz.blackmonster.recipewebapp.converters.UnitOfMeasureConverter;
import xyz.blackmonster.recipewebapp.models.Ingredient;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.repositories.IngredientRepository;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;
import xyz.blackmonster.recipewebapp.repositories.UnitOfMeasureRepository;

public class IngredientServiceImplTest {

	private final IngredientCommandConverter ingredientToIngredientCommand;
	private final IngredientConverter ingredientCommandToIngredient;

	@Mock
	IngredientRepository ingredientRepository;
	
	@Mock
	RecipeRepository recipeRepository;

	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;

	IngredientService ingredientService;

	//init converters
	public IngredientServiceImplTest() {
		this.ingredientToIngredientCommand = new IngredientCommandConverter(new UnitOfMeasureCommandConverter());
		this.ingredientCommandToIngredient = new IngredientConverter(new UnitOfMeasureConverter());
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		ingredientService = new IngredientServiceImpl(ingredientRepository, ingredientCommandToIngredient, ingredientToIngredientCommand,
			recipeRepository, unitOfMeasureRepository);
	}

	@Test
	public void findByRecipeIdAndId() throws Exception {
	}

	@Test
	public void findByRecipeIdAndReceipeIdHappyPath() throws Exception {
		//given
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);

		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(1L);

		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId(3L);

		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		//then
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

		//when
		assertEquals(3L, ingredientCommand.getId());
		assertEquals(1L, ingredientCommand.getRecipeId());
		verify(recipeRepository, times(1)).findById(anyLong());
	}


	@Test
	public void testSaveRecipeCommand() throws Exception {
		//given
		IngredientCommand command = new IngredientCommand();
		command.setId(3L);
		command.setRecipeId(2L);

		Optional<Recipe> recipeOptional = Optional.of(new Recipe());

		Recipe savedRecipe = new Recipe();
		savedRecipe.addIngredient(new Ingredient());
		savedRecipe.getIngredients().iterator().next().setId(3L);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		when(recipeRepository.save(any())).thenReturn(savedRecipe);

		//when
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

		//then
		assertEquals(3L, savedCommand.getId());
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, times(1)).save(any(Recipe.class));

	}
}
