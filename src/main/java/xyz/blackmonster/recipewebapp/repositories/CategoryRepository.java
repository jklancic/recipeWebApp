package xyz.blackmonster.recipewebapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import xyz.blackmonster.recipewebapp.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	Optional<Category> findByDescription(String description);
}
