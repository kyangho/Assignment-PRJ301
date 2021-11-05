<%-- 
    Document   : update
    Created on : Oct 22, 2021, 7:51:48 PM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<script>
    function activeInput(target) {
        var inputs = document.querySelectorAll('input.std');
        inputs[target].removeAttribute('disabled');
    }
    function activeAllInput() {
        var inputs = document.querySelectorAll('input');
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].removeAttribute('disabled');
        }
    }
</script>
<form action="${pageContext.request.contextPath}/admin/movie/update" method="POST" 
      style="display: block; height: 350px; width: 80%; margin: auto">
    <div style="width: 20%; float: left; display: block" ><img src="${requestScope.movie.urlImage}" alt="${requestScope.movie.name}" width="200" height="300"></div>
    <div style="float: right; width: calc(80% - 20px); height: 330px; padding: 10px">
        <div class="movie-name movie-info">
            <input type="text" disabled class="std" name="name" style="display: inline-block;" value="${requestScope.movie.name}">
            <input type="button" value="edit" onclick="activeInput(0)"><br/>
        </div>
        <div class="movie-director movie-info">
            <label style="display: inline-block;">Đạo diễn: </label>
            <input type="text" disabled class="std" name="director" style="display: inline-block;" value="${requestScope.movie.director}">
            <input type="button" value="edit" onclick="activeInput(1)"><br/>
        </div>
        <div class="movie-cast movie-info">
            <label style="display: inline-block;">Diễn viên: </label>
            <input type="text" disabled class="std" name="cast" value="${requestScope.movie.cast}"
                   style="display: inline-block;">
            <input type="button" value="edit" onclick="activeInput(2)"><br/>
        </div>
        <div class="movie-genre movie-info">
            <label style="display: inline-block;">Thể loại: </label>
            <c:forEach items="${requestScope.movieGenres}" var="movieGenre">
                <c:set var="isHave" value="false"></c:set>
                <c:forEach items="${requestScope.movie.genre}" var="genre">
                    <c:if test="${fn:contains(movieGenre.genre, genre.genre)}">
                        <c:set var="isHave" value="true"></c:set>
                    </c:if>
                </c:forEach>
                <input type="checkbox" name="genre" value="${movieGenre.id}"${isHave ? "checked" : ""}>${movieGenre.genre}
            </c:forEach>
        </div>
        <div class="movie-running-time movie-info">
            <label style="display: inline-block;">Ngày khởi chiếu:  </label>
            <input type="date" disabled class="std" name="runningTime" value="${requestScope.movie.runningTime}"
                   style="display: inline-block;">
            <input type="button" value="edit" onclick="activeInput(3)"><br/>
        </div>
        <div class="movie-running-time movie-info">
            <label style="display: inline-block;">Ngày chiếu:  </label>
            <input type="date" disabled class="std" name="toShowing" value="${requestScope.movie.movieShowing.fromShowing}"
                   style="display: inline-block;">
            <input type="button" value="edit" onclick="activeInput(4)"><br/>
        </div>
        <div class="movie-running-time movie-info">
            <label style="display: inline-block;">Ngày dừng chiếu:  </label>
            <input type="date" disabled class="std" name="fromShowing" value="${requestScope.movie.movieShowing.toShowing}"
                   style="display: inline-block;">
            <input type="button" value="edit" onclick="activeInput(5)"><br/>
        </div>
        <div class="movie-language movie-info">
            <label style="display: inline-block;">Ngôn ngữ: </label>
            <c:set var="languageString" value=""></c:set>
            <c:forEach items="${requestScope.movieLanguages}" var="movieLanguage">
                <c:set var="isHave" value="false"></c:set>
                <c:forEach items="${requestScope.movie.languages}" var="language">
                    <c:if test="${fn:contains(movieLanguage.language, language.language)}">
                        <c:set var="isHave" value="true"></c:set>
                    </c:if>
                </c:forEach>
                <input type="checkbox" name="language" value="${movieLanguage.id}" ${isHave ? "checked" : ""}>${movieLanguage.language}
            </c:forEach>
        </div>
        <div class="movie-duration movie-info">
            <label style="display: inline-block;">Thời lương: </label>
            <input type="text" disabled class="std" name="duration" style="display: inline-block;" value="${requestScope.movie.duration}">
            <input type="button" value="edit" onclick="activeInput(6)"><br/>
        </div>
        <div class="movie-rated movie-info">
            <label style="display: inline-block;">Độ tuổi: </label>
            <input type="text" disabled class="std" name="rated" style="display: inline-block;" value="${requestScope.movie.rated}">
            <input type="button" value="edit" onclick="activeInput(7)"><br/>
        </div>
        <div class="movie-description movie-info">
            <label style="display: inline-block;">Giới thiệu </label>
            <input type="text" disabled class="std" name="description" style="display: inline-block;" value="${requestScope.movie.description}">
            <input type="button" value="edit" onclick="activeInput(8)"><br/>
        </div>
        <div class="movie-image movie-info">
            <label style="display: inline-block;">Url image: </label>
            <input type="text" disabled class="std" name="urlImage" style="display: inline-block;" value="${requestScope.movie.urlImage}">
            <input type="button" value="edit" onclick="activeInput(9)"><br/>
        </div>
        <div class="movie-trailer movie-info">
            <label style="display: inline-block;">Url trailer: </label>
            <input type="text" disabled class="std" name="urlTrailer" style="display: inline-block;" value="${requestScope.movie.urlTrailer}">
            <input type="button" value="edit" onclick="activeInput(10)"><br/>
        </div>
        <div class="cinema-id movie-info">
            <label style="display: inline-block;">Rạp: </label>
            <c:forEach items="${requestScope.cinemas}" var="cinema">
                <c:set var="isHave" value="false"></c:set>
                <c:forEach items="${requestScope.movie.languages}" var="language">
                    <c:if test="${fn:contains(movieLanguage.language, language.language)}">
                        <c:set var="isHave" value="true"></c:set>
                    </c:if>
                </c:forEach>
                <input type="checkbox" name="cinema" value="${cinema.id}" >${cinema.name}
            </c:forEach>
        </div>
        <input type="hidden" name="id" value="${param.id}">
        <input type="submit" value="Save" onclick="activeAllInput()">
    </div>

</form>
<div style="display:block">${requestScope.movie.urlTrailer}</div>