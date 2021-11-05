<%-- 
    Document   : home.jsp
    Created on : Oct 18, 2021, 9:44:18 AM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/style.css?v=2.1"/>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <header class="header-container p-3 pb-4 text-dark">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <ul class="nav col-12 col-lg-6  me-lg-auto mb-2  mb-md-0">
                        <li><a href="${requestScope.home_href}" class="nav-link px-2 text-dark">Home</a></li>
                    </ul>

                    <form class="col-12 col-lg-3 mb-3 mb-lg-0 d-flex flex-wrap align-items-center justify-content-center">
                        <input type="search" class="form-control form-control-dark" placeholder="Search..." aria-label="Search">
                    </form>

                    <div class="text-end col-lg-3 d-flex flex-wrap align-items-center justify-content-center">
                        <a href="${requestScope.login_href}" class="btn btn-dark me-2 text-light">${requestScope.login_href_value}</a>
                        <a href="${requestScope.register_href}" class="btn btn-warning me-2 text-dark" ${requestScope.register_href_value == "" ? "hidden" : ""}>${requestScope.register_href_value}</a>
                        <a href="${requestScope.logout_href}" class="btn btn-warning me-2 text-light" ${requestScope.logout_href_value == "" ? "hidden" : ""}>${requestScope.logout_href_value}</a>
                    </div>
                </div>
            </div>
        </header>
        <div class="main-container">
            <c:if test="${requestScope.pageInclude != null}">
                <jsp:include page="${requestScope.pageInclude}"></jsp:include>
            </c:if>
        </div>
        <div class="footer-containter"></div>
    </body>
</html>
<!--/view/account/detail.jsp-->