package my.mmshulga.sfgrecipeproject.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public void attachImage(Long recipeId, MultipartFile file);
}
