package com.iftm.restaurante.model;

import com.iftm.restaurante.model.Produto;
import com.iftm.restaurante.model.CarrinhoCompras;
import com.iftm.restaurante.model.Item;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CarrinhoComprasTest {
    
    
    public CarrinhoComprasTest() {  
    }

    @Test
    public void testGetItens() {
        CarrinhoCompras mockCarrinhoCompras = mock(CarrinhoCompras.class);
        int quantidade = 2;
        Produto produto = createMockProduto();
        mockCarrinhoCompras.adicionarItem(quantidade, produto);
        assertEquals(createNewListItensWithProdutos(), mockCarrinhoCompras.getItens());
    }
    
    @Test
    public void testGetItensEmpty() {
        CarrinhoCompras mockCarrinhoCompras = mock(CarrinhoCompras.class);
        assertEquals(createNewListItensEmpty(), mockCarrinhoCompras.getItens());
    }
    
    @Test(expected = Exception.class) 
    public void testGetItensNull() {
        CarrinhoCompras mockCarrinhoCompras = mock(CarrinhoCompras.class);
        mockCarrinhoCompras = null;
        mockCarrinhoCompras.getItens();
    }

    @Test
    public void testAdicionarItem() {
        int quantidade = 2;
        Produto produto = createMockProduto();
        CarrinhoCompras mockCarrinhoCompras = mock(CarrinhoCompras.class);
        mockCarrinhoCompras.adicionarItem(quantidade, produto);
        assertEquals(createNewListItensWithProdutos(), mockCarrinhoCompras.getItens());
    }

    @Test
    public void testEsvaziar() {
        CarrinhoCompras mockCarrinhoCompras = mock(CarrinhoCompras.class);
        mockCarrinhoCompras.esvaziar();
        assertEquals(createNewListItensEmpty(), mockCarrinhoCompras.getItens());
    }
    
    private Produto createMockProduto(){
        Produto mockProduto = mock(Produto.class);
        mockProduto.setCodigo(1);
        mockProduto.setNome("Produto Teste");
        mockProduto.setDescricao("Produto de Teste");
        mockProduto.setPreco(1.5);
        return mockProduto;
    }
    
    private List<Item> createNewListItensEmpty(){
        List<Item> itens = new ArrayList<>();
        return itens;
    }
    
    private List<Item> createNewListItensWithProdutos(){
        List<Item> itens = new ArrayList<>();
        Item item = new Item(1, 2, createMockProduto());
        return itens;
    }
}
