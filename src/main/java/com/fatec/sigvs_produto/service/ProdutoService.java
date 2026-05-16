package com.fatec.sigvs_produto.service;

import com.fatec.sigvs_produto.model.Produto;
import com.fatec.sigvs_produto.model.ProdutoDTO;
import com.fatec.sigvs_produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProdutoDTO> consultarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProdutoDTO> consultarPorId(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    @Override
    public ProdutoDTO cadastrar(ProdutoDTO produtoDTO) {
        Produto produto = toEntity(produtoDTO);
        Produto salvo = repository.save(produto);
        return toDTO(salvo);
    }

    @Override
    public Optional<ProdutoDTO> atualizar(Long id, ProdutoDTO produtoDTO) {
        return repository.findById(id).map(existente -> {
            existente.setDescricao(produtoDTO.descricao());
            existente.setCategoria(produtoDTO.categoria());
            existente.setCusto(produtoDTO.custo());
            existente.setQuantidadeNoEstoque(produtoDTO.quantidadeNoEstoque());
            Produto atualizado = repository.save(existente);
            return toDTO(atualizado);
        });
    }

    @Override
    public boolean excluir(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getCusto(),
                produto.getQuantidadeNoEstoque()
        );
    }

    private Produto toEntity(ProdutoDTO dto) {
        return new Produto(
                dto.descricao(),
                dto.categoria(),
                dto.custo(),
                dto.quantidadeNoEstoque()
        );
    }
}
