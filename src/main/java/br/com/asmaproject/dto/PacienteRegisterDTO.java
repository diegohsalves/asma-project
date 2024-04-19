package br.com.asmaproject.dto;

import br.com.asmaproject.domain.enums.Sexo;

public record PacienteRegisterDTO(String cpf, String nome, Sexo sexo, Double altura, Double peso, Integer idade, String endereco, String telefone, String email) {
}