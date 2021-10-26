<%-- 
    Document   : testpage
    Created on : Oct 25, 2021, 7:57:58 PM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:forEach items="${requestScope.genre}" var="genre">
    ${genre}<br/>
</c:forEach>
${requestScope.isUpdate ? "update succesfull" : "update fail"}
