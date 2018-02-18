package guru.springframework.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

@Service
public class ImageServiceImpl implements ImageService {
	
	private final RecipeRepository recipeRepository;

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public void saveFileImage(Long id, MultipartFile file) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if(recipe.isPresent()) {
			
		}
	}
}
