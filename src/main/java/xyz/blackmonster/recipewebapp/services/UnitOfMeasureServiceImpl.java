package xyz.blackmonster.recipewebapp.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.blackmonster.recipewebapp.commands.UnitOfMeasureCommand;
import xyz.blackmonster.recipewebapp.converters.UnitOfMeasureCommandConverter;
import xyz.blackmonster.recipewebapp.converters.UnitOfMeasureConverter;
import xyz.blackmonster.recipewebapp.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
	
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UnitOfMeasureConverter unitOfMeasureConverter;
	private final UnitOfMeasureCommandConverter unitOfMeasureCommandConverter;

	@Autowired
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, 
																	UnitOfMeasureConverter unitOfMeasureConverter, 
																	UnitOfMeasureCommandConverter unitOfMeasureCommandConverter) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureConverter = unitOfMeasureConverter;
		this.unitOfMeasureCommandConverter = unitOfMeasureCommandConverter;
	}

	@Override
	public Set<UnitOfMeasureCommand> getAll() {
		
		return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
			.map(unitOfMeasureCommandConverter::convert)
			.collect(Collectors.toSet());
	}
}
