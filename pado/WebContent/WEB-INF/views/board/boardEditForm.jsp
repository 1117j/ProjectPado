<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<style>
</style>
</head>
<body>
		<form action="boardEdit.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td><input type="hidden" name="bidx" value="${board.bidx}"></td>
					<td><input type="text" name="bid" value="${board.bid}" readonly></td>
					<td><textarea rows="10" cols="50" name="bmessage" id="bmessage">${board.bmessage}</textarea></td>
					<td>
						<input type="file" name="file" id="file">${board.bphoto}
						<input type="hidden" name="oldFile" value="${board.bphoto}">
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value="수정">
						<a href="boardList.do">취소</a>
					</td>
				</tr>
			</table>
		</form>
</body>
</html>