<%-- 
    Document   : booking
    Created on : Oct 29, 2021, 6:49:05 PM
    Author     : Ducky
--%>

<%@page import="java.sql.Date"%>
<%@page import="model.movie.Movie"%>
<%@page import="model.cinema.Cinema"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>

</style>
<div class="bottom-content">
    <form method="GET" action="${pageContext.request.contextPath}/cinema/booking" class="clearfix" style="display: inline-block; float: none; vertical-align: top;">
        <input class="" type="text" value="${requestScope.movie.id}" name="movieid" hidden>
        <input type="submit" class="btn-left box" value="Quay lại">
    </form>
    

    <div class="minicart-wrapper">
        <ul>
            <li class="item first">
                <div class="product-details">
                    <table class="info-wrapper">
                        <tbody>
                            <tr>
                                <td>
                                    <img src="${requestScope.movie.urlImage}" alt="${requestScope.movie.name}">
                                </td>									
                                <td>
                                    <table class="info-wrapper">
                                        <tbody><tr><td class="label">${requestScope.movie.name}</td></tr>
                                            <tr><td class="label">${requestScope.movie.rated}</td></tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </li>

            <li class="item">
                <div class="product-details">
                    <table class="info-wrapper">							
                        <tbody>
                            <tr>
                                <td class="label">Rạp</td>
                                <td>${requestScope.cinema.name}</td>
                            </tr>
                            <tr>
                                <td class="label">Suất chiếu</td>
                                <td>
                                    <fmt:formatDate type="time" timeStyle="short" value="${requestScope.performance.fromTime}"/>
                                    <br/><fmt:formatDate type="date" timeStyle="short" value="${requestScope.day}" pattern="dd-MM-yyyy"/></td>
                            </tr>
                            <tr class="block-seats" style="">
                                <td class="label">Ghế</td>
                                <td class="data">
                                    <span class="data-seat" style="clear: both; float: left;"></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </li>

            <li class="item">
                <div class="product-details">
                    <table class="info-wrapper">
                        <thead class="block-price">
                            <tr>
                                <td class="label">Tổng</td>
                                <td class="price" colspan="2"></td>
                            </tr>
                        </thead>
                    </table>
                </div>
            </li>
        </ul>
    </div>
    <form method="POST" action="${pageContext.request.contextPath}/cinema/booking/ticket" class="clearfix" style="display: inline-block; float: none; vertical-align: top;">
        <input class="seat-attribute" type="text" value="" name="seat" hidden>
        <input class="price-attribute" type="text" value="" name ="price" hidden>
        <input type="submit" disabled class="btn-right box" value="Book">
    </form>


</div>