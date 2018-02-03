package xyz.blackmonster.recipewebapp.repositories;

import org.springframework.data.repository.CrudRepository;

import xyz.blackmonster.recipewebapp.models.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
