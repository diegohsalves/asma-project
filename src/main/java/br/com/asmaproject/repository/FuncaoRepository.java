package br.com.asmaproject.repository;

import br.com.asmaproject.domain.Funcao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FuncaoRepository extends MongoRepository<Funcao, String> {
    Optional<Funcao> findByNome(String nome);
}
