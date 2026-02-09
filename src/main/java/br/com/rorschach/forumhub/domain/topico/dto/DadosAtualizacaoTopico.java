package br.com.rorschach.forumhub.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(

        @NotNull
        Long id,

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem
) {}
