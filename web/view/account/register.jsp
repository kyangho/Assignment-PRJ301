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
<div class="register-container col-12 d-flex flex-wrap align-items-center justify-content-center">
    <div class="card-container col-md-5">
        <form action="register" method="POST">
            <div class="mt-3">
                <label for="username" class="col-md-5 col-form-label text-md-right">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required placeholder="Tên đăng nhập" value="${requestScope.username}"/><br/>
            </div>
            <div class="mt-1">
                <label for="displayname" class="col-md-5 col-form-label text-md-right">Tên người dùng:</label>
                <input type="text" id="displayname" name="displayname" required placeholder="Tên người dùng" value="${requestScope.displayname}"/><br/>
            </div>
            <div class="mt-1">
                <label for="phone" class="col-md-5 col-form-label text-md-right">Số điện thoại:</label>
                <input type="text" id="phone" name="phone" value="${requestScope.phone}"
                       accesskey=""pattern="[0][0-9]{9}" title="Invalid phone" placeholder="Số điện thoại"/><br/>
            </div>
            <div class="mt-1">
                <label for="email" class="col-md-5 col-form-label text-md-right">Email:</label>
                <input type="text" id="email" name="email" value="${requestScope.email}"
                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email is invalid" placeholder="Email"/><br/>
            </div>
            <div class="mt-1"></div>
            <label for="password" class="col-md-5 col-form-label text-md-right">Mật khẩu: </label>
            <input type="password" id="password" name=">Mật khẩu"
                   pattern="(?=.*\d)(?=.*[a-z]).{8,}"
                   title="Must contain at least one  number and lowercase letter, and at least 8 or more characters"
                   placeholder="Password"/><br/>
            <div class="mt-1">
                <label for="comfirmPassword" class="col-md-5 col-form-label text-md-right">Nhập lại mật khẩu: </label>
                <input type="password" id="comfirmPassword" name="comfirmPassword"
                        pattern="(?=.*\d)(?=.*[a-z]).{8,}"
                        title="Must contain at least one  number and lowercase letter, and at least 8 or more characters"
                        placeholder="Nhập lại mật khẩu"/><br/>           
            </div>
            <div class="mt-1">
                <label class="col-md-7 col-form-label text-md-right ml-2">
                    <input class="form-check-input" required ${requestScope.gender ? "checked=\"checked\"" : ""} type="radio" name="gender" value="true">
                    <span class="form-check-label"> Nam </span>
                </label>
                <label class="col-md-2 col-form-label">
                    <input class="form-check-input" required ${!requestScope.gender ? "checked=\"checked\"" : ""} type="radio" name="gender" value="false">
                    <span class="form-check-label"> Nữ</span>
                </label>
            </div>
            <div class="mt-1">
                <label for="dob" class="col-md-5 col-form-label text-md-right">Ngày sinh: </label>
                <input type="date" id="dob" name="dob" class="col-md-5" value="${requestScope.dob}" required/> <br/>
            </div>
            <div class="col-md-6 offset-md-5 mt-3">
                <input type="submit" value="Register" class="btn btn-primary"/>    
            </div>
        </form>
    </div>
</div>
