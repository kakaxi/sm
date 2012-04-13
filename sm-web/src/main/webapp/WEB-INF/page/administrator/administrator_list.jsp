<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List All Advertiser</title>
<script type="text/javascript">
	function create_start() {
		window.location = "${path}/administrator/create_start.do";
	}
	
	function backto() {
		window.location = "${path}";
	}
</script>
</head>
<body>
	<input type="button" value="注册管理员" onclick="create_start()"/>
	<input type="button" value="返回" onclick="backto()"/>
	<table>
		<thead style="background-color: gray;">
			<tr>
				<td>管理员账户</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${administratorList}" var="admin">
				<tr>
					<td>${admin.mobile}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>