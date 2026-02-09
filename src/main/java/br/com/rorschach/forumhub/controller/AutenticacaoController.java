package br.com.rorschach.forumhub.controller;

import br.com.rorschach.forumhub.domain.usuario.DadosLogin;
import br.com.rorschach.forumhub.domain.usuario.Usuario;
import br.com.rorschach.forumhub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public String login(@RequestBody @Valid DadosLogin dados) {
        var authenticationToken =
                new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        var authentication = manager.authenticate(authenticationToken);

        var usuario = (Usuario) authentication.getPrincipal();

        return tokenService.gerarToken(usuario);
    }
}
