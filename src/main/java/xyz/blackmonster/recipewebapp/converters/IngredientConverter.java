package xyz.blackmonster.recipewebapp.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.commands.IngredientCommand;
import xyz.blackmonster.recipewebapp.models.Ingredient;

@Component
public class IngredientConverter implements Converter<IngredientCommand, Ingredient> {
	
	private final UnitOfMeasureConverter unitOfMeasureConverter;

	@Autowired
	public IngredientConverter(UnitOfMeasureConverter unitOfMeasureConverter) {
		this.unitOfMeasureConverter = unitOfMeasureConverter;
	}

	@Override
	public Ingredient convert(IngredientCommand ingredientCommand) {
		if(ingredientCommand == null) {
			return null;
		}
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ingredientCommand.getId());
		ingredient.setDescription(ingredientCommand.getDescription());
		ingredient.setAmount(ingredientCommand.getAmount());
		ingredient.setUnitOfMeasure(unitOfMeasureConverter.convert(ingredientCommand.getUnitOfMeasureCommand()));
		return ingredient;
	}
}
