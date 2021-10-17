<%-- 
    Document   : login
    Created on : Oct 16, 2021, 8:35:56 PM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="login" method="POST">
            Username: <input type="text" name="username"/><br/>
            Password: <input type="password" name="password"/><br/>
            <c:if test="${requestScope.isFailed != null}">
                <c:if test="${requestScope.isFailed}">
                    Login successful!
                </c:if><br/>
                <c:if test="${!requestScope.isFailed}">
                    Login failed!
                </c:if><br/>   
            </c:if>
            
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
