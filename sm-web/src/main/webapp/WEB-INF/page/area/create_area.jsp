<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Area</title>
<script type="text/javascript">
	function backto() {
		window.location = "${path}/area/findAllAreas.do?cityId=${city.id}";
	}
</script>
</head>
<body>
	<input type="button" value="返回" onclick="backto()"/>
	<form action="${path}/area/create_area.do" enctype="multipart/form-data" method="post">
		<input name="cityId" type="hidden" value="${city.id}"/>
		<fieldset title="创建区域" lang="创建区域">
		<table>
			<tbody>
				<tr>
					<td  style="text-align: right"><label>所属省份:</label></td>
					<td><label>${city.province.name}</label></td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>所属城市:</label></td>
					<td><label>${city.name}</label></td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>区域名称:</label></td>
					<td><input name="name" type="text"/></td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<input type="submit" value="创建"/>
	</form>
</body>
</html>