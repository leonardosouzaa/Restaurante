package com.iftm.restaurante.model;

import com.iftm.restaurante.model.Produto;
import com.iftm.restaurante.model.Item;
import com.iftm.restaurante.model.Pedido;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PedidoTest {
    
    public PedidoTest() {
    }
    
    @Test
    public void testAdicionarItem() {
        Pedido mockPedido = mock(Pedido.class);
        List mockListProduto = mock(ArrayList.class);
        when(mockListProduto.size()).thenReturn(1);       
        
        when(mockPedido.getItens()).thenReturn(mockListProduto);
        Produto mockProduto = testCreateMockProduto();
        Item mockItem = testCreateMockItem(mockProduto);
        mockPedido.adicionarItem(2, mockProduto);
        
        assertEquals(1, mockPedido.getItens().size());
    }
     private Produto testCreateMockProduto(){
        Produto mockProduto = mock(Produto.class);
        mockProduto.setCodigo(1);
        mockProduto.setNome("Produto Teste");
        mockProduto.setDescricao("Produto de Teste");
        mockProduto.setPreco(1.5);
        return mockProduto;
    }
     
     private Item testCreateMockItem(Produto mockProduto){
        Item mockItem = mock(Item.class);
        mockItem.setCodigo(1);
        mockItem.setQuantidade(2);
        mockItem.setProduto(mockProduto);
        return mockItem;
    }
}
