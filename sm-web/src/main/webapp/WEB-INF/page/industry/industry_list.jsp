<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List All Advertiser</title>
<script type="text/javascript">
	function backto() {
		window.location = "${path}";
	}
	
	function industry_create() {
		window.location = "${path}/industry/create_start.do";
	}
</script>
</head>
<body>
	<input type="button" value="创建行业" onclick="industry_create()"/>
	<input type="button" value="返回" onclick="backto()"/>
	<table border="2">
		<thead style="background-color: gray;">
			<tr>
				<td><label>行业</label></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${industryList}" var="industry">
				<tr>
					<td><label>${industry.name}</label></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>