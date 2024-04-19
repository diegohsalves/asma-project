package br.com.asmaproject.domain;

import br.com.asmaproject.domain.enums.Sexo;
import br.com.asmaproject.dto.PacienteRegisterDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document
public class Paciente implements Serializable {

    @Id
    private String id;
    @Indexed(unique = true)
    private String cpf;
    private String nome;
    private Sexo sexo;
    private Double altura;
    private Double peso;
    private Integer idade;
    private String endereco;
    private String telefone;
    private String email;
    private boolean isDeleted = false;

    public Paciente() {}

    public Paciente(String cpf, String nome, Sexo sexo, Double altura, Double peso, Integer idade, String endereco, String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.altura = altura;
        this.peso = peso;
        this.idade = idade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Paciente(PacienteRegisterDTO novoPaciente) {
        this.cpf = novoPaciente.cpf();
        this.nome = novoPaciente.nome();
        this.sexo = novoPaciente.sexo();
        this.altura = novoPaciente.altura();
        this.peso = novoPaciente.peso();
        this.idade = novoPaciente.idade();
        this.endereco = novoPaciente.endereco();
        this.telefone = novoPaciente.telefone();
        this.email = novoPaciente.email();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id) && Objects.equals(cpf, paciente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}