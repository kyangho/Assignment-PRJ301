<%-- 
    Document   : rowseat
    Created on : Oct 26, 2021, 5:49:19 PM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


<script>
    $(document).ready(function () {
        let movieid;
        let searchParams = new URLSearchParams(window.location.search);
        if (searchParams.has('movieid')) {
            movieid = searchParams.get('movieid');
        }
        let day ;
        if (searchParams.has('day')) {
            day = searchParams.get('day');
        }
        $("input[class^='seat']").on('change', function () {
            if ($(this).attr('class').includes('booked')){
                return;
            }
            
            var price;
            var seat = $(this).parent('td').children('label').text().trim();
            
            if (!$(this).attr('class').includes('checked')) {
                $(this).prop('class', $(this).attr('class') + ' checked');
                if ($("td[class='price']").text() != ''){
                    price = parseInt($("td[class='price']").text().replace('₫', '')) + parseInt($(this).attr('price'));
                }else{
                    price = parseInt($(this).attr('price'));
                }
                
                if ($("td[class='data']").children("span[class='data-seat']").text() == ''){
                    $("td[class='data']").children("span[class='data-seat']").text(seat)
                }else{
                    var dataSeat = $("td[class='data']").children("span[class='data-seat']").text();
                    $("td[class='data']").children("span[class='data-seat']").text(dataSeat + ', ' + seat)
                }
                
                $("input[class='price-attribute']").attr('value', $("input[class='price-attribute']").attr('value') + ' ' + $(this).attr("priceId"));
            } else {
                $(this).prop('class', $(this).attr('class').replace(' checked', ''));
                if ($("td[class='price']").text() != ''){
                    price = parseInt($("td[class='price']").text().replace('₫', '')) - parseInt($(this).attr('price'));
                }
                
                var dataSeat;
                dataSeat = $("td[class='data']").children("span[class='data-seat']").text().replace(', ' + seat, '');
                if (dataSeat.includes(seat))
                    dataSeat = $("td[class='data']").children("span[class='data-seat']").text().replace(seat + ', ', '');
                if (dataSeat.includes(seat))
                    dataSeat = $("td[class='data']").children("span[class='data-seat']").text().replace(seat, '');
                $("td[class='data']").children("span[class='data-seat']").text(dataSeat)
            
                $("input[class='price-attribute']").attr('value', $("input[class='price-attribute']").attr('value').replace($(this).attr("priceId"), ''));
            }
            $("td[class='price']").text(price + '₫');
            $("input[class='seat-attribute']").attr('value', $("td[class='data']").children("span[class='data-seat']").text());
            
            var isNull = $("input[class='seat-attribute']").attr('value');
            if (isNull != ''){
                document.querySelector('input.btn-right').removeAttribute('disabled');
            }else{
                document.querySelector('input.btn-right').setAttribute('disabled', true);
            }
        });
        var response;
        $.ajax({
            type: 'post',
            url: '../cinema/rowseat',
            data: {movieid: movieid, day: day},
            success: function (responseText) {
                if (responseText != ""){
                    $('#isNullMovie').text(responseText);
                    $('.showTimes').prop("style", "display:none")
                }else{
                    $('#isNullMovie').text("");
                    $('.showTimes').prop("style", "");
                }
            }
        });
        
    });

</script>
<div>
    <div class="table-title">Đặt vé</div>
    <table class="coolTable" style="clear: both">
        <%char row = 'A';%>
        <c:forEach items="${requestScope.cinema.rowSeats}" var="rowSeat" varStatus="i">
            <tr>
                <c:if test="${requestScope.cinema.rowSeats.size()*20/100 <= i.index}">
                    <c:set var="type" value="seat-vipprime"></c:set>
                    <c:set var="price" value="${requestScope.vipprimePrice.price}"></c:set>
                    <c:set var="priceId" value="${requestScope.vipprimePrice.id}"></c:set>
                </c:if>
                <c:if test="${requestScope.cinema.rowSeats.size()*20/100 > i.index}">
                    <c:set var="type" value="seat-standard"></c:set>
                    <c:set var="price" value="${requestScope.standardPrice.price}"></c:set>
                    <c:set var="priceId" value="${requestScope.standardPrice.id}"></c:set>
                </c:if>
                <c:forEach begin="0" end="${rowSeat.seat}" varStatus="loop">
                    <c:set var="book" value=""></c:set>
                    <c:if test="${requestScope.seats[i.index + 1][loop.index + 1] == 1}">
                        <c:set var="book" value="booked"></c:set>
                    </c:if>
                    <td itemscope itemid="${rowSeat.row}-${loop.count}">
                        <input class="seat ${type} ${book}" priceId="${priceId}" price="${price}" type="checkbox" id="${rowSeat.row}-${loop.count}-seat">
                        <label for="${rowSeat.row}-${loop.count}-seat">
                            <%=row%><c:out value="${loop.count}"/>
                        </label>
                    </td>
                </c:forEach>
            </tr>
            <%row += 1;%>
        </c:forEach>
    </table>
</div>
<jsp:include page="../../view/ticket/booking.jsp"></jsp:include>
