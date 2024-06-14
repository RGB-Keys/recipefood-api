package com.api.recipe.controllers;

import com.api.recipe.dtos.recipe.RecipeDeleteData;
import com.api.recipe.dtos.recipe.RecipeUpdateData;
import com.api.recipe.dtos.users.UserDetailsData;
import com.api.recipe.dtos.users.UserRegisterData;
import com.api.recipe.dtos.users.UserUpdateData;
import com.api.recipe.services.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid UserRegisterData data) {
        var dto = userService.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<UserDetailsData>> list(@PageableDefault Pageable pagination) {
        var page = userService.list(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity search(@PathVariable Long id){
        var userSearch = userService.search(id);
        return ResponseEntity.ok(userSearch);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UserUpdateData data) {
        var userUpdated = userService.update(id, data);
        return ResponseEntity.ok(userUpdated);
    }
}
