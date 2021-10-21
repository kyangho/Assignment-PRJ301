<%-- 
    Document   : update
    Created on : Oct 21, 2021, 5:24:28 PM
    Author     : Ducky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
    function activeInput(target){
        document.querySelector(target).removeAttribute('disabled');
    }
</script>

Display name: <input class="display-name" type="text" disabled value="${requestScope.account.displayName}">
<input type="button" value="edit" onclick="activeInput('input.display-name')"><br/>

Email: <input class="email" type="text" disabled value="${requestScope.account.email}">
<input type="button" value="edit" onclick="activeInput('input.email')"><br/>

Phone: <input class="phone" type="text" disabled value="${requestScope.account.phone}">
<input type="button" value="edit" onclick="activeInput('input.phone')"><br/>

Date of Birth: <input class="dob" type="date" data-date="" data-date-format="DD MMMM YYYY"
                      disabled value="${requestScope.account.dob}">
<input type="button" value="edit" onclick="activeInput('input.dob')"><br/>
