package my.mmshulga.sfgrecipeproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    private String description;

}
