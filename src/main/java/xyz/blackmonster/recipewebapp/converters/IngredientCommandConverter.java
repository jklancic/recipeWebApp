package xyz.blackmonster.recipewebapp.converters;

import lombok.Synchronized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.commands.IngredientCommand;
import xyz.blackmonster.recipewebapp.models.Ingredient;

@Component
public class IngredientCommandConverter implements Converter<Ingredient, IngredientCommand> {
	
	private final UnitOfMeasureCommandConverter unitOfMeasureCommandConverter;

	@Autowired
	public IngredientCommandConverter(UnitOfMeasureCommandConverter unitOfMeasureCommandConverter) {
		this.unitOfMeasureCommandConverter = unitOfMeasureCommandConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient ingredient) {
		if(ingredient == null) {
			return null;
		}
		
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ingredient.getId());
		ingredientCommand.setDescription(ingredient.getDescription());
		ingredientCommand.setAmount(ingredient.getAmount());
		ingredientCommand.setUnitOfMeasureCommand(unitOfMeasureCommandConverter.convert(ingredient.getUnitOfMeasure()));
		return ingredientCommand;
	}
}
