<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    <%@ include file="/WEB-INF/views/include/sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/default.css">

</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

 <div id="regChkBox">
         <div id="regChkCore">
         	<h3 class="regChkCore">내정보수정</h3>
         </div>
         <hr>
			<form id="editForm" action="memberEdit.do" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="midx" value="${member.midx}">
			<table class="tableregChkCore">
				<tr>
					<td class="tdregChkCore">아이디(email) </td>
					<td class="tdregChkCore"> <input type="email" name="mid" id="mid" value="${member.mid}" readonly  ><br>아이디는 수정이 불가합니다.</td>
				</tr>
				<tr>
					<td class="tdregChkCore">비밀번호</td>
					<td class="tdregChkCore"> <input type="password" name="mpw" required> </td>
				</tr>
				<tr>
					<td class="tdregChkCore">이름</td>
					<td class="tdregChkCore"> <input type="text" name="mname" value="${member.mname}" required> </td>
				</tr>
				<tr>
					<td class="tdregChkCore">전화번호</td>
					<td class="tdregChkCore"> <input type="text" name="mphonenumber" value="${member.mphonenumber}" required> </td>
				</tr>
				<tr>
					<td class="tdregChkCore">사진</td>
					<td class="tdregChkCore"><input type="file" name="photo">
						<br>
						현재 파일 : <img alt="프사 " src="<c:url value="${member.mphoto}"/>">
						
					 </td>
				</tr>
				<tr>
					<td></td>
					<td class="tdregChkCore"> 
						<input type="submit" value="회원수정">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>