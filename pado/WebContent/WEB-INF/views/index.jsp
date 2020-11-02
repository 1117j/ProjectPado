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
	<style>
	
	*{
   background-color: rgb(248, 247, 247);
   margin: auto;
   text-align: center;
   font-family: Nanum Gothic, sans-serif;
   color: rgb(97, 97, 97);
}
	
	/* 바디영역설정 그래야 위에서 좀떨어짐 */
	#loginmain{
	    height: 600px;
	}


	/* 파도 영역 사이즈 */
	#padoimg{
	    width: 150px;
	    height: 120px;
	}

	/* 파도메인로고 사이즈 */
	.headerimg{
	    width: 150px;
	    height: 120px;
	}

	/* 로그인 테이블을 싸고있는 div */
	#loginformcontainer{
	    top: 20%;
	    line-height: 30px;
	    margin: auto;
	    
	    width: 360px;
	    height: 400px;

	    text-align: center;
	    position: relative;
	    transform:translateY(5%); 
	    background-position: 200px 50%;
	}

	/* 로그인 테이블 안에 있는 인풋들 */
	.logininput{
	    position: relative;
	    border-radius: 10px;
	}

	/* 로그인테이블안에 있는 인풋들 중 1번째*/
	.la {
	    text-align: left;
	    width: 300px;
	    height: 50px;
	    background-color: white;
	    border:0.1px solid rgb(211, 209, 209) ;
	}

	/* 로그인테이블안에 있는 인풋들 중 2번째*/
	.lb {
	    text-align: left;
	    width: 300px;
	    height: 50px;
	    background-color: white;
	    border:0.1px solid rgb(211, 209, 209) ;
	}

	/* 로그인테이블안에 있는 인풋들 중 3번째로그인버튼*/
	.lc{
	    width: 300px;
	    height: 60px;
	    color : white;
	    font-size: 1.1em;
	    border:0.1px solid rgb(138,174,244) ;
	    background-color: rgb(138,174,244);
	}

	/* 로그인테이블안에 있는 인풋들 중 4번째 회원가입버튼*/
	.ld{
	    width: 300px;
	    height: 60px;
	    color : white;
	    font-size: 1.1em;
	    border:1px solid rgb(138,174,244);
	    background-color: rgb(138,174,244);
	}
	
	#loginjoin{
		width: 300px;
		height: 60px;
 		color : white;
	    font-size: 1.1em;
	    border:1px solid rgb(138,174,244);	
	    border-radius: 10px;
	    background-color: rgb(138,174,244);
	    }
	    
	 a{
	    text-decoration: none;
	    
	    }
	</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">

</head>
<body id="loginmain">

	<div id="loginformcontainer">
		<div class=headerimg>
        	<img src="<%=request.getContextPath()%>/image/main_logo.png" id="padoimg">
    	</div>
		<form action="<%=request.getContextPath()%>/member/loginReg.do" method="post">
			
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
				<td><hr></td>
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
