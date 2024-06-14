package com.api.recipe.controllers;

import com.api.recipe.dtos.address.AddressData;
import com.api.recipe.dtos.users.UserDetailsData;
import com.api.recipe.dtos.users.UserRegisterData;
import com.api.recipe.dtos.users.UserType;
import com.api.recipe.models.Address;
import com.api.recipe.models.User;
import com.api.recipe.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UserRegisterData> userRegisterDataJson;

    @Autowired
    private JacksonTester<UserDetailsData> userDetailsDataJson;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Deveria devolver código 400 - Bad Request quando as informações estão inválidas!")
    void user_register_test1() throws Exception {
        var response = mvc
                .perform(post("/user"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código 201 - CREATED quando as informações estão válidas!")
    void user_register_test2() throws Exception{
        var userType = UserType.AMADOR;
        var userRegister = new UserRegisterData(
                "Joao",
                "Joao@gmail.com",
                "14961512400",
                "81984567898",
                userType,
                addressData()
        );

        var userDetails = new UserDetailsData(
                0L,
                userRegister.nome(),
                userRegister.email(),
                userRegister.telefone(),
                userRegister.cpf(),
                userRegister.categoria(),
                new Address(userRegister.endereco())
        );

        when(userService.save(any())).thenReturn(userDetails);

        var responseUser = mvc
                .perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRegisterDataJson.write(userRegister).getJson()))
                .andReturn().getResponse();

        var jsonUser = userDetailsDataJson.write(userDetails).getJson();

        assertThat(responseUser.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(responseUser.getContentAsString()).isEqualTo(jsonUser);
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
