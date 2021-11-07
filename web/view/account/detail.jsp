<%-- 
    Document   : detail
    Created on : Oct 18, 2021, 12:16:03 PM
    Author     : Ducky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<div class="box">
    <div class="box-content box-content-my-info">
        <span>Tên: ${requestScope.account.displayName}</span><br/>
        <span>Giới tính: ${requestScope.account.gender ? "Nam" : "Nữ"}</span><br/>
        <span>Email: ${requestScope.account.email}</span><br/>
        <span>Điện thoại: ${requestScope.account.phone}</span>
    </div>
    <div class="box-title">
        <a href="${pageContext.request.contextPath}/account/update">Thay đổi</a><br/>
    </div>
</div>