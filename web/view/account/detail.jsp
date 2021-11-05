<%-- 
    Document   : detail
    Created on : Oct 18, 2021, 12:16:03 PM
    Author     : Ducky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<div class="box">
    <div class="box-head">
        <h2>Thông tin tài khoản</h2>
    </div>
    <div>
    <div class="box-title">
        <h3 class="title-my-info">Liên hệ</h3>
        <a href="${pageContext.request.contextPath}/account/update">Thay đổi</a><br/>
    </div>
    <div class="box-content box-content-my-info">
        <span>Tên: ${requestScope.displayname}</span><br/>
        <span>Email: ${requestScope.email}</span><br/>
        <span>Điện thoại: ${requestScope.phone}</span>
    </div>
</div>