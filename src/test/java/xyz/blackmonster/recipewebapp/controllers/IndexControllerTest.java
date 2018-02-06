package xyz.blackmonster.recipewebapp.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.services.RecipeService;

public class IndexControllerTest {
	
	@Mock
	private RecipeService recipeService;
	
	@Mock
	private Model model;
	
	private IndexController indexController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		indexController = new IndexController(recipeService);
	}

	@Test
	public void getIndexPage() {
		List<Recipe> recipes = new ArrayList<>();
		Recipe recipe1 = new Recipe();
		recipe1.setName("name1");
		Recipe recipe2 = new Recipe();
		recipe2.setName("name1");
		recipes.add(recipe1);
		recipes.add(recipe2);
		when(recipeService.getAllRecipes()).thenReturn(recipes);
		when(model.addAttribute(anyString(), anyList())).thenReturn(model);

		ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);
		
		String viewName = indexController.getIndexPage(model);
		
		assertEquals(viewName, IndexController.INDEX);
		verify(recipeService, times(1)).getAllRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		List<Recipe> setInController = argumentCaptor.getValue();
		assertEquals(recipes.size(), setInController.size());
	}
}
