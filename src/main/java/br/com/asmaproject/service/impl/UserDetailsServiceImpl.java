package br.com.asmaproject.service.impl;

import br.com.asmaproject.domain.UserAuthenticated;
import br.com.asmaproject.exception.NotFoundException;
import br.com.asmaproject.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .map(UserAuthenticated::new)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }
}