package xyz.blackmonster.recipewebapp.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String description;
	
	private int prepTime;
	
	private int cookTime;
	
	private int servings;
	
	private String source;
	
	private String url;
	
	private String directions;
	
	@Lob
	private byte[] image;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Notes notes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.LAZY)
	private Set<Ingredient> ingredients = new HashSet<>();
	
	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;
	
	@ManyToMany
	@JoinTable(name = "recipe_category", 
		joinColumns = @JoinColumn(name = "recipe_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	public void setNotes(Notes notes) {
		if(notes == null) {
			return;
		}
		notes.setRecipe(this);
		this.notes = notes;
	}
	
	public Recipe addIngredient(Ingredient ingredient) {
		if(ingredient == null) {
			return this;
		}
		ingredient.setRecipe(this);
		ingredients.add(ingredient);
		return this;
	}
}
