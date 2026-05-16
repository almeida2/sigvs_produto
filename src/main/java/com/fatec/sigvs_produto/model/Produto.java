package com.fatec.sigvs_produto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricao;
    private String categoria;
    private double custo;
    private int quantidadeNoEstoque;

    public Produto() {
    }

    public Produto(String descricao, String categoria, double custo, int quantidadeNoEstoque) {
        setDescricao(descricao);
        setCategoria(categoria);
        setCusto(custo);
        setQuantidadeNoEstoque(quantidadeNoEstoque);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição não deve estar vazia");
        }
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("A categoria não deve estar vazia");
        }
        this.categoria = categoria;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        if (custo <= 0) {
            throw new IllegalArgumentException("O custo não deve ser zero ou negativo");
        }
        this.custo = custo;
    }

    public int getQuantidadeNoEstoque() {
        return quantidadeNoEstoque;
    }

    public void setQuantidadeNoEstoque(int quantidadeNoEstoque) {
        if (quantidadeNoEstoque <= 0) {
            throw new IllegalArgumentException("A quantidade no estoque não deve ser zero ou negativa");
        }
        this.quantidadeNoEstoque = quantidadeNoEstoque;
    }
}
