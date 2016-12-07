package com.iftm.restaurante.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.iftm.restaurante.model.Item;
import com.iftm.restaurante.model.Pedido;
import com.iftm.restaurante.model.Produto;
import com.iftm.restaurante.model.dao.DAOFactory;
import com.iftm.restaurante.model.dao.ItemDAO;
import com.iftm.restaurante.model.dao.PedidoDAO;
import com.iftm.restaurante.model.dao.ProdutoDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ServletControleVendas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String caminho = request.getServletPath();
        if (caminho.equals("/Vendas/comprar")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                ProdutoDAO dao = factory.criarProdutoDAO();
                List<Produto> produtos = dao.buscarTodos();

                request.setAttribute("produtos", produtos);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/comprar.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        }if (caminho.equals("/Vendas/cadastrar")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                
                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/cadastrar.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        }if (caminho.equals("/Vendas/cadastrarProduto")) {

            Produto produto = new Produto();
            DAOFactory factory = new DAOFactory();
            
            String nome      = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            double preco     = Double.parseDouble(request.getParameter("preco"));

            try {
                factory.abrirConexao();
                ProdutoDAO dao = factory.criarProdutoDAO();
                produto.setNome(nome);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                
                dao.gravar(produto);

                response.sendRedirect("http://localhost:8080/RestauranteProjetoFinal/Vendas/cadastrar");
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        }
        if (caminho.equals("/Vendas/adicionarCarrinho")) {
            Produto produto;
            Item item = new Item();
            Pedido pedido = new Pedido();
            List<Item> itens = new ArrayList<>();
            DAOFactory factory = new DAOFactory();
            int codigo;
            int quantidade;
            double valor;
            codigo = Integer.parseInt(request.getParameter("produto"));
            quantidade = Integer.parseInt(request.getParameter("quantidade"));
            
            if (request.getSession().getAttribute("itens") != null) {
                itens = (List<Item>) request.getSession().getAttribute("itens");
            }
            try {
                factory.abrirConexao();
                ProdutoDAO dao = factory.criarProdutoDAO();
                produto = dao.buscar(codigo);
                item.setProduto(produto);
                item.setQuantidade(quantidade);

                valor = produto.getPreco() * quantidade;
                itens.add(item);
                request.getSession().setAttribute("itens", itens);
                request.getSession().setAttribute("valor", valor);

                response.sendRedirect("http://localhost:8080/RestauranteProjetoFinal/Vendas/comprar?bt_compra=Fazer+compra");
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        }
        if (caminho.equals("/Vendas/finalizarPedido")) {
            List<Item> itens = new ArrayList<>();
            itens = (List<Item>) request.getSession().getAttribute("itens");
            Pedido pedido = new Pedido();
            Item item = new Item();
            
            int mesa = Integer.parseInt(request.getParameter("mesa"));
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            DAOFactory factory = new DAOFactory();
            double valor = (double) request.getSession().getAttribute("valor");
            pedido.setItens(itens);
            pedido.setData(formato.format(cal.getTime()));
            pedido.setValor(valor);
            pedido.setNumeroMesa(mesa);
            try {
                factory.abrirConexao();
                ItemDAO daoitem = factory.criarItemDAO();
                PedidoDAO dao = factory.criarPedidoDAO();
                dao.gravar(pedido);
                request.getSession().removeAttribute("itens");
                request.getSession().removeAttribute("valor");
                response.sendRedirect("http://localhost:8080/RestauranteProjetoFinal/");

            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
