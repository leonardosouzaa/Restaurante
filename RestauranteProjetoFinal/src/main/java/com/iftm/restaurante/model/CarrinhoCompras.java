package com.iftm.restaurante.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarrinhoCompras {
    private List<Item> itens = new ArrayList<>();

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void adicionarItem(int quantidade, Produto produto) {
        itens.add(new Item(quantidade, produto));
    }
    
    public void esvaziar() {
        itens = new ArrayList<>();
    }
}
