package com.iftm.restaurante.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private long codigo;
    private String dataCompra;
    private double valor;
    private int numeroMesa;
    private List<Item> itens = new ArrayList<>();

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return dataCompra;
    }

    public void setData(String data) {
        this.dataCompra = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
        valor = 0.0;
        for (Item item : itens) {
            valor += item.getQuantidade() * item.getProduto().getPreco();
        }
    }

    public void adicionarItem(int quantidade, Produto produto) {
        itens.add(new Item(quantidade, produto));
        valor += quantidade * produto.getPreco();
    }

    @Override
    public String toString() {
        return "Pedido{" + "codigo=" + codigo + ", dataCompra=" + dataCompra + ", valor=" + valor + ", numeroMesa=" + numeroMesa + ", itens=" + itens + '}';
    }
    
    
}
