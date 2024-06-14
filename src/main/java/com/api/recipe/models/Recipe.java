package com.api.recipe.models;

import com.api.recipe.dtos.recipe.Difficult;
import com.api.recipe.dtos.recipe.ReasonDelete;
import com.api.recipe.dtos.recipe.RecipeRegisterData;
import com.api.recipe.dtos.recipe.RecipeUpdateData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Recipes")
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe")
    private Long id;
    private String titulo;
    private String[] ingredientes;
    private String instrucoes;
    private Difficult dificuldade;
    private long porcao;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User usuario;

    @Column(name = "reason_delete")
    @Enumerated(EnumType.STRING)
    private ReasonDelete reasonDelete;

    public Recipe(RecipeRegisterData data) {
        this.titulo = data.titulo();
        this.ingredientes = data.ingredientes();
        this.instrucoes = data.instrucoes();
        this.dificuldade = data.dificuldade();
        this.porcao = data.porcao();
    }

    public void updateRecipe(RecipeUpdateData data) {
        if (data.titulo() != null) {
            this.titulo = data.titulo();
        }
        if (data.ingredientes() !=null) {
            this.ingredientes = data.ingredientes();
        }
        if (data.instrucoes() !=null) {
            this.instrucoes = data.instrucoes();;
        }
        if (data.dificuldade() !=null) {
            this.dificuldade = data.dificuldade();;
        }
        if (data.porcao() !=null) {
            this.porcao = data.porcao();;
        }
    }

    public void delete(ReasonDelete reason){ this.reasonDelete = reason; }
}
