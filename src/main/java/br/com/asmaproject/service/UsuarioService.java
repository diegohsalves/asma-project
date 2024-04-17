package br.com.asmaproject.service;

import br.com.asmaproject.domain.Funcao;
import br.com.asmaproject.domain.Usuario;
import br.com.asmaproject.dto.UsuarioRequestDTO;
import br.com.asmaproject.dto.UsuarioResponseDTO;
import br.com.asmaproject.exception.EmailIndisponivelException;
import br.com.asmaproject.exception.InvalidParameterException;
import br.com.asmaproject.exception.NotFoundException;
import br.com.asmaproject.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private FuncaoService funcaoService;

    public UsuarioService(UsuarioRepository usuarioRepository, FuncaoService funcaoService) {
        this.usuarioRepository = usuarioRepository;
        this.funcaoService = funcaoService;
    }

    public void criarNovoUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRequestDTO.getEmail() != null && !emailEstaDisponivel(usuarioRequestDTO.getEmail())) {
            throw new EmailIndisponivelException("Email já cadastrado!");
        }

        Funcao funcao = funcaoService.encontrarFuncaoPorNome(usuarioRequestDTO.getFuncao());
        Usuario novoUsuario = new Usuario(usuarioRequestDTO, funcao);

        usuarioRepository.save(novoUsuario);
    }

    public UsuarioResponseDTO buscarUsuarioPorId(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        return new UsuarioResponseDTO(usuario);
    }

    public boolean emailEstaDisponivel(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void alterarUsuario(String id, UsuarioRequestDTO usuarioRequestDTO) {
        if (id != null) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

            if (usuarioRequestDTO.getNome() != null) {
                usuario.setNome(usuarioRequestDTO.getNome());
            }

            if (usuarioRequestDTO.getEmail() != null) {
                if (!emailEstaDisponivel(usuarioRequestDTO.getEmail())) {
                    throw new EmailIndisponivelException("Email já cadastrado");
                }

                usuario.setEmail(usuarioRequestDTO.getEmail());
            }

            if (usuarioRequestDTO.getSenha() != null) {
                usuario.setSenha(usuarioRequestDTO.getSenha());
            }

            if (usuarioRequestDTO.getFuncao() != null) {
                usuario.setFuncao(funcaoService.encontrarFuncaoPorNome(usuarioRequestDTO.getFuncao()));
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
