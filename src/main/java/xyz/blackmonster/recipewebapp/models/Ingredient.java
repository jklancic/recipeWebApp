package xyz.blackmonster.recipewebapp.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String description;
	
	private float amount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Recipe recipe;
	
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unitOfMeasure;
	
	public Ingredient() {
	}

	public Ingredient(String description, float amount, UnitOfMeasure unitOfMeasure) {
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = unitOfMeasure;
	}

}
