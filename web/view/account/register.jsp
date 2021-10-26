<%-- 
    Document   : register
    Created on : Oct 19, 2021, 6:33:01 PM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<c:if test="${!requestScope.isPassMatch && requestScope.isPassMatch != null}">
    <div>
        Password is not match!
    </div>
</c:if>
<c:if test="${!requestScope.invalidUser && requestScope.invalidUser != null}">
    <div>
        Username have been used by another!
    </div>
</c:if>

<form action="register" method="POST">
    Username: <input type="text" name="username" required placeholder="User name" value="${requestScope.username}"/><br/>
    Display name: <input type="text" name="displayname" required placeholder="Display name" value="${requestScope.displayname}"/><br/>
    Phone number: <input type="text" name="phone" value="${requestScope.phone}"
                         pattern="[0][0-9]{9}" title="Invalid phone" placeholder="Phone number"/><br/>
    Email: <input type="text" name="email" value="${requestScope.email}"
                  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email is invalid" placeholder="Email"/><br/>
    Password: 
    <input type="password" name="password"
           pattern="(?=.*\d)(?=.*[a-z]).{8,}"
           title="Must contain at least one  number and lowercase letter, and at least 8 or more characters"
           placeholder="Password"/><br/>
    Comfirm password: 
    <input type="password" name="comfirmPassword"
           pattern="(?=.*\d)(?=.*[a-z]).{8,}"
           title="Must contain at least one  number and lowercase letter, and at least 8 or more characters"
           placeholder="Password"/><br/>           
    Gender: <input type="radio" required ${requestScope.gender ? "checked=\"checked\"" : ""} name="gender" value="true"/> Male
    <input type="radio" required ${!requestScope.gender ? "checked=\"checked\"" : ""} name="gender" value="false"/> Female<br/>
    Dob: <input type="date" name="dob" value="${requestScope.dob}" required/> <br/>
    <input type="submit" value="Register"/>
</form>
