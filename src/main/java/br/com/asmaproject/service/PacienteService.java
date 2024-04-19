package br.com.asmaproject.service;

import br.com.asmaproject.domain.Paciente;
import br.com.asmaproject.dto.PacienteRegisterDTO;
import br.com.asmaproject.dto.PacienteResponseDTO;
import br.com.asmaproject.exception.CpfIndisponivelException;
import br.com.asmaproject.exception.NotFoundException;
import br.com.asmaproject.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public void cadastrar(PacienteRegisterDTO novoPaciente) {
        if (pacienteRepository.findByCpf(novoPaciente.cpf()).isPresent()) {
            throw new CpfIndisponivelException("CPF já cadastrado");
        }

        Paciente paciente = new Paciente(novoPaciente);
        pacienteRepository.save(paciente);
    }

    public PacienteResponseDTO buscarPaciente(String id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

        return new PacienteResponseDTO(paciente);
    }

    public void alterarDados(String id, PacienteRegisterDTO novosDadosPaciente) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

        if (!Objects.equals(paciente.getCpf(), novosDadosPaciente.cpf()) && pacienteRepository.findByCpf(novosDadosPaciente.cpf()).isPresent()) {
            throw new CpfIndisponivelException("CPF já cadastrado");
        }

        if (novosDadosPaciente.cpf() != null && !Objects.equals(paciente.getCpf(), novosDadosPaciente.cpf())) {
            paciente.setCpf(novosDadosPaciente.cpf());
        }

        if (novosDadosPaciente.nome() != null && !Objects.equals(paciente.getNome(), novosDadosPaciente.nome())) {
            paciente.setNome(novosDadosPaciente.nome());
        }

        if (novosDadosPaciente.sexo() != null && !Objects.equals(paciente.getSexo(), novosDadosPaciente.sexo())) {
            paciente.setSexo(novosDadosPaciente.sexo());
        }

        if (novosDadosPaciente.altura() != null && !novosDadosPaciente.altura().equals(paciente.getAltura())) {
            paciente.setAltura(novosDadosPaciente.altura());
        }

        if (novosDadosPaciente.peso() != null && !novosDadosPaciente.peso().equals(paciente.getPeso())) {
            paciente.setPeso(paciente.getPeso());
        }

        if (novosDadosPaciente.idade() != null && !novosDadosPaciente.idade().equals(paciente.getIdade())) {
            paciente.setIdade(novosDadosPaciente.idade());
        }

        if (novosDadosPaciente.endereco() != null && !novosDadosPaciente.endereco().equals(paciente.getEndereco())) {
            paciente.setEndereco(novosDadosPaciente.endereco());
        }

        if (novosDadosPaciente.telefone() != null && !novosDadosPaciente.telefone().equals(paciente.getTelefone())) {
            paciente.setTelefone(novosDadosPaciente.telefone());
        }

        if (novosDadosPaciente.email() != null && !novosDadosPaciente.email().equals(paciente.getEmail())) {
            paciente.setEmail(novosDadosPaciente.email());
        }

        pacienteRepository.save(paciente);
    }

    public void deletar(String id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

        paciente.setDeleted(true);
        pacienteRepository.save(paciente);
    }
}