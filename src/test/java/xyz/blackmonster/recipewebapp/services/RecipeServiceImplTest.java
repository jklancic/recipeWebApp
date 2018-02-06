package xyz.blackmonster.recipewebapp.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	private RecipeService recipeService;
	
	@Mock
	private RecipeRepository recipeRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void getAllRecipes() {
		Set<Recipe> set = new HashSet<>();
		Recipe recipe = new Recipe();
		recipe.setName("name");
		set.add(recipe);
		when(recipeRepository.findAll()).thenReturn(set);
		
		assertEquals(set.size(), recipeService.getAllRecipes().size());
		verify(recipeRepository, times(1)).findAll();
	}
}
