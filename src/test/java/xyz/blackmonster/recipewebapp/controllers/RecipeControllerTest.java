package xyz.blackmonster.recipewebapp.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.models.Recipe;
import xyz.blackmonster.recipewebapp.services.RecipeService;

public class RecipeControllerTest {

	@Mock
	private RecipeService recipeService;

	private RecipeController recipeController;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		recipeController = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
	}
	
	@Test
	public void testShowById() throws Exception {
		long id = 1L;
		Recipe recipe = new Recipe();
		recipe.setId(id);
		
		when(recipeService.getRecipeById(eq(id))).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/1/show"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/show"))
			.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	public void testNewRecipe() throws Exception {
		mockMvc.perform(get("/recipe/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/recipeform"))
			.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	public void testUpdateRecipe() throws Exception {
		long id = 1L;
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(id);
		
		when(recipeService.findRecipeCommandById(eq(id))).thenReturn(recipeCommand);
		
		mockMvc.perform(get("/recipe/1/update"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/recipeform"))
			.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		long id = 1L;
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(id);
		
		when(recipeService.saveRecipeCommand(any(RecipeCommand.class))).thenReturn(recipeCommand);
		
		mockMvc.perform(post("/recipe")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("description", "Some description"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/recipe/show/1"));
	}
	
	@Test
	public void testDelete() throws Exception {
		long id = 1L;
		
		doNothing().when(recipeService).deleteById(eq(id));

		mockMvc.perform(get("/recipe/1/delete"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"))
			.andExpect(model().attributeDoesNotExist("recipe"));
	}
}
