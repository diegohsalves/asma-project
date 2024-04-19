package br.com.asmaproject.dto;

import br.com.asmaproject.domain.enums.Funcao;

public record UsuarioRegisterDTO(String nome, String email, String senha, Funcao funcao) {
}
