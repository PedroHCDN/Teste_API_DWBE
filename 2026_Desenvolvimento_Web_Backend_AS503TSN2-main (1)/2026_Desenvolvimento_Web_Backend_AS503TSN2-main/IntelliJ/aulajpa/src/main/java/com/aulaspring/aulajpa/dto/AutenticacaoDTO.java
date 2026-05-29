package com.aulaspring.aulajpa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class AutenticacaoDTO {
    private String email;
    private String senha;

}
