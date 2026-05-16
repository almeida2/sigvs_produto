package com.fatec.sigvs_produto.service;

import com.fatec.sigvs_produto.model.ProdutoDTO;
import java.util.List;
import java.util.Optional;

public interface IProdutoService {
    List<ProdutoDTO> consultarTodos();
    Optional<ProdutoDTO> consultarPorId(Long id);
    ProdutoDTO cadastrar(ProdutoDTO produtoDTO);
    Optional<ProdutoDTO> atualizar(Long id, ProdutoDTO produtoDTO);
    boolean excluir(Long id);
}
