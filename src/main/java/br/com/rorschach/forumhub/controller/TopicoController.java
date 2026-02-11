package br.com.rorschach.forumhub.controller;

import br.com.rorschach.forumhub.domain.topico.*;
import br.com.rorschach.forumhub.domain.topico.dto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key") // Indica ao Swagger que este controller exige Token JWT
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private TopicoService service;

    /**
     * Endpoint: POST /topicos
     * Objetivo: Criar um novo tópico vinculado ao usuário autenticado.
     */
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados,
                                    Authentication authentication,
                                    UriComponentsBuilder uriBuilder) {

        // O service utiliza o authentication.getName() para identificar o autor pelo Token
        var topico = service.criar(dados, authentication.getName());

        // Boas práticas REST: Retorna a URL do novo recurso no cabeçalho 'Location'
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    /**
     * Endpoint: GET /topicos
     * Objetivo: Listar todos os tópicos de forma paginada e ordenada por data.
     */
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {

        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    /**
     * Endpoint: GET /topicos/{id}
     * Objetivo: Retornar os detalhes completos de um tópico específico.
     */
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        // Busca a referência no banco. Se não existir, o Spring lança EntityNotFoundException
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    /**
     * Endpoint: PUT /topicos/{id}
     * Objetivo: Atualizar título e mensagem de um tópico existente.
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = repository.getReferenceById(id);

        // Lógica de atualização dentro da entidade para manter o encapsulamento
        topico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    /**
     * Endpoint: DELETE /topicos/{id}
     * Objetivo: Remover um tópico do banco de dados.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        // Verifica se o ID existe antes de tentar deletar
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        // Retorno padrão 204 No Content para deleções bem-sucedidas
        return ResponseEntity.noContent().build();
    }
}