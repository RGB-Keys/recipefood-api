package com.api.recipe.controllers;

import com.api.recipe.dtos.recipe.RecipeDeleteData;
import com.api.recipe.dtos.recipe.RecipeDetailsData;
import com.api.recipe.dtos.recipe.RecipeRegisterData;
import com.api.recipe.dtos.recipe.RecipeUpdateData;
import com.api.recipe.services.RecipeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService service;

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid RecipeRegisterData data) {
        var dto = service.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<RecipeDetailsData>> list(@PageableDefault Pageable pagination) {
        var page = service.list(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRecipeById(@PathVariable Long id){
        var getRecipe = service.getRecipeById(id);
        return ResponseEntity.ok(getRecipe);
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String titulo){
        var recipeSearch = service.search(titulo);
        return ResponseEntity.ok(recipeSearch);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid RecipeUpdateData data) {
        var recipeUpdated = service.update(id, data);
        return ResponseEntity.ok(recipeUpdated);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@RequestBody @Valid RecipeDeleteData data) {
        service.delete(data);
        return ResponseEntity.noContent().build();
    }
}