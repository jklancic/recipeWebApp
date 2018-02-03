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
	
	public Notes(String recipeNotes) {
		this.recipeNotes = recipeNotes;
	}

	public long getId() {
		return this.id;
	}

	public Recipe getRecipe() {
		return this.recipe;
	}

	public String getRecipeNotes() {
		return this.recipeNotes;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public void setRecipeNotes(String recipeNotes) {
		this.recipeNotes = recipeNotes;
	}

	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Notes)) return false;
		final Notes other = (Notes) o;
		if (!other.canEqual((Object) this)) return false;
		if (this.getId() != other.getId()) return false;
		final Object this$recipe = this.getRecipe();
		final Object other$recipe = other.getRecipe();
		if (this$recipe == null ? other$recipe != null : !this$recipe.equals(other$recipe)) return false;
		final Object this$recipeNotes = this.getRecipeNotes();
		final Object other$recipeNotes = other.getRecipeNotes();
		if (this$recipeNotes == null ? other$recipeNotes != null : !this$recipeNotes.equals(other$recipeNotes))
			return false;
		return true;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final long $id = this.getId();
		result = result * PRIME + (int) ($id >>> 32 ^ $id);
		final Object $recipe = this.getRecipe();
		result = result * PRIME + ($recipe == null ? 43 : $recipe.hashCode());
		final Object $recipeNotes = this.getRecipeNotes();
		result = result * PRIME + ($recipeNotes == null ? 43 : $recipeNotes.hashCode());
		return result;
	}

	protected boolean canEqual(Object other) {
		return other instanceof Notes;
	}

	public String toString() {
		return "Notes(id=" + this.getId() + ", recipe=" + this.getRecipe() + ", recipeNotes=" + this.getRecipeNotes() + ")";
	}
}
