package br.com.rorschach.forumhub.infra.security;

import br.com.rorschach.forumhub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    @Autowired
    private JwtEncoder encoder;

    public String gerarToken(Usuario usuario) {
        // 1. Definimos o cabeçalho explicitamente como HS256
        var header = JwsHeader.with(MacAlgorithm.HS256).build();

        // 2. Definimos os dados do usuário (Claims)
        var claims = JwtClaimsSet.builder()
                .issuer("ForumHub API")
                .subject(usuario.getLogin())
                .expiresAt(Instant.now().plusSeconds(3600)) // 1 hora
                .build();

        // 3. Passamos o header e os claims para o encoder
        return encoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}