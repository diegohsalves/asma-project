package br.com.asmaproject.domain.enums;

public enum Role {
    ADMIN("Administrador"),
    SUPERVISOR("Supervisor"),
    USUARIO("Usuario");

    private String nome;

    Role(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}