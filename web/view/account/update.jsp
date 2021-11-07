<%-- 
    Document   : update
    Created on : Oct 21, 2021, 5:24:28 PM
    Author     : Ducky
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
    function activeInput(target) {
        document.querySelector(target).removeAttribute('disabled');
        document.querySelector('input.button-submit').removeAttribute('disabled');
    }
    function activeAllInput() {
        document.querySelector('input.button-submit').removeAttribute('disabled');
        document.querySelector('input.display-name').removeAttribute('disabled');
        document.querySelector('input.email').removeAttribute('disabled');
        document.querySelector('input.phone').removeAttribute('disabled');
        document.querySelector('input.dob').removeAttribute('disabled');
    }
</script>


<div class="update-container col-12 ">
    <div class="col-md-12">
        <form action="${pageContext.request.contextPath}/account/update" method="POST">
            <div class="mt-3">
                <label for="displayName" class="col-md-4 col-form-label text-md-right">Tên hiển thị:</label>
                <input class="display-name col-md-6" id="displayname" name="displayName" type="text"  value="${requestScope.account.displayName}">
            </div>
            <div class="mt-1">
                <label class="col-md-7 col-form-label text-md-right ml-2">
                    <input class="form-check-input" required ${requestScope.account.gender ? "checked=\"checked\"" : ""} type="radio" name="gender" value="true">
                    <span class="form-check-label"> Nam </span>
                </label>
                <label class="col-md-2 col-form-label">
                    <input class="form-check-input" required ${!requestScope.account.gender ? "checked=\"checked\"" : ""} type="radio" name="gender" value="false">
                    <span class="form-check-label"> Nữ</span>
                </label>
            </div>
            <div class="mt-1">
                <label for="email" class="col-md-4 col-form-label text-md-right">Email:</label>
                <input type="text" id="email" name="email" class="col-md-6" value="${requestScope.account.email}"
                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email is invalid" placeholder="Email"/><br/>
            </div>
            <div class="mt-1">
                <label for="phone" class="col-md-4 col-form-label text-md-right">Số điện thoại:</label>
                <input type="text" id="phone" name="phone" class="col-md-6" value="${requestScope.account.phone}"
                       accesskey=""pattern="[0][0-9]{9}" title="Invalid phone" placeholder="Số điện thoại"/><br/>
            </div>
            <div class="mt-1">
                <label for="dob" class="col-md-4 col-form-label text-md-right">Ngày sinh: </label>
                <input type="date" id="dob" name="dob" class="col-md-6" value="${requestScope.account.dob}" required data-date="" data-date-format="DD MMMM YYYY"/> <br/>
            </div>
            <div class="mt-1">
                <c:if test="${requestScope.isUpdateSuccess != null}">
                    <div class="col-md-12 text-center text-success">${requestScope.isUpdateSuccess ? "Cập nhật thành công" : ""}</div>
                    <div class="col-md-12 text-center text-danger">${requestScope.isUpdateSuccess ? "" : "Cập nhật thất bại"}</div>
                </c:if>
            </div>
            <div class="col-md-6 offset-md-5 mt-3">
                <input type="submit" value="Save" class="btn btn-primary"/>    
            </div>
        </form>
    </div>
</div>