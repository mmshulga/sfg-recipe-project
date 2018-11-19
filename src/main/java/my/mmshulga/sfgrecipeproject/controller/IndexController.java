package my.mmshulga.sfgrecipeproject.controller;

import my.mmshulga.sfgrecipeproject.model.Category;
import my.mmshulga.sfgrecipeproject.model.UnitOfMeasure;
import my.mmshulga.sfgrecipeproject.repositories.CategoryRepository;
import my.mmshulga.sfgrecipeproject.repositories.RecipeRepository;
import my.mmshulga.sfgrecipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository uomRepository;

    @Autowired
    public IndexController(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository uomRepository) {
        this.recipeRepository   = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.uomRepository      = uomRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String index() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> uomOptional = uomRepository.findByDescription("Teaspoon");
        System.out.println("category_id: " + categoryOptional.get().getId());
        System.out.println("uom_id: " + uomOptional.get().getId());
        return "index";
    }
}
