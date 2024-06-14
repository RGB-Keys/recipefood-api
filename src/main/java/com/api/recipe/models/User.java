package com.api.recipe.models;

import com.api.recipe.dtos.users.UserRegisterData;
import com.api.recipe.dtos.users.UserType;
import com.api.recipe.dtos.users.UserUpdateData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private UserType categoria;
    @Embedded
    private Address endereco;

    public User(UserRegisterData data) {
        this.nome = data.nome();
        this.email = data.email();
        this.cpf = data.cpf();
        this.telefone = data.telefone();
        this.categoria = data.categoria();
        this.endereco = new Address(data.endereco());
    }

    public void updateUser(UserUpdateData data) {
        if(data.nome() != null){
            this.nome = data.nome();
        }
        if(data.email() != null){
            this.email = data.email();
        }
        if(data.telefone() != null) {
            this.telefone = data.telefone();
        }
        if(data.categoria() != null) {
            this.categoria = data.categoria();
        }
        if(data.endereco() != null) {
            this.endereco = data.endereco();
        }
    }

}
