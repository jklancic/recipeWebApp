package xyz.blackmonster.recipewebapp.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import xyz.blackmonster.recipewebapp.commands.IngredientCommand;
import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.services.IngredientService;

public class IngredintControllerTest {
	
	@Mock
	private IngredientService ingredientService;
	
	private IngredientController ingredientController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		ingredientController = new IngredientController(ingredientService);
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}
	
	@Test
	public void testListIngredients() throws Exception {
		long id = 1L;
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(id);
		ingredientCommand.setAmount(1);
		ingredientCommand.setDescription("Ingredient description.");
		ingredientCommand.setUnitOfMeasureCommand(new UnitOfMeasureCommand());
		RecipeCommand command = new RecipeCommand();
		command.setId(id);
		command.setIngredients(Collections.singleton(ingredientCommand));
		
		when(ingredientService.listIngredients(eq(id))).thenReturn(command);
		
		mockMvc.perform(get("/recipe/1/ingredients"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/list"))
			.andExpect(model().attributeExists("recipe"));
	}
}
