package br.com.rorschach.forumhub.domain.topico;

import br.com.rorschach.forumhub.domain.curso.CursoRepository;
import br.com.rorschach.forumhub.domain.topico.dto.DadosCadastroTopico;
import br.com.rorschach.forumhub.domain.usuario.Usuario;
import br.com.rorschach.forumhub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico criar(DadosCadastroTopico dados, String loginUsuario) {
        // 1. Busca o usuário logado (o subject do token)
        var usuario = (Usuario) usuarioRepository.findByLogin(loginUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        // 2. Busca o curso pelo nome enviado no JSON
        var curso = cursoRepository.findByNome(dados.nomeCurso());
        if (curso == null) {
            throw new RuntimeException("Curso não encontrado!");
        }

        // 3. Cria e salva o tópico
        var topico = new Topico(dados.titulo(), dados.mensagem(), usuario, curso);
        return topicoRepository.save(topico);
    }
}