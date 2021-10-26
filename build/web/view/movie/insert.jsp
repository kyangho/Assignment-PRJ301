<%-- 
    Document   : insert
    Created on : Oct 25, 2021, 11:03:09 PM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<form action="${pageContext.request.contextPath}/admin/movie/insert" method="POST" 
    <div>
        <div class="movie-name movie-info">
            <label style="display: inline-block;">Tên phim: </label>
            <input type="text" required class="std" name="name" style="display: inline-block;" v
        </div>
        <div class="movie-director movie-info">
            <label style="display: inline-block;">Đạo diễn: </label>
            <input type="text" required class="std" name="director" style="display: inline-block;" >
        </div>
        <div class="movie-cast movie-info">
            <label style="display: inline-block;">Diễn viên: </label>
            <input type="text"  required class="std" name="cast" style="display: inline-block;">
        </div>
        <div class="movie-genre movie-info">
            <label style="display: inline-block;">Thể loại: </label>
            <c:forEach items="${requestScope.movieGenres}" var="movieGenre">
                <input type="checkbox" name="genre" value="${movieGenre.id}">${movieGenre.genre}
            </c:forEach>
        </div>
        <div class="movie-running-time movie-info">
            <label style="display: inline-block;">Ngày khởi chiếu:  </label>
             <input type="date" required class="std" name="runningTime" style="display: inline-block;">
        </div>
        <div class="movie-running-time movie-info">
            <label style="display: inline-block;">Ngày chiếu:  </label>
            <input type="date" required class="std" name="toShowing" style="display: inline-block;">
        </div>
        <div class="movie-running-time movie-info">
            <label style="display: inline-block;">Ngày dừng chiếu:  </label>
            <input type="date" required class="std" name="fromShowing" style="display: inline-block;">
        </div>
        <div class="movie-language movie-info">
            <label style="display: inline-block;">Ngôn ngữ: </label>
            <c:forEach items="${requestScope.movieLanguages}" var="movieLanguage">
                <input type="checkbox" name="language" value="${movieLanguage.id}">${movieLanguage.language}
            </c:forEach>
        </div>
        <div class="movie-duration movie-info">
            <label style="display: inline-block;">Thời lương: </label>
            <input type="text" class="std" required name="duration" style="display: inline-block;">
        </div>
        <div class="movie-rated movie-info">
            <label style="display: inline-block;">Độ tuổi: </label>
            <input type="text" class="std" required name="rated" style="display: inline-block;">
        </div>
        <div class="movie-description movie-info">
            <label style="display: inline-block;">Giới thiệu </label>
            <input type="text" class="std" required name="description" style="display: inline-block;">
        </div>
        <div class="movie-image movie-info">
            <label style="display: inline-block;">Url image: </label>
            <input type="text" class="std" required name="urlImage" style="display: inline-block;">
        </div>
        <div class="movie-trailer movie-info">
            <label style="display: inline-block;">Url trailer: </label>
            <input type="text" class="std" required name="urlTrailer" style="display: inline-block;">
        </div>
        <input type="submit" value="Insert">
    </div>
</form>