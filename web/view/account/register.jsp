<%-- 
Document   : register
Created on : Oct 19, 2021, 6:33:01 PM
Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<div class="register-container col-12 d-flex flex-wrap align-items-center justify-content-center">
    <div class="card-container col-md-6">
        <form action="register" method="POST">
            <div class="mt-3">
                <label for="username" class="col-md-4 col-form-label text-md-right">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required placeholder="Tên đăng nhập" class="col-md-6" value="${requestScope.username}"/><br/>
            </div>
            <div class="mt-1">
                <label for="displayname" class="col-md-4 col-form-label text-md-right">Tên người dùng:</label>
                <input type="text" id="displayname" name="displayname" required placeholder="Tên người dùng" class="col-md-6" value="${requestScope.displayname}"/><br/>
            </div>
            <div class="mt-1">
                <label for="phone" class="col-md-4 col-form-label text-md-right">Số điện thoại:</label>
                <input type="text" id="phone" name="phone" value="${requestScope.phone}" class="col-md-6"
                       accesskey=""pattern="[0][0-9]{9}" title="Invalid phone" placeholder="Số điện thoại"/><br/>
            </div>
            <div class="mt-1">
                <label for="email" class="col-md-4 col-form-label text-md-right">Email:</label>
                <input type="text" id="email" name="email" value="${requestScope.email}" class="col-md-6"
                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email is invalid" placeholder="Email"/><br/>
            </div>
            <div class="mt-1"></div>
            <label for="password" class="col-md-4 col-form-label text-md-right">Mật khẩu: </label>
            <input type="password" id="password" name="password" class="col-md-6"
                   pattern="(?=.*\d)(?=.*[a-z]).{8,}"
                   title="Must contain at least one  number and lowercase letter, and at least 8 or more characters"
                   placeholder="Password"/><br/>
            <div class="mt-1">
                <label for="comfirmPassword" class="col-md-4 col-form-label text-md-right">Nhập lại mật khẩu: </label>
                <input type="password" id="comfirmPassword" name="comfirmPassword" class="col-md-6"
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
                <label for="dob" class="col-md-4 col-form-label text-md-right">Ngày sinh: </label>
                <input type="date" id="dob" name="dob" class="col-md-6" value="${requestScope.dob}" required/> <br/>
            </div>
            <c:if test="${!requestScope.isPassMatch && requestScope.isPassMatch != null}">
                <div class="col-md-12 text-center text-danger">
                    Mật khẩu không khớp
                </div>
            </c:if>
            <c:if test="${!requestScope.invalidUser && requestScope.invalidUser != null}">
                <div class="col-md-12 text-center text-danger">
                    Tên đăng nhập đã được dùng bởi người khác!
                </div>
            </c:if>
            <div class="col-md-6 offset-md-5 mt-3">
                <input type="submit" value="Register" class="btn btn-primary"/>    
            </div>
        </form>
    </div>
</div>
