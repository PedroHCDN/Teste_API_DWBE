package com.aulaspring.aulajpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
    private String cpf;
    @NotBlank
    private String perfil;
}
