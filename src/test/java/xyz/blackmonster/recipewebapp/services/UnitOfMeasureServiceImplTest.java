package xyz.blackmonster.recipewebapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.converters.UnitOfMeasureCommandConverter;
import xyz.blackmonster.recipewebapp.converters.UnitOfMeasureConverter;
import xyz.blackmonster.recipewebapp.models.UnitOfMeasure;
import xyz.blackmonster.recipewebapp.repositories.UnitOfMeasureRepository;

public class UnitOfMeasureServiceImplTest {
	
	@Mock
	private UnitOfMeasureRepository unitOfMeasureRepository;
	@Mock
	private UnitOfMeasureConverter unitOfMeasureConverter;
	@Mock
	private UnitOfMeasureCommandConverter unitOfMeasureCommandConverter;
	
	private UnitOfMeasureService unitOfMeasureService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		unitOfMeasureService = new UnitOfMeasureServiceImpl(
			unitOfMeasureRepository, unitOfMeasureConverter, unitOfMeasureCommandConverter);
	}
	
	@Test
	public void testGetAll() {
		Set<UnitOfMeasure> set = new HashSet<>();
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		String spoon = "Spoon";
		unitOfMeasure.setUom(spoon);
		set.add(unitOfMeasure);
		
		when(unitOfMeasureRepository.findAll()).thenReturn(set);
		
		Set<UnitOfMeasureCommand> returnSet =  unitOfMeasureService.getAll();
		assertEquals(1, returnSet.size());
		verify(unitOfMeasureRepository, times(1)).findAll();
	}
}
