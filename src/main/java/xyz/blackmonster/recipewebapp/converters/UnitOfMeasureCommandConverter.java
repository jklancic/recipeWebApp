package xyz.blackmonster.recipewebapp.converters;

import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandConverter implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
		if(unitOfMeasure == null) {
			return null;
		}

		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(unitOfMeasure.getId());
		uomCommand.setUom(unitOfMeasure.getUom());
		return uomCommand;
	}
}
