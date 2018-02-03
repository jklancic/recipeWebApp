package xyz.blackmonster.recipewebapp.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Recipe recipe;
	
	@Lob
	private String recipeNotes;

	public Notes() {
	}
	
	public Notes(String recipeNotes, Recipe recipe) {
		this.recipeNotes = recipeNotes;
		this.recipe = recipe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getRecipeNotes() {
		return recipeNotes;
	}

	public void setRecipeNotes(String recipeNotes) {
		this.recipeNotes = recipeNotes;
	}
}
