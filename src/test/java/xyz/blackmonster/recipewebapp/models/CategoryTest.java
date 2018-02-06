package xyz.blackmonster.recipewebapp.models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {
	
	Category category;
	
	@Before
	public void setUp() {
		category = new Category();
	}

	@Test
	public void getId() {
		long id = 1L;
		category.setId(id);
		
		assertEquals(id, category.getId());
	}

	@Test
	public void getDescription() {
		String description = "BlaBLa";
		category.setDescription(description);
		
		assertEquals(description, category.getDescription());
	}

	@Test
	public void getRecipes() {
		Recipe recipe = new Recipe();
		recipe.setName("name");
		Set<Recipe> set = new HashSet<>();
		set.add(recipe);
		category.setRecipes(set);

		category.getRecipes().forEach(item -> {
			assertEquals(recipe.getName(), item.getName());
		});
	}
}
