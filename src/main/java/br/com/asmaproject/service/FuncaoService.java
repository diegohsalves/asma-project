package br.com.asmaproject.service;

import br.com.asmaproject.domain.Funcao;
import br.com.asmaproject.exception.NotFoundException;
import br.com.asmaproject.repository.FuncaoRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncaoService {

    FuncaoRepository funcaoRepository;

    public FuncaoService(FuncaoRepository funcaoRepository) {
        this.funcaoRepository = funcaoRepository;
    }

    Funcao encontrarFuncaoPorNome(String funcaoNome) {
        return funcaoRepository.findByNome(funcaoNome)
                .orElseThrow(() -> new NotFoundException("Função não encontrada"));
    }
}