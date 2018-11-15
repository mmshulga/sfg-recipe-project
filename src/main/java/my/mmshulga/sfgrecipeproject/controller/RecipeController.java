package my.mmshulga.sfgrecipeproject.controller;

import my.mmshulga.sfgrecipeproject.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/recipes")
@Controller
public class RecipeController {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String index(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/index";
    }
}
