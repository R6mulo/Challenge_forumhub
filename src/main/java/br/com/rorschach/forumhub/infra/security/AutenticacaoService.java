package br.com.rorschach.forumhub.infra.security;

import br.com.rorschach.forumhub.domain.usuario.UsuarioRepository; // IMPORT CORRIGIDO AQUI
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Tentando autenticar o usuário: " + username);
        var usuario = repository.findByLogin(username);
        if (usuario == null) {
            System.out.println("Usuário não encontrado no banco!");
        } else {
            System.out.println("Usuário encontrado! Validando senha...");
        }
        return usuario;
    }
}