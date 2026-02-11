package br.com.rorschach.forumhub.domain.topico.dto;

import br.com.rorschach.forumhub.domain.topico.Topico;
import java.time.LocalDateTime;

public record DadosListagemTopico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao) {
    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao());
    }
}