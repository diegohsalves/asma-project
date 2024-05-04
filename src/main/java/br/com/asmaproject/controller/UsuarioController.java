package br.com.asmaproject.controller;

import br.com.asmaproject.dto.UsuarioRegisterDTO;
import br.com.asmaproject.dto.UsuarioResponseDTO;
import br.com.asmaproject.exception.ApiResponse;
import br.com.asmaproject.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> buscarUsuario(@PathVariable @Valid String id) {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), usuario));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> buscarTodosUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), usuarios));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ApiResponse<?>> atualizarUsuario(@PathVariable String id, @RequestBody @Valid UsuarioRegisterDTO usuarioRegisterDTO) {
        usuarioService.alterarUsuario(id, usuarioRegisterDTO);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Dados alterados com sucesso!"));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ApiResponse<?>> deletarUsuario(@PathVariable @Valid String id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Dados alterados com sucesso!"));
    }
}