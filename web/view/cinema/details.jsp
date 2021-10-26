<%-- 
    Document   : details
    Created on : Oct 26, 2021, 11:44:55 AM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app-ajax.js" type="text/javascript"></script>
        <style>
            ul {
                padding: 0;
                margin: 0;
                clear: both;
            }

            li{
                list-style-type: none;
                list-style-position: outside;
                padding: 10px;
                float: left;
            }

            label {
                display: inline-block;
                padding: 10px;
                cursor: pointer;
                border: 1px solid black;
                color: black;
                background-color: white;
                margin-bottom: 10px;
            }
            label:hover{
                background: black;
                color: white;
            }
            input[type="checkbox"]:checked + label{
                background: black;
                color: white;
            }
            input{
                display:none;
            }
            .cinema::after {
                content: '';
                display: block;
                clear: both;
            }
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="cinema">
            <ul>
                <c:forEach items="${requestScope.cinemas}" var="cinema">
                    <li>
                        <input type="checkbox" class="name" id="${cinema.id}" name="name" value="${cinema.id}">
                        <label for="${cinema.id}">${cinema.name}</label>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div id="rowSeat-view" style="width: 100%">
            
        </div>
    </body>
</html>
