package com.fatec.sigvs_produto.model;

public record ProdutoDTO(Long id, String descricao, String categoria, double custo, int quantidadeNoEstoque) {
}
