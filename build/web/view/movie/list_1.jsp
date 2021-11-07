<%-- 
    Document   : list
    Created on : Oct 22, 2021, 11:17:15 AM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
<c:forEach items="${requestScope.movies}" var="movie">
    <div style="display: block; height: 300px; width: 80%; margin: auto">
        <div style="width: 20%; float: left; display: block" ><img src="${movie.urlImage}" alt="${movie.name}" width="200" height="300"></div>
        <div style="float: right; width: calc(80% - 20px); height: 280px; padding: 10px">
            <h2 style="margin: 0px">${movie.name}</h2>
            <div class="movie-director movie-info">
                <label style="display: inline-block;">Đạo diễn: </label>
                <div class="std" style="display: inline-block;">${movie.director}</div>
            </div>
            <div class="movie-cast movie-info">
                <label style="display: inline-block;">Diễn viên: </label>
                <div class="std" style="display: inline-block;">${movie.cast}</div>
            </div>
            <div class="movie-genre movie-info">
                <label style="display: inline-block;">Thể loại: </label>
                <div class="std" style="display: inline-block;"><c:forEach items="${movie.genre}" var="genre" varStatus="loop"><c:if test="${loop.index == 0}">${genre.genre}</c:if><c:if test="${loop.index != 0}">, ${genre.genre}</c:if></c:forEach></div>
            </div>
            <div class="movie-running-time movie-info">
                <label style="display: inline-block;">Ngày khởi chiếu:  </label>
                <div class="std" style="display: inline-block;">${movie.runningTime}</div>
            </div>
            <div class="movie-language movie-info">
                <label style="display: inline-block;">Ngôn ngữ: </label>
                <div class="std" style="display: inline-block;"><c:forEach items="${movie.languages}" var="language" varStatus="loop"><c:if test="${loop.index == 0}">${language.language}</c:if><c:if test="${loop.index != 0}">, ${language.language}</c:if></c:forEach></div>
            </div>
            <div class="movie-rated movie-info">
                <label style="display: inline-block;">Độ tuổi: </label>
                <div class="std" style="display: inline-block;">${movie.rated}</div>
            </div>
            <form action="${pageContext.request.contextPath}/cinema/booking">
                <input type="text" value="${movie.id}" name="movieid" hidden>
                <input type="submit" value="Đặt vé">
            </form>
        </div>
    </div><br/>
</c:forEach>
<html/>