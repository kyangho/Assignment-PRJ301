<%-- 
    Document   : list
    Created on : Oct 22, 2021, 11:17:15 AM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<script>
$(document).ready(function () {
    let movieId;
    $(".movie-header").click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        movieId = $(this).attr("value");
        var popup = '.popup-' + movieId;
        $(popup).prop("style", "visibility: visible");
        $(this).closest(".movie-container").addClass("show-popup");
        console.log('a');
    });
    $(".popup").click(function(e) {
        console.log('b');
        e.preventDefault();
        e.stopPropagation();
        e.stopImmediatePropagation();
        $(".popup").prop("style", "visibility: hidden;");
        $(".movie-container").removeClass("show-popup");
        return false;
    });

});
</script>

<div class="movie-container">
    <div class="movie-card-container">
        <c:forEach items="${requestScope.movies}" var="movie">
            <div class="movie-card">
                <div class="movie-header movie-image" value="${movie.id}" style="background: url(${movie.urlImage})">
                    <div class="header-icon-container">
                        <i class="material-icons header-icon"></i>
                    </div>
                </div>
                <div class="movie-content">
                    <div class="movie-content-header">
                        <a href="${pageContext.request.contextPath}/movie/details?movieid=${movie.id}">
                            <h3 class="movie-title">${movie.name}</h3>
                        </a>
                    </div>
                    <div class="movie-info">
                        <div class="info-section">
                            <label>Ngày khởi chiếu</label>
                            <span>${movie.runningTime}</span>
                        </div>
                        <div class="info-section">
                            <label>Thể loại</label>
                            <span>${movie.rated}</span>
                        </div>
                    </div>
                </div>
                <div class="movie-footer">
                    <div class="movie-btn"><a href="${pageContext.request.contextPath}/cinema/booking?movieid=${movie.id}">Đặt vé</a></div>
                    <div class="movie-btn"></div>
                    <div class="movie-btn"><a href="${pageContext.request.contextPath}/movie/details?movieid=${movie.id}">Chi tiết</a></div>
                </div>
            </div>
            <div class="popup-container">
                <div class="popup popup-${movie.id}" style="visibility: hidden" id="media-popup">
                    ${movie.urlTrailer}
                </div>  
            </div>
        </c:forEach>
    </div>
</div>
