package xyz.blackmonster.recipewebapp.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.converters.IngredientCommandConverter;
import xyz.blackmonster.recipewebapp.converters.IngredientConverter;
import xyz.blackmonster.recipewebapp.repositories.IngredientRepository;

public class IngredientServiceImplTest {
	
	@Mock
	private IngredientRepository ingredientRepository;
	@Mock
	private IngredientConverter ingredientConverter;
	@Mock
	private IngredientCommandConverter ingredientCommandConverter;
	@Mock
	private RecipeService recipeService;
	
	private IngredientService ingredientService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		ingredientService = new IngredientServiceImpl(
			ingredientRepository, ingredientConverter, ingredientCommandConverter, recipeService);
	}
	
	@Test
	public void testListIngredients() {
		long id = 1L;
		RecipeCommand command = new RecipeCommand();
		command.setId(id);
		
		when(recipeService.findRecipeCommandById(eq(id))).thenReturn(command);
		
		RecipeCommand returnCommand = ingredientService.listIngredients(id);
		assertEquals(id, returnCommand.getId());
	}
}
