package xyz.blackmonster.recipewebapp.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import xyz.blackmonster.recipewebapp.commands.IngredientCommand;
import xyz.blackmonster.recipewebapp.commands.RecipeCommand;
import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.services.IngredientService;
import xyz.blackmonster.recipewebapp.services.RecipeService;
import xyz.blackmonster.recipewebapp.services.UnitOfMeasureService;

public class IngredientControllerTest {

	@Mock
	private RecipeService recipeService;
	@Mock
	private IngredientService ingredientService;
	@Mock
	private UnitOfMeasureService unitOfMeasureService;
	
	private IngredientController ingredientController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}
	
	@Test
	public void testListIngredients() throws Exception {
		long id = 1L;
		RecipeCommand command = new RecipeCommand();
		command.setId(id);
		
		when(recipeService.findRecipeCommandById(eq(id))).thenReturn(command);
		
		mockMvc.perform(get("/recipe/1/ingredients"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/list"))
			.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	public void testShowIngredient() throws Exception {
		IngredientCommand command = new IngredientCommand();
		
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);
		
		mockMvc.perform(get("/recipe/1/ingredient/1/show"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/show"))
			.andExpect(model().attributeExists("ingredient"));
	}
	
	@Test
	public void testUpdateRecipeIngredient() throws Exception {
		IngredientCommand ingredientCommand = new IngredientCommand();
		Set<UnitOfMeasureCommand> commandSet = Collections.EMPTY_SET;
		
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
		when(unitOfMeasureService.getAll()).thenReturn(commandSet);
		
		mockMvc.perform(get("/recipe/1/ingredient/1/update"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/ingredientform"))
			.andExpect(model().attributeExists("ingredient"))
			.andExpect(model().attributeExists("uomList"));
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		IngredientCommand command = new IngredientCommand();
		command.setId(3L);
		command.setRecipeId(2L);

		when(ingredientService.saveIngredientCommand(any(IngredientCommand.class))).thenReturn(command);
		
		mockMvc.perform(post("/recipe/2/ingredient")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("description", "Some description")
			.param("recipeId", "1")
			.param("amount", "2"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));
	}
}
