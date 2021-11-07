<%-- 
    Document   : details
    Created on : Oct 26, 2021, 11:44:55 AM
    Author     : Ducky
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $("input[class='performance']").on('change', function () {
            $("input[class='performance']").not(this).prop('checked', false);
            var id = $(this).val();
            let searchParams = new URLSearchParams(window.location.search);
            let movieid;
            if (searchParams.has('movieid')) {
                movieid = searchParams.get('movieid');
            }
            let day = $("li[class='current']").attr("value");
            window.location.href = "../cinema/rowseat?id=" + id + "&movieid=" + movieid + "&day=" + day
        });
    });
    
    $(document).ready(function () {
        $("div[class='day']").click(function () {
            $("div[class='day']").parent("li").not($(this).parent("li")).prop("class", '');
            $(this).parent("li").prop("class", 'current');
            let day = $("li[class='current']").attr("value");
            let searchParams = new URLSearchParams(window.location.search);
            let movieid;
            if (searchParams.has('movieid')) {
                movieid = searchParams.get('movieid');
            }
            
            var date = new Date();

            var output = date.getFullYear() + '-' +
                (date.getMonth() + 1 < 10 ? '0' : '') + (date.getMonth() + 1) + '-' +
                (date.getDate() < 10 ? '0' : '') + date.getDate();
        
            
            if ($("li[class='current']").attr("value") == output){
                $("ul[class='showTimes now']").prop("style", "");
                $("ul[class='showTimes']").prop("style", "display:none");
            }else{
                $("ul[class='showTimes now']").prop("style", "display:none");
                $("ul[class='showTimes']").prop("style", "");
            }
            console.log(movieid + ' ' + day);
            $.ajax({
                type: 'post',
                url: 'booking',
                data: {movieid: movieid, day: day},
                success: function (responseText) {
                    if (responseText != "") {
                        
                        $('#isNullMovie').text(responseText);
                        $('.showTimes').prop("style", "display:none")
                    } else {
                        $('#isNullMovie').text("");
                    }
                    
                }
            });
        });
    });
</script>

<body>
    <div class="quick-booking clearfix">
        <ul class="toggle-tabs">
            <%Calendar cal = Calendar.getInstance();
                cal.getTime();
            %>
            <c:set var="now" value="<%=cal%>"></c:set>
            <fmt:formatDate var="day" value="${now.getTime()}" pattern="yyyy-MM-dd"/>
            <li class="current" value="${day}">
                <div class="day">
                    <span><fmt:formatDate value="${now.getTime()}" pattern="MM"/></span>
                    <em><fmt:formatDate value="${now.getTime()}" pattern="EE"/></em>
                    <strong><fmt:formatDate value="${now.getTime()}" pattern="dd"/></strong>
                </div>
                <%cal.add(Calendar.DATE, 1);%>
            </li>
            <c:forEach begin="1" end="13" varStatus="i">
                <fmt:formatDate var="day" value="${now.getTime()}" pattern="yyyy-MM-dd"/>
                <li class value="${day}">
                    <div class="day">
                        <span><fmt:formatDate value="${now.getTime()}" pattern="MM"/></span>
                        <em><fmt:formatDate value="${now.getTime()}" pattern="EE"/></em>
                        <strong><fmt:formatDate value="${now.getTime()}" pattern="dd"/></strong>
                    </div>
                </li>
                <%cal.add(Calendar.DATE, 1);%>
            </c:forEach>
        </ul>
            
        <div id="isNullMovie" class="clearfix"></div>
        
        <ul class="showTimes now" style="">
            <c:if test="${requestScope.cinemasNow.size() == 0}">
                <p>Xin lỗi, hiện tại không có rạp nào hoạt động</p>
            </c:if>
            <c:forEach items="${requestScope.cinemasNow}" var="cinema">
                <div class="clearfix">
                    <li>
                        <h3>${cinema.name}</h3>
                        <ul>
                            <c:forEach items="${cinema.performanceNumbers}" var="performance">
                                <li>
                                    <input type="checkbox" class="performance" id="${cinema.id}-${performance.number}" name="performance" value="${cinema.id}-${performance.number}">
                                    <label for="${cinema.id}-${performance.number}"><fmt:formatDate type="time" timeStyle="short" value="${performance.fromTime}"/></label>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </div>
            </c:forEach>
        </ul>
        
        <ul class="showTimes" style="display:none">
            <c:forEach items="${requestScope.cinemas}" var="cinema">
                <div class="clearfix">
                    <li>
                        <h3>${cinema.name}</h3>
                        <ul>
                            <c:forEach items="${cinema.performanceNumbers}" var="performance">
                                <li>
                                    <input type="checkbox" class="performance" id="${cinema.id}-${performance.number}" name="performance" value="${cinema.id}-${performance.number}">
                                    <label for="${cinema.id}-${performance.number}"><fmt:formatDate type="time" timeStyle="short" value="${performance.fromTime}"/></label>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </div>
            </c:forEach>
        </ul>
    </div>

</body>
