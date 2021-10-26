<%-- 
    Document   : update
    Created on : Oct 22, 2021, 7:12:32 PM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    function gotoPage(target) {
        window.location.href = target;
    }
</script>
<c:forEach items="${requestScope.movies}" var="movie">
    <div style="display: block; height: 300px; width: 80%; margin: auto">
        <div style="width: 20%; float: left; display: block" ><img src="${movie.urlImage}" alt="${movie.name}" width="200" height="300"></div>
        <div style="float: right; width: calc(80% - 20px); height: 280px; padding: 10px">
            <h2 style="margin: 0px">${movie.name}</h2><br/>
            <div class="movie-director movie-info">
                <label style="display: inline-block;">Director: </label>
                <div class="std" style="display: inline-block;">${movie.director}</div>
            </div>
            <div class="movie-cast movie-info">
                <label style="display: inline-block;">Casts; </label>
                <div class="std" style="display: inline-block;">${movie.cast}</div>
            </div>
            <div class="movie-genre movie-info">
                <label style="display: inline-block;">Genre: </label>
                <div class="std" style="display: inline-block;"><c:forEach items="${movie.genre}" var="genre" varStatus="loop"><c:if test="${loop.index == 0}">${genre.genre}</c:if><c:if test="${loop.index != 0}">, ${genre.genre}</c:if></c:forEach></div>
            </div>
            <div class="movie-running-time movie-info">
                <label style="display: inline-block;">Running time:  </label>
                <div class="std" style="display: inline-block;">${movie.runningTime}</div>
            </div>
            <div class="movie-language movie-info">
                <label style="display: inline-block;">Language: </label>
                <div class="std" style="display: inline-block;"><c:forEach items="${movie.languages}" var="language" varStatus="loop"><c:if test="${loop.index == 0}">${language.language}</c:if><c:if test="${loop.index != 0}">, ${language.language}</c:if></c:forEach></div>
            </div>
            <div class="movie-rated movie-info">
                <label style="display: inline-block;">Rated: </label>
                <div class="std" style="display: inline-block;">${movie.rated}</div>
            </div>
            <input type="submit" value="edit" onclick="gotoPage('${pageContext.request.contextPath}/admin/movie/update?id=${movie.id}')">
        </div>
    </div><br/>
</c:forEach>