package xyz.blackmonster.recipewebapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import xyz.blackmonster.recipewebapp.commands.CategoryCommand;
import xyz.blackmonster.recipewebapp.models.Category;

@Component
public class CategoryCommandConverter implements Converter<Category, CategoryCommand> {
	
	@Override
	public CategoryCommand convert(Category category) {
		if(category == null) {
			return null;
		}
		
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(category.getId());
		categoryCommand.setDescription(category.getDescription());
		return categoryCommand;
	}
}
