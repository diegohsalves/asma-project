package br.com.asmaproject.domain.enums;

public enum Funcao {
    ADMIN("Administrador"),
    SUPERVISOR("Supervisor"),
    USUARIO("Usuario");

    private String nome;

    Funcao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}