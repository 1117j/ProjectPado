<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX</title>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
<style type="text/css">

.check_ok {
		color : blue;
	}
	.check_not {
		color : red;
	}s
	#idchk {
		display: none;
	}</style>

</head>
<body>

	<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<div>
		<h1 class="subtitle">회원 수정</h1>
		<hr>
		<!-- 이 페이지는 데이터 베이스에 저장된 데이터를 value 값에 세팅해야합니다. -->
		<form id="editForm" action="memberEdit.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="midx" value="${member.midx}">
			<table>
				<tr>
					<td>아이디(email) </td>
					<td> <input type="email" name="mid" id="mid" value="${member.mid}" readonly  >아이디는 수정이 불가합니다.</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td> <input type="password" name="mpw" required> </td>
				</tr>
				<tr>
					<td>이름</td>
					<td> <input type="text" name="mname" value="${member.mname}" required> </td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td> <input type="text" name="mphonenumber" value="${member.mphonenumber}" required> </td>
				</tr>
				<tr>
					<td>사진</td>
					<td><input type="file" name="photo">
						<br>
						현재 파일 : ${member.mphoto}
						<input type="hidden" name="oldFile" value="${member.mphoto}" >
					 </td>
				</tr>
				<tr>
					<td></td>
					<td> 
						<input type="submit" name="정보수정">
						<input type="reset">
					</td>
				</tr>
			</table>
		</form>
	</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>

<script>
	
	$(document).ready(function(){
		

		
		
		
		
	});
	
	
</script>









