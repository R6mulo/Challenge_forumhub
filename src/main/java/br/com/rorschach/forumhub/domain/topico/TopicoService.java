package br.com.rorschach.forumhub.domain.topico;

import br.com.rorschach.forumhub.domain.curso.Curso;
import br.com.rorschach.forumhub.domain.curso.CursoRepository;
import br.com.rorschach.forumhub.domain.topico.dto.DadosCadastroTopico;
import br.com.rorschach.forumhub.domain.usuario.Usuario;
import br.com.rorschach.forumhub.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import br.com.rorschach.forumhub.domain.topico.dto.DadosListagemTopico;
import java.util.List;
import br.com.rorschach.forumhub.domain.topico.dto.DadosDetalhamentoTopico;
import jakarta.transaction.Transactional;
import br.com.rorschach.forumhub.domain.topico.dto.DadosAtualizacaoTopico;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    private final TopicoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository repository,
                         UsuarioRepository usuarioRepository,
                         CursoRepository cursoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public Topico cadastrar(DadosCadastroTopico dados) {

        Usuario autor = usuarioRepository.findById(dados.autorId())
                .orElseThrow();

        Curso curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow();

        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus("ABERTO");
        topico.setAutor(autor);
        topico.setCurso(curso);

        return repository.save(topico);
    }

    public List<DadosListagemTopico> listar() {
        return repository.findAll().stream()
                .map(t -> new DadosListagemTopico(
                        t.getId(),
                        t.getTitulo(),
                        t.getMensagem(),
                        t.getDataCriacao(),
                        t.getStatus()
                ))
                .toList();
    }

    public DadosDetalhamentoTopico detalhar(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow();

        return new DadosDetalhamentoTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus()
        );

    }

    @Transactional
    public void atualizar(DadosAtualizacaoTopico dados) {

        Topico topico = repository.findById(dados.id())
                .orElseThrow();

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
