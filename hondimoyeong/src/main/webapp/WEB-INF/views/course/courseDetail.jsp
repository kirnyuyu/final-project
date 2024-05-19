<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코스 상세</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>

        #innerOuter{
            width: 1200px;
            height: 1000px;
            margin: auto;
        }
        .carousel-inner img {
            width: 100%;
            height: 100%;
        }

        #main{
            width: 1200px;
            height: auto;
            text-align: center;
            font-weight: bold;
            margin-top: 40px;
        }

        #main > p {
            font-weight: 400;
            width: 1000px;
            margin: auto;
            margin-top: 40px;
            text-align: left;
        }


  </style>
</head>
<body>

	<jsp:include page="../common/header.jsp"/>
	
	 <div id="innerOuter">

        <div id="demo" class="carousel slide" data-ride="carousel">

            <!-- Indicators -->
            <ul class="carousel-indicators">
            <li data-target="#demo" data-slide-to="0" class="active"></li>
            <li data-target="#demo" data-slide-to="1"></li>
            <li data-target="#demo" data-slide-to="2"></li>
            </ul>
        
            <!-- The slideshow -->
            <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath }/${ course.files[2].changeName }">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath }/${ course.files[3].changeName }">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath }/${ course.files[4].changeName }">
            </div>
            </div>
        
            <!-- Left and right controls -->
            <a class="carousel-control-prev" href="#demo" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#demo" data-slide="next">
            <span class="carousel-control-next-icon"></span>
            </a>
        </div>

        <div id="main">
            <h2>${course.courseNo }. ${course.courseName }</h2>
            <h4>${course.startEnd }</h4>
            <h4>거리:${course.distance } / 소요시간 : ${course.takeTime }</h4>
            <p>${course.content }</p>
        </div>

        <button>코스 상세보기</button>

        <div id="kakaomap">


        </div>
    </div>
	
	<jsp:include page="../common/footer.jsp"/>
	
</body>
</html>