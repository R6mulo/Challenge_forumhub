package br.com.rorschach.forumhub.domain.topico.dto;

import br.com.rorschach.forumhub.domain.topico.Topico;
import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, String autor, String curso) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getAutor().getLogin(),
                topico.getCurso().getNome());
    }
}