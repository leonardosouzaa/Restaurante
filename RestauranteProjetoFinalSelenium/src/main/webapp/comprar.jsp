<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedido</title>
    </head>
    <body>
        <form name="adicionarCarrinho" action="/RestauranteProjetoFinal/Vendas/adicionarCarrinho" method="POST">
            <label>Quantidade:</label><input type="text" name="quantidade" id="quantidade" size="5"> 
            <select name="produto">
                <c:forEach var="produto" items="${produtos}" >
                    <option id="${produto.codigo}" value="${produto.codigo}">${produto.nome}</option>
                </c:forEach>
            </select>
            </br>
            <input type="submit" value="Adicionar">
        </form>
        <br>
        <label>Conta:</label>
        </br>
        <c:forEach var="item" items="${itens}" >
            ${item.quantidade}
            ${item.produto.nome}
            </br>
        </c:forEach>
        <br>
        <form name="finalizar" action="/RestauranteProjetoFinal/Vendas/finalizarPedido">
            <label>mesa</label><input type="text" name="mesa" id="mesa" size="5">
            <input type="submit" value="Fechar Conta">
        </form>
    </body>
</html>
