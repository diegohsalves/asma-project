package br.com.asmaproject.dto;

import br.com.asmaproject.domain.enums.Role;

public record UsuarioRegisterDTO(String nome, String email, String senha, Role role) {
}
