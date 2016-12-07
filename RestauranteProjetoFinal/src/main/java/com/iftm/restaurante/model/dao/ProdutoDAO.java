package com.iftm.restaurante.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.iftm.restaurante.model.Produto;

public class ProdutoDAO {
    
    private final Connection conexao;

    public ProdutoDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public void gravar(Produto produto) throws SQLException {
        String insercao = "INSERT INTO produto (nome, descricao, preco) VALUES (?, ?, ?);";
        try (PreparedStatement pstmt = conexao.prepareStatement(insercao, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getDescricao());
            pstmt.setDouble(3, produto.getPreco());
            int resultado = pstmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("\nInserção bem sucedida.");
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        produto.setCodigo(rs.getLong(1));
                    }
                }
            } else {
                System.out.println("A inserção não foi feita corretamente.");
            }
        }
    }

    public Produto buscar(long codigo) throws SQLException {
        Produto produto = null;
        String selecao = "SELECT * FROM produto WHERE codigo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(selecao)) {
            pstmt.setLong(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setCodigo(rs.getLong(1));
                    produto.setNome(rs.getString(2));
                    produto.setDescricao(rs.getString(3));
                    produto.setPreco(rs.getDouble(4));
                }
            }
        }
        return produto;
    }

    public List<Produto> buscarTodos() throws SQLException {
        Produto produto;
        List<Produto> produtos = new ArrayList<Produto>();
        String selecao = "SELECT * FROM produto";
        try (Statement stmt = conexao.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(selecao)) {
                while (rs.next()) {
                    produto = new Produto();
                    produto.setCodigo(rs.getLong(1));
                    produto.setNome(rs.getString(2));
                    produto.setDescricao(rs.getString(3));
                    produto.setPreco(rs.getDouble(4));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
}
