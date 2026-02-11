package br.com.rorschach.forumhub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Este método faz um: SELECT * FROM cursos WHERE nome = ?
    Curso findByNome(String nome);

}