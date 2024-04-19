package br.com.asmaproject.controller;

import br.com.asmaproject.dto.PacienteRegisterDTO;
import br.com.asmaproject.dto.PacienteResponseDTO;
import br.com.asmaproject.exception.ApiResponse;
import br.com.asmaproject.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ApiResponse<?>> cadastrar(@RequestBody @Valid PacienteRegisterDTO novoPaciente) {
        pacienteService.cadastrar(novoPaciente);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Paciente cadastrado com sucesso!"));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ApiResponse<PacienteResponseDTO>> buscar(@PathVariable @Valid String id) {
        PacienteResponseDTO paciente = pacienteService.buscarPaciente(id);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Busca realizada com sucesso!", paciente));
    }

    @PutMapping("/alterarDados/{id}")
    public ResponseEntity<ApiResponse<?>> alterarDados(@PathVariable @Valid String id, @RequestBody @Valid PacienteRegisterDTO novosDadosPaciente) {
        pacienteService.alterarDados(id, novosDadosPaciente);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Dados alterados com sucesso!"));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ApiResponse<?>> deletar(@PathVariable @Valid String id) {
        pacienteService.deletar(id);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Paciente deletado com sucesso!"));
    }
}