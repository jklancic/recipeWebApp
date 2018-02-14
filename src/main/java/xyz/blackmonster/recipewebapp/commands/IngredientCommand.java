package xyz.blackmonster.recipewebapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

	private long id;
	private String description;
	private float amount;
	private UnitOfMeasureCommand unitOfMeasureCommand;
}
