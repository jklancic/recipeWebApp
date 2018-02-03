package xyz.blackmonster.recipewebapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
	
	Optional<UnitOfMeasure> findByUom(String uom);
}
