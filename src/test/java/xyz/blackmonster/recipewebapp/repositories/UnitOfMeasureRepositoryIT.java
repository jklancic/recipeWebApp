package xyz.blackmonster.recipewebapp.repositories;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void findByUom_teaspoon() {

		String teaspoon = "Teaspoon";
		Optional<UnitOfMeasure> uofmOptional = unitOfMeasureRepository.findByUom(teaspoon);
		assertTrue(uofmOptional.isPresent());
		assertEquals(teaspoon, uofmOptional.get().getUom());
	}

	@Test
	public void findByUom_cup() {

		String cup = "Cup";
		Optional<UnitOfMeasure> uofmOptional = unitOfMeasureRepository.findByUom(cup);
		assertTrue(uofmOptional.isPresent());
		assertEquals(cup, uofmOptional.get().getUom());
	}
}
