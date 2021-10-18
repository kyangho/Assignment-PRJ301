<%-- 
    Document   : home.jsp
    Created on : Oct 18, 2021, 9:44:18 AM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div class="header-login-inventory">
            <a href="${requestScope.home_href}">Home</a>
            <a href="${requestScope.login_href}">${requestScope.login_href_value}</a>
            <a href="${requestScope.logout_href}"> ${requestScope.logout_href_value}</a>
        </div>
        <c:if test="${requestScope.pageInclude != null}">
            <jsp:include page="${requestScope.pageInclude}"></jsp:include>
        </c:if>
        <header id="header" class="page-header"></header>
        <div class="main-container"></div>
        <div class="footer-containter"></div>
    </body>
</html>
<!--/view/account/detail.jsp-->