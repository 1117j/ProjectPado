
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header id="header">
	<div id="logoSide">
		<img src="<%=request.getContextPath()%>/image/main_logo.png" id="logo">
	
		<nav>
			<ul>
				<c:if test="${!empty loginInfo}">
				<li id="headermenu"><a href="<c:url value="/member/logout.do"/>" class="navIcon" style="color: lightsteelblue;">logout</a></li>
				</c:if>
				
				<c:if test="${empty loginInfo}">
				<li id="headermenu"><a href="<c:url value="/member/loginRegForm.do"/>" class="navIcon" style="color: lightsteelblue;">login</a></li>
				</c:if>
				
				
				
				<li id="headermenu"><a href="<c:url value="/member/mypage.do"/>"><img src="<%=request.getContextPath()%>/image/icon_mypage.png"   class="navIcon"></a></li>
             
             	<li id="headermenu"><a href="<c:url value="/board/boardRegForm.do"/>"><img src="<%=request.getContextPath()%>/image/icon_write.png"   class="navIcon"></a></li>
             
             	<li id="headermenu"><a href="<c:url value="/board/boardList.do"/>"><img src="<%=request.getContextPath()%>/image/icon_home.png"   class="navIcon"></a></li>
             
           </ul>
         </nav>
      
      
      </div>
    </header>