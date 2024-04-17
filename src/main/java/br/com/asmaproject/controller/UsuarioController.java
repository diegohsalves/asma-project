package br.com.asmaproject.controller;

import br.com.asmaproject.dto.UsuarioRequestDTO;
import br.com.asmaproject.dto.UsuarioResponseDTO;
import br.com.asmaproject.exception.ApiResponse;
import br.com.asmaproject.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse<?>> criarNovoUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        usuarioService.criarNovoUsuario(usuarioRequestDTO);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Usuário cadastrado com sucesso!"));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> buscarUsuario(@PathVariable String id) {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), usuario));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ApiResponse<?>> atualizarUsuario(@PathVariable String id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        usuarioService.alterarUsuario(id, usuarioRequestDTO);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Dados alterados com sucesso!"));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ApiResponse<?>> deletarUsuario(@PathVariable String id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Dados alterados com sucesso!"));
    }
}