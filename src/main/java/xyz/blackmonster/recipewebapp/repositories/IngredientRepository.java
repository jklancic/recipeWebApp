package xyz.blackmonster.recipewebapp.repositories;

import org.springframework.data.repository.CrudRepository;

import xyz.blackmonster.recipewebapp.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
