package guru.springframework.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;

public class ImageControllerTest {
	
	@Mock
	ImageService imageService;
	
	@Mock
	RecipeService recipeService;
	
	ImageController imageController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		imageController = new ImageController(imageService, recipeService);
		mockMvc = MockMvcBuilders
			.standaloneSetup(imageController)
			.setControllerAdvice(new ControllerExceptionHandler())
			.build();
	}
	
	@Test
	public void testGetImageForm() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(1L);

		when(recipeService.findCommandById(anyLong())).thenReturn(command);

		//when
		mockMvc.perform(get("/recipe/1/image"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("recipe"));

		verify(recipeService, times(1)).findCommandById(anyLong());
	}
	
	@Test
	public void testHandleImagePost() throws Exception {
		MockMultipartFile multipartFile =
			new MockMultipartFile("imagefile", "testing.txt", "text/plain",
				"Spring Framework Guru".getBytes());

		mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().is3xxRedirection())
			.andExpect(header().string("Location", "/recipe/1/show"));

		verify(imageService, times(1)).saveFileImage(anyLong(), any());
	}
	
	@Test
	public void renderImageFromDB() throws Exception {
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);
		
		String s = "Fake image text.";
		Byte[] bytes = new Byte[s.getBytes().length];
		int index = 0;
		for (byte b : s.getBytes()) {
			bytes[index++] = b;
		}
		
		recipeCommand.setImage(bytes);
		
		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

		MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
			.andExpect(status().isOk())
			.andReturn().getResponse();
		
		byte[] responseBytes = response.getContentAsByteArray();
		Assert.assertEquals(s.getBytes().length, responseBytes.length);
	}

	@Test
	public void testGetImageForm_NumberFormatException() throws Exception {
		
		mockMvc.perform(get("/recipe/asd/image"))
			.andExpect(status().isBadRequest())
			.andExpect(view().name("error_400"))
			.andExpect(model().attributeExists("exception"));
	}
}
