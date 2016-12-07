<%-- 
    Document   : newjsp
    Created on : 24/09/2015, 19:45:52
    Author     : aluno01
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach var="item" items="${pedido.itens}" >
            ${item.quantidade}
            ${item.produto.nome}
        </c:forEach>
    </body>
</html>
