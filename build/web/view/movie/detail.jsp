<%-- 
    Document   : details
    Created on : Oct 22, 2021, 11:09:58 AM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="movie-container">
    <div class="movie-detail-container">
        <div class="movie-image"><img src="${requestScope.movie.urlImage}" alt="${requestScope.movie.name}" width="200" height="300"></div>
        <div class="movie-header">
            <h2>${movie.name}</h2>
        </div>
        <div class="movie-info">
            <div class="movie-director info-section">
                <label>Đạo diễn: </label>
                <div class="std">${requestScope.movie.director}</div>
            </div>
            <div class="movie-cast info-section">
                <label>Diễn viên: </label>
                <div class="std">${requestScope.movie.cast}</div>
            </div>
            <div class="movie-genre info-section">
                <label>Thể loại: </label>
                <div class="std"><c:forEach items="${requestScope.movie.genre}" var="genre" varStatus="loop"><c:if test="${loop.index == 0}">${genre.genre}</c:if><c:if test="${loop.index != 0}">, ${genre.genre}</c:if></c:forEach></div>
            </div>
            <div class="movie-running-time info-section">
                <label>Ngày khởi chiếu:  </label>
                <div class="std">${requestScope.movie.runningTime}</div>
            </div>
            <div class="movie-language info-section">
                <label>Ngôn ngữ: </label>
                <div class="std"><c:forEach items="${requestScope.movie.languages}" var="language" varStatus="loop"><c:if test="${loop.index == 0}">${language.language}</c:if><c:if test="${loop.index != 0}">, ${language.language}</c:if></c:forEach></div>
            </div>
            <div class="movie-rated info-section">
                <label>Độ tuổi: </label>
                <div class="std">${requestScope.movie.rated}</div>
            </div>
            <form action="${pageContext.request.contextPath}/cinema/booking">
                <input type="text" value="${requestScope.movie.id}" name="movieid" hidden>
                <input type="submit" value="Đặt vé">
            </form>
        </div>
        <div class="movie-footer">
            <div class="movie-description">
                <h3 class="mb-2">Mô tả</h3>
                ${requestScope.movie.description}
            </div>
            <div class="movie-trailer mt-3" >
                <h3 class="mb-2">Trailer</h3><br/>
                ${requestScope.movie.urlTrailer}
            </div>
        </div>
    </div>
</div>
