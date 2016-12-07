package com.iftm.restaurante.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.iftm.restaurante.model.Item;
import com.iftm.restaurante.model.Pedido;
import com.iftm.restaurante.model.Produto;

public class ItemDAO {

    private final Connection conexao;

    public ItemDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void gravar(Item item, Pedido pedido) throws SQLException {
        String insercao = "INSERT INTO item (quantidade, produto_codigo, pedido_codigo) VALUES (?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, item.getQuantidade());
            pstmt.setLong(2, item.getProduto().getCodigo());
            pstmt.setLong(3, pedido.getCodigo());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\nInserção bem sucedida.");
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        item.setCodigo(rs.getLong(1));
                    }
                }
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }
    }

    public void buscarTodos(Pedido pedido) throws SQLException {
        Item item;
        Produto produto;
        List<Item> itens = new ArrayList<>();
        ProdutoDAO dao = new ProdutoDAO(conexao);
        String selecao = "SELECT * FROM item WHERE pedido_codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, pedido.getCodigo());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    item = new Item();
                    item.setCodigo(rs.getLong(1));
                    produto = dao.buscar(rs.getLong(2));
                    item.setProduto(produto);
                    item.setQuantidade(rs.getInt(4));                  
                    itens.add(item);
                }
                pedido.setItens(itens);
            }
        }
    }
}
