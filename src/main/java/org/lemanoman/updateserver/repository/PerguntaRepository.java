package org.lemanoman.updateserver.repository;

import org.lemanoman.updateserver.model.Book;
import org.lemanoman.updateserver.model.Pergunta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerguntaRepository extends CrudRepository<Pergunta, Long> {
}
