<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List All Area</title>
<script type="text/javascript">

	var cityId = null;
	
	function create_area() {
		window.location = "${path}/area/create_start_area.do?cityId=" + document.getElementById("city").value;
	}
	
	function backto() {
		window.location = "${path}/area/findAllCities.do?proId=${city.province.id}";
	}
</script>
</head>
<body>
	<input type="button" value="创建区域" onclick="create_area()"/>
	<input type="button" value="返回" onclick="backto()"/>
	<input id="city" type="hidden" value="${city.id}"/>
	<br/>
	<label>省份:${city.province.name} -- 城市:${city.name}</label>
	<br/>
	<br/>
	<table border="2">
		<thead style="background-color: gray;">
			<tr>
				<td><label>名称</label></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${areaList}" var="area">
				<tr>
					<td><label>${area.name}</label></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>