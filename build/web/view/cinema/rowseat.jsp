<%-- 
    Document   : rowseat
    Created on : Oct 26, 2021, 5:49:19 PM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>

</style>

<script>
    function log(a){
        console.log(a.getAttribute('itemid'));
    }
</script>

<table class="coolTable">
    <c:forEach items="${requestScope.cinema.rowSeats}" var="rowSeat">
        <tr>
            <td style="padding-right: 10px"><c:set var="row" value="&\#${(rowSeat.row + 96)}"/>${row}</td>
            <c:forEach begin="0" end="${rowSeat.seat}" varStatus="loop">
                <td itemscope itemid="${rowSeat.row}-${loop.count}" style="width: 20px" onclick="log(this)">
                    <input type="checkbox" id="${rowSeat.row}-${loop.count}">
                    <label for="${rowSeat.row}-${loop.count}"><c:out value="${loop.count}"/></label>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>