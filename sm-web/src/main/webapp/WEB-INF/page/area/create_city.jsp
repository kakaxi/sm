<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create City</title>
<script type="text/javascript">
	function backto() {
		window.location = "${path}/area/findAllCities.do?proId=${province.id}";
	}
</script>
</head>
<body>
	<input type="button" value="返回" onclick="backto()"/>
	<form action="${path}/area/create_city.do" enctype="multipart/form-data" method="post">
		<input name="proId" type="hidden" value="${province.id}"/>
		<fieldset title="创建城市" lang="创建城市">
		<table>
			<tbody>
				<tr>
					<td  style="text-align: right"><label>所属省份:</label></td>
					<td><label>${province.name}</label></td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>城市名称:</label></td>
					<td><input name="name" type="text"/></td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<input type="submit" value="创建"/>
	</form>
</body>
</html>