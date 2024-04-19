package br.com.asmaproject.dto;

import br.com.asmaproject.domain.Usuario;

import java.io.Serial;
import java.io.Serializable;

public class UsuarioResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String nome;
    private String email;
    private String funcao;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.funcao = usuario.getFuncao().name();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
