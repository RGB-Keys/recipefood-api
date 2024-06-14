package com.api.recipe.controllers;

import com.api.recipe.dtos.address.AddressData;
import com.api.recipe.dtos.recipe.Difficult;
import com.api.recipe.dtos.recipe.RecipeDetailsData;
import com.api.recipe.dtos.recipe.RecipeRegisterData;
import com.api.recipe.dtos.users.UserRegisterData;
import com.api.recipe.dtos.users.UserType;
import com.api.recipe.services.RecipeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class RecipeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RecipeRegisterData> recipeRegisterDataJson;

    @Autowired
    private JacksonTester<RecipeDetailsData> recipeDetailsDataJson;

    @MockBean
    private RecipeService recipeService;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas!")
    @WithMockUser
    void register_test1() throws Exception {
        var response = mvc
                .perform(post("/recipes"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 201 quando informações estão válidas!")
    @WithMockUser
    void register_test2() throws Exception {

        var recipeRegister = recipeRegisterData();
        var recipeDetails = recipeDetailsData();

        when(recipeService.save(any())).thenReturn(recipeDetails);

        var responseRecipe = mvc
                .perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(recipeRegisterDataJson.write(recipeRegister).getJson()))
                .andReturn().getResponse();

        var jsonRecipe = recipeDetailsDataJson.write(recipeDetails).getJson();

        assertThat(responseRecipe.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(responseRecipe.getContentAsString()).isEqualTo(jsonRecipe);
    }

    @Test
    @DisplayName("Deveria devolver codigo 200")
    void get_test1() throws Exception {

        var recipeDetails = recipeDetailsData();
        var title = recipeDetails.titulo();

        when(recipeService.search(title)).thenReturn(recipeDetails);

        var responseRecipe = mvc
                .perform(get("/recipes/search", title)
                        .contentType(MediaType.APPLICATION_JSON)).andExpectAll();
    }


    private UserRegisterData userRegister() {
        return new UserRegisterData(
                "João",
                "João@gmail.com",
                "11111111111",
                "81984567898",
                UserType.AMADOR,
                addressData()
        );
    }

    private RecipeRegisterData recipeRegisterData() {
        return new RecipeRegisterData(
                "Tentativa",
                new String[]{"Arroz Branco"},
                "Bota na Panela",
                Difficult.FACIL,
                1L,
                0L
        );
    }

    private RecipeDetailsData recipeDetailsData() {
        return new RecipeDetailsData(
                null,
                recipeRegisterData().titulo(),
                recipeRegisterData().ingredientes(),
                recipeRegisterData().instrucoes(),
                recipeRegisterData().dificuldade(),
                recipeRegisterData().porcao(),
                recipeRegisterData().idUser()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "rua xpto",
                "olinda",
                "00000000",
                "DF",
                null,
                null
        );
    }
}

