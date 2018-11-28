package my.mmshulga.sfgrecipeproject.services.impl;

import lombok.extern.slf4j.Slf4j;
import my.mmshulga.sfgrecipeproject.exceptions.NotFoundException;
import my.mmshulga.sfgrecipeproject.model.Recipe;
import my.mmshulga.sfgrecipeproject.repositories.RecipeRepository;
import my.mmshulga.sfgrecipeproject.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static my.mmshulga.sfgrecipeproject.utils.Utils.boxArrayOfBytes;

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

            Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
            Recipe recipe = optionalRecipe.orElseThrow(() -> new NotFoundException("no such recipe"));

            recipe.setImage(bytes);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("error while attaching image: " + e);
            throw new RuntimeException(e);
        }
    }
}
