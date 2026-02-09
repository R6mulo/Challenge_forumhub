package br.com.rorschach.forumhub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import br.com.rorschach.forumhub.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final String SECRET = "12345678";

    public String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("forumhub")
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(Algorithm.HMAC256(SECRET));
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
