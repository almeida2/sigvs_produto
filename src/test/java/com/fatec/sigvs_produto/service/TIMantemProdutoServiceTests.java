package com.fatec.sigvs_produto.service;

import com.fatec.sigvs_produto.model.ProdutoDTO;
import com.fatec.sigvs_produto.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testes de integração para a classe TIMantemProdutoService, garantindo o correto funcionamento dos métodos de cadastro, consulta, atualização e exclusão de produtos, bem como a validação dos dados de entrada.
 */
@SpringBootTest
public class TIMantemProdutoServiceTests {

    @Autowired
    private IProdutoService service;

    @Autowired
    private ProdutoRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void deveCadastrarProdutoComSucesso() {
        // GIVEN
        ProdutoDTO produtoDTO = new ProdutoDTO(null, "Eletro", "Geladeira", 2500.0, 10);

        // WHEN
        ProdutoDTO salvo = service.cadastrar(produtoDTO);

        // THEN
        assertNotNull(salvo.id());
        assertEquals("Eletro", salvo.descricao());
    }

    @Test
    void deveRetornarErroAoCadastrarComDescricaoVazia() {
        // GIVEN
        ProdutoDTO produtoDTO = new ProdutoDTO(null, "", "Geladeira", 2500.0, 10);

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(produtoDTO);
        });

        // THEN
        assertEquals("A descrição não deve estar vazia", exception.getMessage());
    }

    @Test
    void deveRetornarErroAoCadastrarComCategoriaVazia() {
        // GIVEN
        ProdutoDTO produtoDTO = new ProdutoDTO(null, "Eletro", "", 2500.0, 10);

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(produtoDTO);
        });

        // THEN
        assertEquals("A categoria não deve estar vazia", exception.getMessage());
    }

    @Test
    void deveRetornarErroAoCadastrarComCustoZero() {
        // GIVEN
        ProdutoDTO produtoDTO = new ProdutoDTO(null, "Eletro", "Geladeira", 0.0, 10);

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(produtoDTO);
        });

        // THEN
        assertEquals("O custo não deve ser zero ou negativo", exception.getMessage());
    }

    @Test
    void deveRetornarErroAoCadastrarComQuantidadeNegativa() {
        // GIVEN
        ProdutoDTO produtoDTO = new ProdutoDTO(null, "Eletro", "Geladeira", 2500.0, -1);

        // WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(produtoDTO);
        });

        // THEN
        assertEquals("A quantidade no estoque não deve ser zero ou negativa", exception.getMessage());
    }

    @Test
    void deveConsultarTodosOsProdutos() {
        // GIVEN
        service.cadastrar(new ProdutoDTO(null, "Eletro", "Geladeira", 2500.0, 10));
        service.cadastrar(new ProdutoDTO(null, "Moveis", "Sofa", 1500.0, 5));

        // WHEN
        List<ProdutoDTO> produtos = service.consultarTodos();

        // THEN
        assertEquals(2, produtos.size());
    }

    @Test
    void deveConsultarProdutoPorId() {
        // GIVEN
        ProdutoDTO salvo = service.cadastrar(new ProdutoDTO(null, "Eletro", "Geladeira", 2500.0, 10));

        // WHEN
        Optional<ProdutoDTO> consultado = service.consultarPorId(salvo.id());

        // THEN
        assertTrue(consultado.isPresent());
        assertEquals(salvo.id(), consultado.get().id());
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        // GIVEN
        ProdutoDTO salvo = service.cadastrar(new ProdutoDTO(null, "Eletro", "Geladeira", 2500.0, 10));
        ProdutoDTO dadosAtualizados = new ProdutoDTO(salvo.id(), "Eletro Premium", "Geladeira Frost Free", 3000.0, 15);

        // WHEN
        Optional<ProdutoDTO> atualizado = service.atualizar(salvo.id(), dadosAtualizados);

        // THEN
        assertTrue(atualizado.isPresent());
        assertEquals("Eletro Premium", atualizado.get().descricao());
        assertEquals(3000.0, atualizado.get().custo());
    }

    @Test
    void deveExcluirProdutoComSucesso() {
        // GIVEN
        ProdutoDTO salvo = service.cadastrar(new ProdutoDTO(null, "Eletro", "Geladeira", 2500.0, 10));

        // WHEN
        boolean excluido = service.excluir(salvo.id());

        // THEN
        assertTrue(excluido);
        assertFalse(service.consultarPorId(salvo.id()).isPresent());
    }
}
