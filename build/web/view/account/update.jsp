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
        document.querySelector('input.button-submit').removeAttribute('disabled');
    }
    function activeAllInput(){
        document.querySelector('input.button-submit').removeAttribute('disabled');
        document.querySelector('input.display-name').removeAttribute('disabled');
        document.querySelector('input.email').removeAttribute('disabled');
        document.querySelector('input.phone').removeAttribute('disabled');
        document.querySelector('input.dob').removeAttribute('disabled');
    }
</script>
<form action="${pageContext.request.contextPath}/account/update" method="POST">
    Display name: <input class="display-name" name="displayName" type="text" disabled value="${requestScope.account.displayName}">
    <input type="button" value="edit" onclick="activeInput('input.display-name')"><br/>
    Gender: <input type="radio" required ${requestScope.account.gender ? "checked=\"checked\"" : ""} name="gender" value="true"/> Male
    <input type="radio" required ${!requestScope.account.gender ? "checked=\"checked\"" : ""} name="gender" value="false"/> Female
    <input type="button" value="edit" onclick="activeInput('input.email')"><br/>
    Email: <input class="email" type="text" name="email" disabled value="${requestScope.account.email}">
    <input type="button" value="edit" onclick="activeInput('input.email')"><br/>

    Phone: <input class="phone" type="text" name="phone" disabled value="${requestScope.account.phone}">
    <input type="button" value="edit" onclick="activeInput('input.phone')"><br/>

    Date of Birth: <input class="dob" type="date" name="dob" data-date="" data-date-format="DD MMMM YYYY"
                          disabled value="${requestScope.account.dob}">
    <input type="button" value="edit" onclick="activeInput('input.dob')"><br/>
    
    <input type="submit" class="button-submit" onclick="activeAllInput()" value="Save" disabled>
</form>