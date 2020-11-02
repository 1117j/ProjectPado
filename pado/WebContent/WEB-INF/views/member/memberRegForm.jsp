<%@page import="member.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>INDEX</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<style>
   .check_ok {
      color : blue;
   }
   .check_not {
      color : red;
   }
   #idchk {
      display: none;
   }

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 50%; /* Could be more or less, depending on screen size */                          
}
/* The Close Button */
.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}
.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

</style>
</head>
<body id="loginmain">
	<%@ include file="/WEB-INF/views/include/header.jsp" %>


<div class= "loginformcontainer">


<div>
    <h1 class="subtitle">회원 가입</h1>
    <hr>
    
        <table>
            <tr>
                <td>아이디(email)</td>
                <td> <input type="email" name="mid" id="mid" >
                     <span  id="checkmsg"></span>
                     <input type="checkbox" name="idchk" id="idchk">
                 </td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td> <input type="password" name="mpw" id="mpw" required> </td>
            </tr>
            <tr>
                <td>이름</td>
                <td> <input type="text" name="mname" id="mname" required> </td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td> <input type="text" name="mphonenumber" id="mphonenumber" required> </td>
            </tr>
            <tr>
                <td>사진</td>
                <td> <input type="file" name="mphoto" id="mphoto"> </td>
            </tr>
            <tr>
                <td></td>
                <td> 
                    <input type="submit" value="작성완료" id="myBtn">
                    <input type="reset">
                </td>
            </tr>
        </table>
        
    </div>
      
    
    <script>

</script>

        <div id="myModal" class="modal">
      <span class="close">수정하기</span> 
            <!-- Modal content -->
            <div class="modal-content" id="modal-content">
                
                    
            </div>
            </div>
            


</body>
</html>

<script>

 

// modal변수 선언 #myModal
var modal = document.getElementById('myModal');

// btn변수 >> #mybtn누르면 실행
var btn = document.getElementById("myBtn");

//span변수 >> 창 닫기

var mid =  document.getElementById("mid");
var mpw =  document.getElementById("mpw");
var mname =  document.getElementById("mname");
var mphonenumber =  document.getElementById("mphonenumber");
var mphoto =  document.getElementById("mphoto");


//버튼을 클릭하면 모달창이 실행
btn.onclick = function() {
 modal.style.display = "block";

 var html = '';
 html += '<form id="regForm" action="memberReg.do" method="post" enctype="multipart/form-data">';

 html += '<table>';
 html += '<tr>';
 html += '<td>아이디</td>';
 html += '<td><input type="email" name="mid" id="mid" value="'+mid.value+'" required></td>';
 html += '</tr>';
 html += '<tr>';
 html += '<td>비밀번호</td>';
 html += '<td><input type="password" name="mpw" id="mpw" value="'+mpw.value+'" required></td>';
 html += '</tr>';
 html += '<tr>';
 html += '<td>이름</td>';
 html += '<td><input type="text" name="mname" id="mname" value="'+mname.value+'" required></td>';
 html += '</tr>';
 html += '<tr>';
 html += '<td>전화번호</td>';
 html += '<td><input type="text" name="mphonenumber" id="mphonenumber" value="'+mphonenumber.value+'" required></td>';
 html += '</tr>';
 html += '<tr>';
 html += '<td>사진</td>';
 html += '<td>'+mphoto.value+'</td>';
 html += '</tr>';
 html += '<tr>';
 html += '<td><input type="submit" name="회원가입" ></td>';
 html += '</tr>';
 html += '</table>';
 html += '</form>';
 /* <input type="file" name="mphoto" id="mphoto" value="'+mphoto.value+'"> */
 
 $('#modal-content').html(html);
}

var close = document.getElementsByClassName("close")[0];                                

close.onclick = function() {
    modal.style.display = "none";
   }


// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
 if (event.target == modal) {
     modal.style.display = "none";
 }
}

$(document).ready(function(){
   
   $('#regForm').submit(function(){
      
      if(!$('#idchk').prop('checked')){
         alert('아이디 중복 체크는 필수 항목 입니다');
         $('#mid').focus();
         return false;
      }
      
      
      
   });
   
   $('#mid').focusin(function(){
      
      $(this).val('');
      $('#idchk').prop('checked', false);
      
      $('#checkmsg').text('');
      
      $('#checkmsg').removeClass('check_not');
      $('#checkmsg').removeClass('check_ok');
   });
   
   $('#mid').focusout(function(){
      
      if($(this).val().length<1){
         $('#checkmsg').text("아이디 항목은 필수 항목입니다.");
         $('#checkmsg').addClass('check_not');
         return false;
      }
      
      // 비동기 통신
      $.ajax({
         url : 'idCheck.do',
         data : { mid : $(this).val()},
         success : function(data){
            if(data == 'Y'){
               $('#checkmsg').text("사용가능한 아이디 입니다.");
               $('#checkmsg').addClass('check_ok');
               $('#idchk').prop('checked', true);
            } else {
               $('#checkmsg').text("사용이 불가능한 아이디 입니다.");
               $('#checkmsg').addClass('check_not');
               $('#idchk').prop('checked', false);
            }
         }
      });
      
      
   });
   
   
});

</script>