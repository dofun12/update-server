package org.lemanoman.updateserver.repository;

import org.lemanoman.updateserver.model.Alternativa;
import org.lemanoman.updateserver.model.Pergunta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlternativaRepository extends CrudRepository<Alternativa, Long> {
    List<Alternativa> findByIdPergunta(Long idPergunta);
}
