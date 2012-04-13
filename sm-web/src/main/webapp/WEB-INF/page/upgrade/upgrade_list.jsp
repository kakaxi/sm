<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List All Upgrade</title>
<script type="text/javascript">
	function backto() {
		window.location = "${path}";
	}
	
	function upgrade_start() {
		window.location = "${path}/upgrade/upgrade_start.do";
	}
</script>
</head>
<body>
	<input type="button" value="更新版本" onclick="upgrade_start()" />
	<input type="button" value="返回" onclick="backto()"/>
	<table border="2">
		<thead style="background-color: gray;">
			<tr>
				<td><label>类型</label></td>
				<td><label>版本</label></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${versionList}" var="version">
				<tr>
					<td><label>${version.type}</label></td>
					<td><label>${version.version}</label></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>