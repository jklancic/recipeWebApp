package xyz.blackmonster.recipewebapp.converters;

import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.commands.CategoryCommand;
import xyz.blackmonster.recipewebapp.models.Category;

@Component
public class CategoryConverter implements Converter<CategoryCommand, Category> {
	
	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand categoryCommand) {
		if(categoryCommand == null) {
			return null;
		}
		
		Category category = new Category();
		category.setId(categoryCommand.getId());
		category.setDescription(categoryCommand.getDescription());
		return category;
	}
}
