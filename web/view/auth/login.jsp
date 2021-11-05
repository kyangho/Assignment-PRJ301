<%-- 
    Document   : login
    Created on : Oct 16, 2021, 8:35:56 PM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<div class="login-container col-12 d-flex flex-wrap align-items-center justify-content-center">
    <div class="card-container d-flex justify-content-center">
        <form action="${pageContext.request.contextPath}/account/login" method="POST">
            <div class="input-group mt-3 mb-3">
                <div class="input-group-append">
                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                </div>
                <input type="text" name="username" class="form-control input-user" value="" placeholder="Tên đăng nhập">
            </div>
            <div class="input-group mb-2">
                <div class="input-group-append">
                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                </div>
                <input type="password" name="password" class="form-control input-pass" value="" placeholder="Mật khẩu">
            </div>
            <c:if test="${requestScope.isFailed != null}">
                <c:if test="${requestScope.isFailed}">
                    Đăng nhập thất bại
                </c:if><br/>   
            </c:if>
            <div class="d-flex justify-content-center ">
                <button type="submit" name="button" class="btn login_btn">Đăng nhập</button>
            </div>
            <div class="mt-3">
                <div class="d-flex justify-content-center">
                    Bạn chưa có tài khoản? <a href="${pageContext.request.contextPath}/account/register" class="ml-2">Đăng kí</a>
                </div>
            </div>
        </form>
    </div>
</div>
