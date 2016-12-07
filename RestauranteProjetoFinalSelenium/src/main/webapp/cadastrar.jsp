<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produtos</title>
    </head>
    <body>
      <form name="cadastrarProduto" action="/RestauranteProjetoFinal/Vendas/cadastrarProduto" method="POST">  
        <label>Nome:</label><input type="text" name="nome" id="produto"> </br>
        <label>Descrição:</label><input type="text" name="descricao" id="quantidade"> </br>
        <label>Preço:</label><input type="text" name="preco" id="quantidade"> </br>
        <input type="submit" value="cadastrar produtos">
      </form>
    </body>
</html>
