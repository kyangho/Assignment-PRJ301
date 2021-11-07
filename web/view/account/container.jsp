<%-- 
    Document   : container
    Created on : Nov 7, 2021, 11:55:47 AM
    Author     : Ducky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="main-container" style="margin-top: 50px">
    <div class="account-container">
        <div class="account-menu">
            <div class="menu-section">
                <a href="info">Thông tin tài khoản</a>
            </div>
            <div class="menu-section">
                <a href="update">Cập nhập tài khoản</a>
            </div>
            <div class="menu-section">
                <a href="transaction">Lịch sử giao dịch</a>
            </div>
        </div>
        <div class="header">
            <div class="title">${requestScope.mainTitle}</div>
        </div>
        <div class="account-main">
            <jsp:include page="${requestScope.mainBody}"></jsp:include>
        </div>
    </div>
</div>