<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>혼디모영 - 이벤트 글 수정</title>

<style>
	/* 컨텐트 */
	#container{
	    width: 1200px;
	    height: auto;
	    margin: 0 auto;
	}
	
	.notice_insert_title{
	    width: 1200px;
	    height: 100px;
	    font-size: 35px;
	    font-weight: bold;
	    text-align: center;
	}
	
	.insert_box{
	    width: 800px;
	    margin: 0 auto;
	}
	
	.tb_input {
	    width: 800px;
	    border-top: solid 2px #FF9843;
	    margin-top: 10px;
	}
	
	.tb_input th {
	    width: 150px;
	    background-color: #f3f3f3;
	}
	
	.tb_input th, .tb_input td {
	    border-bottom: solid 1px #ddd;
	    padding: 10px 20px;
	    text-align: left;
	    vertical-align: middle;
	    font-size: 14px;
	}
	
	.title_inp{
	    width: 650px;
	    font-size: 15px;
	}
	
	.title_inp, .date_inp, .course_inp, .people_inp, .content_inp{
		height: 40px;
		border: 1px solid #b1b1b1;
		outline:none;
		border-radius: 10px;
		font-size: 15px;
		color: #292929;
		padding: 10px;
	}
	
	.date_inp, .course_inp, .people_inp{
	    width: 150px;
	    font-size: 15px;
	}

	.people_info{
	    color: #292929;
	    padding-left: 10px;
	}
	
	.people_info:hover{
		text-decoration: none;
		color: #292929;
	}
	
	.content_inp{
	    width: 650px;
	    height: 400px;
	    resize: none;
	}
	
	.file_label{
		font-size: 15px;
		color: #777;
		float: right;
	}
	
	/* 등록 취소 버튼 */
	.detail_btn_box{
	    width: 800px;
	    margin-top: 30px;
	}
	
	.hdmy_detail_btn {
	    display: inline-block;
	    width: 80px;
	    height: 50px;
	    padding: 8px;
	    font-size: 20px;
	    font-weight: bold;
	    border: none;
	    border-radius: 15px;
	    background-color: #FF9843;
	    color: #ffffff;
	    margin-right: 10px;
	    cursor: pointer;
	    line-height: 35px;
	}
	
	/* 별점 */
	#myform fieldset{
	    display: inline-block;
	    direction: rtl;
	    border:0;
	}
	#myform fieldset legend{
	    text-align: right;
	}
	#myform input[type=radio]{
	    display: none;
	}
	#myform label{
	    font-size: 3em;
	    color: transparent;
	    text-shadow: 0 0 0 #cfcfcf;
	}
	#myform label:hover{
	    text-shadow: 0 0 0 #FF9843;
	}
	#myform label:hover ~ label{
	    text-shadow: 0 0 0 #FF9843;
	}
	#myform input[type=radio]:checked ~ label{
	    text-shadow: 0 0 0 #FF9843;
	}
	#reviewContents {
	    width: 100%;
	    height: 150px;
	    padding: 10px;
	    box-sizing: border-box;
	    border: solid 1.5px #D3D3D3;
	    border-radius: 5px;
	    font-size: 16px;
	    resize: none;
	}

   </style>
</head>
<body>

	<jsp:include page="../common/header.jsp"/>
	
	<c:choose>
		<c:when test="${empty loginUser}">
			<script>
				alert('관리자만 수정 가능합니다.');
				location.href = '${ path }/event';
			</script>
		</c:when>
		<c:otherwise>
		    <div id="container">
		        <div class="notice_insert_title"><span>이벤트 수정</span></div>
		
		        <div class="insert_box">
		            <form action="${path}/event/update" method="post" id="myform" enctype="multipart/form-data">
		            	<input type="hidden" name="userNo" value="${ sessionScope.loginUser.userNo }"/>
		            	<input type="hidden" name="eventNo" value="${event.eventNo}">
		            	
						<table class="tb_input">
							<tbody>
		                        <tr>
									<th>* 제목</th>
									<td><input type="text" name="eventTitle" class="title_inp" value="${event.eventTitle}" required/></td>
								</tr>
								<tr>
									<th>* 내용</th>
									<td><textarea class="content_inp" name="eventContent" required>${event}</textarea>
		                            </td>
								</tr>
		                        <tr>
									<th>* 첨부파일</th>
									<td>
										<input type="file" name="reUpfile1" id="upfiles1" style="padding-right: 35px;"> <span class="file_label">* 썸네일로 등록 되는 이미지 입니다.</span>
		                            </td>
								</tr>
		                        <tr>
									<th>* 첨부파일</th>
									<td>
										<input type="file" name="reUpfile2" id="upfiles2"> <span class="file_label">* 본문에 등록되는 이미지 입니다.</span>
		                            </td>
								</tr>
							</tbody>
						</table>
		                <div class="detail_btn_box" align="center">
		                    <button class="hdmy_detail_btn" type="submit">등록</button>
		                    <button class="hdmy_detail_btn" type="button" onclick="backPage();">취소</button>
		                </div>
		            </form>
		        </div> <!-- inset_box -->
		    </div>
		</c:otherwise>
	</c:choose>
	<jsp:include page="../common/footer.jsp"/>
	
    <script>
    	 function backPage(){
    		 location.href = '${path}/event';
    	 }
    </script>

</body>
</html>