<%-- 
    Document   : transaction
    Created on : Nov 7, 2021, 3:03:49 PM
    Author     : Ducky
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="convert" uri="../../WEB-INF/myTag.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/style/css/pagger.css?v=1.1" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/pagging.js" type="text/javascript"></script>


<c:if test="${requestScope.account.transactions.size() == 0}">
    <div class="col-md-12 text-center text-danger">
        Bạn chưa mua vé lần nào!
    </div>
</c:if>
<c:if test="${requestScope.account.transactions.size() != 0}">
    <div id="paggerTop" class="pagger"></div>
    <c:forEach items="${requestScope.account.transactions}" var="transaction">
        <div class="transaction-id mt-3">Mã đơn hàng: ${transaction.id}</div>
        <div class="transaction-container">
            <div class="movie-image" style="background-image: url(${transaction.movie.urlImage})">
            </div>
            <div class="transaction-info">
                <div class="info-section">${transaction.movie.name}</div>
                <div class="info-section"><fmt:formatDate type="date" timeStyle="short" value="${transaction.transaction_made_date}" pattern="dd-MM-yyyy"/></div>

                <div class="info-section">From <fmt:formatDate type="time" timeStyle="short" value="${transaction.performanceNumber.fromTime}"/> ~
                    To <fmt:formatDate type="time" timeStyle="short" value="${transaction.performanceNumber.toTime}"/></div>
                <div class="info-section">${transaction.cinema.name}</div>
                <div class="info-section">
                    <c:forEach items="${transaction.tickets}" var="ticket" varStatus="i">
                        <c:if test="${i.index == 0}"><convert:toChar number="${ticket.row_number}"></convert:toChar>${ticket.seat_number}</c:if><c:if test="${i.index > 0}">, <convert:toChar number="${ticket.row_number}"></convert:toChar>${ticket.seat_number}</c:if>
                    </c:forEach>
                </div>
                <div class="info-section">${transaction.price}₫</div>
            </div>
        </div>
    </c:forEach>
    <div id="paggerBottom" class="pagger"></div>
    <script>
        createPagger('paggerTop', ${requestScope.pageIndex}, 2, ${requestScope.totalPage});
        createPagger('paggerBottom', ${requestScope.pageIndex}, 2, ${requestScope.totalPage});
    </script> 
</c:if>
<!--<div class="transaction-container">
    <div class="movie-image" style="background-image: url(https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/p/o/poster_lat_mat_4_160421_1_.jpg)">
    </div>
    <div class="transaction-info">
        <div class="info-section">LẬT MẶT: 48H</div>
        <div class="info-section">31-10-2021</div>

        <div class="info-section">From 3:00 PM ~
            To 5:30 PM</div>
        <div class="info-section">Hà Nội</div>
        <div class="info-section">
            C8
        </div>
        <div class="info-section">59000.0₫</div>
    </div>
</div>-->