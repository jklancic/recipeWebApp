package xyz.blackmonster.recipewebapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.converters.RecipeCommandConverter;
import xyz.blackmonster.recipewebapp.converters.RecipeConverter;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	private RecipeService recipeService;
	
	@Mock
	private RecipeRepository recipeRepository;
	@Mock
	private RecipeConverter recipeConverter;
	@Mock
	private RecipeCommandConverter recipeCommandConverter;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository, recipeConverter, recipeCommandConverter);
	}

	@Test
	public void testGetAllRecipes() {
		Set<Recipe> set = new HashSet<>();
		Recipe recipe = new Recipe();
		recipe.setName("name");
		set.add(recipe);
		when(recipeRepository.findAll()).thenReturn(set);
		
		assertEquals(set.size(), recipeService.getAllRecipes().size());
		verify(recipeRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetRecipeById() {
		Recipe recipe = new Recipe();
		Long recipeId = 1L;
		String recipeName = "recipeName";
		recipe.setId(recipeId);
		recipe.setName(recipeName);

		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(eq(recipeId))).thenReturn(recipeOptional);
		
		Recipe returnedRecipe = recipeService.getRecipeById(recipeId);
		
		assertTrue(recipeId == returnedRecipe.getId());
		assertEquals(recipeName, returnedRecipe.getName());
		verify(recipeRepository, times(1)).findById(eq(recipeId));
		verify(recipeRepository, never()).findAll();
	}
	
	@Test
	public void testSaveRecipeCommand() {
		long id = 1L;
		String description = "Recipe description";
		
		RecipeCommand command = new RecipeCommand();
		command.setId(id);
		command.setDescription(description);
		Recipe model = new Recipe();
		model.setId(id);
		model.setDescription(description);
		
		when(recipeConverter.convert(any(RecipeCommand.class))).thenReturn(model);
		when(recipeRepository.save(any(Recipe.class))).thenReturn(model);
		when(recipeCommandConverter.convert(any(Recipe.class))).thenReturn(command);
		
		RecipeCommand returnCommand = recipeService.saveRecipeCommand(command);
		
		assertEquals(id, returnCommand.getId());
		assertEquals(description, returnCommand.getDescription());
	}
	
	@Test
	public void testFindRecipeCommandById() {
		long id = 1L;
		String description = "Recipe description";

		RecipeCommand command = new RecipeCommand();
		command.setId(id);
		command.setDescription(description);
		
		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(new Recipe()));
		when(recipeCommandConverter.convert(any(Recipe.class))).thenReturn(command);

		RecipeCommand returnCommand = recipeService.findRecipeCommandById(id);

		assertEquals(id, returnCommand.getId());
		assertEquals(description, returnCommand.getDescription());
	}
	
	@Test
	public void testDeleteById() {
		long id = 1L;
		
		doNothing().when(recipeRepository).deleteById(eq(id));
		
		recipeService.deleteById(id);
		verify(recipeRepository, times(1)).deleteById(eq(id));
	}
}
