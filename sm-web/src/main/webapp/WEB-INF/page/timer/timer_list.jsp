<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List All Timer</title>
<script type="text/javascript">
	function create_start() {
		window.location = "${path}/timer/create_start.do";
	}
</script>
</head>
<body>
	<input type="button" value="创建时间表" onclick="create_start()"/>
	<table>
		<thead style="background-color: gray;">
			<tr>
				<td>开始时间(分)</td>
				<td>结束时间(分)</td>
				<td>间隔时间(分)</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${timerList}" var="timer">
				<tr>
					<td>${timer.start}</td>
					<td>${timer.end}</td>
					<td>${timer.interval}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>