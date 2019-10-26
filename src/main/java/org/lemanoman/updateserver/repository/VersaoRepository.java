package org.lemanoman.updateserver.repository;

import org.lemanoman.updateserver.model.Book;
import org.lemanoman.updateserver.model.Versao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VersaoRepository extends CrudRepository<Versao, Long> {
    List<Versao> findByNome(String title);
}
