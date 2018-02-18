package guru.springframework.services;

import java.io.IOException;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
	
	private final RecipeRepository recipeRepository;

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public void saveFileImage(Long id, MultipartFile file) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(id);
		if(recipeOptional.isPresent()) {
			try {
				Byte[] byteObjects = new Byte[file.getBytes().length];
				int index = 0;
				for (byte b: file.getBytes()) {
					byteObjects[index++] = b;
				}
				
				Recipe recipe = recipeOptional.get();
				recipe.setImage(byteObjects);
				recipeRepository.save(recipe);
				log.info("Image saved.");
			} catch (IOException e) {
				log.error("Image file is bad:", e.getMessage());
				log.error(e.toString());
			}
		}
	}
}
