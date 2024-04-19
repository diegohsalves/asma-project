package br.com.asmaproject.service;

import br.com.asmaproject.domain.Usuario;
import br.com.asmaproject.dto.UsuarioRegisterDTO;
import br.com.asmaproject.dto.UsuarioResponseDTO;
import br.com.asmaproject.exception.EmailIndisponivelException;
import br.com.asmaproject.exception.InvalidParameterException;
import br.com.asmaproject.exception.NotFoundException;
import br.com.asmaproject.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void criarNovoUsuario(UsuarioRegisterDTO usuarioRegisterDTO) {
        if (usuarioRepository.findByEmail(usuarioRegisterDTO.email()).isPresent()) {
            throw new EmailIndisponivelException("Email já cadastrado!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRegisterDTO.senha());
        Usuario usuario = new Usuario(usuarioRegisterDTO.nome(), usuarioRegisterDTO.email(), encryptedPassword, usuarioRegisterDTO.funcao());

        usuarioRepository.save(usuario);
    }

    public UsuarioResponseDTO buscarUsuarioPorId(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        return new UsuarioResponseDTO(usuario);
    }

    public void alterarUsuario(String id, UsuarioRegisterDTO usuarioRegisterDTO) {
        if (id != null) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

            if (usuarioRegisterDTO.email() != null) {
                if (usuarioRepository.findByEmail(usuarioRegisterDTO.email()).isPresent()) {
                    throw new EmailIndisponivelException("Email já cadastrado");
                }

                usuario.setEmail(usuarioRegisterDTO.email());
            }

            if (usuarioRegisterDTO.nome() != null) {
                usuario.setNome(usuarioRegisterDTO.nome());
            }

            if (usuarioRegisterDTO.senha() != null) {
                usuario.setSenha(usuarioRegisterDTO.senha());
            }

            if (usuarioRegisterDTO.funcao() != null) {
                usuario.setFuncao(usuarioRegisterDTO.funcao());
            }

            usuarioRepository.save(usuario);
        } else {
            throw new InvalidParameterException("Id está nulo");
        }
    }

    public void deletarUsuario(String id) {
        if (id != null) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

            usuarioRepository.delete(usuario);
        } else {
            throw new InvalidParameterException("Id está nulo");
        }
    }

}