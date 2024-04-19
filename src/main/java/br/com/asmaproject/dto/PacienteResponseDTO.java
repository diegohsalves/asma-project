package br.com.asmaproject.dto;

import br.com.asmaproject.domain.Paciente;
import br.com.asmaproject.domain.enums.Sexo;

public class PacienteResponseDTO {
    private String nome;
    private Sexo sexo;
    private Double altura;
    private Double peso;
    private Integer idade;
    private String endereco;
    private String telefone;
    private String email;

    public PacienteResponseDTO() {}

    public PacienteResponseDTO(Paciente paciente) {
        this.nome = paciente.getNome();
        this.sexo = paciente.getSexo();
        this.altura = paciente.getAltura();
        this.peso = paciente.getPeso();
        this.idade = paciente.getIdade();
        this.endereco = paciente.getEndereco();
        this.telefone = paciente.getTelefone();
        this.email = paciente.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}