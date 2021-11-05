<%-- 
    Document   : ticket
    Created on : Oct 30, 2021, 6:22:09 PM
    Author     : Ducky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3>${requestScope.isSuccess ? "Đặt vé thành công" : "Đặt vé thất bại"}</h3>
<div class="movie-container">
    <div class="movie-card">
        <div class="movie-header babyDriver">
            <div class="header-icon-container">
                <a href="#">
                    <i class="material-icons header-icon"></i>
                </a>
            </div>
        </div><!--movie-header-->
        <div class="movie-content">
            <div class="movie-content-header">
                <a href="#">
                    <h3 class="movie-title">Baby Driver</h3>
                </a>
                <div class="imax-logo"></div>
            </div>
            <div class="movie-info">
                <div class="info-section">
                    <label>Ngày khởi chiếu</label>
                    <span>Tue 4 July - 05:00PM</span>
                </div><!--date,time-->
                <div class="info-section">
                    <label>Thể loại</label>
                    <span>C18</span>
                </div>
            </div>
        </div>
    </div>
</div>