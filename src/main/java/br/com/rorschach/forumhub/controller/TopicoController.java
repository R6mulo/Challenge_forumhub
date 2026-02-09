package br.com.rorschach.forumhub.controller;

import br.com.rorschach.forumhub.domain.topico.Topico;
import br.com.rorschach.forumhub.domain.topico.TopicoRepository;
import br.com.rorschach.forumhub.domain.topico.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository repository;

    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    // POST
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroTopico dados) {

        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus("ABERTO");

        repository.save(topico);

        return ResponseEntity.ok().build();
    }

    // GET LISTAGEM
    @GetMapping
    public ResponseEntity<List<Topico>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    // GET DETALHAMENTO
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {

        var topicoOptional = repository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();

        return ResponseEntity.ok(
                new DadosDetalhamentoTopico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensagem(),
                        topico.getDataCriacao(),
                        topico.getStatus()
                )
        );
    }

    // PUT  ✅ CORRIGIDO
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoTopico dados) {

        var topico = repository.findById(dados.id()).orElseThrow();

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        return ResponseEntity.ok().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}