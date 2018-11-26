package my.mmshulga.sfgrecipeproject.services.impl;

import lombok.extern.slf4j.Slf4j;
import my.mmshulga.sfgrecipeproject.model.Recipe;
import my.mmshulga.sfgrecipeproject.repositories.RecipeRepository;
import my.mmshulga.sfgrecipeproject.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void attachImage(Long recipeId, MultipartFile file) {
        log.debug("attaching image");
        try {
            Byte[] bytes = boxArrayOfBytes(file.getBytes());

            Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> {
                log.error("no recipe with id " + recipeId + " exists.");
                throw new RuntimeException("error attaching image to recipe!");
            });

            recipe.setImage(bytes);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("error while attaching image: " + e);
            throw new RuntimeException(e);
        }
    }

    private static Byte[] boxArrayOfBytes(byte[] bytes) {
        int length = bytes.length;
        Byte[] boxed = new Byte[length];
        for (int i = 0; i < length; i++) {
            boxed[i] = bytes[i];
        }
        return boxed;
    }
}
