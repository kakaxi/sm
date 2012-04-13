<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List All City</title>
<script type="text/javascript">

	var cityId = null;
	
	function create_city() {
		window.location = "${path}/area/create_start_city.do?proId=" + document.getElementById("province").value;
	}
	
	function create_area() {
		if(cityId == null) {
			alert("请选择城市");
		} else {
			window.location = "${path}/area/create_start_area.do?cityId=" + cityId;
		}
	}
	
	function view_area() {
		if(cityId == null) {
			alert("请选择城市");
		} else {
			window.location = "${path}/area/findAllAreas.do?cityId=" + cityId;
		}
	}
	
	function select_city(id) {
		cityId = id;
	}
	
	function backto() {
		window.location = "${path}/area/findAll.do";
	}
</script>
</head>
<body>
	<input type="button" value="创建城市" onclick="create_city()"/>
	<input type="button" value="创建区域" onclick="create_area()"/>
	<input type="button" value="查看区域" onclick="view_area()"/>
	<input type="button" value="返回" onclick="backto()"/>
	<input id="province" type="hidden" value="${province.id}"/>
	<br/>
	<label>省份:${province.name}</label>
	<br/>
	<br/>
	<table border="2">
		<thead style="background-color: gray;">
			<tr>
				<td><label>单选</label></td>
				<td><label>名称</label></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cityList}" var="city">
				<tr>
					<td><input name="cityId" onclick="select_city('${city.id}')" type="radio"/></td>
					<td><label>${city.name}</label></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>