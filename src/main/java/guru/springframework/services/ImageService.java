package guru.springframework.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	void saveFileImage(Long id, MultipartFile file);
}
