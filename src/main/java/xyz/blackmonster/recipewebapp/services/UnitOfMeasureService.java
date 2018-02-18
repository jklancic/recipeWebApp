package xyz.blackmonster.recipewebapp.services;

import java.util.Set;

import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
	
	Set<UnitOfMeasureCommand> getAll();
}
