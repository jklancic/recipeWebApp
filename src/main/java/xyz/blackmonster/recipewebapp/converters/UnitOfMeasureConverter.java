package xyz.blackmonster.recipewebapp.converters;

import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;

@Component
public class UnitOfMeasureConverter implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
	
	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
		if(unitOfMeasureCommand == null) {
			return null;
		}
		
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(unitOfMeasureCommand.getId());
		uom.setUom(unitOfMeasureCommand.getUom());
		return uom;
	}
}
