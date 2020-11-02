<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page trimDirectiveWhitespaces="true" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">

</head>
<body id="loginmain">
	<div id="loginformcontainer">
		<div class=headerimg>
        	<img src="/image/main_logo.png" id="padoimg">
    	</div>
		<form action="loginReg.do" method="post">
			
			<table class="table">
				<tr>
					<td> <input class="logininput la" type="text" name="mid" value="${cookie.mid.value}"> </td>
				</tr>
				<tr>
					<td> <input class="logininput lb" type="password" name="mpw"> </td>
				</tr>				
				<tr>
					<td> <input type="checkbox" name="remember" value="r" ${cookie.mid != null ? 'checked' : '' } > 아이디 기억하기  </td>
				</tr>
				<tr>
					<td> <input class="logininput lc" type="submit" value="로그인"> </td>
				</tr>
					<tr>
					<!-- 변경 시작  -->
					<td id="loginjoin"><a href="<c:url value="/member/memberRegForm.do"/>"class="logininput ld">회원가입 </td>
				</tr> <!-- 변경 완료  -->
			</table>
		
		</form>
	</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>